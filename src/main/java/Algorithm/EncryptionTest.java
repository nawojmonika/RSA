package Algorithm;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void encrypt() {
        Encryption encryption = new Encryption();
        byte[] messageToEncrypt = new byte[]{'P'};
        Assert.assertArrayEquals(new byte[]{75}, encryption.encrypt(messageToEncrypt, 64));
    }

    @Test
    void decryption() {
        Encryption encryption = new Encryption();
        byte[] messageToEncrypt = new byte[]{'P'};
        byte[] encryptedMessage = encryption.encrypt(messageToEncrypt, 64);
        Decryption decryption = new Decryption(encryption.getD(), encryption.getPublicKey());
        System.out.println(Arrays.toString(decryption.decrypt(encryptedMessage, 64)));
        Assert.assertArrayEquals(messageToEncrypt, decryption.decrypt(encryptedMessage, 64));
    }

    @Test
    void decryptionAlaMaKota() {
        Encryption encryption = new Encryption();
        byte[] messageToEncrypt = new byte[]{'a','l', 'a', 'm', 'a', 'k', 'o', 't','a'};
        byte[] encryptedMessage = encryption.encrypt(messageToEncrypt, 64);
        Decryption decryption = new Decryption(encryption.getD(), encryption.getPublicKey());
        System.out.println(Arrays.toString(decryption.decrypt(encryptedMessage, 64)));
        Assert.assertArrayEquals(messageToEncrypt, decryption.decrypt(encryptedMessage, 64));
    }

    @Test
    void decryptionFileTest() throws IOException {
        Path fileLocation = Paths.get("/home/grayrattus/keku.png");
        byte[] messageToEncrypt = Files.readAllBytes(fileLocation);
        Encryption encryption = new Encryption();
        byte[] encryptedMessage = encryption.encrypt(messageToEncrypt, 64);

        Decryption decryption = new Decryption(encryption.getD(), encryption.getPublicKey());
        Assert.assertArrayEquals(messageToEncrypt, decryption.decrypt(encryptedMessage, 64));
    }
}