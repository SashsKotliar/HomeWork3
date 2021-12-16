import java.util.Random;
import java.util.Scanner;

public class Exercise9 {
    public static void main(String[] args) {
        Random random = new Random();
        int secretCode;
        do {
            secretCode = random.nextInt(5210)+ 1234;
        } while (!validCode(secretCode));
        System.out.println(secretCode);
        String code = Integer.toString(secretCode);
        gameFlow(code);
    }

    public static boolean validCode (int secretCode){
        boolean isValid = false;
        if (fourDigitsFromOneToSix(secretCode)&&sameDigitCheck(secretCode)){
            isValid = true;
        }
        return isValid;
    }

    public static boolean sameDigitCheck(int code) {
        boolean noSimilarDigits = true;
        while (code != 0) {
            if (code % 10 != (code / 10) % 10 && code % 10 != (code / 100) % 10
                    && code % 10 != (code / 1000) % 10) {
                code = code / 10;
            } else {
                noSimilarDigits = false;
                break;
            }
        }
        return noSimilarDigits;
    }

    public static boolean fourDigitsFromOneToSix (int code){
        boolean isValid = true;
        if (code < 1000 || code > 6666){
            isValid = false;
        }
        while (code != 0){
            if (code % 10 != 0 &&  code % 10 != 7 && code % 10 != 8
                    && code % 10 !=9){
                code = code / 10;
            } else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public static void gameFlow (String code){
        Scanner scanner = new Scanner(System.in);
        int triesLeft = 0;
        int level = hardnessLevelChoose();
        switch (level){
            case 1: triesLeft = 20;
            break;
            case 2: triesLeft = 15;
            break;
            case 3: triesLeft = 10;
            break;
            case 4: triesLeft = 25;
            break;
        }
        int codeGuess;
        String codeGuessString = "";
        do {
            do {
                if (level == 4) {
                    System.out.println("Guess the code! Enter four digits from 1 to 6: ");
                } else {
                    System.out.println("The try number: " + triesLeft + ". Guess the code! Enter four digits from 1 to 6: ");
                }
                codeGuess = scanner.nextInt();
                if (fourDigitsFromOneToSix(codeGuess)) {
                    if (!sameDigitCheck(codeGuess)){
                        triesLeft = triesLeft - 2;
                        System.out.println("You entered the same digits in your code!" +
                                "You're fined 2 tries!");
                    }
                    triesLeft--;
                    codeGuessString = Integer.toString(codeGuess);
                    hint(code, codeGuessString);
                }
            } while (!fourDigitsFromOneToSix(codeGuess));
        } while (!revealedCode(code, codeGuessString) && triesLeft != 0);
        if (revealedCode(code, codeGuessString)){
            System.out.println("Congrats! You guessed a secret code!");
        } else {
            System.out.println("Loser! The secret code is : " + code);
        }
    }

    public static void hint (String code, String codeGuessString){
        int exactlyGuessedDigit = 0;
        int partiallyGuessedDigit = 0;
        for (int i = 0; i < code.length(); i++){
            for (int k = 0; k < codeGuessString.length(); k++){
                if (code.charAt(i) == codeGuessString.charAt(k)){
                    if (i == k){
                        exactlyGuessedDigit+=1;
                    } else {
                        partiallyGuessedDigit+=1;
                    }
                    break;
                }
            }
        }
        System.out.println("The number of exactly guessed digits is: " + exactlyGuessedDigit);
        System.out.println("The number of partially guessed digits is: " + partiallyGuessedDigit);
    }

    public static boolean revealedCode (String code, String guessCode){
        boolean isRevealed = true;
        for (int i= 0; i < 4; i++){
            if (code.charAt(i) != guessCode.charAt(i)) {
                isRevealed = false;
                break;
            }
        }
        return isRevealed;
    }

    public static int hardnessLevelChoose (){
        Scanner scanner = new Scanner(System.in);
        int level;
        do {
            System.out.println("Choose your level: ");
            System.out.println("1 for easy (20 tries)");
            System.out.println("2 for medium (15 tries)");
            System.out.println("3 for hard (10 tries)");
            System.out.println("4 for surprise.");
            level = scanner.nextInt();
        } while (level > 4 || level < 1);
        return level;
    }
}
