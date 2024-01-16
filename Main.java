package org.example;

import java.io.*;

/*
Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
Обеспечьте поддержку сериализации для этого класса.
Создайте объект класса Student и инициализируйте его данными.
Сериализуйте этот объект в файл.
Десериализуйте объект обратно в программу из файла.
Выведите все поля объекта, включая GPA, и ответьте на вопрос,
почему значение GPA не было сохранено/восстановлено.
*/
public class Main {
    public static void main(String[] args) {
        Student student = new Student("Nikita", 16, 4.5);

        try (FileOutputStream fileout = new FileOutputStream("userdata.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileout)) {
            out.writeObject(student);
            System.out.println("Объект Student сериализован.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fileIn = new FileInputStream("userdata.bin");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            student = (Student)in.readObject();
            System.out.println("name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("GPA: " + student.getGPA());
            System.out.println("Значение GPA не было сохранено/восстановлено, " +
                    "так как GPA был помечен специальным модификатором" +
                    " 'transient' и изначально не был сериализован.");
        }
        catch (IOException e) {
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
