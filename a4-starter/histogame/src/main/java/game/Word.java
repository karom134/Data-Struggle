package game;

import histogram.Histogram;
import histogram.SimpleHistogram;
import java.util.Iterator;

public class Word implements Formable<Word>, Comparable<Word> {
    private Histogram<Character> hist;
    private String word;
    public Word(String word){
        //https://stackoverflow.com/questions/10006165/converting-string-to-character-array-in-java
        Character[] charObjectArray = word.toLowerCase().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        hist=new SimpleHistogram<>(charObjectArray);
        this.word=word.toLowerCase();
    }
    public String getWord(){
        return this.word;
    }
    public Histogram<Character> getHistogram(){
        return hist;
    }

    @Override
    public boolean canForm(Word other) {
        // TODO: Fix me
        Histogram<Character> tmp = other.getHistogram();
        for (Iterator<Character> it = tmp.iterator(); it.hasNext(); ) {
            Character ele = it.next();
            if (tmp.getCount(ele) > hist.getCount(ele)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Word o) {
        // TODO: Fix me
        if(this.word.length()<o.getWord().length()){
            return -1;
        }
        if(this.word.length()==o.getWord().length()){
            for(int idx=0;idx<this.word.length();idx++){
                int alp1=this.word.charAt(idx);
                int alp2=o.getWord().charAt(idx);
                if(alp1>alp2) {
                    return -1;
                }
                else if(alp1==alp2){
                    continue;
                }
                else{
                    return 1;
                }
            }
        }
        if(this.word.compareToIgnoreCase(o.getWord())==0){
            return 0;
        }
        return 1;
    }
    public String toString(){
        return hist.toString();
    }
}
