package ir.smartlab.java.ch06.variablescope;

/*
 * An example about variables' scope
 */
public class VariableScopeTest {

    int someInt;

    public void firstMethod() {
        int myNumber = 5;
    }

    public void someMethod() {
        myNumber = 10; // ?

        for (int i = 0; i < 10; i++) {
            // Do something
        }

        // Now what is i's value?
        System.out.println("i = " + i);

        int someInt;
        someInt = 5; // ?
        this.someInt = 7; // class variable
    }
}