import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    private static final HashMap<String, Integer> bagContents = new HashMap<>();

    private static final Path inputPath = Path.of("src/main/resources/day2.txt");

    private static void part1() {
        int sum = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Game ID: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                String gameID = line.split(":")[0].split(" ")[1];
                String gameContents = line.split(":")[1].trim();
                String[] revelations = gameContents.split(";");
                gameLoop:
                while (true) {
                    for (String s : revelations) {
                        String[] cubes = s.split(", ");
                        for (String cube : cubes) {
                            cube = cube.trim();
                            int quantity = Integer.parseInt(cube.split(" ")[0]);
                            String colour = cube.split(" ")[1];
                            if (bagContents.containsKey(colour)) {
                                if (bagContents.get(colour) < quantity) {
                                    break gameLoop;
                                }
                            }
                        }
                    }
                    sum += Integer.parseInt(gameID);
                    break;
                }
            }
            System.out.println("Sum of all valid game IDs: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void part2() {
        int power = 0;
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String gameContents = line.replaceAll(";", ",").split(":")[1].trim();

                HashMap<String, Integer> minimumValues = new HashMap<>();

                String[] cubes = gameContents.split(",");

                for (String cube : cubes) {
                    cube = cube.trim();
                    // format should now be: QTY COLOUR
                    int qty = Integer.parseInt(cube.split(" ")[0]);
                    String colour = cube.split(" ")[1];
                    if (minimumValues.containsKey(colour)) {
                        if (minimumValues.get(colour) < qty) {
                            minimumValues.put(colour, qty);
                        }
                    } else {
                        minimumValues.put(colour, qty);
                    }
                }
                int cpower = 0;
                for (Map.Entry<String, Integer> entry : minimumValues.entrySet()) {
                    if (cpower == 0) {
                        cpower = entry.getValue();
                    } else {
                        cpower = cpower * entry.getValue();
                    }
                }
                power = power + cpower;
            }

            System.out.println("Power: " + power);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        bagContents.put("red", 12);
        bagContents.put("green", 13);
        bagContents.put("blue", 14);
        System.out.println("Day 2 - Part 1");
        part1();

        System.out.println("Day 2 - Part 2");
        part2();
    }
}