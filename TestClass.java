package com.zh.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TestClass {

    public static void main(String[] args) {
        Class class1 = Test1.class; //类名.class
        System.out.println("包名:" + class1.getPackage().getName());
        System.out.println("父类:" + class1.getSuperclass().getName());//类的全限定名  包名.类名
        System.out.println("父类:" + class1.getSuperclass().getSimpleName());//只是类名
        System.out.println("========>");
        System.out.println("接口： ");
        Class[] interfaces = class1.getInterfaces();
        for (Class cls : interfaces) {
            System.out.println(cls.getName());
            System.out.println(cls.getSimpleName());
        }
    }

    public static void main1(String[] args) {
        //获取所有的构造方法
        Class class1 = Test1.class; //类名.class
        Constructor[] constructors=class1.getConstructors();
        System.out.println("获取所有的构造方法");

        for(Constructor c:constructors){
            Class[] parameterCls=c.getParameterTypes();
            String parameter= Arrays.toString(parameterCls);
            System.out.println(c.getName()+"(" + parameter + ")");

        }

        System.out.println("获取指定的构造方法");
        try{
            Constructor constructor=class1.getConstructor(Integer.class,String.class);
            System.out.println(constructor);
            //通过Constructor实例化对象
            //new Test(...)
            Object object=constructor.newInstance(20,"zhanghao");
            System.out.println(object.getClass());
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
interface Clothes1{}
interface Message1{}
class Test1 implements Clothes1, Message1{
        public Test1(Integer a, Integer b) {

        }

        public Test1(Integer a, String b) {

        }

        public Test1(Integer a) {

        }

        public Test1() {

        }
}

