package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int task = 8;
        Scanner in = new Scanner(System.in);
        while (task != 0) {
            try {
                System.out.println("[0] - завершение работы" + '\n' +
                        "[1] - задание 1" + '\n' +
                        "[2] - задание 2" + '\n' +
                        "[3] - задание 3" + '\n' +
                        "[4] - задание 4" + '\n' +
                        "Выберите задание:");
                task = in.nextInt();
                in.nextLine();
                switch (task) {
                    case 0:

                        break;
                    case 1:
                        task1();
                        break;
                    case 2:
                        System.out.println("Введите названеие файла для задания 2.");
                        String fileName = in.nextLine();
                        Task2.save(fileName);
                        ArrayList<Student> students = Task2.load(fileName);//"Task2.txt"
                        System.out.println("Список студентов после выполнения первой части задания");
                        for (Student student : students) {
                            System.out.println(student.getSurname() + " " + student.getName());
                        }
                        students = Task2.randomAccess(students);
                        System.out.println("Список студентов после выполнения второй части задания");
                        for (Student student : students) {
                            System.out.println(student.getSurname() + " " + student.getName());
                        }
                        break;
                    case 3:
                        System.out.println("Задание 3 упражнение 1:");
                        task3Ex1();
                        System.out.println("Задание 3 упражнение 2:");
                        task3Ex2();
                        System.out.println("Задание 3 упражнение 3:");
                        task3Ex3();
                        break;
                    case 4:
                        task4();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private static void task1(){
        try {
            Scanner in = new Scanner(System.in);
            // Упражнение 1. Исследовать возможности класса File по созданию файлов (пустых) и папок программой. Применение конструктора и метода. е.
            File f1 = new File("MyFile1.txt");
            System.out.println("Упражнение 1 ---------------------------------------------------------------");
            f1.createNewFile();
            File f2 = new File ("/MyFile2.txt");
            f2.createNewFile();
            File f3 = new File("C:\\Zadanie1\\MyFile3.txt");
            f3.createNewFile();

            if(f1.exists())
                System.out.println(f1.getName() + " был создан");
            else
                System.out.println(f1.getName() + " не был создан");
            if(f2.exists())
                System.out.println(f2.getName() + " был создан");
            else
                System.out.println(f2.getName() + " не был создан");
            if(f3.exists())
                System.out.println(f3.getName() + " был создан");
            else
                System.out.println(f3.getName() + " не был создан");
            File dir = new File("first\\second\\third");
            dir.mkdirs();
            if(dir.exists())
                System.out.println(dir.getName() + " был создан");
            else
                System.out.println(dir.getName() + " не был создан");
            // Упражнение 2. Получить параметры файлов методами класса File. В пунктах задания использовать объекты, созданные в задании 1.
            System.out.println("\nУпражнение 2 ---------------------------------------------------------------");
            if (f3.isFile())
                System.out.println(f3.getName() + " является файлом, его родитель - " + f3.getParent());
            else
                System.out.println(f3.getName() + " не является файлом");
            if(dir.isDirectory())
                System.out.println(dir.getName() + " является папкой, ее родитель - " + dir.getParent());
            else
                System.out.println(dir.getName() + " не является папкой");
            File f4 = new File(f1.getName());
            if (f4.exists())
                System.out.println(f4.getName() + " находитсся в папке приложения");
            else
                System.out.println(f4.getName() + " не находится в папке приложения");
            System.out.println("Полный путь к файлу " + f1.getName() + ":\n" + f1.getAbsolutePath());
            if (f1.isFile())
                System.out.println("Размер файла" + f1.getName() + " в байтах равен " + f1.length());
            else
                System.out.println("Размер папки" + f1.getName() + " в байтах равен " + f1.length());
            //Упражнение 3. Модификация файловой структуры приложения средствами класса File.
            System.out.println("\nУпражнение 3 ---------------------------------------------------------------");
            File dir1 = new File("OneMoreDirectory");
            dir1.mkdir();
            File dir2 = new File("C:\\Users\\whoami\\Desktop\\Games & stuff\\JavaOOP\\Laba4");
            String arr[] = dir2.list();
            File arr1[] = dir2.listFiles();
            int count = 0;
            System.out.println("Содержимое папки " + dir2.getName() + ":\n");
            for(String str: arr)
                System.out.println(str);
            System.out.println("Содержимое папки " + dir2.getName() + ":\n");
            for(File file: arr1) {
                System.out.println(file.getName());
                if(file.isDirectory())
                    count++;
            }
            System.out.println("Количество папок в папке приложения равно " + count);


            dir1.delete();
            while(dir.getParent() != null){
                dir.delete();
                dir = new File(dir.getParent());
            }
            dir.delete();
            f1.delete();
            f2.delete();
            f3.delete();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private static void task3Ex1(){
        try {
            Reader reader = new InputStreamReader(new FileInputStream("T1.txt"));
            Writer writer = new OutputStreamWriter(new FileOutputStream("T2.txt"));
            int x;
            while((x = reader.read()) != -1) {
                writer.write((char)x);
            }
            System.out.println("Все символы были пернесены из файла T1.txt в файл T2.txt");
            reader.close();
            writer.close();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
    private static void task3Ex2(){
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream("A.txt"));
            for (int i = 40; i < 552; i++) {
                writer.write((char) i);
            }
            writer.close();
            System.out.println("Файл A.txt был заполнен 512 символами");

            BufferedReader inb = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt")), 128);
            BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("B.txt")), 128);
            char[] buf = new char[128];
            for(int i = 0; i < 4; i++){
                inb.read(buf);
                outb.write(buf);
                outb.newLine();
            }
            System.out.println("Символы по 128 штук были перенесены из файла A.txt в B.txt");
            inb.close();
            outb.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private static void task3Ex3(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\whoami\\Desktop\\Games & stuff\\JavaOOP\\Laba4\\src\\A.txt"), "Cp1251"));
            System.out.println("Кодировка системы: " + Charset.defaultCharset().name());
            String str = in.readLine();
            in.close();
            System.out.println("Строка прочитанная в неверной кодировке:\n" + str);
            in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\whoami\\Desktop\\Games & stuff\\JavaOOP\\Laba4\\src\\B.txt"), "UTF-8"));
            str = in.readLine();
            System.out.println("Строка прочитанная в верной кодировке:\n" + str);
            in.close();
        }
        catch (Exception e){System.err.println(e);}
    }
    private static void task4(){
        Scanner in = new Scanner(System.in);
        String fileName;
        int ex = 42;
        while (ex != 0){
            try {
                System.out.println("[0] - возвращение к предыдущему меню"  + '\n' +
                        "[1] - класс ClassTextFile" + '\n' +
                        "[2] - класс для представления объекта из лабораторной работы 2" + '\n' +
                        "[3] - класс для сериализации коллекции обхектов" + '\n' +
                        "Выберите задание:");
                ex = in.nextInt();
                in.nextLine();
                switch (ex) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Введите имя файла для чтения из него данных");
                        fileName = in.nextLine();
                        ClassTextFile obj = new ClassTextFile(fileName);
                        System.out.println("Текст из файла, который был помещен в объект:\n" + obj.getText().getLine());
                        break;
                    case 2:
                        ClassForObject object = new ClassForObject();
                        object.consoleInput();
                        System.out.println("Текст объкта введенный с консоли:\n" + object.getObject().getLine());
                        System.out.println("Введите имя файла из которого следует взять текст:");
                        object.dataStream(in.nextLine());
                        System.out.println("Текст объкта полученный из файла:\n" + object.getObject().getLine());
                        break;
                    case 3:
                        System.out.println("Введите имя файла");
                        ClassSerialization sObj = new ClassSerialization(in.nextLine());
                        sObj.majeCollection();
                        sObj.readCollection();
                        ArrayList<NumWork> arr = sObj.getCollection();
                        System.out.println("Десериализованная коллекция обхектов:");
                        for(NumWork str: arr)
                            System.out.println(str.getLine());
                        break;
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
