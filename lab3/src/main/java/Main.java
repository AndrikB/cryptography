
import com.cryptology.elgamal.*;

public class Main {
    public static void main(String[] args) {
        Key key =  Key.generateKey(16);
        Encryptors encryptors = new Encryptors(key);

        String plaintext = "Hello, World! tratata I m here be two 1";
        System.out.println(encryptors.decryptString(encryptors.encryptString(plaintext)));
    }
}
