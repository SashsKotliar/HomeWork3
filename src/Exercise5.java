public class Exercise5 {
    public static String changedText(String text){
        char mostUsableLetter = mostUsableCharFinder(text);
        char secondUsableLetter = secondMostUsableCharFinder(text, mostUsableLetter);
        char [] textToChange = text.toCharArray();
        for (int i = 0; i < textToChange.length; i++) {
            if (textToChange[i] == mostUsableLetter){
                textToChange[i] = secondUsableLetter;
            } else if (textToChange[i] == secondUsableLetter){
                textToChange[i] = mostUsableLetter;
            }
        }
        return new String(textToChange);
    }
    public static char mostUsableCharFinder (String text){
        int maxCounter = 0;
        char mostUsableLetter = ' ';
        for (int i = 0; i < text.length(); i++){
            int counter = 1;
            for (int j = i + 1; j < text.length(); j++){
                if (text.charAt(i) == text.charAt(j)){
                   counter++;
                }
            }
            if (counter > maxCounter){
                maxCounter = counter;
                mostUsableLetter = text.charAt(i);
            }
        }
        return mostUsableLetter;
    }

    public static char secondMostUsableCharFinder (String text, char mostUsableLetter){
        int secondCounter = 0;
        char secondUsableLetter = ' ';
        for (int i = 0; i < text.length(); i++){
            int counter = 1;
            for (int j = i + 1; j < text.length(); j++){
                if (text.charAt(i) == text.charAt(j) && text.charAt(i)!= mostUsableLetter){
                    counter++;
                }
            }
            if (counter > secondCounter){
                secondCounter = counter;
                secondUsableLetter = text.charAt(i);
            }
        }
        return secondUsableLetter;
    }

    public static void main(String[] args) {
        String text = "sasha kotliar";
        String textAfterChanging = changedText(text);
        System.out.println(textAfterChanging);
    }
}
