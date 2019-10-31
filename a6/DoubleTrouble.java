public class DoubleTrouble {
    public static Integer binarySearch(int[] arr, int k, int l, int r) {// this return an index
        if (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < k) {
                return binarySearch(arr, k, m + 1, r);
            } else {
                return binarySearch(arr, k, l, m-1 );
            }
        } else {
            return r;
        }
    }

    public static Integer rank(int[] A, int[] B, int e) {
        int a = binarySearch(A, e, 0, A.length-1);
        int b = binarySearch(B, e, 0, B.length-1);
        return a + b + 2;// +2 since we start count index from 0 but normally we count from 1.
    }
    public static Integer weird_binary(int[] A,int[] B, int l, int r, int k){
        if(l<r){
            int m=(l+r)/2;
            if(rank(A,B,A[m])<k){
                return weird_binary(A, B, m+1, r, k);
            }
            else{
                return weird_binary(A, B, l, m, k);
            }
        }
        else{
            if(rank(A,B,A[l])==k){
                return l;
            }
            else{
                return null;
            }
        }
    }

    public static Integer select(int[] A, int[] B, int k) {
        if(k>A.length+B.length-1||k<0){
            return null;
        }
        if(weird_binary(A, B, 0, A.length-1, k)!=null){
            return A[weird_binary(A, B, 0, A.length-1, k)];
        }
        else{
            return B[weird_binary(B, A, 0, B.length-1, k)];
        }
}

    public static void main(String arg[]) {
        int[] tester = new int[] { 1, 4, 7, 8, 9, 12, 13, 20, 22, 27, 28, 32, 33, 35, 37, 43, 45, 47 };
        int[] tester2 = new int[] { 2, 3, 5, 6, 10, 11, 14, 15, 16, 17, 18, 19, 21, 23, 24, 25, 26, 29, 30, 31, 34, 36,
                38, 39, 40, 41, 42, 44, 46, 48, 49 };
        System.out.println(select(tester, tester2, 26));
    }
}