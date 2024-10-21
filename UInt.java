/**
 * @auth
 */

import java.util.Arrays;

/**
 * <h1>UInt</h1>
 * Represents an unsigned integer using a boolean array to store the binary representation.
 * Each bit is stored as a boolean value, where true represents 1 and false represents 0.
 *
 * @author Tim Fielder
 * @version 1.0 (Sept 30, 2024)
 */
public class UInt {

    // The array representing the bits of the unsigned integer.
    protected boolean[] bits;

    // The number of bits used to represent the unsigned integer.
    protected int length;

    /**
     * Constructs a new UInt by cloning an existing UInt object.
     *
     * @param toClone The UInt object to clone.
     */
    public UInt(UInt toClone) {
        this.length = toClone.length;
        this.bits = Arrays.copyOf(toClone.bits, this.length);
    }

    /**
     * Constructs a new UInt from an integer value.
     * The integer is converted to its binary representation and stored in the bits array.
     *
     * @param i The integer value to convert to a UInt.
     */
    public UInt(int i) {

        if (i == 0) {
            length = 1;
            bits = new boolean[length];
            bits[0] = false;
            return;
        }
        // Determine the number of bits needed to store i in binary format.
        length = (int)(Math.ceil(Math.log(i)/Math.log(2.0)) + 1);
        bits = new boolean[length];

        // Convert the integer to binary and store each bit in the array.
        for (int b = length-1; b >= 0; b--) {
            // We use a ternary to decompose the integer into binary digits, starting with the 1s place.
            bits[b] = i % 2 == 1;
            // Right shift the integer to process the next bit.
            i = i >> 1;



            // Deprecated analog method
            /*int p = 0;
            while (Math.pow(2, p) < i) {
                p++;
            }
            p--;
            bits[p] = true;
            i -= Math.pow(2, p);*/
        }
    }

    /**
     * Creates and returns a copy of this UInt object.
     *
     * @return A new UInt object that is a clone of this instance.
     */
    @Override
    public UInt clone() {
        return new UInt(this);
    }

    /**
     * Creates and returns a copy of the given UInt object.
     *
     * @param u The UInt object to clone.
     * @return A new UInt object that is a copy of the given object.
     */
    public static UInt clone(UInt u) {
        return new UInt(u);
    }

    /**
     * Converts this UInt to its integer representation.
     *
     * @return The integer value corresponding to this UInt.
     */
    public int toInt() {
        int t = 0;
        // Traverse the bits array to reconstruct the integer value.
        for (int i = 0; i < length; i++) {
            // Again, using a ternary to now re-construct the int value, starting with the most-significant bit.
            t = t + (bits[i] ? 1 : 0);
            // Shift the value left for the next bit.
            t = t << 1;
        }
        return t >> 1; // Adjust for the last shift.
    }

    /**
     * Static method to retrieve the int value from a generic UInt object.
     *
     * @param u The UInt to convert.
     * @return The int value represented by u.
     */
    public static int toInt(UInt u) {
        return u.toInt();
    }

    /**
     * Returns a String representation of this binary object with a leading 0b.
     *
     * @return The constructed String.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("0b");
        // Construct the String starting with the most-significant bit.
        for (int i = 0; i < length; i++) {
            // Again, we use a ternary here to convert from true/false to 1/0
            s.append(bits[i] ? "1" : "0");
        }
        return s.toString();
    }

    /**
     * Performs a logical AND operation using this.bits and u.bits, with the result stored in this.bits.
     *
     * @param u The UInt to AND this against.
     */
    public void and(UInt u) {
        // We want to traverse the bits arrays to perform our AND operation.
        // But keep in mind that the arrays may not be the same length.
        // So first we use Math.min to determine which is shorter.
        // Then we need to align the two arrays at the 1s place, which we accomplish by indexing them at length-i-1.

        // original method would not function correctly if the longer boolean array belongs to "this" object because it wouldn't change the values at the lower indices that it never reaches. 
        // to solve this, create a new boolean array with size of minimum
        int min = Math.min(this.length, u.length);
        boolean[] realAND = new boolean[min];
        for (int i = 0; i < min; i++)
        {
            realAND[realAND.length - i - 1] = this.bits[this.length - i - 1] && u.bits[u.length - i - 1]; // put in one line for my preference
        }
        this.bits = realAND;
        this.length = realAND.length;
    }

    /**
     * Accepts a pair of UInt objects and uses a temporary clone to safely AND them together (without changing either).
     *
     * @param a The first UInt
     * @param b The second UInt
     * @return The temp object containing the result of the AND op.
     */
    public static UInt and(UInt a, UInt b) {
        UInt temp = a.clone();
        temp.and(b);
        return temp;
    }

    /**
     * Performs a logical OR operation using this.bits and u.bits, with the result stored in this.bits
     * 
     * @param u The UInt to OR this against
     */
    public void or(UInt u) {
        // use same code "template" for AND method
        for (int i = 0; i < Math.min(this.length, u.length); i++)
        {
            this.bits[this.length - i - 1] = this.bits[this.length - i - 1] | u.bits[u.length - i - 1];

        }
        return;
    }

    /**
     * Accepts a pair of UInt objects and uses a temporary clone to safely OR them together (without changing either).
     * 
     * @param a The first UInt
     * @param b The second UInt
     * @return The temp object containing the result of the OR operation
     */
    public static UInt or(UInt a, UInt b) {
        // use the same "template" as the static AND method
        UInt temp = a.clone();
        temp.or(b);
        return temp;
    }

    /**
     * Peforms a logical XOR operation using this.bits and u.bits, with the result stored in this.bits
     * 
     * @param u The UInt to XOR this against
     */
    public void xor(UInt u) {
        // use the "templates" of both previous logical operations to complete this third one
        for (int i = 0; i < Math.min(this.length, u.length); i++)
        {
            this.bits[this.length - i - 1] = this.bits[this.length - i - 1] ^ u.bits[u.length - i - 1];

        }
        return;
    }

    /**
     * Accepts a pair of UInt objects and uses a temporary clone to safely XOR them together (without changing either).
     * 
     * @param a The first UInt object
     * @param b The second UInt object
     * @return
     */
    public static UInt xor(UInt a, UInt b) {
        // use the "templates" of both previous static logical operations to complete this one
        UInt temp = a.clone();
        temp.xor(b);
        return temp;
    }

    /**
     * Adds passed UInt object "u" to "this" UInt object and stores the result in this.bits
     * 
     * @param u
     */
    public void add(UInt u) {
        // The result will be stored in this.bits
        // You will likely need to create a couple of helper methods for this.
        // Note this one, like the bitwise ops, also needs to be aligned on the 1s place.
        // Also note this may require increasing the length of this.bits to contain the result.

        int max = Math.max(this.length, u.length); // the result of add should be the length of the longer of the two 
        boolean[] result = new boolean[max]; // this.bits will be changed to this array at the end of all the addition
        boolean carry = false; // carry starts off false

        for (int i = 0; i < max; i++) {
            // predefine bits
            boolean bit1, bit2;


            if (i < this.length) {
                bit1 = this.bits[this.length - 1 - i]; // iterate through the current bits if there is a value at the length, set bit1 equal to that boolean
            } else {
                bit1 = false; // if there is no bit, it will be padded w zero's 
            }

            if (i < u.length) {
                bit2 = u.bits[u.length - i - 1]; // iterate through the current bits if there is a value at the length, set bit2 equal to that boolean
            } else {
                bit2 = false; // if there is no bit, it will be padded w zero's 
            }

            boolean[] answer = addBit(bit1, bit2, carry); // set answer to the array returned from addBit(sum and carry)

            result[max - i - 1] = answer[0]; // 0'th index is the sum. So, set the index that corresponds with the BIGGER number's length equal to the sum
            carry = answer[1]; // the new carry value is at index 1 of answer. So, update the carry for the next bit
        }

        // if there is a carry after the loop finishes, then that means there has to be one extra bit
        if (carry) {
            boolean[] resultPlus = new boolean[max + 1]; // create a boolean array ONE bit wider
            
            // use built-in array methods to copy result to extend bits. start copying from the 1st index, so the most significant bit is untouched. 
            System.arraycopy(result, 0, resultPlus, 1, max);
            // after copying is done, change most significant bit 1, or true
            resultPlus[0] = true;
            // finally, change result to point to the memory location of resultPlus
        }

        // after the correct result is found, we have to update the current bits and current length to the result array
        this.bits = result;
        this.length = result.length;

        return;
    }

    /**
     * Accepts a pair of UInt objects and adds them together without changing either
     * 
     * @param a first UInt object 
     * @param b second UInt object
     * @return new result UInt object that is the sum of "a" and "b"
     */
    public static UInt add(UInt a, UInt b) {

        // use the same template for all previous static methods
        UInt temp = a.clone();
        temp.add(b);
        return temp;
    }

    /**
     * Negates "this" UInt object using 2's complement
     */
    public void negate() {
        // The add() method will be helpful with this.
        // invert all bits of this.bits
        for (int i = 0; i < this.length;  i++) {
            this.bits[i] = !this.bits[i]; // set every bit to the negation of itself
        }

        // have to create new logic for adding 1 to ignore overflow
        boolean carry = true; // if we just add carry to it, we don't have to worry about creating new Uint(1)
        
        // iterate through this.bits backwards
        for(int i = this.length - 1; i >= 0; i--) {
            boolean[] answer = addBit(this.bits[i], false, carry); // b is set to false because we aren't adding carry to two objects, just one.
            this.bits[i] = answer[0]; // index 0 holds the sum of the two bits
            carry = answer[1]; // update carry for the next bit, stored in index 1
        }
    }

    /**
     * Subtracts the passed UInt object from "this" UInt object and stores the result in "this.bits"
     * 
     * @param u the UInt object that will be subtracted
     */
    public void sub(UInt u) {
        // As this class is supposed to handle only unsigned values,
        //   if the result of the subtraction operation would be a negative number then it should be coerced to 0.

        UInt negated = u.clone(); // create a new UInt object that is a copy of "u"
        // for this to function properly, the boolean arrays of the UInt objects have to be the same size
        int max = Math.max(this.length, u.length);
        int min = Math.min(this.length, u.length);
        boolean[] newNegated = new boolean[max];

        System.arraycopy(negated.bits, 0, newNegated, max - min, negated.length); // copy the old array to the new one, starting from the index equal to the difference in the sizes
        // change negated size and length to newNegated
        negated.bits = newNegated;
        negated.length = newNegated.length; 
        negated.negate(); // negate it
        
        this.add(negated); // now just add the two numbers together

        // then, we need to check if the most significant bit is true, and if it is, meaning it is a negative number, set the whole object to 0;
        if (this.bits[0]) {

            this.bits = new boolean[]{false}; // create a new boolean array with one value, false, and set it equal to this.bits
            this.length = this.bits.length; // set this.length = 1
        }
        return;

    }

    /**
     * Accepts a pair of UInt objects and subtracts b from a and returns the result without changing either
     * 
     * @param a first UInt object
     * @param b second UInt object
     * @return UInt object represnting the binary subtraction of a - b
     */
    public static UInt sub(UInt a, UInt b) {
        // use same template as all other static methods
        UInt temp = a.clone();
        temp.sub(b);
        return temp;
    }

    /**
     * Multiplies passed UInt object with "this" UInt object and stores the result in this.bits
     * 
     * @param u UInt object that will be multiplied with "this" UInt object
     */
    public void mul(UInt u) {
        // Using Booth's algorithm, perform multiplication
        // This one will require that you increase the length of bits, up to a maximum of X+Y.
        // Having negate() and add() will obviously be useful here.
        // Also note the Booth's always treats binary values as if they are signed,
        //   while this class is only intended to use unsigned values.
        // This means that you may need to pad your bits array with a leading 0 if it's not already long enough.

        
        // start by finding values of X and Y
        // check if this.bits[0] is 1, if it is, create a new array that is one bit longer and copy this.bits into it.
        if (this.bits[0]) {
            boolean[] bitsPlus = new boolean[this.length + 1];
            System.arraycopy(this.bits, 0, bitsPlus, 1, this.length);
            this.bits = bitsPlus;
            this.length = bitsPlus.length;
        }
        // check if u.bits[0] is 1, if it is, create a new array that is one bit longer and copy u.bits into it.
        if (u.bits[0]) {
            boolean[] bitsPlus = new boolean[u.length + 1];
            System.arraycopy(u, 0, bitsPlus, 1, u.length);
            u.bits = bitsPlus;
            u.length = bitsPlus.length;
        }

        // now we need to check if the boolean arrays are the same size, otherwise pad the smaller array with zeros

        // if the boolean arrays are equal in size, then just pad a zero in case the most significant bit of either arrays is a 1
        if (this.length == u.length) {
            // create new arrays one bit longer for both objects
            boolean[] thisExtended = new boolean[this.length + 1];
            boolean[] uExtended = new boolean[u.length + 1];

            // set the 0 index of both arrays to false
            thisExtended[0] = false;
            uExtended[0] = false;
            // use built in function to copy the original arrays to the new ones
            System.arraycopy(this.bits, 0, thisExtended, 1, this.length);
            System.arraycopy(u.bits, 0, uExtended, 1, u.length);
        }
        else if (u.length < this.length) { // if this.length is bigger, make a new boolean array of that size, copy u.bits into it, and pad the extra space with 0's
            boolean[] extended = new boolean[this.length];

            int diff = this.length - u.length; // start copying u.bits onto the new array starting at index of the difference between this.length and u.length
            System.arraycopy(u.bits, 0, extended, diff, u.length);

            u.bits = extended; // make u.bits point to extended
            u.length = extended.length;

        } else if (this.length < u.length) { // if u.length is bigger, make a new boolean array of that size, copy this.bits into it, and pad the extra space with 0's
            boolean[] extended = new boolean[u.length];

            int diff = u.length - this.length; // start copying this.bits onto the new array starting at index of the difference between u.length and this.length
            System.arraycopy(this.bits, 0, extended, diff, this.length);

            this.bits = extended; // make this.bits point to extended
            this.length = extended.length;
        }
    
        // all that code was to ensure X and Y are the same size and that the leading bit is a zero
        int totalBits = this.length + u.length + 1;
        // now we will create objects to add the arrays A, S, and P for booth algorithms
        UInt A = this.clone();
        UInt P = u.clone();
        // set the boolean arrays of the objects to their corresponding arrays, i.e. A = extend(m)
        A.bits = extend(this.bits, totalBits);
        A.length = totalBits;
        // create S after A, since it is the negated version of A
        UInt S = A.clone();
        S.negate(); 
        S.length = totalBits;
        // for P, we copy the array u.bits into a temporary array starting at the index equal to u.length, so that we don't go into the booth's algorithm extra bit
        boolean[] arrayP = new boolean[totalBits];
        System.arraycopy(u.bits, 0, arrayP, u.length, u.length);
        // update array and length for P
        P.bits = arrayP;
        P.length = arrayP.length;
        

        // start the algorithm
        int numberOfShifts = this.length; // number of shifts that need to occur
        int currentShift = 0; // the current amount of shifts that have occured

        // loop until the correct amount of shifts have occured
        while (currentShift < numberOfShifts) {
            // first examine least two significant bits of P
            int leastSignificant = P.length - 1;
            int secondLeast = P.length - 2;

            if ((P.bits[secondLeast] == false) & (P.bits[leastSignificant] == false)) {  // if both values are 0, just rightshift
                P.bits = shiftRight(P.bits);
            }
            else if (P.bits[secondLeast] && P.bits[leastSignificant]) { // if both values are 1, just rightshift
                P.bits = shiftRight(P.bits);
            }
            else if ((P.bits[secondLeast] == false) & (P.bits[leastSignificant] == true)) { // if the last two bits are 01, then add A and shift right
                P.bits = ignoreOverflowAdd(P.bits, A.bits);
                P.bits = shiftRight(P.bits);
            } 
            else if((P.bits[secondLeast] == true) && (P.bits[leastSignificant] == false)) { // if the last two bits are 10, then add S and shift right
                P.bits = ignoreOverflowAdd(P.bits, S.bits);
                P.bits = shiftRight(P.bits);
            }
            currentShift++; // increment current shift to ensure the loop breaks correctly
        }
        // now create new array to ignore the last "booth's algorithm's bit"
        boolean[] result = new boolean[totalBits - 1];
        System.arraycopy(P.bits, 0, result, 0, result.length);
        // finally, we update this.bits
        this.bits = result;
        this.length = result.length;
        return;
    }

    public static UInt mul(UInt a, UInt b) {
        // same template for all of the previous static methods
        UInt temp = a.clone();
        temp.mul(b);
        return temp;
    }

    /**
     * Add two bits together and the carry, and returns the sum of the bits and the carry for the next bits
     * 
     * @param a The first bit
     * @param b The second bit
     * @param carry The carry from previous addition
     * @return boolean array of sum and carry
     */
    private  boolean[] addBit(boolean a, boolean b, boolean carry) {

        boolean sum = a ^ b ^ carry; // XOR operation for the sum logically
        boolean newCarry = (a && b) || (b && carry) || (a && carry); // if there are two or more true's, then the carry will be true

        boolean[] answer = {sum, newCarry};

        return answer;

    }
    
    /**
     * extends the arrays A and S, to be the length of X + Y
     * 
     * @param array the original array, either m or notM
     * @param numberOfBits the length X + Y
     */
    private static boolean[] extend(boolean[] array, int numberOfBits) {
        // create new array with the desired length
        boolean[] extended = new boolean[numberOfBits];
        System.arraycopy(array, 0, extended, 0, array.length);
        return extended;
    }
    
    /**
     * Shifts a boolean array to the right and makes the new bit the most significant bit of the old array
     * 
     * @param array
     * @return boolean array that has been shifted
     */
    private boolean[] shiftRight(boolean[] array) {
        boolean newBit = array[0]; // save the most signficant bit of the old array
        boolean[] result = new boolean[array.length]; // create an array the same length as the one passed
        result[0] = newBit;
        // use built in functions to copy the passed array to the new array
        System.arraycopy(array, 0, result, 1, array.length-1);

        return result;
        
    }

    private boolean[] ignoreOverflowAdd(boolean[] array, boolean[] array2) {
        // basically copy the add method but implement for arrays instead of UInt objects

        int length = array.length;
        boolean[] result = new boolean[length]; // this.bits will be changed to this array at the end of all the addition
        boolean carry = false; // carry starts off false

        for (int i = 0; i < length; i++) {
            // predefine bits
            boolean bit1, bit2;

            bit1 = array[length - 1 - i];
            bit2 = array2[length - 1 - i];
            
            boolean[] answer = addBit(bit1, bit2, carry); // set answer to the array returned from addBit(sum and carry)

            result[length - i - 1] = answer[0]; // 0'th index is the sum. So, set the index that corresponds with the BIGGER number's length equal to the sum
            carry = answer[1]; // the new carry value is at index 1 of answer. So, update the carry for the next bit
        }
        return result;
    }
}
