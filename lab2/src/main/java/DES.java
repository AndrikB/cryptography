import sun.security.util.BitArray;

import static p.Helper.*;

@SuppressWarnings("fallthrough")
public class DES {
    private BitArray newKey = new BitArray(64);

    DES(String keyS) {

        if (keyS.length() != 7)
            throw new IllegalArgumentException("Key has incorrect size");
        BitArray key = new BitArray(56, keyS.getBytes());
        for (int i = 0; i < 8; i++) {
            boolean b1 = true;
            for (int j = 0; j < 7; j++) {
                newKey.set(i * 8 + j, key.get(i * 7 + j));
                b1 = b1 ^ key.get(i * 7 + j);
            }
            newKey.set(i * 7 + 7, b1);
        }


    }

    //split block 48 bit to 8 block 6bit
    private BitArray[] splitBlock(BitArray E) {
        BitArray[] blocks = new BitArray[8];

        for (int i = 0; i < 8; i++) {
            blocks[i] = new BitArray(6);
            for (int j = 0; j < 6; j++) {
                blocks[i].set(j, E.get(i * 6 + j));
            }
        }
        return blocks;
    }


    private BitArray feistelHelper(BitArray R, BitArray K) {
        //System.out.println(R.toString() + "\t\t" + "input feistelHelper");
        BitArray result = new BitArray(32);
        BitArray E = new BitArray(48);
        for (int i = 0; i < 48; i++) {
            E.set(i, R.get(extensionTable[i]));
        }

        for (int i = 0; i < 48; i++) {
            E.set(i, E.get(i) ^ K.get(i));
        }

        BitArray[] blocks = splitBlock(E);
        BitArray tempRes = new BitArray(32);
        BitArray block;
        for (int i = 0; i < 8; i++) {
            block = blocks[i];
            int a = toInt(block.get(0)) * 2 + toInt(block.get(5));
            int b = toInt(block.get(1)) * 8 + toInt(block.get(2)) * 4 + toInt(block.get(3)) * 2 + toInt(block.get(4));
            int c = blockTransformationTables[i][a][b];
            boolean[] arr = new boolean[4];
            for (int j = 0; j < 4; j++) {
                arr[3 - j] = (c % 2 == 1);
                c = (c - c % 2) / 2;
            }
            for (int j = 0; j < 4; j++) {
                tempRes.set(i * 4 + j, arr[j]);
            }

        }
        for (int i = 0; i < 32; i++) {
            result.set(i, tempRes.get(replaseTableP[i]));
        }
        return result;
    }

    public void leftShift(BitArray bitArray, int a) {
        while (a != 0) {
            a--;
            boolean b = bitArray.get(0);
            for (int i = 0; i < 27; i++) {
                bitArray.set(i, bitArray.get(i + 1));
            }
            bitArray.set(27, b);
        }
    }

    public void rightShift(BitArray bitArray, int a) {
        while (a != 0) {
            a--;
            boolean b = bitArray.get(27);
            for (int i = 27; i > 0; i--) {
                bitArray.set(i, bitArray.get(i - 1));
            }
            bitArray.set(0, b);
        }
    }

    //create key from C and D array
    private BitArray getKey(BitArray C, BitArray D) {
        BitArray result = new BitArray(48);
        for (int i = 0; i < 48; i++) {
            if (keyCreateTable[i] < 28)
                result.set(i, C.get(keyCreateTable[i]));
            else result.set(i, D.get(keyCreateTable[i] - 28));
        }
        return result;
    }


    private BitArray feistelEncrypt(BitArray b) {
        BitArray L = new BitArray(32), R = new BitArray(32), temp;
        for (int i = 0; i < 32; i++) {
            L.set(i, b.get(i));
            R.set(i, b.get(i + 32));
        }

        BitArray C = new BitArray(28), D = new BitArray(28);
        for (int i = 0; i < 28; i++) {
            C.set(i, newKey.get(replaseTableC[i]));
            D.set(i, newKey.get(replaseTableD[i]));
        }

        for (int i = 0; i < 16; i++) {
            leftShift(C, shiftTable[i]);
            leftShift(D, shiftTable[i]);
            BitArray key = getKey(C, D);
            temp = new BitArray(R.length(), R.toByteArray());
            BitArray f = feistelHelper(R, key);
            for (int j = 0; j < 32; j++) {
                R.set(j, L.get(j) ^ f.get(j));
            }
            L = temp;

        }

        for (int i = 0; i < 32; i++) {
            b.set(i, L.get(i));
            b.set(i + 32, R.get(i));
        }

        return b;
    }

    private BitArray feistelDecrypt(BitArray b) {
        BitArray L = new BitArray(32), R = new BitArray(32), temp;
        for (int i = 0; i < 32; i++) {
            L.set(i, b.get(i));
            R.set(i, b.get(i + 32));
        }

        BitArray C = new BitArray(28), D = new BitArray(28);
        for (int i = 0; i < 28; i++) {
            C.set(i, newKey.get(replaseTableC[i]));
            D.set(i, newKey.get(replaseTableD[i]));
        }

        for (int i = 0; i < 16; i++) {
            BitArray key = getKey(C, D);

            temp = new BitArray(L.length(), L.toByteArray());
            BitArray f = feistelHelper(L, key);
            for (int j = 0; j < 32; j++) {
                L.set(j, R.get(j) ^ f.get(j));
            }
            R = temp;
            rightShift(C, shiftTable[15 - i]);
            rightShift(D, shiftTable[15 - i]);
        }

        for (int i = 0; i < 32; i++) {
            b.set(i, L.get(i));
            b.set(i + 32, R.get(i));
        }

        return b;
    }

    private void replaseS(BitArray from, BitArray to) {
        for (int i = 0; i < 64; i++) {
            to.set(i, from.get(replaseTable1[i]));
        }
    }

    private void replaseB(BitArray from, BitArray to) {
        for (int i = 0; i < 64; i++) {
            to.set(replaseTable1[i], from.get(i));
        }
    }

    public String encrypt(String s) {
        while (s.length() % 8 != 0)
            s = s.concat(String.valueOf('\0'));
        String result = "";

        BitArray b = new BitArray(64);
        BitArray bitArray;

        for (int i = 0; i < s.length() / 8; i++) {
            bitArray = new BitArray(64, s.substring(i * 8, (i + 1) * 8).getBytes());
            replaseS(bitArray, b);
            feistelEncrypt(b);
            replaseB(b, bitArray);

            result = result.concat(new String(bitArray.toByteArray()));
        }
        return result;
    }

    public String decrypt(String s) {
        if (s.length() % 8 != 0)
            throw new IllegalArgumentException("s has incorrect size");

        String result = "";

        BitArray b = new BitArray(64);
        BitArray bitArray;
        for (int i = 0; i < s.length() / 8; i++) {
            bitArray = new BitArray(64, s.substring(i * 8, (i + 1) * 8).getBytes());
            replaseS(bitArray, b);
            feistelDecrypt(b);
            replaseB(b, bitArray);

            result = result.concat(new String(bitArray.toByteArray()));
        }

        return result;
    }

}
