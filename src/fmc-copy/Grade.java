package com.fmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

public class Grade extends Course {
    


private Map<Course,Double> grade =new HashMap<>();
private Double GPA ;


public Grade( Double mark,Course course) {
    super();
      this.grade.put(course , mark);

}

public Grade() {
}

public Double getGrade(Course course) {
 return grade.get(course);
}

public void setGrade(Course course,Double grade) {
    this.grade.put(course, grade);
    //jhkjhkjh
}

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



public Double GPA() {
    double sum = this.grade.values().stream().mapToDouble(e->e*Course.getCredits()).sum();//((Course)(grade.get(e)))
    return sum / grade.size();
}

}
