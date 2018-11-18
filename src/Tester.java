
import Controller.Controller;
import java.io.IOException;

/**
 * @author Sukamto 23518017 Andreas Novian 23518002
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        Controller ct = new Controller("input1.txt","output1.txt");
        ct.start();
        ct = new Controller("input2.txt","output2.txt");
        ct.start();
        ct = new Controller("input3.txt","output3.txt");
        ct.start();
    }
}
