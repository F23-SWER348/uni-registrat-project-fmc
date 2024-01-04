package com.fmc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.JSONWriter;

import java.io.File;

public class Course extends user {// جربنا كل الميثود و نجحوا
    // mai
    private String name;
    private String shortcut;
    private int credits;
    private Staff staff;
    private Faculty faculty;
    private Semester semester;
    private LocalTime start;
    private LocalTime end;
    private DayOfWeek day;
    private Course depend;
    private int Capacity;

    // private LocalDate day; مش كثير مفهومة
    // protected Map<Course, ArrayList<Student> > studentCourse = new HashMap<>();
    // //طلاب الكورس

    // ArrayList =new ArrayList<>();
    private BlockingQueue<Student> studentCourseArray = new LinkedBlockingQueue<>(30);

    // كونستركتر
    public Course(String name, String shortcut, int credits, Staff staff, Semester semester, Faculty faculty) {
        super();
        this.name = name;
        this.shortcut = shortcut;
        this.credits = credits;
        this.staff = staff;
        this.semester = semester;
        user.CourseList.add(this);
        staff.addCourse(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
        // this.studentCourse.put(this, studentCourseArray);//موقعها غلط
        
        faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);
    }

    // كونستركتر اذا في للكورس بري كورس
    public Course(String name, String shortcut, int credits, Staff staff, Semester semester, Faculty faculty,
            Course course) {
        super();
        this.name = name;
        this.shortcut = shortcut;
        this.credits = credits;
        this.staff = staff;
        this.semester = semester;
        user.CourseList.add(this);
        staff.addCourse(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
        faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);
        this.depend = course;
    }

    // كونستركتر اذا لسا ما تحدد اله معلم للكورس
    public Course(String name, String shortcut, int credits, Semester semester, Faculty faculty) {
        super();
        user.CourseList.add(this);
        this.name = name;
        this.shortcut = shortcut;
        this.semester = semester;
        semester.c.add(this);
        user.semesterCourse.put(semester, semester.c);
        faculty.facultyCourseArray.add(this);
        user.FacultyCourse.put(faculty, faculty.facultyCourseArray);

        staff.staffCourseArray.add(this);
        user.staffCourse.put(staff, staff.staffCourseArray);
        // this.studentCourse.put(this, studentCourseArray);//موقعها غلط

    }

    // كونستركتر اذا ما في اي معلومة عن الكورس وانا بدي ادخلهم يدوي
    public Course() {
    };

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    // اشوف شو علامة المادة ب الرموز
    public String gradeABC(Double grade) {

        return grade == 4 ? "A"
                : grade >= 3.5 ? "B+"
                        : grade >= 3 ? "B"
                                : grade <= 2.5 ? "C+"
                                        : grade >= 2 ? "C"
                                                : grade >= 1.5 ? "D+" : grade >= 1 ? "D" : "I";

    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        try {
            this.start = start;
        } catch (Exception e) {

        }

    }

    public LocalTime getEnd() {
        return end = start.plusHours(1);
    }

    public void setEnd(int end) {
        this.end = start.plusHours(end);
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(String Day) {
        try {
            day = DayOfWeek.valueOf(Day.toUpperCase());
        } catch (Exception e) {
        }

    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getDepend() {
        return depend;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public BlockingQueue<Student> getStudent() {///////////
        return studentCourseArray;
    }

    // public List <Student> getStudent() {///////////

    // return
    // studentCourseArray.stream().filter(e->e.getName().equals(this.getName())).collect(Collectors.toList());
    // }
    // بضيف طالب على الكورس اذا ماخذ المواد المعتمدة على هاد الكورس
    public String setStudent(Student student) {
        if (this.depend == null) {
            studentCourseArray.offer(student);
            student.addCourse(this);
            Capacity++;
            return "Dear " + student.getName() + " Done";

        } else {
            // هان جبنا كورسات الطالب وفتشنا اذا اخذ المادة المعتمدة على هاد الكورس او لا و
            // عملنا كاونت اذا كان الكاونت واحد يعني اخذها اذا صفر يعني لا
            int x = (int) student.getCourse().stream().filter(e -> e.equals(this.getDepend())).count();

            if (x >= 1) {
                try {// هان بعد ما اتأكدت انو اخذها ضفته على هاد الكورس
                    studentCourseArray.offer(student);
                    student.addCourse(this);
                    Capacity++;
                    return "Dear " + student.getName() + " Done";
                } catch (Exception e) {
                    e.printStackTrace();
                } // السعة تم حلها ب بلوكنج كيو
            }
        }
        // اذا مش ماخذها بطلعله هاد المسج
        return "Dear " + student.getName() + " you must take this course " + this.getDepend();
    }

    public void removeStudent(int studentId) {// اول اشي عملت جبنا الكيو طلعنا منها الستيودنت الي اي دي تاعهم نفس الاي
                                              // دي الي عندي بعدين بالماب حذفتهم جبت كل واحد اله نفس الايديي و مستحت
                                              // الطالب من هاد الكورس من كورسات الطالب و بعدها مسحت الطالب من هاد الكورس
        // int index = findIndex(studentCourseArray, studentToFind);
        // int index =studentCourseArray.indexOf(studentId);
        // this.studentCourseArray.take(student);//السعة

        List<Student> stu = studentCourseArray.stream()
                .filter(e -> e.getId() == studentId)
                .collect(Collectors.toList());

        stu.forEach(e -> e.getCourse().remove(this));
        this.studentCourseArray.removeIf(s -> s.getId() == studentId);
        // student.course.remove(stu);
    }

    // اذا بنحب نعمل ابديت ستيودنت
    // public void printAllCourse(){
    // CourseList.stream().forEach(e->System.out.println(e.getStudent().size()));;
    // }
    // بطبع الكورس مع طلابه
    public String toStringWithStudent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Course Name: ").append(name).append("\n");
        stringBuilder.append("Shortcut: ").append(shortcut).append("\n");
        stringBuilder.append("Credits: ").append(credits).append("\n");
        stringBuilder.append("Staff: ").append(staff.getName()).append("\n");
        stringBuilder.append("Semester: ").append(semester.getYear()).append("\n");

        stringBuilder.append("Students in the Course:\n");
        for (Student student : studentCourseArray) {
            stringBuilder.append("- ").append(student.getName()).append("\n\n\n\n");
        }

        return stringBuilder.toString();
    }

    // بطبع معلومات الكورس
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Course Name: ").append(name).append("---");
        stringBuilder.append("Shortcut: ").append(shortcut).append("---");
        stringBuilder.append("Credits: ").append(credits).append("---");
        // stringBuilder.append("Staff: ").append(staff.getName()).append("---");
        // stringBuilder.append("Semester: ").append(semester.getYear()).append("\n");

        return stringBuilder.toString();
    }

    public JsonNode jsonify_object() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{")
                .append("\"name\": \"").append(escapeJsonString(name)).append("\",")
                .append("\"shortcut\": \"").append(escapeJsonString(shortcut)).append("\",")
                .append("\"credits\": \"").append(escapeJsonString(credits + "")).append("\",")
                .append("\"staff\": \"").append(escapeJsonString(staff.getName())).append("\",")
                .append("\"semester\": \"").append(escapeJsonString(semester.getName()))
                // .append("\",")
                // .append("\"faculty\": \"").append(escapeJsonString(faculty.getName())).append("\",")
                // .append("\"start\": \"").append(escapeJsonString(start.toString())).append("\",")
                // .append("\"day\": \"").append(escapeJsonString(day.toString())).append("\"}")
                .append("\"}")
                ;

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

            File file = new File("assets\\data\\course.json");
            JSONWriter jfa = new JSONWriter();

            jfa.appendToArray(file, jsonify_object());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
