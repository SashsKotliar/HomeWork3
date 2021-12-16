public class Exercise4 {
    public static void main(String[] args) {
        int [] sortedArray = {7, 5, 4, 3, 2};
        int [] notSortedArray = {8, 9, 7, 12, 10, 11};
        System.out.println(ifSortedArrayIsFull(sortedArray));
        System.out.println(ifUnsortedArrayIsFull(notSortedArray));
    }

    public static boolean ifUnsortedArrayIsFull (int [] notSortedArray){
        boolean fullArray;
        if (duplicateCheck(notSortedArray)){
            fullArray = (ifSortedArrayIsFull(sortDecreaseArray(notSortedArray)));
        } else {
            fullArray = false;
        }
       return fullArray;
    }

   public static int [] sortDecreaseArray(int [] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] >= array[j]) {
                    int x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
        }
        return array;
    }

    public static boolean duplicateCheck (int [] array){
        boolean noDuplicate = true;
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1; j < array.length; j++){
                if (array[i] == array[j]){
                    noDuplicate = false;
                    break;
                }
            }
        }
       return noDuplicate;
    }

    public static boolean ifSortedArrayIsFull(int [] sortedArray){
        boolean fullArray = true;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i] != (sortedArray[i + 1]) + 1) {
                    fullArray = false;
                    break;
                }
            }
        return fullArray;
    }
}
