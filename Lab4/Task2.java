package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public static void save(String fileName)throws Exception{
        Scanner in = new Scanner(System.in);
        int count, studentNumber, groupNumber;
        String surname, name, birthDate, mobile, enrollmentDate;

        File file = new File(fileName);
        if(file.exists())
            System.out.println("Файл " + fileName + " уже сущкствует");
        else {
            file.createNewFile();
            System.out.println("Файл " + fileName + " был создан");
        }

        DataOutputStream out = new DataOutputStream((new FileOutputStream(file, true)));
        System.out.println("Введите количество тудентов:");
        count = in.nextInt();
        in.nextLine();
        for(int i = 0; i < count; i++){
            System.out.println("Введите номер зачетной книжки студента №" + (i + 1));
            studentNumber = in.nextInt();
            in.nextLine();
            System.out.println("Введите фамилию студента №" + (i + 1));
            surname = in.nextLine();
            System.out.println("Введите имя студента №" + (i + 1));
            name = in.nextLine();
            System.out.println("Введите дату рождения студента №" + (i + 1));
            birthDate = in.nextLine();
            System.out.println("Введите номер телефона студента №" + (i + 1));
            mobile = in.nextLine();
            System.out.println("Введите дату зачисления студента №" + (i + 1));
            enrollmentDate = in.nextLine();
            System.out.println("Введите номер группы студента №" + (i + 1));
            groupNumber = in.nextInt();
            in.nextLine();
            out.writeInt(studentNumber);
            out.writeUTF(surname);
            out.writeUTF(name);
            out.writeUTF(birthDate);
            out.writeUTF(mobile);
            out.writeUTF(enrollmentDate);
            out.writeInt(groupNumber);
        }
        out.close();
    }
    public static ArrayList<Student> load(String filename){
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            while (true)
                students.add(new Student(in.readInt(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(),in.readUTF(), in.readInt()));
        }
        catch (Exception e){}
        System.out.println("Введите номер группы список которой хотите получить");
        return Student.getStudentsOfGroup(students, new Scanner(System.in).nextInt());
    }
    public static ArrayList<Student> randomAccess(ArrayList<Student> students)throws Exception{
        RandomAccessFile rf = new RandomAccessFile("FileForRandomAccess.txt", "rw");
        int buf = students.size();
        for(Student student: students){
            student.increase();
            rf.writeInt(student.getStudentNumber());
            rf.writeUTF(student.getSurname());
            rf.writeUTF(student.getName());
            rf.writeUTF(student.getBirthDate());
            rf.writeUTF(student.getMobile());
            rf.writeUTF(student.getEnrollmentDate());
            rf.writeInt(student.getGroupNumber());
        }
        students.clear();
        rf.seek(0);
        for(int i = 0; i < buf; i++){
            students.add(new Student(rf.readInt(), rf.readUTF(), rf.readUTF(), rf.readUTF(), rf.readUTF(), rf.readUTF(), rf.readInt()));
        }
        System.out.println("Введите номер зачетной книжки студента которого вы хотите удалить");
        return Student.delStudent(students, new Scanner(System.in).nextInt());
    }
}
