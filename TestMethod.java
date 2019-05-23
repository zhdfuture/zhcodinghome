package com.zh.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

class Person {

    private int age;

    public String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {

    private String school;
    private String skill;
    private LocalDateTime birthday;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "Student{" +
                "school='" + school + '\'' +
                ", skill='" + skill + '\'' +
                ", birthday=" + birthday +
                "} " + super.toString();
    }
}
public class TestMethod {
    public static void main1(String[] args)  {
        Class class1 = Person.class;
        System.out.println("获取包括父类的所有的方法：");
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("=======>");
        System.out.println("获取自己的所有的方法: ");
        Method[] methods1 = class1.getDeclaredMethods();
        for (Method method : methods1) {
            System.out.println(method);
        }
        System.out.println("======>");
        System.out.println("获取指定的方法： ");
        try {
            Person person = (Person) class1.newInstance(); //1.获取对象
            System.out.println("before Person :" + person);
            System.out.println("=======>");
            //2.获取对象的方法，并且调用（修改）
            Method method = class1.getMethod("setName", String.class);
            Object returnVal = method.invoke(person, "zhanghao");
            System.out.println(returnVal);//null
            System.out.println("after Person: " + person);
            System.out.println("====>");
            //3.获取对象的方法，并且调用（取值）
            Method getNameMethod = class1.getMethod("getName");
            Object returnName = getNameMethod.invoke(person);
            System.out.println(returnName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
       Class class1=Student.class;
        System.out.println("获取属性");
        System.out.println("1.====>");
        System.out.println("父类和子类的公开属性: ");
        Field[] fileds=class1.getFields() ;//返回父类和子类的公开属性
        for(Field field:fileds){
            System.out.println(field.getType() + " " + field.getName());
            System.out.println(field);
        }
        System.out.println("2====>");
        System.out.println("子类的所有属性");
        Field[] fields1=class1.getDeclaredFields() ;//返回子类的所有属性
        for(Field field:fields1){
            System.out.println(field.getType() + " " + field.getName());
            System.out.println(field);
        }

        System.out.println("=====>");
        System.out.println("获取指定属性");
        try {
            Field skillField = class1.getDeclaredField("skill");
            System.out.println(skillField);

            //使用属性
            Student student = new Student();
            student.setSkill("yuwen,shuxue,english");
            student.getSkill();
            //true表示可以访问私有的属性
            skillField.setAccessible(true);
            //get 获取
            Object skillValue = skillField.get(student);
            System.out.println(skillValue);
            //set 修改
            skillField.set(student, "pysh");  //修改
            System.out.println(student);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}