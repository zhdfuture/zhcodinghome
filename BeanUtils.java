import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static javafx.scene.input.KeyCode.T;


/**
     * 对象的属性值拷贝
     * <p>
     * 将source对象中的属性值赋值到target对象中的属性，属性名一样，类型一样
     * <p>
     * example:
     * <p>
     * source:
     * <p>
     * String name;
     * String address;
     * Integer age;
     * Date   birthday;
     * <p>
     * target:
     * String name;
     * String address;
     * String email
     * <p>
     * 结果： source name, address -> target name, address
     *
     */
class Source{
    private String name;
    private int age;
    private String address;
    private LocalDateTime birthday;
     public String getName(){
    return name;
     }
     public void setName(String name){
         this.name=name;
     }
     public int getAge(){
         return age;
     }
     public void setAge(int age){
         this.age=age;
     }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LocalDateTime getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDateTime birthday) {
            this.birthday = birthday;
        }
    }
    class Target {
        private String name;
        private int age;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

public class BeanUtils {
    public static void copy(Object source, Object target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //1.参数校验
        if (source == null) {
            throw new IllegalArgumentException("Source object must be not null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target object must be not null.");
        }

        Class sourceClass=source.getClass();    //2.得到Class对象
      Class targetClass=target.getClass();
      //得到Class对象的所有属性
        Field[] sourceFields=sourceClass.getDeclaredFields();
        Field[] targetFields=targetClass.getDeclaredFields();
        for(Field sourceFiled: sourceFields ){
            String name=sourceFiled.getName();  //属性名
            Class type=sourceFiled.getType();//属性类型
            for(Field targetField:targetFields){
                String targetName=targetField.getName();//目标对象的属性名
                Class targetType=targetField.getType();
                if(targetName.equals(name)&&targetType==type){
                    try {
                       sourceFiled.setAccessible(true);   //都设置为可以访问
                        targetField.setAccessible(true);
                        Object value = sourceFiled.get(source);
                       targetField.set(target, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }


        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Source source=new Source();
        Target target=new Target();
        source.setName("张豪");
        source.setAge(18);
        source.setAddress("汉中");
       //source.setBirthday(1998-08-01 T 10:05:30);
        source.setBirthday(LocalDateTime.of(2003,11,13,10,30,54));

        System.out.println("copy before:");
        System.out.println(source);
        System.out.println(target);

        System.out.println("copy after:");

        BeanUtils.copy(source, target);

        System.out.println(source);
        System.out.println(target);
        System.out.println("name:"+target.getName());
        System.out.println("age: "+target.getAge());
        System.out.println("address:"+target.getEmail());;
    }
}
