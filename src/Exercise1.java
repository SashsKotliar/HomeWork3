public class Exercise1 {
    public static void main(String[] args) {
        int[] array = {121, 76, 22, 33, 77};
        if (positiveArrayCheck(array)){
            int minSumIndex = index(array);
            System.out.println(minSumIndex);
        } else {
            System.out.println("Invalid array.");
        }
    }

    public static boolean positiveArrayCheck(int [] array){
        boolean validArray = true;
        for (int i = 0; i < array.length; i++){
            if (array[i] < 0){
                validArray = false;
                break;
            }
        }
        return validArray;
    }

    public static boolean changingNumber(int number) {
       boolean changingNumber = true;
       while (number > 9){
           if (sameSignNumber(number % 10, (number / 10) % 10)){
           changingNumber = false;
           break;
           }
           number = number / 10;
       }
        return changingNumber;
    }

    public static boolean sameSignNumber (int num1, int num2){
        return num1 % 2 == num2 % 2;
    }

    public static int index (int [] array) {
        int minIndex;
        int firstChangingNumberIndex = 0;
        while (!changingNumber(array[firstChangingNumberIndex]) && firstChangingNumberIndex != array.length - 1){
            firstChangingNumberIndex++;
        }
        if (!changingNumber(array[firstChangingNumberIndex])){
          minIndex = -1;
        } else {
            int minSum = digitsSum(array[firstChangingNumberIndex]);
            minIndex = firstChangingNumberIndex;
            for (int i = 0; i < array.length; i++) {
                if (changingNumber(array[i])) {
                    if (minSum > digitsSum(array[i])) {
                        minSum = digitsSum(array[i]);
                        minIndex = i;
                    }
                }
            }
        }
        return minIndex;
    }

    public static int digitsSum(int number){
        int sum = 0;
        if (number != 0){
            sum = (number % 10) + digitsSum(number/10);
        }
        return sum;
    }
}
