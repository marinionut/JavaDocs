package ro.teamnet.zerotohero.reflection;

import java.util.HashSet;
import java.util.Set;
import ro.teamnet.zerotohero.reflection.demoObjects.MyClass;

public class ClassReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        //get class for a String object
        Class c = "foo".getClass();
        System.out.println(c);

        //get class for an Enum
        c = E.A.getClass();
        System.out.println(c);

        //get class for collections
        Set<String> s = new HashSet<>();
        c = s.getClass();
        System.out.println(c);

        //get class for primitive type
        boolean b = true;
//        c = b.getClass();   // compile-time error
        c = boolean.class;  // correct
        System.out.println(c);

        //get class for specified class name
        c = Class.forName("ro.teamnet.zerotohero.reflection.E");
        System.out.println(c);

        //get class for primitive type associated to a class
        c = Double.TYPE;
        System.out.println(c);


        //get superclass
        c = MyClass.class.getSuperclass();
//        c = MyClass.class.getSuperclass().getSuperclass();
//        c = MyClass.class.getSuperclass().getSuperclass().getSuperclass();
        System.out.println(c);

        Class[] classes = MyClass.class.getDeclaredClasses();
        for(Class x:classes){
            System.out.println(x);
        }
    }
}
