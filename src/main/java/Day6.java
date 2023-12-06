import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Day6 {

    private static final Path inputPath = Path.of("src/main/resources/day6.txt");

    private static final HashMap<Integer, Race> races = new HashMap<>();

    private static class Race {

        private long time, recordDistance, numOfVictoryConditions;

        Race(long time, long recordDistance) {
            this.time = time;
            this.recordDistance = recordDistance;
            this.numOfVictoryConditions = 0;
        }

        private void calcVictoryConditions() {
            for (long wait = 0; wait < time; wait++) {
                long achieved = (time - wait) * wait;
                if (achieved >= recordDistance) {
                    numOfVictoryConditions++;
                }
            }
        }

        private long getNumOfVictoryConditions() {
            return numOfVictoryConditions;
        }
    }

    private static void p1getRacesFromInput() {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String times = reader.readLine();
            String distances = reader.readLine();

            times = times.split(":")[1].trim();
            distances = distances.split(":")[1].trim();

            // convert all spaces to single spaces
            times = times.replaceAll("\\s+", " ");
            distances = distances.replaceAll("\\s+", " ");

            String[] timeArr = times.split(" ");
            String[] distanceArr = distances.split(" ");

            int i = 0;

            for (String time : timeArr) {
                races.put(i, new Race(Integer.parseInt(time), Integer.parseInt(distanceArr[i])));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void part1() {
        p1getRacesFromInput();

        long possibleVictoryConditions = 1;

        for (Race race : races.values()) {
            race.calcVictoryConditions();
            possibleVictoryConditions *= race.getNumOfVictoryConditions();
        }
        System.out.println("Possible victory conditions: " + possibleVictoryConditions);
    }

    private static void part2() {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String time = reader.readLine();
            String distance = reader.readLine();

            // convert all spaces to single spaces
            time = time.replaceAll("\\s+", "");
            distance = distance.replaceAll("\\s+", "");

            time = time.split(":")[1];
            distance = distance.split(":")[1];

            long timeLong = Long.parseLong(time);
            long distanceLong = Long.parseLong(distance);

            races.put(1,new Race(timeLong, distanceLong));

            races.get(1).calcVictoryConditions();

            System.out.println(races.get(1).getNumOfVictoryConditions());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Day 6 - Part 1");
        part1();
        System.out.println("Day 6 - Part 2");
        part2();
    }
}
