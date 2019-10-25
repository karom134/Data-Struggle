package histogram;

import java.util.HashMap;
import java.util.Iterator;


// TODO: Uncomment this and make sure to implement all the methods

public class SimpleHistogram<DT> implements Histogram<DT>, Iterable<DT> {
    private HashMap<DT,Integer> keep;
    public  SimpleHistogram(){
        keep=new HashMap<DT,Integer>();
    }

    public SimpleHistogram(DT[] input) {
        keep = new HashMap<DT,Integer>();
        for(DT ele: input ){
            if(keep.keySet().contains(ele)){
                int num=keep.get(ele)+1;
                keep.put(ele,num);
            } else{
                keep.put(ele,1);
            }
        }
    }
    public  SimpleHistogram(Histogram hist){
        Iterator<DT> iter=hist.iterator();
        keep=new HashMap<DT,Integer>();
        while(iter.hasNext()){
            DT ele=iter.next();
            int num=hist.getCount(ele);
            keep.put(ele,num);
        }
    }



    @Override
    public int getTotalCount() {
        int summ=0;
        for(DT item: keep.keySet()){
            summ=summ+keep.get(item);
        }
        return summ;
    }

    @Override
    public int getCount(DT item) {
        return keep.getOrDefault(item, 0);
    }

    @Override
    public void setCount(DT item, int count) {
        keep.put(item,count);
    }

    @Override
    public Iterator<DT> iterator() {
        return keep.keySet().iterator();
    }
    @Override
    public String toString(){
        String tmp="";
        for(DT ele: keep.keySet()){
            for(int i=0;i<keep.get(ele);i++){
                tmp=tmp+ele+" ";
            }
        }
        return tmp;
    }
    public boolean equals(Histogram hist){
        Iterator<DT> iter=hist.iterator();
        while(iter.hasNext()){
            DT ele= iter.next();
            if(keep.containsKey(ele)){
                if(getCount(ele)!=hist.getCount(ele)){
                    return false;
                }
            }
            else{
                    return false;
                }
            }
        return getTotalCount()==hist.getTotalCount();
    }
}

