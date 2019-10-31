public class Last{
    public static Integer binarySearchLast(int[] a, int k){
        if(a[0]>k||a[a.length-1]<k){
            return null;
        }
        if (a[a.length-1]==k){
            return a.length-1;
        }
        int ans=binary_search_helper(a, k, 0, a.length);
        if(a[ans]!=k){
            return null;
        }
        return ans;
    }
    public static Integer binary_search_helper(int[] a,int k, int l, int r){
        int m = (l+r)/2;
        if(r-l<=1){
            return m;
        }
        else{
            if(a[m]>k){
                return binary_search_helper(a, k, l, m-1);
            }
            else if(a[m]==k){
                return binary_search_helper(a, k, m, r);
            }
            else{
                return binary_search_helper(a, k, m+1, r);
            }
        }
    }

    public static void main(String arg[]){
        System.out.println(binarySearchLast(new int[]{2, 2, 2, 2, 2, 2,2,2,2,2,2,2,2,2,2,3}, 2));
        System.out.println(binarySearchLast(new int[]{1, 2, 2, 2, 4,4, 5}, 1));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2, 4,4, 5}, 2));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2, 4,4, 5}, 4));
        System.out.println(binarySearchLast(new int[]{1,1, 2, 2, 2,3, 4,5, 5}, 5));
    }
}