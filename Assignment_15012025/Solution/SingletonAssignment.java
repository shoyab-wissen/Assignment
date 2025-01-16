package Assignment_15012025.Solution;

import Assignment_15012025.Exceptions.InstanceExistException;

class A {
    private static A obj = null;

    A() {
        if (this instanceof B) {
            System.out.println("Instance of B Created");
        } else if (this instanceof A && obj == null) {
            obj = this;
            System.out.println("Instance of A Created");
        } else {
            throw new InstanceExistException("Instance of this Object has Already Been Created For Class A");
        }
    }

    public static A getObject() {
        if (obj == null) {
            obj = new A();
        }
        return obj;
    }
}

class B extends A {
    private static B obj = null;

    private B() {
        System.out.println("Object created");
    }

    public static B getObject() {
        if (obj == null) {
            obj = new B();
        }
        return obj;
    }
}

public class SingletonAssignment {
    public static void main(String[] args) {
        try {
            A obj3 = new A();
            A obj1 = A.getObject();
            B obj2 = B.getObject();
            System.out.println(obj1);
            System.out.println(obj2);
            System.out.println(obj3);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
