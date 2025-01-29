package Assignment_15012025.SingletonClasses;

public class SingletonAssignment {
    public static void main(String[] args) {
        try {
            A obj1 = A.getObject();
            A.B obj2 = A.B.getObject();
            System.out.println(obj1);
            System.out.println(obj2);

            try {
                A obj3 = A.getObject();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                A.B obj4 = A.B.getObject();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
