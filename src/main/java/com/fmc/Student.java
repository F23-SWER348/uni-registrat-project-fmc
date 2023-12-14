package com.fmc;

import java.util.ArrayList;
import java.util.Collection;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
=======
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.text.html.HTMLDocument.Iterator;
>>>>>>> 56cc727db9c018b2a90fcea8da54d315c36d92d1

public class Student extends Grade {
    
private String name;
private int id;
private String contact;
private double gpa;
 Map<Course,Double> grade =new HashMap<>();
<<<<<<< HEAD
 
// ArrayList<Course> course;
=======
 private Faculty faculty;
ArrayList<Course> StuCourse=new ArrayList<>();;
>>>>>>> 56cc727db9c018b2a90fcea8da54d315c36d92d1


public Student(){};
// public Student(String name, int id, String contact, double gpa) {
//     super();
//     this.name = name;
//     this.id = id;
//     this.contact = contact;
//     this.gpa = gpa;
// }


public Student(String name, int id, String contact, double gpa) {
    super();
    this.name = name;
    this.id = id;
    this.contact = contact;
    this.gpa = gpa;
    
}


public Student(String name, int id, String contact) {
    this.name = name;
    this.id = id;
    this.contact = contact;
}



public List<Course> getCourse() {
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
<<<<<<< HEAD






public List<Course> getCourse() {
     List<Course> s= (List<Course>) Stream.of(studentCourse).map(e -> Stream.of(e)).reduce((e1, e2) -> Stream.concat(e1, e2)).filter(e->e.equals(this)).get();
// ArrayList<Course> s= Stream.of(studentCourse).collect(Collectors.groupingBy(e->e. );
// .forEach((k,v)->Stream.of(v).filter(e->e.equals(this)));
// 
// .anyMatch(e->e.equals(this)).collect(collector.toList);

    return s;
}
public void setCourse(Course course) {
    studentCourse.get(course).add(this);
 
}
public Double getGrade(Course course) {
   return this.grade.get(course);
    
=======

public Faculty getFacilty() {
    return faculty;
}
public void updateFacilty(Faculty Newfaculty) {
     faculty=Newfaculty;
}

public void addCourse(Course course1){
   StuCourse.add(course1);
>>>>>>> 56cc727db9c018b2a90fcea8da54d315c36d92d1
}
// // جبت الماب الي جواها الكورس و ارري الطلاب الي فيه 
// public List<Course> getCourse() {
//      List<Course> s= (List<Course>) Stream.of(studentCourse).map(e -> Stream.of(e)).reduce((e1, e2) -> Stream.concat(e1, e2)).filter(e->e.equals(this)).get();
// // ArrayList<Course> s= Stream.of(studentCourse).collect(Collectors.groupingBy(e->e. );
// // .forEach((k,v)->Stream.of(v).filter(e->e.equals(this)));
// // 
// // .anyMatch(e->e.equals(this)).collect(collector.toList);

<<<<<<< HEAD

public void setGrade( Course c,Double b) {
    this.grade.put(c, b);
=======
//     return s;
// }
// public void setCourse(Course course) {
//     studentCourse.get(course).add(this);
 
// }
public Double getGrade(Course course) {
   return this.grade.get(course);
    
>>>>>>> 56cc727db9c018b2a90fcea8da54d315c36d92d1
}


public void setGrade( Course c,Double b) {
    this.grade.put(c, b);
}



public Double GPA() {
<<<<<<< HEAD
    // grade.forEach((k,v)->k.getCredits * v);
    // double sum = Stream.of(this.grade)
    // .reduce(1,(k,v)->k *v)
    // .mapToDouble(e->e * Course.getCredits()).sum();//((Course)(grade.get(e)))
    // return sum / grade.size();
//    Stream.of( grade).forEach((k,v)->k.getCredits()*v).sum();
   
    // double sum = grade.entrySet().stream()
    // .mapToDouble(entry -> entry.getValue() * (Course)entry.getKey().getCredits()).sum();

double totalCredits = grade.keySet().stream().mapToDouble(e->((Course)e).getCredits()).sum();
// return sum / totalCredits;
return 0.0;
}



=======
    Collection<Double> gradeStu = grade.values();  // طلت العلامات 
    Collection<Course> courseStu = grade.keySet();  // طلت الكورسات
    
    // حسبت مجموع (علامة × عدد الساعات)
    final int[] totalCredits = {0}; 

    double weightedPoints = IntStream.range(0, Math.min(courseStu.size(), grade.size()))
            .mapToDouble(i -> {
                Course course = courseStu.stream().skip(i).findFirst().orElse(null);
                Double gradeValue = grade.values().stream().skip(i).findFirst().orElse(0.0);
                totalCredits[0] += course.getCredits(); 
                return gradeValue * course.getCredits();
            }).sum();
    //  System.out.println("Total Credits: " + totalCredits[0]);
    // System.out.println("GPA: " + gpa);
    this.gpa=weightedPoints / totalCredits[0];
    return weightedPoints / totalCredits[0];

   
}

public String evaluation(Double GPA){
    // if(GPA==4)
    // return "Heighest Honor";
    // else if(GPA>=3.5)
    // return "Deans";
    // else if(GPA>=3)
    // return "Honor";
    //  else if(GPA<=1.5)
    // return "Probation";
    // return"";
    return GPA == 4 ? "Heighest Honor" : GPA >= 3.5 ? "Deans" : GPA >= 3 ? "Honor" : GPA <= 1.5 ? "Probation" : "";
>>>>>>> 56cc727db9c018b2a90fcea8da54d315c36d92d1

}

@Override
public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Student Name: ").append(name).append("\n");
    stringBuilder.append("Student ID: ").append(id).append("\n");
    stringBuilder.append("Contact: ").append(contact).append("\n");
    stringBuilder.append("GPA: ").append(gpa).append("\n");

  
    return stringBuilder.toString();
}
}
//  static void sum(Object[]  objects2 , Object[]  objects){
  
//         if (objects2.length !=objects.length) {
//             throw new IllegalArgumentException("Matrix dimensions do not match");
//         }
//         ForkJoinPool pool=new ForkJoinPool();
//         pool.invoke(new RecursiveActionclass(objects2, objects, 0, objects2.length));
//     }
// }


//  class RecursiveActionclass extends RecursiveAction {
  
// Object[]  objects2;
// Object[]  objects;
// int startRow;
// int endRow;

//     public RecursiveActionclass(Course[]  objects2,Double[]  objects, int startRow, int endRow) {
//     this.objects = objects2;
//     this.objects2 = objects;
//     this.startRow = startRow;
//     this.endRow = endRow;}
// double c=0.0;//sum
//     @Override
//     protected void compute() {
//        if(endRow-startRow<=1){
//         c = (double)(((Course) objects2[startRow]).getCredits())* objects[startRow];
//     }
         
//          int midRow=(endRow+startRow)/2;
       
// RecursiveActionclass left =new RecursiveActionclass(a, b, startRow, midRow);
// RecursiveActionclass right =new RecursiveActionclass(a, b,  midRow, endRow);


//        invokeAll(left,right);
//     }
//  }