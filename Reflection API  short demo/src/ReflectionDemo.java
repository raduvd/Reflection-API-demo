import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String args[]) throws Exception {

        Constructor<?> constructor = Class
                .forName("random.PrivateClass")
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();
        Class<?> cls = o.getClass();

        Field privateField = cls.getDeclaredField("s");
        privateField.setAccessible(true);

        System.out.println("Accessing a private field, which has the value == " + privateField.get(o));

        privateField.set(o, "NEW PRIVATE FIELD VALUE");

        System.out.println("The value for the new private field is " + privateField.get(o));

    }
}


// class whose object is to be created
class Test {
    // creating a private field
    private String s;

    // creating a public constructor
    public Test() {
        s = "GeeksforGeeks";
    }

    // Creating a public method with no arguments
    public void method1() {
        System.out.println("The string is " + s);
    }

    // Creating a public method with int as argument
    public void method2(int n) {
        System.out.println("The number is " + n);
    }

    // creating a private method
    private void method3() {
        System.out.println("Private method invoked");
    }
}

class Demo {
    public static void main(String args[]) throws Exception {
        // Creating object whose property is to be checked
        Test obj = new Test();

        // Creating class object from the object using
        // getclass method
        Class cls = obj.getClass();
        System.out.println("The name of class is " +
                cls.getName());

        // Getting the constructor of the class through the
        // object of the class
        Constructor constructor = cls.getConstructor();
        System.out.println("The name of constructor is " +
                constructor.getName());

        System.out.println("The public methods of class are : ");

        // Getting methods of the class through the object
        // of the class by using getMethods
        Method[] methods = cls.getMethods();

        // Printing method names
        for (Method method : methods)
            System.out.println(method.getName());

        // creates object of desired method by providing the
        // method name and parameter class as arguments to
        // the getDeclaredMethod
        Method methodcall1 = cls.getDeclaredMethod("method2",
                int.class);

        // invokes the method at runtime
        methodcall1.invoke(obj, 19);

        // creates object of the desired field by providing
        // the name of field as argument to the
        // getDeclaredField method
        Field field = cls.getDeclaredField("s");

        // allows the object to access the field irrespective
        // of the access specifier used with the field
        field.setAccessible(true);

        System.out.println("The original value of the field is " + field.get(obj));

        // takes object and the new value to be assigned
        // to the field as arguments
        field.set(obj, "JAVA");

        System.out.println("The new value of the private field is " + field.get(obj));
        ;

        // Creates object of desired method by providing the
        // method name as argument to the getDeclaredMethod
        Method methodcall2 = cls.getDeclaredMethod("method1");

        // invokes the method at runtime
        methodcall2.invoke(obj);

        // Creates object of the desired method by providing
        // the name of method as argument to the
        // getDeclaredMethod method
        Method methodcall3 = cls.getDeclaredMethod("method3");

        // allows the object to access the method irrespective
        // of the access specifier used with the method
        methodcall3.setAccessible(true);

        // invokes the method at runtime
        methodcall3.invoke(obj);
    }
}