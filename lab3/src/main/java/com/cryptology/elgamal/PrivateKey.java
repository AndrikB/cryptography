package com.cryptology.elgamal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PrivateKey {
    private final BigInteger key;
}
