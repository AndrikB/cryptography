package company;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class MultKarazubaTest {

    @Test
    public void karazubaMult() {
        BigInteger num = new BigInteger("722876385");

        assertEquals(MultKarazuba.karazubaMult(num, BigInteger.ONE), num);
        assertEquals(MultKarazuba.karazubaMult(BigInteger.ZERO, BigInteger.ONE), BigInteger.ZERO);

        assertEquals(MultKarazuba.karazubaMult(new BigInteger("846348765834658363523376721"), new BigInteger("53423434348768")),
                new BigInteger("45214857727728692358861251840800966229728"));
        assertEquals(MultKarazuba.karazubaMult(new BigInteger("12341634163146136152"), new BigInteger("374672651726121212154315321132")),
                new BigInteger("4624072798539651690985560306986814303740874764064"));
    }
}