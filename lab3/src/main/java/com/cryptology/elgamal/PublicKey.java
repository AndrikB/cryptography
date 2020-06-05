package com.cryptology.elgamal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PublicKey {
    private final BigInteger keyY;
    private final BigInteger keyG;
    private final BigInteger keyP;
}
