package Assignment_15012025.SingletonClasses;

import Assignment_15012025.Exceptions.InstanceExistException;

public class A {
    private static A obj = null;

    private A() {
        System.out.println("Object created");
    }

    public static A getObject() throws InstanceExistException {
        if (obj == null) {
            obj = new A();
            return obj;
        } else {
            throw new InstanceExistException("Instance of this Object has Already Been Created For Class A");
        }
    }
}
