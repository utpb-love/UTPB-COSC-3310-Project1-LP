import java.util.Arrays;

public class UInt {

    protected boolean[] bits;
    protected int length;

    public UInt() {

    }

    public UInt(UInt toClone) {
        this.length = toClone.length;
        this.bits = Arrays.copyOf(toClone.bits, this.length);
    }

    public UInt(int i) {
        length = (int)(Math.ceil(Math.log(i)/Math.log(2.0)) + 1);
        bits = new boolean[length];

        for (int b = length-1; b >= 0; b--) {
            bits[b] = i % 2 == 1;
            i = i >> 1;
            /*int p = 0;
            while (Math.pow(2, p) < i) {
                p++;
            }
            p--;
            bits[p] = true;
            i -= Math.pow(2, p);*/
        }
    }

    @Override
    public UInt clone() {
        return new UInt(this);
    }

    public static UInt clone(UInt u) {
        return new UInt(u);
    }

    public int toInt() {
        int t = 0;
        for (int i = 0; i < length; i++) {
            t = t + (bits[i] ? 1 : 0);
            t = t << 1;
        }
        return t >> 1;
    }

    @Override
    public String toString() {
        String s = "0b";
        for (int i = 0; i < length; i++) {
            s += bits[i] ? "1" : "0";
        }
        return s;
    }

    public static int toInt(UInt u) {
        return 0;
    }

    public void and(UInt u) {
        return;
    }

    public static UInt and(UInt a, UInt b) {
        return null;
    }

    public void or(UInt u) {
        return;
    }

    public static UInt or(UInt a, UInt b) {
        return null;
    }

    public void xor(UInt u) {
        return;
    }

    public static UInt xor(UInt a, UInt b) {
        return null;
    }

    public void add(UInt u) {
        return;
    }

    public static UInt add(UInt a, UInt b) {
        return null;
    }

    public void sub(UInt u) {
        return;
    }

    public static UInt sub(UInt a, UInt b) {
        return null;
    }

    public void mul(UInt u) {
        return;
    }

    public static UInt mul(UInt a, UInt b) {
        return null;
    }
}
