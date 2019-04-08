package Algorithm;

import java.util.Random;

public class Decryption {
    private int p = 11;
    private int q = 17;
    private int e = calculateRelativelyPrime(getEulerValueFromPandQ());
    public static String encrypt(String message, String key) {

        return "";
    }

    public int getPrivateKey() {
        return getP() * getQ();
    }

    public int getEulerValueFromPandQ() {
        return (getP() - 1) * (getQ() - 1);
    }

    public int calculateRelativelyPrime(int n2) {
        int gcd = 1;

        Random generator = new Random();
        int randomNumber = 0;
        do {
            randomNumber = generator.nextInt(n2);
            int n1 = randomNumber;
            for (int i = 1; i <= n1 && i <= n2; ++i) {
                // Checks if i is factor of both integers
                if (n1 % i == 0 && n2 % i == 0)
                    gcd = i;
            }
        }while (gcd != 1);

        System.out.println(randomNumber);

        return randomNumber;
    }

    public char calculateCypher(char character) {
        int charAsciiValue = (int)character;
        return (char)modPow(charAsciiValue, e, getPrivateKey());
    }

    public char calculateDecrypted(char character, int decryptionKey, int privateKey) {
        int charAsciiValue = (int)character;
        return (char)modPow(charAsciiValue, decryptionKey, privateKey);
    }

    public int modPow(int value, int exponent, int moduloTo) {
        int encryptedCharAsciiValue = 1;
        for (int e = 1 ; e <= exponent ; e++) {
            encryptedCharAsciiValue = (encryptedCharAsciiValue * value) % moduloTo;
        }
        return encryptedCharAsciiValue;
    }

    public int getDecryptionKey() {
        return 26; // Add calculation for that
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }
}
