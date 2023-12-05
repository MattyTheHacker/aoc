import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day5 {

    private static final Path inputPath = Paths.get("src/main/resources/day5.txt");

    ArrayList<Seed> seeds = new ArrayList<>();

    private class Seed {

        int id, soil, fertilizer, water, light, temp, humidity, loc;
        Seed(int id, int soil, int fertilizer, int water, int light, int temp, int humidity, int loc) {
            this.id = id;
            this.soil = soil;
            this.fertilizer = fertilizer;
            this.water = water;
            this.light = light;
            this.temp = temp;
            this.humidity = humidity;
            this.loc = loc;
        }

        private static void getSeedsFromFile(){
            try (BufferedReader reader = Files.newBufferedReader(inputPath)) {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void part1() {

    }
    public static void main(String[] args) {
        System.out.println("Day 5 - Part 1");
        System.out.println("Day 5 - Part 2");
    }
}
