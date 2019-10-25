public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> tmp = new ArrayDeque<Character>();
        for (int idx = 0; idx < word.length(); idx++) {
            tmp.addLast(word.charAt(idx));
        }
        return tmp;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> tmp = wordToDeque(word);
        int c=tmp.size();
        for (int con = 0; con < c / 2; con++) {
            Character last = tmp.removeLast();
            Character first = tmp.removeFirst();
            if (first != last) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> tmp = wordToDeque(word);
        int c=tmp.size();
        for (int con = 0; con < c / 2; con++) {
            Character last = tmp.removeLast();
            Character first = tmp.removeFirst();
            if (! cc.equalChars(first,last)) {
                return false;
            }
        }
        return true;
    }


}
