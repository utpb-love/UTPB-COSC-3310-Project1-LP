/**
 * <h1>Test</h1>
 * A unit-testing script for UInt.
 * Ultimately, this is intended to function as an "auto-grader" for the project.
 * It will check the UInt class to ensure it functions properly and without errors,
 *   and assign a score to signify whether the class works as intended.
 *
 * @author Tim Fielder
 * @version 0.1 (Sept 30, 2024)
 */
public class Test {
    public static void main(String[] args) {
        UInt u1 = new UInt(120);
        UInt u2 = new UInt(157);
        UInt u3 = new UInt(212);
        UInt u4 = new UInt(39);
        System.out.println(u1);
        System.out.println(u1.toInt());
    }
}
