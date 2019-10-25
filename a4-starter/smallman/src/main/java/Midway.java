import java.util.Arrays;

public class Midway {
    public static long stepsRemaining(int[] diskPos) {
        return helper_func(diskPos,0,1,2); // TODO: change me
    }
    public static long helper_func(int[] diskpos, int from , int to , int aux){
        if(diskpos.length==0){
            return 0;
        }
        else{
            if(diskpos[diskpos.length-1]==to){
                int[] ne= Arrays.copyOfRange(diskpos, 0, diskpos.length-1);
                return helper_func(ne, aux,to,from);
            }
            else{
                int[] ne= Arrays.copyOfRange(diskpos, 0, diskpos.length-1);
                return (long) (helper_func(ne,from ,aux,to)+Math.pow(2,diskpos.length-1));
            }
        }
    }
    public static void main(String[] args){
        System.out.println(stepsRemaining(new int[]{2, 2, 1, 1, 2, 2, 1}));
        System.out.println(stepsRemaining(new int[]{0,0,0}));
    }


}
