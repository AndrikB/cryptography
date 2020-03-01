

public class Main {
    public static void main(String[] args) {
        String s = new String("qwerty12dawdled12dsdwqqwdbівїїїїй    йцукен");
        String password = new String("zxcvasd");
        DES d = new DES(password);
        System.out.println(s + "\t\tstart");
        s = d.encrypt(s);
        System.out.println(s + "\t\tbetween");
        s = d.decrypt(s);
        System.out.println(s + "\t\tend");
    }
}
