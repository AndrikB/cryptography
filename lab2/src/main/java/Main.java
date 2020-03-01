import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        String s = new String("qwerty12dawdled12dsdwqqwdbівїїїїй    йцукен");
        String password = new String("zxcvasd");
        DES d = new DES(password);
        System.out.println(s + "\t\tstart");
        Pair<String,String> p = d.encrypt(s);
        System.out.println(p.getValue() + "\t\tbetween");
        s = d.decrypt(p);
        System.out.println(s + "\t\tend");
    }
}
