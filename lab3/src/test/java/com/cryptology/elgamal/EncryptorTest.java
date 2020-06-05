package com.cryptology.elgamal;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptorTest {

    @Test
    public void encryptDecryptString() {
        String testString = "I am a test string";
        Encryptor encryptor = new Encryptor(Key.generateKey(8));
        assertEquals(testString, encryptor.decryptString(encryptor.encryptString(testString)));
    }

    @Test
    public void encryptDecrypt() {
        Encryptor charEncrypt1 = new Encryptor(Key.generateKey(8));
        Encryptor encrypt2 = new Encryptor(Key.generateKey(20));
        byte[] expectedData1 = new byte[]{127};
        byte[] expectedData2 = new byte[]{15,64};

        assertArrayEquals(expectedData1, charEncrypt1.decrypt(charEncrypt1.encrypt(expectedData1)));
        assertArrayEquals(expectedData2, encrypt2.decrypt(encrypt2.encrypt(expectedData2)));
    }
}