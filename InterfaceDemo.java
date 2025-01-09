interface X {
    int a = 100;

    void add();

    void sub();
}

interface Y {
    int b = 50;

    void mul();

    void div();
}

interface Z extends X, Y {
}

class Calculator implements Z {

    @Override
    public void add() {
        System.out.println("Addition: " + (X.a + Y.b));

    }

    @Override
    public void sub() {
        System.out.println("Subtraction: " + (X.a - Y.b));

    }

    @Override
    public void div() {
        System.out.println("Division: " + (X.a / Y.b));

    }

    @Override
    public void mul() {
        System.out.println("Multiplication: " + (X.a * Y.b));

    }

}

public class InterfaceDemo {
    public static void main(String[] args) {
        Calculator c1 = new Calculator();
        X x1 = c1;
        Y y1 = c1;

        System.out.println("-----------------------");
        x1.add();
        x1.sub();
        System.out.println("-----------------------");
        y1.mul();
        y1.div();
        System.out.println("------------------------");
        c1.add();
        c1.sub();
        c1.mul();
        c1.div();
    }
}
