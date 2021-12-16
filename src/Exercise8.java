import java.util.Scanner;

public class Exercise8 {
    public static String[] textsArray() {
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        String text = "";
        String complexText = "";
        do {
            System.out.println("Enter a text: ");
            text = scanner.nextLine();
            if (text.length() >= 4) {
                size++;
                complexText = complexText + "," + text + ",";
            }
        } while (text.length() >= 4);
        String [] textsArray = new String[size];
        int index = 0;
        for (int i = 0; i < complexText.length(); i++){
            for (int k = i + 1; k < complexText.length(); k++){
                if (complexText.charAt(i) == ',' && complexText.charAt(k) == ','){
                    textsArray[index] = textCut(complexText, i, k);
                    i=k;
                    index++;
                    break;
                }
            }
        }
        return textsArray;
    }

    public static String textCut (String complexText, int i, int k){
        String newString = "";
        for (int newIndex = i + 1; newIndex < k; newIndex++){
            newString = newString + complexText.charAt(newIndex);
        }
        return newString;
    }

    public static String [] fullUnderTextsArray (String text){
        String [] fullArray = new String[underTextArraySize(text.length())];
        int firstEmptyPlace = 0;
        for (int i = -1; i < text.length() - 2; i ++) {
            String[] tempArray = underTexts(textCut(text, i, text.length()));
            for (int k = 0; k < tempArray.length; k++){
                fullArray[firstEmptyPlace] = tempArray[k];
                firstEmptyPlace++;
            }
        }
        return fullArray;
    }

    public static String [] underTexts (String text){
        String [] underTextsArray = new String[text.length() - 1];
        String underText = "";
        for (int i = 1; i <= text.length(); i++){
            underText = textCut(text, -1, i);
            if (underText.length() >=2) {
                underTextsArray[i - 2] = underText;
            }
        }
        return underTextsArray;
    }

    public static int underTextArraySize (int counter){
        int arraySize = 0;
        for (int i = 2; i < counter; i ++){
            arraySize = arraySize + i;
        }
        return arraySize + 1;
    }

    public static int size (String [] textsArray){
        int counter = 0;
        for (int i = 0; i < textsArray.length; i++){
            String [] underArray = fullUnderTextsArray(textsArray[i]);
            counter = counter + underArray.length;
        }
        return counter;
    }

    public static String[] allUnderTextsInOne(String [] textsArray){
        String [] allInOne = new String[size(textsArray)];
        int firstEmptyPlace = 0;
        for (int i = 0; i < textsArray.length; i++){
            String [] underTexts = fullUnderTextsArray(textsArray[i]);
            for (int k = 0; k < underTexts.length; k++){
                allInOne[firstEmptyPlace] = underTexts[k];
                firstEmptyPlace++;
            }
        }
        return allInOne;
    }

    public static String mostPopularUnderText (String [] allInOne){
        int maxCounter = 0;
        String mostPopularUnderText = "";
        for (int i = 0; i < allInOne.length; i++){
            int counter = 1;
            for (int k = i+1; k < allInOne.length; k++){
                if (similar(allInOne[i], allInOne[k])){
                    counter++;
                }
            }
            if (counter > maxCounter){
                maxCounter = counter;
                mostPopularUnderText = allInOne[i];
            }
        }
        return mostPopularUnderText;
    }

    public static boolean similar (String str1, String str2){
        boolean isSimilar = true;
        if (str1.length() != str2.length()){
            isSimilar = false;
        } else {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    isSimilar = false;
                    break;
                }
            }
        }
        return isSimilar;
    }

    public static void main(String[] args) {
        String [] arr = textsArray();
        System.out.println(mostPopularUnderText(allUnderTextsInOne(arr)));
    }
}
