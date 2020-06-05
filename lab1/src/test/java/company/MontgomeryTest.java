package company;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MontgomeryTest {

    private final BigInteger R = new BigInteger("128");
    private final int pow = 8;

    @Test
    public void multiply() {
        assertEquals(Montgomery.multiply(new BigInteger("68"), new BigInteger("57"),
                R, new BigInteger("109"), pow),
                new BigInteger("6"));
        assertEquals(Montgomery.multiply(new BigInteger("853"), new BigInteger("7821"),
                R, new BigInteger("2957"), pow),
                new BigInteger("719"));
    }

    @Test
    public void pow() {
        assertEquals(Montgomery.pow(new BigInteger("68"), new BigInteger("57"),
                R, new BigInteger("109"), pow),
                new BigInteger("39"));
        assertEquals(Montgomery.pow(new BigInteger("357"), new BigInteger("875"),
                R, new BigInteger("733"), pow),
                new BigInteger("402"));
    }
}