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
        
        // Test constructors and toString
        UInt u1 = new UInt(10);
        UInt u2 = new UInt(3);
        UInt u3 = new UInt(0);
        UInt u4 = new UInt(255);
        
        System.out.println("UInt u1 (10): " + u1); // Expected: 0b01010
        System.out.println("UInt u2 (3): " + u2);  // Expected: 0b011
        System.out.println("UInt u3 (0): " + u3);  // Expected: 0b0
        System.out.println("UInt u4 (255): " + u4);// Expected: 0b011111111
        
        // Test clone method
        UInt uClone = u1.clone();
        System.out.println("Cloned u1: " + uClone); // Expected: 0b1010
        
        // Test toInt method
        System.out.println("u1 to int: " + u1.toInt()); // Expected: 10
        System.out.println("u2 to int: " + u2.toInt()); // Expected: 3

        // Test and method
        UInt uAnd = UInt.and(u1, u2);
        System.out.println("u1 AND u2: " + uAnd); // Expected: 0b010
        
        // Test or method
        UInt uOr = UInt.or(u1, u2);
        System.out.println("u1 OR u2: " + uOr);   // Expected: 0b01011

        // Test xor method
        UInt uXor = UInt.xor(u1, u2);
        System.out.println("u1 XOR u2: " + uXor); // Expected: 0b01001
        
        // Test add method
        UInt uAdd = UInt.add(u1, u2);
        System.out.println("u1 + u2: " + uAdd);   // Expected: 0b01101 (13 in decimal)
        
        // Test sub method
        UInt uSub = UInt.sub(u1, u2);
        System.out.println("u1 - u2: " + uSub);   // Expected: 0b0111 (7 in decimal)
        
        // Test negate method
        UInt uNegate = new UInt(10);
        uNegate.negate();
        System.out.println("Negate u1: " + uNegate); // Expected: 2's complement of 0b01010
        
        // Test mul method
        UInt uMul = new UInt(6);
        uMul.mul(new UInt(7));
        System.out.println("uMul (6 * 7): " + uMul); // Expected: 0b0101010 (42 in decimal)
    }
}
