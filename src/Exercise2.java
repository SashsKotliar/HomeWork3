public class Exercise2 {
    public static void main(String[] args) {
        int [] array = {12, 782, 980, 92100};
        int [] array2 = {3, 5453, 12, 8761, 2234};
        System.out.println(hasTheBiggestBroAmount(array, array2));
    }

    public static int sum (int number){
        int sum = 0;
        while (number != 0){
            sum = sum + (number % 10);
            number = number / 10;
        }
        return sum;
    }

    public static boolean areBro(int num1, int num2){
     return sum(num1)==sum(num2);
    }

    public static int broCheck (int [] array, int number){
        int broLevel = 0;
        for (int i = 0; i < array.length; i++){
           if (areBro(number, array[i])){
               broLevel++;
           }
        }
        return broLevel;
    }

    public static int hasTheBiggestBroAmount(int [] array, int [] array2){
        int max = 0;
        int maxIndex=0;
        for (int i = 0; i < array.length; i++){
           if (broCheck(array2, array[i]) > max){
               max = broCheck(array2,array[i]);
               maxIndex = i;
           }
        }
        return maxIndex;
    }

}
