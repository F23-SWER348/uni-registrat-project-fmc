package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grade  extends Course{
    


public Grade(){};
public Grade( Double mark,Course course,Student student) {////////////
    super();
   student.setGrade(course , mark);


}

// public Double getGrade(Course course) {
//  return grade.get(course);
// }
// public void setGrade(Course course,Double grade) {
//     this.grade.put(course, grade);
//     //jhkjhkjh
// }





}