package Algorithm;

public class Encryption {
    private int p = 11;
    private int q = 17;
    public static String encrypt(String message, String key) {

        return "";
    }

    public int getPrivateKey() {
        return getP() * getQ();
    }

    public int getEulerValueFromPandQ() {
        return (getP() - 1) * (getQ() - 1);
    }

    public int calculateRelativelyPrime() {
        return 7; // this is from example, needs to be calculated
    }

    public char calculateCypher(char character) {
        int charAsciiValue = (int)character;
        return (char)modPow(charAsciiValue, calculateRelativelyPrime(), getPrivateKey());
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
}
