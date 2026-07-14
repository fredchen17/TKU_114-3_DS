public class SequentialSearch {
    public static int findIndex(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){

    int[] scores = {66, 74, 91, 78, 80};
    int target1 = scores[1];
    int target2 = scores[3];

    int index1 = findIndex(scores, target1);
        int index2 = findIndex(scores, target2);

        System.out.println(index1);
        System.out.println(index2);
    }
}
