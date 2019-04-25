package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Decryption {

    LargeInteger publicKey;
    LargeInteger d;

    public Decryption(LargeInteger d, LargeInteger publicKey) {
        this.d = d;
        this.publicKey = publicKey;
    }

    public byte[] decrypt(byte[] cypher, int chunkSize) {
        ArrayList<LargeInteger> decryptedText = new ArrayList<>();
        for (int chunk = 0; chunk < cypher.length; chunk+=chunkSize + 1) {
            byte[] chunkToDecrypt = Arrays.copyOfRange(cypher, chunk, chunk+chunkSize + 1);
            if (chunkToDecrypt[chunkSize] == 0) {
                chunkToDecrypt = Arrays.copyOfRange(chunkToDecrypt, 0, chunkSize);
            }
            decryptedText.add(new LargeInteger(chunkToDecrypt).modularExp(this.d, this.publicKey));
        }
        byte[] messageToReturn = new byte[(chunkSize + 1) * decryptedText.size()];
        int currentByte = 0;
        for (int decryptedIndex = 0 ; decryptedIndex < decryptedText.size(); decryptedIndex++) {
            for (byte cypherByte : decryptedText.get(decryptedIndex).getVal()) {
                messageToReturn[currentByte] = cypherByte;
                currentByte++;
            }
        }
        byte[] newMessageToReturn = new byte[0];
        for (int i = messageToReturn.length - 1; i >= 0 ; i--) {
            if (messageToReturn[i] == 0) {
                newMessageToReturn = Arrays.copyOfRange(messageToReturn, 0, i);
            } else {
                break;
            }
        }
        return newMessageToReturn;
    }
}
