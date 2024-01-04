package com.fmc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.JSONWriter;

public class Student extends Grade {
    static Lock lock = new ReentrantLock();
    static Condition EvaluationAwaite = lock.newCondition();
    private String name;
    private int id;
    private String contact;
    private double gpa;
    Map<Course, Double> grade = new HashMap<>();
    private Faculty faculty;
    ArrayList<Course> StuCourse = new ArrayList<>();

    public Student() {
    };
    // public Student(String name, int id, String contact, double gpa) {
    // super();
    // this.name = name;
    // this.id = id;
    // this.contact = contact;
    // this.gpa = gpa;
    // }

    public Student(String name, int id, String contact) {
        this.name = name;
        this.id = id;
        this.contact = contact;
    }
    // 'طالب جديد جاي من جامعة ثانية '
    // public void preStudent(String name, int id, String contact, double gpa) {
    // this.name = name;
    // this.id = id;
    // this.contact = contact;
    // this.gpa = gpa;

    // }

    public List<Course> getCourse() {
        if (this.StuCourse.isEmpty())
            System.out.println(this.name + "  dont have any course");
        return this.StuCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Faculty getFacilty() {
        return faculty;
    }

    public void updateFacilty(Faculty Newfaculty) {
        faculty = Newfaculty;
    }

    public void addCourse(Course course1) {

        StuCourse.add(course1);

    }
    // // جبت الماب الي جواها الكورس و ارري الطلاب الي فيه
    // public List<Course> getCourse() {
    // List<Course> s= (List<Course>) Stream.of(studentCourse).map(e ->
    // Stream.of(e)).reduce((e1, e2) -> Stream.concat(e1,
    // e2)).filter(e->e.equals(this)).get();
    // // ArrayList<Course> s=
    // Stream.of(studentCourse).collect(Collectors.groupingBy(e->e. );
    // // .forEach((k,v)->Stream.of(v).filter(e->e.equals(this)));
    // //
    // // .anyMatch(e->e.equals(this)).collect(collector.toList);

    // return s;
    // }
    // public void setCourse(Course course) {
    // studentCourse.get(course).add(this);

    // }
    // /جبنا علاماته
    public Double getGrade(Course course) {
        return this.grade.get(course);

    }

    // حطينا علامة اله
    public void setGrade(Course c, Double b) {
        this.grade.put(c, b);
    }

    // حسبت معدل الطالب
    public Double GPA() {
        List<Double> gradeStu = grade.values().stream().collect(Collectors.toList()); // طلت العلامات
        List<Course> courseStu = grade.keySet().stream().collect(Collectors.toList()); // طلت الكورسات

        // حسبت مجموع (علامة × عدد الساعات)
        final int[] totalCredits = { 0 };
        // بطول عنصر عصر بجيت فيرست و بطول العلامة و بجمع الساعات و بضرب العلامة و بعدد
        // الساعات تاعت المادة و بعمل مجموعهم
        double weightedPoints = IntStream.range(0, Math.min(courseStu.size(), grade.size()))
                .mapToDouble(i -> {
                    Course course = courseStu.stream().skip(i).findFirst().orElse(null);
                    Double gradeValue = grade.values().stream().skip(i).findFirst().orElse(0.0);
                    totalCredits[0] += course.getCredits();
                    return gradeValue * course.getCredits();
                }).sum();
        // هان عملنا parallel عشان اعمل المعدل ب هاي الطريقة اني اقسم و اجمع
        RecursiveTask rAction = new AssTask(gradeStu, courseStu, 0, gradeStu.size());
        ForkJoinPool pool = new ForkJoinPool();
        double result = (double) pool.invoke(rAction);
        // this.gpa = weightedPoints / totalCredits[0];
        // weightedPoints / totalCredits[0];

        // عشان ما اخليه يحسب التقدير قبل ما يحسب المعدل

        lock.lock();

        EvaluationAwaite.signal();
        lock.unlock();
        return result / totalCredits[0];
    }

    // هان حسبت التقدير و ما بزبط احسبه اذا ما في معدل
    public String evaluation(Double GPA) throws InterruptedException {
        lock.lock();
        if (this.GPA() == 0) {
            EvaluationAwaite.await();
        }
        String s = GPA == 4 ? "Heighest Honor"
                : GPA >= 3.5 ? "Deans" : GPA >= 3 ? "Honor" : GPA <= 1.5 ? "Probation" : "";
        lock.unlock();
        return s;

    }

    // طبعت معلومات الطالب
    public String StudentInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student Name: ").append(this.name).append("\n");
        stringBuilder.append("Student ID: ").append(this.id).append("\n");
        stringBuilder.append("Contact: ").append(this.contact).append("\n");
        stringBuilder.append("GPA: ").append(this.GPA()).append("\n");

        return stringBuilder.toString();
    }

    // طبعت كشف علامات الطالب
    public StringBuilder StudentTranscripts() throws InterruptedException {
        Collection<Double> gradeStu = grade.values(); // طلت العلامات
        Collection<Course> courseStu = grade.keySet(); // طلت الكورسات

        final int[] totalCredits = { 0 };
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student Name: ").append(name).append("      ");
        stringBuilder.append("Student ID: ").append(id).append("       ");
        stringBuilder.append("Contact: ").append(contact).append("      ");
        stringBuilder.append("\n" + "name----Credits----grade----Mark estimation" + "\n");
        IntStream.range(0, Math.min(courseStu.size(), grade.size()))
                .forEach(i -> {
                    Course course = courseStu.stream().skip(i).findFirst().orElse(null);
                    Double gradeValue = grade.values().stream().skip(i).findFirst().orElse(0.0);
                    totalCredits[0] += course.getCredits();
                    // return gradeValue * course.getCredits();
                    stringBuilder.append(course.getName() + "------" + course.getCredits() + "--------" + gradeValue
                            + "---------" + course.gradeABC(gradeValue) + "\n");

                });

        stringBuilder.append("GPA: " + this.GPA());
        stringBuilder.append("Your evaluation :" + this.evaluation(GPA()) + "\n");
        return stringBuilder;

    }

    // كلاس تاسك ابن ل ريكيرسف تاسك عشان بجي اياه يرجع
    public class AssTask extends RecursiveTask<Double> {
        List<Double> arr;
        List<Course> courseStu;
        int low;
        int high;
        int capacity = 1000;

        // هان جبت ارري العلامات و جبت ارري عدد الساعات
        public AssTask(List<Double> gradeStu, List<Course> courseStu, int low, int high) {
            this.arr = gradeStu;
            this.low = low;
            this.high = high;
            this.courseStu = courseStu;
        }

        // هان بدي اضل اقسم و لما يصير الحجم صغير بضرب العلامة و ب عدد ساعاتها
        @Override
        protected Double compute() {
            if (high - low < 5) {
                double sum = 0;
                for (int i = low; i < high; i++) {
                    // sum+=arr[i];
                    sum += arr.get(i).doubleValue() * courseStu.get(i).getCredits();
                }
                return sum;
            } else {
                // برجع اقسم
                int mid = (high + low) / 2;
                AssTask left = new AssTask(arr, courseStu, low, mid);
                AssTask right = new AssTask(arr, courseStu, mid, high);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }

        public JsonNode jsonify_object() throws Exception {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{")
                    .append("\"name\": \"").append(escapeJsonString(name))
                    // .append("\",")
                    // ToDo: Write the other attibutes for this class...
                    // See Course.java as an example.
                    .append("\"}");

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(stringBuilder.toString());
            } catch (Exception e) {
                throw e;
            }
        }

        private String escapeJsonString(String input) {
            // Replace special characters with their escaped versions
            return input
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
        }

        public void append_to_json_file() {
            try {

                File file = new File("assets\\data\\student.json");
                JSONWriter jfa = new JSONWriter();

                jfa.appendToArray(file, jsonify_object());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}