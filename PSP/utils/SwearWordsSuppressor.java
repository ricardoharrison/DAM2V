package utils;

import java.util.ArrayList;

public class SwearWordsSuppressor {

    public static String suppressBadWords(String input, ArrayList<String> badWords) {
        StringBuilder result = new StringBuilder(input);

        for (String badWord : badWords) {
            int startIndex = result.indexOf(badWord);
            while (startIndex != -1) {
                // Check if the found bad word is a standalone word
                if ((startIndex == 0 || !Character.isLetter(result.charAt(startIndex - 1))) &&
                        (startIndex + badWord.length() == result.length()
                                || !Character.isLetter(result.charAt(startIndex + badWord.length())))) {

                    // Replace bad word with suppressed version
                    String suppressed = result.charAt(startIndex) + "*".repeat(badWord.length() - 1);
                    result.replace(startIndex, startIndex + badWord.length(), suppressed);

                    // Move to the next occurrence
                    startIndex = result.indexOf(badWord, startIndex + suppressed.length());
                } else {
                    // Move to the next occurrence, skipping substrings inside other words
                    startIndex = result.indexOf(badWord, startIndex + badWord.length());
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> badWords = new ArrayList<>();
        badWords.add("bad");
        badWords.add("stupid");

        String input = "This is the stupidest of a number of bad examples of a stupid test.";
        String processed = suppressBadWords(input, badWords);

        System.out.println("Original: " + input);
        System.out.println("Processed: " + processed);
    }
}
