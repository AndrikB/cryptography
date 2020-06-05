package com.cryptology.elgamal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private final byte[] a;
    private final byte[] b;
}
