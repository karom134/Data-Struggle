import java.util.ArrayList;
import java.util.List;

public class Hello {
    public static void main(String args[]) {
        System.out.println("Hello, World!");
        List<Integer> tmp=new ArrayList<>();
        for (int pass=100; pass >= 4; pass--) {
            tmp.add(pass);
        }
        System.out.println(tmp.size());
    }
}