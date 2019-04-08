package Algorithm;

import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void addSimple() {
        int[] binaryHugeNumber = new int[] {30, 0};
        int[] binaryHugeNumber2 = new int[] {0, 0};
        assertArrayEquals(new int[]{30,0, 0, 0}, MathUtils.add(binaryHugeNumber, binaryHugeNumber2));
    }

    @Test
    void addSimple2() {
        int[] binaryHugeNumber = new int[] {30, 0};
        int[] binaryHugeNumber2 = new int[] {30, 0};
        assertArrayEquals(new int[]{60,0, 0, 0}, MathUtils.add(binaryHugeNumber, binaryHugeNumber2));
    }

    @Test
    void addSimple3() {
        int[] binaryHugeNumber = new int[] {30, 30};
        int[] binaryHugeNumber2 = new int[] {0, 0};
        assertArrayEquals(new int[]{30,30, 0, 0}, MathUtils.add(binaryHugeNumber, binaryHugeNumber2));
    }

    @Test
    void addOverflow() {
        int[] binaryHugeNumber = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] binaryHugeNumber2 = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println(Integer.MAX_VALUE);
        assertArrayEquals(new int[]{Integer.MAX_VALUE - 1,Integer.MAX_VALUE, 0, 0}, MathUtils.add(binaryHugeNumber, binaryHugeNumber2));
    }

    @Test
    void turnOffBit() {
        int thirty = 0b00011110;
        assertEquals(0b00001110, MathUtils.turnOffBit(thirty, 4));
        assertEquals(0b00011100, MathUtils.turnOffBit(thirty, 1));
    }

    @Test
    void setBit() {
        int thirty = 0b00011110;
        assertEquals(0b00011111, MathUtils.setBit(thirty, 0));
        assertEquals(0b00111110, MathUtils.setBit(thirty, 5));
    }

    @Test
    void isBitSet() {
        int thirty = 0b00011110;
        assertEquals(false, MathUtils.isBitSet(thirty, 0));
        assertEquals(true, MathUtils.isBitSet(thirty, 1));
    }
}