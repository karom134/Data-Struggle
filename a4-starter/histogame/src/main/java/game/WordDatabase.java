package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// HINT(s):
//   To read from src/resources/<filename>
//   InputStream is = getClass().getClassLoader().getResourceAsStream(filename);

public class WordDatabase implements IDatabase {
    private List<Word> database=new ArrayList<>();

    public WordDatabase(String filename) throws IOException {
        //http://zetcode.com/java/readtext/
        List<String> lines = Files.readAllLines(Paths.get(filename));

        for (String line : lines){
            Word tmp=new Word(line);
            add(tmp);
        }
    }
    public WordDatabase(List<Word> input){
        database=input;
    }

    @Override
    public void add(Word w) {
        // TODO:
        database.add(w);
    }

    @Override
    public void remove(Word w) {
        // TODO:
        database.remove(w);
    }

    @Override
    public List<Word> getWordWithLength(int l) {
        // TODO:
        List<Word> keep=new ArrayList<>();
        for(Word ele:database){
            if(ele.getWord().length()==l){
                keep.add(ele);
            }
        }
        return keep;
    }

    @Override
    public List<Word> getAllSubWords(Word w, int minLen) {
        // TODO:
        List<Word> keep=new ArrayList<>();
        for(int i=minLen;i<=w.getWord().length();i++){
            List<Word> tmp=getWordWithLength(i);
            for(Word ele:tmp){
                if(w.canForm(ele)){
                    keep.add(ele);
                }
            }
        }
        return keep;
    }

    @Override
    public boolean contains(Word o) {
        // TODO:
        for(Word ele:database){
            if(ele.getWord().equals(o.getWord())){
                return true;
            }
        }
        return false;
    }
    public Integer count(){
        Integer count=0;
        for(Word w: database){
            count++;
        }
        return count;
    }
    public Word getIndex(Integer idx){
        return database.get(idx);
    }
    public static void main(String[] arg) throws IOException {
        WordDatabase test=new WordDatabase("..\\histogame\\src\\main\\resources\\linuxwords.txt");
        List<Word> tester=new ArrayList<>();
        tester.addAll(test.getAllSubWords(new Word("maprang"), 1));
        for(Word ele:tester){
            System.out.println(ele.getWord());
        }
        WordDatabase test2=new WordDatabase(tester);
        System.out.println(test2.contains(new Word("am")));
        System.out.println(tester.size());
    }
}

