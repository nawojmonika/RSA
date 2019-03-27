package Algorithm;

import static Algorithm.Encryption.encrypt;
import static org.testng.Assert.*;

public class EncryptionTest {

    @org.testng.annotations.Test
    public void testEncrypt() {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIY6lDm5ybpYYrvTrLtRH5ezp5z+4IprKTYwE9L3nc1UWB3UCplFq8p2P6ovLzMTu6BsrFCSGuQRkIfRi2KpR8MCAwEAAQ==";
        String privateKey = "MIIBOgIBAAJBAIY6lDm5ybpYYrvTrLtRH5ezp5z+4IprKTYwE9L3nc1UWB3UCplF" +
                "q8p2P6ovLzMTu6BsrFCSGuQRkIfRi2KpR8MCAwEAAQJAIWDlxSkPIfiirMRperck" +
                "nQu+zYqP+bvGJsRz2Y1bv1gvuza1yM8huhg1OJ8JQtzic4jLmXm4bWmI28qFYSqn" +
                "aQIhAPAJvM4pMzpZoS1uqIv51Ff2b5ATCD0VUVh1evC9RetPAiEAjyecAO0Yu9kT" +
                "CTPvSfRNFQVoZYwbZASpqFGfHIcyL00CIQDkkCEcHdcNxLLY0/nh723mpF3uznCV" +
                "2Wqv1I3QFioxuQIgFn35s+eMwE79/68qRKtexRPBcFsQHqng4jJY1omjZ+0CIH8s" +
                "HyQgCgWt0B0LEeWlydNms4IUxK0sFzuB5y/N9H+B";
        String message = "Testowa wiadomość";
        String expectedCryptedText = "EIkL2Ts6e3pDFM2LK0tU9ramgJEbyRMQcqA4LFAffeUgK69dxBt/2bGKdPgma7Aw+5h7L3l5uWf9jXrR/uJcZA==";
        assertEquals(encrypt(message, privateKey) ,expectedCryptedText);
    }

    @org.testng.annotations.Test
    public void simpleCharEncryptionTest() {
        Encryption e = new Encryption();

        assertEquals(e.calculateCypher('P'), (char)75);
    }

    @org.testng.annotations.Test
    public void simpleCharDecryptionTest() {
        Encryption e = new Encryption();

        assertEquals(e.calculateDecrypted((char)75, 23, 187), 'P');
    }
}