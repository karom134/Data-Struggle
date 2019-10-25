public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        int char1=x;
        int char2=y;
        System.out.println(char1);
        System.out.println(char2);
        if(char1==char2+1 || char1==char2-1){
            return true;
        }
        return false;
    }
}