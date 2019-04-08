package Algorithm;

import java.util.Arrays;
import java.util.Random;

public class MathUtils {

    public static int[] add(int[] first, int[] second) {
        final int numberOf8Bits = first.length > second.length ? first.length * 32 : second.length * 32;
        final int maxNumOfChunks = first.length > second.length ? first.length * 2 : second.length * 2;
        final int[] addedBits = new int [maxNumOfChunks];

        boolean carry = false;
        int currentChunk = 0;
        int currentBitFieldToOperate = 0;
        int bitInChunk = 0;
        for (int currentBit = 0 ; currentBit < numberOf8Bits; currentBit++) {
            if (bitInChunk == 31) {
                addedBits[currentChunk] = currentBitFieldToOperate;
                currentChunk++;
                if (currentChunk + 1 > first.length || currentChunk + 1> second.length) {
                    break;
                }
                currentBitFieldToOperate = 0;
                bitInChunk = 0;
            }
            if (isBitSet(first[currentChunk], bitInChunk) && isBitSet(second[currentChunk], bitInChunk)) {
                if (carry) {
                    carry = true;
                    currentBitFieldToOperate = setBit(currentBitFieldToOperate, bitInChunk);
                } else {
                    currentBitFieldToOperate = turnOffBit(currentBitFieldToOperate, bitInChunk);
                    carry = true;
                }
            }
            if (isBitSet(first[currentChunk], bitInChunk) && !isBitSet(second[currentChunk], bitInChunk)) {
                if (carry) {
                    currentBitFieldToOperate = turnOffBit(currentBitFieldToOperate, bitInChunk);
                    carry = true;
                } else {
                    currentBitFieldToOperate = setBit(currentBitFieldToOperate, bitInChunk);
                    carry = false;
                }
            }
            if (!isBitSet(first[currentChunk], bitInChunk) && isBitSet(second[currentChunk], bitInChunk)) {
                if (carry) {
                    currentBitFieldToOperate = turnOffBit(currentBitFieldToOperate, bitInChunk);
                    carry = true;
                } else {
                    currentBitFieldToOperate = setBit(currentBitFieldToOperate, bitInChunk);
                    carry = false;
                }
            }
            if (!isBitSet(first[currentChunk], bitInChunk) && !isBitSet(second[currentChunk], bitInChunk)) {
                if (carry) {
                    currentBitFieldToOperate = setBit(currentBitFieldToOperate, bitInChunk);
                    carry = false;
                } else {
                    currentBitFieldToOperate = turnOffBit(currentBitFieldToOperate, bitInChunk);
                    carry = false;
                }
            }
            bitInChunk++;
        }
        return addedBits;
    }

    static int turnOffBit(int number, int bit) {
        if (bit <= 0)
            return number;

        return (number & ~(1 << (bit)));
    }

    public static int setBit(int target, int bit) {
        // Create mask
        int mask = 1 << bit;
        // Set bit
        return target | mask;
    }

    public static boolean isBitSet(int number, int bit) {
        return (number & (1 << (bit))) >= 1;
    }
}
