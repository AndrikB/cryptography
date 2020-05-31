import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.*;
import static java.math.BigInteger.*;

public class Main {


    static public BigInteger genPr2(int bits) {
        SecureRandom ran = new SecureRandom();

        return (BigInteger.probablePrime(bits, ran));
    }

    // Utility function to store prime factors of a number
    static void findPrimeFactors(HashSet<BigInteger> s, BigInteger n) {
        // Print the number of 2s that divide n
        while (n.mod(TWO).equals(ZERO)) {
            s.add(TWO);
            n = n.divide(TWO);
        }
        System.out.println("here1" + s);
        // n must be odd at this point. So we can skip
        // one element (Note i = i +2)

        BigInteger count = n.sqrt();
        System.out.println("sqrt=" + count);
        ret:
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(count) < 0; i = i.nextProbablePrime()) {

            // While i divides n, print i and divide n
            while (n.mod(i).equals(ZERO)) {
                s.add(i);
                n = n.divide(i);
                System.out.println("here2 n=" + n + " s=" + s);
                if (n.isProbablePrime(n.bitCount())) break ret;
                System.out.println("end test");
            }
            if (i.mod(BigInteger.valueOf((long) 1e5)).equals(ONE))
                System.out.println("cur= "+i);
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n.compareTo(TWO) > 0) {
            s.add(n);
        }
    }

    // Function to find smallest primitive root of n
    static BigInteger findPrimitive(BigInteger p) {


        BigInteger phi = p.subtract(ONE);


        HashSet<BigInteger> s = new HashSet<>();


        // Find value of Euler Totient function of n
        // Since n is a prime number, the value of Euler
        // Totient function is n-1 as there are n-1
        // relatively prime numbers.

        // Find prime factors of phi and store in a set
        findPrimeFactors(s, phi);
        System.out.println(s);

        // Check for every number from 2 to phi
        for (BigInteger r = TWO; r.compareTo(phi) < 0; r = r.add(ONE)) {
            // Iterate through all prime factors of phi.
            // and check if we found a power with value 1
            boolean flag = false;
            for (BigInteger a : s) {

                // Check if r^((phi)/primefactors) mod n
                // is 1 or not
                if (r.modPow(phi.divide(a), p).equals(ONE)) {
                    flag = true;
                    break;
                }
            }

            // If there was no power with value 1.
            if (!flag) {
                return r;
            }
        }

        // If no primitive root found
        return BigInteger.valueOf(-1);
    }/**/


    /*public static long modPow(long value, long power, long mod) {
        long res = 1;
        value = value % mod;
        while (power > 0) {
            if (power % 2 == 1) {
                res = ((res * value) % mod);
            }
            power = power >> 1; // y = y/2
            value = (value * value) % mod;
        }

        return res;
    }


    static public long genPr2(int bits) {
        SecureRandom ran = new SecureRandom();

        BigInteger c = new BigInteger(bits, ran);

        while (!c.isProbablePrime(bits)) {
            c = c.subtract(ONE);
        }

        return (c.longValue());
    }

    // Utility function to store prime factors of a number
    static void findPrimeFactors(HashSet<Long> s, long n) {
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            s.add(2L);
            n = n / 2;
        }

        // n must be odd at this point. So we can skip
        // one element (Note i = i +2)
        for (long i = 3; i < sqrt(n); i = i + 2) {

            // While i divides n, print i and divide n
            while (n % i == 0) {
                s.add(i);
                n = n / i;
                System.out.println(s + " " + n);
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2) {
            s.add(n);
        }
    }

    // Function to find random primitive root of n
    static long findPrimitive(long n) {
        HashSet<Long> s = new HashSet<>();


        // Find value of Euler Totient function of n
        // Since n is a prime number, the value of Euler
        // Totient function is n-1 as there are n-1
        // relatively prime numbers.
        long phi = n - 1;

        // Find prime factors of phi and store in a set
        findPrimeFactors(s, phi);
        System.out.println(s);
        int count = 0;
        // Check for every number from 2 to phi
        while (true) {
            // Iterate through all prime factors of phi.
            // and check if we found a power with value 1
            count++;
            long r = (long) (random() * phi);
            boolean flag = false;
            for (Long a : s) {
                // Check if r^((phi)/primefactors) mod n
                // is 1 or not
                if (modPow(r, phi / a, n) == 1) {
                    flag = true;
                    break;
                }
            }

            // If there was no power with value 1.
            if (!flag) {
                System.out.println("count" + count);
                return r;
            }
        }
    }*/

    public static void main(String[] arr) {
        BigInteger a = genPr2(1000);
        String s = "Hello, World! tratata I m here be two 1";
        System.out.println(s);
        System.out.println(a);
        System.out.println(findPrimitive(a));
    }
}

