package company;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ExtendedEuclidTest {

    @Test
    public void extendedAlgorithm() {
        BigInteger[] gcd1 = ExtendedEuclid.extendedAlgorithm(new BigInteger(String.valueOf(84)), new BigInteger(String.valueOf(275)));
        assertEquals(gcd1[0], BigInteger.ONE);
        assertEquals(gcd1[1],  new BigInteger("-36"));

        BigInteger[] gcd2 = ExtendedEuclid.extendedAlgorithm(new BigInteger(String.valueOf(100)), new BigInteger(String.valueOf(100)));
        assertEquals(gcd2[0], new BigInteger("100"));
        assertEquals(gcd2[1], BigInteger.ZERO);

        BigInteger[] gcd3 = ExtendedEuclid.extendedAlgorithm(new BigInteger(String.valueOf(51)), new BigInteger(String.valueOf(17)));
        assertEquals(gcd3[0], new BigInteger("17"));
        assertEquals(gcd3[1], BigInteger.ZERO);

        BigInteger[] gcd4 = ExtendedEuclid.extendedAlgorithm(new BigInteger(String.valueOf(35)), new BigInteger(String.valueOf(20)));
        assertEquals(gcd4[0], new BigInteger("5"));
        assertEquals(gcd4[1], new BigInteger("-1"));
    }
}