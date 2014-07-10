package ir.smartlab.java.ch05.circlesample;

/*
 * The Circle class
 */
public class Circle {
    // Attributes and methods come here
    private double radius;

    public void setRadius(double r) {
        radius = r;
    }

    public double perimeter() {
        return (2 * Math.PI * radius);
    }

    public double area() {
        return (Math.PI * radius * radius);
    }
}