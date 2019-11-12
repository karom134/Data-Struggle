import java.util.*;
public class Heaptree{
    private List<Integer> heap;

    public Heaptree(int[] a){
        this.heap=new ArrayList<>();
        this.heap.add(null);
        for(int idx=0;idx<a.length;idx++){
            a[idx]=-a[idx];
        }
        Arrays.sort(a);
        for(int idx=0;idx<a.length;idx++){
            a[idx]=-a[idx];
        }
        for(Integer ele: a){
            this.heap.add(ele);
        }
    }
    private int parentsearch(int idx){
        return idx/2;
    }
    private int leftkid(int idx){
        return 2*idx;
    }
    private int rightkid(int idx){
        return 2*idx+1;
    }
    private boolean leaf(int idx){
        if(idx<this.heap.size() && leftkid(idx)>=this.heap.size()){
            return true;
        }
        return false;
    }
    private void swap(int a,int b){
        Integer tmp=this.heap.get(a);
        this.heap.set(a, this.heap.get(b));
        this.heap.set(b, tmp);
    }
    private void fixing(int loc){
        int parent=parentsearch(loc);
        while(this.heap.get(loc)>this.heap.get(parent)){
            swap(loc,parent);
            loc=parent;
            parent=parentsearch(loc);
            if(parent==0){
                break;
            }
        }
    }
    private Integer findmax(){
        return this.heap.get(1);
    }
    private void add(int ele){
        this.heap.add(ele);
        fixing(this.heap.size()-1);
    }
    private Integer maximumkid(int node){
        if(leaf(node)){
            return null;
        }
        else{
            if(rightkid(node)>=this.heap.size()){
                return leftkid(node);
            }
            else{
                if(this.heap.get(rightkid(node))<=this.heap.get(leftkid(node))){
                    return leftkid(node);
                }
                else{
                    return rightkid(node);
                }
            }
        }
    }
    private void fixing_del(int loc){
        Integer fmaxie=maximumkid(loc);
        while(this.heap.get(fmaxie)>this.heap.get(loc)){
            Integer maxie=maximumkid(loc);
            swap(maxie, loc);
            loc=maxie;
        }
    }
    private void del_max(){
        Integer last=this.heap.remove(this.heap.size()-1);
        if(this.heap.size()!=0){
            this.heap.set(1,last);
            fixing_del(1);
        }
    }
    private String checker(){
        return this.heap.toString();
    }
    public static void main(String arg[]){
        Heaptree tester=new Heaptree(new int[]{2,3,4,5,6,7,8,9,10});
        tester.add(20);
        System.out.println(tester.checker());
        tester.del_max();
        System.out.println(tester.checker());
        tester.del_max();
        System.out.println(tester.checker());
    }
}