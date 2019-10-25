package game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class TextTwist{
    private Scanner reader;
    private Word main_word;
    private WordDatabase keep;
    private List<Word> tmp;
    private Random rand=new Random();
    private WordDatabase data;
    private List<String> correct_guess;
    private Integer score=0;
    private Integer max_score;
    private Long timestamp1;
    private Long timestamp2;
    private Integer victory_count=0;
    private Integer Surrender=0;
    private Integer helper=5;
    private Integer wrong_guess=0;

    public TextTwist() throws IOException {
        reader= new Scanner(System.in);
        data = new WordDatabase("..\\histogame\\src\\main\\resources\\linuxwords.txt");
        tmp = data.getWordWithLength(6);
        int n= rand.nextInt(tmp.size());//n \in {0,1,2,3,...,tmp.size-1}
        main_word=tmp.get(n);//tmp[n]
        keep=new WordDatabase(data.getAllSubWords(main_word,1));
        correct_guess=new ArrayList<>();
        max_score=keep.count();
        timestamp1=System.currentTimeMillis();
    }
    public String getInput(){
        String input=reader.nextLine();
        return input;
    }
    public void new_Word(){
        int n =rand.nextInt(tmp.size());
        main_word=tmp.get(n);
        for(int i=2;i<=6;i++){
            String a="";
            List<Word> tmp2 =keep.getWordWithLength(i);
            for(Word ele: tmp2){
                a=a+ele.getWord()+" ";
            }
            System.out.println(a);
        }
        keep=new WordDatabase(data.getAllSubWords(main_word,1));
        correct_guess=new ArrayList<>();
        score=0;
        max_score=keep.count();
        timestamp1=System.currentTimeMillis();
        helper=5;
        wrong_guess=0;
    }
    public void questioned(){
        for(int i=2;i<=6;i++){
            String a="";
            List<Word> tmp2 =keep.getWordWithLength(i);
            for(Word ele: tmp2){
                if(correct_guess.contains(ele.getWord())){
                    a=a+ele.getWord()+" ";
                }
                else{
                    for(int j=0;j<i;j++){
                        a=a+"?";
                    }
                    a=a+" ";
                }
            }
            System.out.println(a);
        }
    }
    public void annoying_thing(){
        timestamp2=System.currentTimeMillis();
        System.out.println("Your score for this round "+score+"/"+max_score);
        System.out.println("Time this round: "+((timestamp2 - timestamp1) / 1000)+" seconds");
        System.out.println("You have finished "+victory_count+" rounds");
        System.out.println("You have surrendered "+Surrender+" rounds");
        System.out.println("wrong guess "+wrong_guess +" out of "+ Math.round(max_score*0.4));
        System.out.println("Helper left= "+helper);
        System.out.println(main_word.toString());
    }
    public void check_answer(String w){
        Word b=new Word(w);
        if(keep.contains(b)&&!correct_guess.contains(w)){
            correct_guess.add(w);
            score=score+1;
            System.out.println("Correct");
        }
        else if(keep.contains(b)&&correct_guess.contains(w)){
            System.out.println("You already guess this word");
        }
        else{
            System.out.println("Wrong");
            wrong_guess++;
        }
    }
    public boolean victory_or_not(Integer score){
        if(score==max_score){
            victory_count++;
            return true;
        }
        return false;
    }
    public void helper() {
        if(score==max_score-1){
            System.out.println("Please try to get the last word by yourself, helper will not help you here.");
        }
        else if (helper > 0) {
            System.out.println("You called for help");
            int n = rand.nextInt(keep.count());
            Word w = keep.getIndex(n);
            while (correct_guess.contains(w.getWord())) {
                n = rand.nextInt(keep.count());
                w = keep.getIndex(n);
            }
            System.out.println("One of the word you need is " + w.getWord());
            helper--;
        }
        else{
            System.out.println("You already run out of helper, plan more wisely next time.");
        }
    }
    public boolean process_input(){
        Boolean quit=false;
        String input=getInput();
        if(input.equals("q")){
            quit=true;
        }
        else if(input.equals("!")){
            Surrender++;
            new_Word();
        }
        else if(input.equals("?")){
            questioned();
        }
        else if(input.equals("$")){
            helper();
        }
        else{
            check_answer(input.toLowerCase());
        }
        if(victory_or_not(score)){
            System.out.println("Good job, You get all the word for this round");
            timestamp2=System.currentTimeMillis();
            System.out.println("Time Taken: "+((timestamp2 - timestamp1) / 1000)+" seconds");
            new_Word();
        }
        if(max_score*0.4<=wrong_guess){
            System.out.println("You have too much wrong guess.");
            new_Word();
        }
        return quit;
    }
    public void play(){
        boolean done=false;
        while(!done){
            annoying_thing();
            done=process_input();
        }
        System.out.println("Thank you for playing");
    }
    public static void main(String[] arg) throws IOException {
        TextTwist test=new TextTwist();
        System.out.println("Command Lists: q-quit !-surrender and get new word ?-check all possible word $-ask for help");
        test.play();
    }
}