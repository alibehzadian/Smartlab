package ir.smartlab.java.ch05.circlesample;

public class Main {

    public static void main(String[] args) {
        double radius = 5;
        Circle theCircle = new Circle();
        theCircle.setRadius(radius);
        System.out.println("New Circle's Perimeter is:" + theCircle.perimeter());
        System.out.println("New Circle's area is:" + theCircle.area());
    }

}
