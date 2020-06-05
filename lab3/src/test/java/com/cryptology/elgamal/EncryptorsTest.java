package com.cryptology.elgamal;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptorsTest {

    @Test
    public void encryptDecryptString() {
        String testString = "I am a test string";
        Encryptors encryptors = new Encryptors(Key.generateKey(8));
        assertEquals(testString, encryptors.decryptString(encryptors.encryptString(testString)));
    }

    @Test
    public void encryptDecrypt() {
        Encryptors charEncrypt1 = new Encryptors(Key.generateKey(8));
        Encryptors encrypt2 = new Encryptors(Key.generateKey(20));
        byte[] expectedData1 = new byte[]{127};
        byte[] expectedData2 = new byte[]{15,64};

        assertArrayEquals(expectedData1, charEncrypt1.decrypt(charEncrypt1.encrypt(expectedData1)));
        assertArrayEquals(expectedData2, encrypt2.decrypt(encrypt2.encrypt(expectedData2)));
    }
}