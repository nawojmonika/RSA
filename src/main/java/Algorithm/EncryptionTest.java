package Algorithm;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void alamakotatest() {
        Assert.assertEquals("92 48 92 76 131 92 76 112 155 74 92", new Encryption().encrypt("ala ma kota"));
        String decryptedAsciiValues = new Encryption().decrypt("92 48 92 76 131 92 76 112 155 74 92", new BigNumber("187"), new BigNumber("7"));
        String[] asciiValues = decryptedAsciiValues.split(" ");
        String joined = Arrays.stream(asciiValues).map(s -> Integer.parseInt(s)).map(Character::toChars).map(String::valueOf).collect(Collectors.joining());
        Assert.assertEquals("ala ma kota", joined);
    }

    @Test
    void encrypt() {
        Assert.assertEquals("75", new Encryption().encrypt("P").toString());
    }

    @Test
    void decrypt() {
        Assert.assertEquals("80", new Encryption().decrypt("75", new BigNumber("187"), new BigNumber("7")));
    }

    @Test
    void modInverseFast() {
        System.out.println("modInverse");
        Assert.assertEquals(42, new Encryption().modInverse(999999999,67));
        System.out.println("modInverseFast");
        Assert.assertEquals("42", new Encryption().modInverseFast(new BigNumber("999999999"), new BigNumber("67")).toString());
    }

    @Test
    void encryptBigNumber() {
        Encryption e = new Encryption();
        e.setP(new BigNumber("13664448953568609103366505270468864803"));
        e.setQ(new BigNumber("89237930322164203062913419384979774451"));
        e.setE(new BigNumber("17"));
        Assert.assertEquals("225179981368524800000000000000000", e.encrypt("P").toString());
        Assert.assertEquals("80", e.decrypt("K", e.getPublicKey(), e.getE()).toString());
        // Assert.assertEquals("111207866638831050062527907415903071843430635396609576465044971338722814420282945860084901618441804947564356842296398677934074474799178049802718585869221815287740218796384649735000781797401108308168170885237511780480467456521018955124874824924559503208898393943344711710146772288357934922843201863705411333229", e.getPQ().toString());
    }
}