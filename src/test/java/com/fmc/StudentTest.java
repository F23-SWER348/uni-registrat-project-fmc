package com.fmc;

import static org.junit.Assert.assertEquals; 

import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    Student student;

    @Before
    public void setup(){
        student = new Student("Mai", 1, "059234234234", 3.5);
    }

    @Test
    public void GPA_TEST() {
        assertEquals(  3.5, (double)student.GPA(), 0);
    }
}
