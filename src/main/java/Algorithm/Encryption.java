package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Encryption {
    /*
    private static int p = 173;
    private static int q = 149;
    private static int e = 3;
    private static int pq = p * q;
    private static int publicKey = (p - 1)*(q -1);
    private static int d = MathUtils.MultiplicativeInverse(e, publicKey);

    public static String encrypt(String message) {
        String cryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            cryptedMessage += MathUtils.modPow((int)message.charAt(i), e, pq) + " ";
        }

        return cryptedMessage;
    }

    public static String decrypt(String message) {
        String decryptedMessage = "";
        System.out.println(publicKey);
        System.out.println(d);
        for (String m : message.split("\\s")) {
            decryptedMessage += MathUtils.modPow(Integer.parseInt(m), d, pq) + " ";
        };

        return decryptedMessage;
    }



    public static ArrayList<Integer> splitMessageToIntChunks(String message) {
        String[] dividedMessage = message.split("(?<=\\G.{3})");

        ArrayList<Integer> dividedMessagesInInt = new ArrayList<>();

        for (String m : dividedMessage) {
            int valueOfDividedMessage = 0;
            int charIndex = 0;
            for (int power = m.length() - 1; power >= 0 ; power--, charIndex++) {
                valueOfDividedMessage += (int)m.charAt(charIndex) * Math.pow(27, power);
            }
            dividedMessagesInInt.add(valueOfDividedMessage);
        }

        return dividedMessagesInInt;
    }
    */

}
