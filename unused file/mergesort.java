import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class mergesort{
    private Scanner reader;
    private List<Integer> array;
    public mergesort(){
        reader=new Scanner(System.in);
        array=new ArrayList<Integer>();
    }
    public void process_input(){
        String inputline=reader.nextLine();
        Scanner tokenizer =new Scanner(inputline);
        while(tokenizer.hasNext()){
            array.add(Integer.parseInt(tokenizer.next()));
        }
        tokenizer.close();
    }
    public List<Integer> getarray(){
        return this.array;
    }
    public List<Integer> merge(List<Integer> lst1, List<Integer> lst2) {
        List<Integer> ans = new ArrayList<>();
        Integer i = 0, j = 0, s = 0;
        while (i < lst1.size() && j < lst2.size()) {
            if (lst1.get(i) < lst2.get(j)) {
                ans.add(s, lst1.get(i));
                i++;
                s++;
            } else {
                ans.add(s, lst2.get(j));
                j++;
                s++;
            }
        }
        if (j < lst2.size()) {
            for (int idx = j; idx < lst2.size(); idx++) {
                ans.add(s, lst2.get(idx));
                s++;
            }
        }
        if (i < lst1.size()) {
            for (int idx = i; idx < lst1.size(); idx++) {
                ans.add(s, lst1.get(idx));
                s++;
            }
        }
        return ans;
    }

    public List<Integer> merge_sort(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }
        List<Integer> aList = new ArrayList<Integer>();
        aList = list.subList(0, list.size() / 2);

        List<Integer> bList = new ArrayList<Integer>();
        bList = list.subList(list.size() / 2, list.size());

        List<Integer> a = merge_sort(aList);
        List<Integer> b = merge_sort(bList);
        return merge(a, b);
    }
    
    public static void main(String[] arg){
        mergesort test=new mergesort();
        List<Integer> a=new ArrayList<>(Arrays.asList(1,2,3,5,6,10,11));
        List<Integer> b=new ArrayList<>(Arrays.asList(3,4,7));
        System.out.println(test.merge(a, b).toString());
        test.process_input();
        System.out.println(test.getarray().toString());
        System.out.println(test.merge_sort(test.getarray()));
    }
}