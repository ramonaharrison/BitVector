package nyc.c4q.ramonaharrison;


/**
 * Exercise: A bit vector behaves like a list of booleans; however, the booleans are encoded as individual bits
 * in a larger integer type such as int or long. This is eight times as efficient as an array of boolean,
 * each of which occupies one byte.
 *
 * Write a BitVector class. It should accept a length, in bits, at construction, and provide set(index, bit)
 * and get(index) methods, where the individual bits are represented by booleans.
 * Your class should use about length / 32 integer values or length / 64 long values to store the bits.
 *
 * Created by Ramona Harrison
 * on 11/24/15.
 */


public class BitVector {

    private final byte[] vector;
    private final int length;

    public BitVector(int length) {
        vector = new byte[(length + 7)/8];
        this.length = length;
    }

    public boolean get(int index) {
        int byteIndex = index / 8;
        int bitIndex = index % 8;

        byte section = vector[byteIndex];
        byte mask = (byte) (1 << bitIndex);

        return (section & mask) != 0;

        // return (vector[i / 8] & (1 << (i % 8))) != 0;
    }

    public void set(int index, boolean bit) {
        int byteIndex = index / 8;
        int bitIndex = index % 8;

        byte section = vector[byteIndex];
        byte mask = (byte) (1 << bitIndex);

        if (bit) {
            vector[byteIndex] = (byte) (section | mask);
        } else {
            vector[byteIndex] = (byte) (section & ~mask);
        }
    }

    public static void main(String[] args) {
        BitVector bitVector = new BitVector(24);
        System.out.println(bitVector.get(10));
        bitVector.set(10, true);
        System.out.println(bitVector.get(10));
    }

    // 01000010 00001000 10010111
    // Bitwise operators: & (and), | (or), ~ (not), ^ (xor), << (shift right), >> (shift left)
}
