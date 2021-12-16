import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println("Enter a number: ");
            number = scanner.nextInt();
        } while (number<=1); // לפי הגדרה מספר ראשוני יכול להיות רק מספר טבעי גדול מ1.
        int[] simpleFactorsArray = simpleFactorsArray(number);
        for (int i = 0; i < simpleFactorsArray.length; i++) {
             System.out.print(simpleFactorsArray[i] + ",");
        }
    }

   public static int [] simpleFactorsArray (int number){
        int counter = 0;
        int factor = 2;
        int [] simpleFactors = new int[arraySize(number, factor, counter)];
        for (int i = 0; i < simpleFactors.length; i++){
                if (number % factor == 0){
                    simpleFactors [i] = factor;
                    number = number/factor;
                } else {
                    simpleFactors[i] = factorFind(number, factor);
                    number = number / simpleFactors[i];
                }
            }
        return simpleFactors;
    }


    public static int arraySize (int number, int factor, int counter) {
       if (number == factor){
           counter++;
       } else if (number%factor==0){
           counter = arraySize(number/factor, factor, counter + 1);
       } else {
           counter = arraySize(number, factor + 1, counter);
       }
       return counter;
    }

    public static int factorFind(int number, int factor){
        if (number % factor!=0){
            factor = factorFind(number, factor + 1);
        }
        return factor;
    }
}

