public class OffByN implements CharacterComparator{
    private int n;
    public OffByN(int N){
        this.n=N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int char1=Character.getNumericValue(x);
        int char2=Character.getNumericValue(y);
        if(char1==char2+this.n || char1==char2-this.n){
            return true;
        }
        return false;
    }
}