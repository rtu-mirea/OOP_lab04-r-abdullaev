package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassSerialization {
    private String fileName;
    ArrayList<NumWork> objects;

    public ClassSerialization(String str){
        fileName = str;
        objects = new ArrayList<NumWork>();
    }
    public void writeOneObject(NumWork obj)throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(obj);
        out.close();
    }
    public NumWork readObeObject()throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        NumWork x = (NumWork) in.readObject();
        in.close();
        return x;
    }
    public void majeCollection() throws Exception{
        Scanner in = new Scanner(System.in);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, true));
        NumWork o;
        System.out.println("Введите количество объектов: ");
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++){
            System.out.println("Введите строку для объекта №" + (i + 1));
            o = new NumWork(in.nextLine());
            objects.add(o);
            out.writeObject(o);
        }
        out.close();
    }
    public void readCollection() throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        ArrayList<NumWork> arr = new ArrayList<NumWork>();
        try{
            while(true){
                arr.add((NumWork)in.readObject());
            }
        }
        catch (Exception e){}
        objects = arr;
    }
    public ArrayList<NumWork> getCollection(){return objects;}
}
