import java.util.Arrays;

public class Zombies {
    private static class Pair<S,T>{
        public S first;
        public T second;
        public Pair(S first, T second) {
            this.first = first;
            this.second = second;
        }
    }
    public static int countBad(int[] hs) {
        return mergesort_count(hs).first;
    }

    public static Pair<Integer, int[]> merge_count(Pair<Integer, int[]> P1, Pair<Integer, int[]> P2) {
        int i = 0, j = 0, t = 0, count = 0, l1 = P1.second.length, l2 = P2.second.length;
        int[] ans = new int[l1 + l2], lst1 = P1.second, lst2 = P2.second;
        while (i < l1 && j < l2) {
            if (lst1[i] > lst2[j]) {
                ans[t] = lst1[i];
                i++;
                t++;
            } else {
                ans[t] = lst2[j];
                j++;
                t++;
                count = count + l1 - i;
            }
        }
        if (j < l2) {
            for (int idx = j; idx < l2; idx++) {
                ans[t] = lst2[idx];
                t++;
            }
        }
        if (i < l1) {
            for (int idx = i; idx < l1; idx++) {
                ans[t] = lst1[idx];
                t++;
            }
        }
        return new Pair<>(count+P1.first+P2.first, ans);
    }

    public static Pair<Integer, int[]> mergesort_count(int[] arr) {
        int count = 0, l = arr.length;
        if (arr.length <= 1) {
            return new Pair<>(count, arr);
        } else {
            int[] left = Arrays.copyOfRange(arr, 0, l / 2);
            int[] right = Arrays.copyOfRange(arr, l / 2, l);
            return merge_count(mergesort_count(left), mergesort_count(right));
        }
    }
    public static void main(String arg[]){
        System.out.println(Zombies.countBad(new int[]{1, 7, 22, 13, 25, 4, 10, 34, 16, 28, 19, 31}));
    }
}
