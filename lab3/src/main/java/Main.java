
import com.cryptology.elgamal.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Key key =  Key.generateKey(16);
        Encryptor encryptor = new Encryptor(key);

        String plaintext = "Hello, World! tratata I m here be two 1";
        System.out.println(encryptor.decryptString(encryptor.encryptString(plaintext)));
    }
}
