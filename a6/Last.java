public class Last{
    public static Integer binarySearchLast(int[] a, int k){
        return binary_search_helper(a, k, 0, a.length,false);
    }
    public static Integer binary_search_helper(int[] a,int k, int l, int r,boolean x){
        int m = (l+r)/2;
        if(r-l<=1){
            if(!x&&a[m]!=k){
                return null;
            }
            return m;
        }
        else{
            if(a[m]>k){
                return binary_search_helper(a, k, l, m-1,x);
            }
            else if(a[m]==k){
                x=true;
                return binary_search_helper(a, k, m, r,x);
            }
            else{
                return binary_search_helper(a, k, m+1, r,x);
            }
        }
    }

    public static void main(String arg[]){
        System.out.println(binarySearchLast(new int[]{2, 2, 2, 2, 2, 2,2,2,2,2,2,2,2,2,2,3}, 2));
        System.out.println(binarySearchLast(new int[]{1, 2, 2, 2, 4,4, 5}, 0));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2, 4,4, 5}, 2));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2, 4,4, 5}, 3));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2,3, 4,5, 5}, 5));
    }
}