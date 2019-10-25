public class aveMinMax {
    public static double minMaxAverage(double[] array) {
        double maxie = 0;
        double minnie = 0;
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            return (array[0] + array[1]) / 2;
        }
        if (array.length % 2 == 0) {
            if (array[0] < array[1]) {
                maxie = array[1];
                minnie = array[0];
            } else {
                maxie = array[0];
                minnie = array[1];
            }
            for (int i = 2; i < array.length; i += 2) {
                if (array[i] < array[i + 1]) {
                    if (maxie < array[i + 1]) {
                        maxie = array[i + 1];
                    }
                    if (minnie > array[i]) {
                        minnie = array[i];
                    }
                } else {
                    if (maxie < array[i]) {
                        maxie = array[i];
                    }
                    if (minnie > array[i + 1]) {
                        minnie = array[i + 1];
                    }
                }
            }
        }
        if (array.length % 2 == 1) {
            maxie = array[0];
            minnie = array[0];
            for (int i = 1; i < array.length - 1; i += 2) {
                if (array[i] < array[i + 1]) {
                    if (maxie < array[i + 1]) {
                        maxie = array[i + 1];
                    }
                    if (minnie > array[i]) {
                        minnie = array[i];
                    }
                }

                else {
                    if (maxie < array[i]) {
                        maxie = array[i];
                    }

                    if (minnie > array[i + 1]) {
                        minnie = array[i + 1];
                    }
                }
            }
        }
        return (maxie + minnie) / 2.0;
    };

    public static void main(String args[]) {
        double[] tester = new double[] { 12, 54, 4, 5, 12, 1, 5, 54, 21, 1, 78, 1, 24, 52, 54, 5 };
        System.out.println(tester.length);
        System.out.println(minMaxAverage(tester));
    }
}