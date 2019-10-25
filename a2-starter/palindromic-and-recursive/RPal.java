import java.util.*;

public class RPal {

    // store all the known answers, so we don't have to compute them again
    private Map<Integer, List<List<Integer>>> storageAllRPals;

    // a constructor: creates the above storage and puts in answers
    // for our base cases of n = 0 and n = 1.
    public RPal() {
        storageAllRPals = new HashMap<>();
        storageAllRPals.put(0, Arrays.asList(Arrays.asList()));
        storageAllRPals.put(1, Arrays.asList(Arrays.asList(1)));
    }

    public static List<Integer> helper(List<Integer> front, int mid) {
        List<Integer> lst = new ArrayList<Integer>();
        if (mid == 0) {
            lst.addAll(front);
            lst.addAll(front);
        } else {
            lst.addAll(front);
            lst.add(mid);
            lst.addAll(front);
        }
        return lst;
    }

    private List<List<Integer>> computeAllRPals(int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(Arrays.asList(n));
        for (int i = 1; i <= n / 2; i++) {
            for (List<Integer> l : allRPals(i)) {
                ans.add(helper(l, n - 2 * i));
            }
        }
        return ans;
    }

    public List<List<Integer>> allRPals(int n) {
        // Challenge: if you feel like learning a new trick, how would you
        // rewrite the following using just storageAllRPals.computeIfAbsent(..)?
        List<List<Integer>> answer = storageAllRPals.get(n);
        if (answer == null) {
            answer = computeAllRPals(n);
            storageAllRPals.put(n, answer);
        }
        return answer;
    }

    public static void main(String[] args) {
        RPal rPal = new RPal();
        System.out.println("n=11:");
        System.out.println(rPal.allRPals(11));
        System.out.println("n=99:");
        System.out.println(rPal.allRPals(99).size());
        System.out.println("n=224:");
        System.out.println(rPal.allRPals(224).size());
    }
}
