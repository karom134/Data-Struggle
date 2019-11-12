import java.util.Arrays;

public class MakeTree{
    public static BinaryTreeNode buildBST(int[] keys){
        Arrays.sort(keys);//this one run at O(nlog(n))
        return helpBuilder(keys, 0, keys.length);
    }
    public static BinaryTreeNode helpBuilder(int[] arr,int l,int r){//this run at O(n)
        int middle=(l+r)/2;
        if(l-r<=1){
            return new BinaryTreeNode(middle);
        }
        else{
            BinaryTreeNode left =helpBuilder(arr, l, middle-1);
            BinaryTreeNode right =helpBuilder(arr, middle+1, r);
            return new BinaryTreeNode(left, middle, right);
        }
    }
    //O(nlogn)+O(n)=O(nlogn)
    public static void main(String arg[]){
        
    }
}