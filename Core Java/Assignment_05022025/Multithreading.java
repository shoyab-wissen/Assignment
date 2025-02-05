package Assignment_05022025;

class A {
    public void print1to10() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Range : " + i);
        }
    }
}

class B {
    public void evenTill50() {
        for (int i = 0; i <= 50; i += 2) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Even : " + i);
        }
    }
}

class C {
    public void fibonacciFrom1to1000() {
        int a = 0;
        int b = 1;
        int sum = 0;
        System.out.println(a);
        System.out.println(b);
        while (sum < 1000) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sum = a + b;
            System.out.println(sum);
            a = b;
            b = sum;
        }
    }
}

public class Multithreading {
    public static void main(String args[]) {

        // Runnable r1 = () -> new A().print1to10();
        // Runnable r2 = () -> new B().evenTill50();
        // Runnable r3 = () -> new C().fibonacciFrom1to1000();

        // Thread t1 = new Thread(r1);
        // Thread t2 = new Thread(r2);
        // Thread t3 = new Thread(r3);

        new Thread(new A()::print1to10).start();
        new Thread(new B()::evenTill50).start();
        new Thread(new C()::fibonacciFrom1to1000).start();

        // t1.start();
        // t2.start();
        // t3.start();
    }
}