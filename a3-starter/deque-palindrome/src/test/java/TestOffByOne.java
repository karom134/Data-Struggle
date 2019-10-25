/* IMPORTANT:
 * Uncomment this file after you have implemented OffByOne */
 
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    public static void main(String args[]) {
        System.out.println(offByOne.equalChars('a', 'b'));
        System.out.println(offByOne.equalChars('r', 'q'));
        System.out.println(offByOne.equalChars('a', 'e'));
        System.out.println(offByOne.equalChars('z', 'a'));
        System.out.println(offByOne.equalChars('a', 'a'));
        System.out.println(offByOne.equalChars('b', 'a'));
        System.out.println(offByOne.equalChars('B', 'a'));
    }
}
