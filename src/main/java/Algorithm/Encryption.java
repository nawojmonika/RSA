package Algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class Encryption {
    public void init(LargeInteger q, LargeInteger p, LargeInteger e) {
        this.q = q;
        this.publicKey = this.calculatePublicKey(this.p, this.q);
        LargeInteger one = new LargeInteger(new byte[]{(byte) 1});
        phiN = (p.subtract(one)).multiply(q.subtract(one));
        d = e.modularExp(one.negate(), phiN);
    }

    LargeInteger p;
    LargeInteger q;
    LargeInteger publicKey;
    LargeInteger e;
    LargeInteger d;
    LargeInteger phiN;

    public LargeInteger getD(){
        return this.d;
    }

    public LargeInteger getPublicKey(){
        return this.publicKey;
    }

    public Encryption() {
        Random random = new Random();
        LargeInteger one = new LargeInteger(new byte[]{(byte) 1});
        LargeInteger two = new LargeInteger(new byte[]{(byte) 2});

        do {
            this.p = new LargeInteger(256, random);
            this.q = new LargeInteger(256, random);
            publicKey = p.multiply(q);
            phiN = (p.subtract(one)).multiply(q.subtract(one));
            e = new LargeInteger(512, random);
        } while(phiN.subtract(e).isNegative() || !phiN.XGCD(e)[0].subtract(two).isNegative());

        d = e.modularExp(one.negate(), phiN);
    }

    public byte[] encrypt(byte[] message, int chunkSize) {
        ArrayList<LargeInteger> cypherText = new ArrayList<>();
        for (int chunk = 0; chunk < message.length; chunk+=chunkSize) {
            byte[] chunkToCrypt = Arrays.copyOfRange(message, chunk, chunk+chunkSize);
            cypherText.add(new LargeInteger(chunkToCrypt).modularExp(this.e, this.publicKey));
        }
        byte[] messageToReturn = new byte[(chunkSize + 1) * cypherText.size()];
        int currentByte = 0;
        for (LargeInteger cypher : cypherText) {
            for (byte cypherByte : cypher.getVal()) {
                messageToReturn[currentByte] = cypherByte;
                currentByte++;
            }
            if (cypher.getVal().length == chunkSize) {
                messageToReturn[currentByte] = 0;
                currentByte++;
            }
        }
        return messageToReturn;
    }

    public LargeInteger calculatePublicKey(LargeInteger p, LargeInteger q) {
        return p.multiply(q);
    }
}
