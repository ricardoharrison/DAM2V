### 1\. Random Three-Digit Number:

```java

import java.util.Random;

public class RandomNumberExample {

    public static void main(String[] args) {

        if (args.length != 1) {

            System.out.println("Usage: java RandomNumberExample <numberOfDigits>");

            return;

        }

        int numberOfDigits = Integer.parseInt(args[0]);

        int randomNumber = generateRandomNumber(numberOfDigits);

        System.out.println("Random Number with " + numberOfDigits + " digits: " + randomNumber);

    }

    private static int generateRandomNumber(int numberOfDigits) {

        Random random = new Random();

        int minBound = (int) Math.pow(10, numberOfDigits - 1);

        int maxBound = (int) Math.pow(10, numberOfDigits) - 1;

        return random.nextInt(maxBound - minBound + 1) + minBound;

    }

}
```


### 2\. Random Color:
```java
private int generateRandomColor() {\
    int randomR = (int)(Math.random() * MAX_COLOR_RANGE);\
    int randomG = (int)(Math.random() * MAX_COLOR_RANGE);\
    int randomB = (int)(Math.random() * MAX_COLOR_RANGE);

    return Color.rgb(randomR, randomG, randomB);\
}
```
### 3\. Random Letter:

```java

public class RandomLetterExample {
    public static void main(String[] args) {
        char randomLetter = (char) ('A' + Math.random() * 26); // Generates a random uppercase letter
        System.out.println("Random Letter: " + randomLetter);
    }
}
```
### 4\. Random Boolean:
```java

public class RandomBooleanExample {
    public static void main(String[] args) {
        boolean randomBoolean = Math.random() < 0.5; // Generates a random boolean
        System.out.println("Random Boolean: " + randomBoolean);
    }
}
```