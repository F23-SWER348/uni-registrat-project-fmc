package com;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fmc.Course;
import com.fmc.Faculty;
import com.fmc.Semester;
import com.fmc.Staff;
import com.fmc.Student;
import java.time.LocalDate;

public class main {
    
//mai
//celina
//Fatma

public static void main(String[] args) {
  LocalDate starDate=LocalDate.of(2023, 9 ,1);
   LocalDate endDate=LocalDate.of(2024, 1,17);


    Faculty seince=new Faculty("seince", "133");
    Staff suhail= new Staff("suhail", "ssss@kjhg", 1235852);
      Staff anas= new Staff("anas", "anas@kjhg", 235545952);
      Semester fall2023=new Semester(2023,starDate,endDate);
      Course java1 = new Course("java", "SWER114",3,fall2023,seince);
      Course java2 = new Course("java", "SWER114",3,fall2023,seince);
      Course java3 = new Course("java", "SWER114",3,fall2023,seince);
      Student fatma = new Student("fatma",202106545, "hgfd@kjhg",3.5);
      Student celina = new Student("celina",202102255, "hgfd@dfgb",2.5);
      Student mai = new Student("mai",20215455, "hgfd@dfgb",2.4);
      Student shouq = new Student("shouq",202107655, "hgffds@dfgb",2.8);

      //System.out.println(fatma.getName());
      java1.setStaff(anas);
      java1.setStudent(fatma);
      java2.setStaff(anas);
      java3.setStaff(suhail);
      java2.setStudent(fatma);
      java3.setStudent(fatma);
      //System.out.println(fatma.getCourse());//مش زابطة هاي 
       fatma.setGrade(java1,3.0);
       fatma.setGrade(java2,2.5);
       fatma.setGrade(fatma, 1.0);

System.out.println(fatma.GPA());


      


}














}





