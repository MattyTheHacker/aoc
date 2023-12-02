import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    private static Map<String, Integer> wordsToNumbers() {
        Map<String, Integer> wordsToNumbers = new HashMap<>();
        wordsToNumbers.put("zero", 0);
        wordsToNumbers.put("one", 1);
        wordsToNumbers.put("two", 2);
        wordsToNumbers.put("three", 3);
        wordsToNumbers.put("four", 4);
        wordsToNumbers.put("five", 5);
        wordsToNumbers.put("six", 6);
        wordsToNumbers.put("seven", 7);
        wordsToNumbers.put("eight", 8);
        wordsToNumbers.put("nine", 9);
        wordsToNumbers.put("ten", 10);
        wordsToNumbers.put("oneight", 18);
        wordsToNumbers.put("twone", 21);
        wordsToNumbers.put("eightwo", 82);
        return wordsToNumbers;
    }

    private static void partOne() {
        // read in a file
        Path filePath = Path.of("src/main/resources/day1.txt");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = null;
            int sum = 0;

            while ((line = reader.readLine()) != null) {
                char[] digits = new char[100];
                // extract any numbers and append them to the char array
                int counter = 0;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        digits[counter] = c;
                        counter++;
                    }
                }
                if (counter == 1) {
                    // append the value to itself and convert to int
                    sum += Integer.parseInt(String.valueOf(digits[0]) + String.valueOf(digits[0]));
                } else if (counter > 1) {
                    // append the last digit to the first and convert to int
                    sum += Integer.parseInt(String.valueOf(digits[0]) + String.valueOf(digits[counter - 1]));
                } else {
                    System.out.println("No digits found");
                }
            }
            System.out.println("Sum: " + sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int extractNumber(String input) {
        Map<String, Integer> wordToNumber = wordsToNumbers();
        StringBuilder sb = new StringBuilder();

        Pattern p = Pattern.compile("(?:\\d+|oneight|twone|eightwo|one|two|three|four|five|six|seven|eight|nine)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            String match = m.group().toLowerCase();
            if (wordToNumber.containsKey(match)) {
                sb.append(wordToNumber.get(match));
            } else {
                sb.append(match);
            }
        }

        String number = "";
        number += sb.charAt(0);
        number += sb.charAt(sb.length() - 1);

        return Integer.parseInt(number);
    }

    private static void partTwo() throws IOException {
        Path filePath = Path.of("src/main/resources/day1.txt");
        List<String> lines = Files.readAllLines(filePath);
        int ans = 0;

        for (String line : lines) {
            ans += extractNumber(line);
        }

        System.out.println("Sum: " + ans);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Day 1 - Part 1");
        partOne();

        System.out.println("Day 1 - Part 2");
        partTwo();
    }
}
