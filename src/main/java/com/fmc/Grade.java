package com.fmc;

public class Grade  extends Course{
    
private Double GPA ;

public Grade(){};
public Grade( Double mark,Course course,Student student) {////////////
    super();
   student.getGrade().put(course , mark);

}

// public Double getGrade(Course course) {
//  return grade.get(course);
// }
// public void setGrade(Course course,Double grade) {
//     this.grade.put(course, grade);
//     //jhkjhkjh
// }

public String evaluation(){
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

}



}
