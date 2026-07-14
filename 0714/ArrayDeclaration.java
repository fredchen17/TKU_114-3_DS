public class ArrayDeclaration {

    public static void main(String[] args) {
        int[] scores;
        scores = new int[5];

        int[] combinedScores = new int[5];

        int[] intArray = new int[3];
        double[] doubleArray = new double[3];
        boolean[] booleanArray = new boolean[3];
        String[] objectArray = new String[3];

        System.out.println(scores.length);
        System.out.println(combinedScores.length);
        System.out.println(intArray[0]);
        System.out.println(doubleArray[0]);
        System.out.println(booleanArray[0]);
        System.out.println(objectArray[0]);

        int[] initScores = {80, 75, 92, 68, 88};
        String[] products = {"Keyboard", "Mouse", "Monitor"};

        System.out.println(initScores.length);
        System.out.println(products.length);

        System.out.println(initScores[0]);
        System.out.println(initScores[4]);

        initScores[1] = 78;
        System.out.println(initScores[1]);

        System.out.println(initScores.length);

        System.out.println(initScores[initScores.length - 1]);
    }
}