package com.company;

import javax.swing.text.Style;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student {
    private int studentNumber;
    private String surname;
    private String name;
    private String birthDate;
    private String mobile;
    private String enrollmentDate;
    private int groupNumber;

    public Student(){
        studentNumber= -1;
        surname = "";
        name = "";
        birthDate = "";
        mobile = "";
        enrollmentDate = "";
        groupNumber = -1;
    }
    public Student(int studNum, String sur, String n, String birth, String mob, String enrolment, int group){
        studentNumber= studNum;
        surname = sur;
        name = n;
        birthDate = birth;
        mobile = mob;
        enrollmentDate = enrolment;
        groupNumber = group;
    }
    public int getStudentNumber(){return studentNumber;}
    public String getSurname(){return surname;}
    public String getName(){return name;}
    public String getBirthDate(){return birthDate;}
    public String getMobile(){return mobile;}
    public String getEnrollmentDate(){return enrollmentDate;}
    public int getGroupNumber(){return groupNumber;}
    public String getSurnameOfStudentNumber(int n){
        if(n == studentNumber)
            return surname;
        else return  "";
    }
    public String getBirthDateOfStudentNumber(int n){
        if(n == studentNumber)
            return birthDate;
        else return "";
    }
    public boolean compare(Student s1){
        return enrollmentDate == s1.enrollmentDate;
    }
    public static ArrayList<Student> getStudentsOfGroup(ArrayList<Student> students, int groupNumber){
        ArrayList<Student> res = new ArrayList<Student>();
        for(Student student: students){
            if (student.groupNumber == groupNumber)
                res.add(student);
        }
        return res;
    }
    public static ArrayList<Student> delStudent(ArrayList<Student> students, int studentNumber){
        Student last = students.get(students.size() - 1);
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).studentNumber == studentNumber){
                students.remove(i);
                students.add(i, last);
                break;
            }
        }
        return students;
    }
    public void increase(){
        surname = grow(surname);
        name = grow(name);
        birthDate= grow(birthDate);
        mobile = grow(mobile);
        enrollmentDate = grow(enrollmentDate);
    }
    private static String grow(String str){
        for(int i = str.length(); i <= 15; i++)
            str+=" ";
        return str;
    }
}
