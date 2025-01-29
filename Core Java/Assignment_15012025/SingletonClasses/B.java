package Assignment_15012025.SingletonClasses;

import Assignment_15012025.Exceptions.InstanceExistException;

public class B extends A {
    private static B obj = null;

    private B() {
        System.out.println("Object created");
    }

    public static B getObject() throws InstanceExistException {
        if (obj == null) {
            try {
                obj = new B();
            } catch (Exception e) {
                throw new InstanceExistException("Instance of this Object has Already Been Created For Class B");
            }
            return obj;
        } else {
            throw new InstanceExistException("Instance of this Object has Already Been Created For Class B");
        }
    }
}