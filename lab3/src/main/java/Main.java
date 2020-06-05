import com.cryptology.elgamal.Encryptors;
import com.cryptology.elgamal.Key;
import com.cryptology.elgamal.Message;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Key key = Key.generateKey(16);
        Encryptors encryptors = new Encryptors(key);

        String plaintext = "Hello, World! tratata I m here be two 1";
        List<Message> encrypted = encryptors.encryptString(plaintext);
        String decrypted = encryptors.decryptString(encrypted);
        System.out.println(decrypted);
    }
}
