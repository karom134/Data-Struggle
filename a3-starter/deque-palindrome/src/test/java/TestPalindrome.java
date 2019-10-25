 /*IMPORTANT: Uncomment this file after you're done with the Deque interface and wordToDeque 
 */
 
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    private static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    static CharacterComparator offBy5 = new OffByN(5);
    public static void main(String args[]){
        System.out.println(palindrome.isPalindrome("abcdefgfedcba"));
        System.out.println(palindrome.isPalindrome("racecar"));
        System.out.println(palindrome.isPalindrome("RacecAR"));
        System.out.println(palindrome.isPalindrome("ijrgoihlsjfdljfweojfl"));
        System.out.println(palindrome.isPalindrome("noob"));
        System.out.println(palindrome.isPalindrome("flake", offBy5));
    }
}

