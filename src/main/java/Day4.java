import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day4 {

    private static final Path inputPath = Path.of("src/main/resources/day4.txt");

    private static final Hashtable<Integer, Integer> howManyWinningNumbers = new Hashtable<>();

    public static void part1() {
        int total = 0;
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                int cardTotal = 0;
                // format: Card 1: 21 22 24 | 5 98 77 54
                // the numbers before the pipe are the card numbers
                // the numbers after the pipe are the winning numbers
                // convert any blank spaces to a single space
                line = line.replaceAll("\\s+", " ");
                int cardNumber = Integer.parseInt(line.split(":")[0].trim().split(" ")[1]);
                String cardNumbersWithID = line.split("\\|")[0].trim();
                String cardNumbers = cardNumbersWithID.split(":")[1].trim();
                String winningNumbers = line.split("\\|")[1].trim();
                // replace any double spaces with a single space
                cardNumbers = cardNumbers.replaceAll("\\s+", " ");
                winningNumbers = winningNumbers.replaceAll("\\s+", " ");
                int[] cardNumbersInts = Arrays.stream(cardNumbers.split(" ")).mapToInt(Integer::parseInt).toArray();
                int[] winningNumbersInts = Arrays.stream(winningNumbers.split(" ")).mapToInt(Integer::parseInt).toArray();

                for (int i : cardNumbersInts) {
                    if (Arrays.stream(winningNumbersInts).anyMatch(x -> x == i)) {
                        if (cardTotal == 0) {
                            cardTotal = 1;
                            howManyWinningNumbers.put(cardNumber, 1);
                        } else {
                            cardTotal *= 2;
                            howManyWinningNumbers.put(cardNumber, howManyWinningNumbers.get(cardNumber) + 1);
                        }
                    } else if (!howManyWinningNumbers.containsKey(cardNumber)) {
                        howManyWinningNumbers.put(cardNumber, 0);
                    }
                }
                total += cardTotal;
            }
            System.out.println("Total: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void part2() {
        Hashtable<Integer, Integer> cardQty = new Hashtable<>();
        LinkedHashMap<Integer, int[]> cardNumbersMap = new LinkedHashMap<>();
        Hashtable<Integer, int[]> winningNumbersMap = new Hashtable<>();
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\s+", " ");
                int cardNumber = Integer.parseInt(line.split(":")[0].trim().split(" ")[1]);
                String cardNumbersWithID = line.split("\\|")[0].trim();
                String cardNumbers = cardNumbersWithID.split(":")[1].trim();
                String winningNumbers = line.split("\\|")[1].trim();
                // replace any double spaces with a single space
                cardNumbers = cardNumbers.replaceAll("\\s+", " ");
                winningNumbers = winningNumbers.replaceAll("\\s+", " ");
                int[] cardNumbersInts = Arrays.stream(cardNumbers.split(" ")).mapToInt(Integer::parseInt).toArray();
                int[] winningNumbersInts = Arrays.stream(winningNumbers.split(" ")).mapToInt(Integer::parseInt).toArray();

                cardQty.put(cardNumber, 1);
                cardNumbersMap.put(cardNumber, cardNumbersInts);
                winningNumbersMap.put(cardNumber, winningNumbersInts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, int[]> card : cardNumbersMap.entrySet()) {
            int thisCardQty = cardQty.get(card.getKey());
            int thisCardID = card.getKey();
            int thisCardWinners = howManyWinningNumbers.get(thisCardID);

            int[] cardsWon = new int[thisCardWinners];
            int k = thisCardID;
            for (int i = 0; i < thisCardWinners; i++) {
                cardsWon[i] = k + 1;
                k++;
            }

            for (int i : cardsWon) {
                cardQty.put(i, cardQty.get(i) + thisCardQty);
            }
        }

        int total = 0;
        for (Map.Entry<Integer, Integer> card : cardQty.entrySet()) {
            total += card.getValue();
        }

        System.out.println("Total: " + total);

    }

    public static void main(String[] args) {
        System.out.println("Day 4 - Part 1");
        part1();
        System.out.println("Day 4 - Part 2");
        part2();
    }
}
