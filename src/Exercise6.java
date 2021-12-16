import java.util.Scanner;

public class Exercise6 {

    public static boolean validExpression(String mathExpression){
        boolean expressionIsValid = true;
        if (mathExpression == "(" || !validBracketsCheck(mathExpression)){
            expressionIsValid = false;
        } else if(!arithmeticCheck(mathExpression)) {
            expressionIsValid=false;
        }
        return expressionIsValid;
    }

    public static boolean validBracketsCheck(String mathExpression){
        boolean validBrackets = true;
        int counter = 0;
        for (int i =0; i < mathExpression.length(); i++){
            if (mathExpression.charAt(i) == '('){
                counter++;
                while (charInStringCheck("0123456789", mathExpression.charAt(i+1))){
                    if (i == mathExpression.length() - 2 || mathExpression.charAt(i+1) == ')'){
                        validBrackets = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == mathExpression.length() - 1 || mathExpression.charAt(i+1) == ')'){
                    validBrackets = false;
                    break;
                }
                if (!charInStringCheck("+-^*/", mathExpression.charAt(i+1))){
                    validBrackets = false;
                    break;
                }
            } else if (mathExpression.charAt(i) == ')'){
                counter--;
            }
            if (counter < 0){
                validBrackets = false;
                break;
            }

        }
        if (counter != 0){
            validBrackets = false;
        }
        return validBrackets;
    }

    public static boolean arithmeticCheck(String mathExpression){
        boolean validArithmetic = true;
        for (int i = 0; i < mathExpression.length(); i++){
            if (charInStringCheck("+-*/^", mathExpression.charAt(i))){
                if (i == 0 || i == mathExpression.length() - 1){
                    validArithmetic = false;
                    break;
                } else if (!charInStringCheck("0123456789)", mathExpression.charAt(i-1)) ){
                    validArithmetic = false;
                    break;
                } else if (!charInStringCheck("0123456789(", mathExpression.charAt(i+1))){
                    validArithmetic = false;
                    break;
                }
            }
        }
        return validArithmetic;
    }

    public static boolean charInStringCheck(String sign, char action){
        boolean actionAppears = false;
        for (int i = 0; i < sign.length(); i++){
            if (sign.charAt(i) == action){
                actionAppears = true;
            }
        }
        return actionAppears;
    }

    public static int calculator (String mathExpression){
        int result = 0;
        if (isNumber(mathExpression)){
            result = Integer.parseInt(mathExpression);
        }
        else{
            while (findIndexOfChar(mathExpression,'+')==-1 && findIndexOfChar(mathExpression,'-')==-1 && findIndexOfChar(mathExpression,'*')==-1 && findIndexOfChar(mathExpression,'/')==-1 && findIndexOfChar(mathExpression,'^')==-1){
            if(mathExpression.charAt(0)=='('&&mathExpression.charAt(mathExpression.length()-1)==')'){
                mathExpression=cutString(mathExpression,1,mathExpression.length()-1);
            }
            }

            if (findIndexOfChar(mathExpression, '+') != -1) {
                result = calculator(cutString(mathExpression, 0, findIndexOfChar(mathExpression, '+'))) +
                        calculator(cutString(mathExpression,findIndexOfChar(mathExpression, '+') + 1, mathExpression.length()));
            }

            if (findIndexOfChar(mathExpression, '-') != -1) {
                result = calculator(cutString(mathExpression, 0, findIndexOfChar(mathExpression, '-'))) -
                        calculator(cutString(mathExpression,findIndexOfChar(mathExpression, '-') + 1, mathExpression.length()));
            }
            if (findIndexOfChar(mathExpression, '*') != -1) {
                result = calculator(cutString(mathExpression, 0, findIndexOfChar(mathExpression, '*'))) *
                        calculator(cutString(mathExpression,findIndexOfChar(mathExpression, '*') + 1, mathExpression.length()));
            }
            if (findIndexOfChar(mathExpression, '/') != -1) {
                result = calculator(cutString(mathExpression, 0, findIndexOfChar(mathExpression, '/'))) /
                        calculator(cutString(mathExpression,findIndexOfChar(mathExpression, '/') + 1, mathExpression.length()));
            }
            if (findIndexOfChar(mathExpression, '^') != -1) {
                result = (int) Math.pow(calculator(cutString(mathExpression, 0, findIndexOfChar(mathExpression, '^'))), calculator(cutString(mathExpression,findIndexOfChar(mathExpression, '^') + 1, mathExpression.length())));
            }
        }
        return result;
    }

    public static int findIndexOfChar (String mathExpression, char symbol){
        int index = -1;
        int counter = 0;
        for (int i = 0; i < mathExpression.length(); i++){
            if (mathExpression.charAt(i)=='('){
                counter++;
            }
            else if(mathExpression.charAt(i)==')'){
                counter--;
            }
            if(counter == 0 && mathExpression.charAt(i) == symbol){
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean isNumber (String mathExpression){
        boolean number = true;
        for (int i = 0; i < mathExpression.length(); i++){
            if (!charInStringCheck("0123456789", mathExpression.charAt(i))){
                number = false;
                break;
            }
        }
        return number;
    }

    public static String cutString (String mathExpression, int fromIndex, int toIndex){
        String newString = "";
        for (int i = fromIndex; i < toIndex; i++){
            newString = newString + mathExpression.charAt(i);
        }
        return newString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mathExpression;
        do {
            System.out.println("Enter math expression");
            mathExpression = scanner.nextLine();
        } while (!validExpression(mathExpression));
        int result = calculator(mathExpression);
        System.out.println(result);
    }
}
