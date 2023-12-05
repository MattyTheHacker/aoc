import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Day5 {

    private static final Path inputPath = Paths.get("src/main/resources/day5.txt");

    private static final HashMap<Integer, Seed> seeds = new HashMap<>();

    private static void getSeedsFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("seeds:")) {
                    line = line.replaceAll("\\s+", " ");
                    String seedsLine = line.split(":")[1].trim();
                    String[] seedIDs = seedsLine.split(" ");
                    for (String seedID : seedIDs) {
                        int id = Integer.parseInt(seedID);
                        seeds.put(Integer.parseInt(seedID), new Seed(id));
                    }
                } else if (line.contains("seed-to-soil map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }

                        line = line.replaceAll("\\s+", " ");
                        String[] seedToSoil = line.split(" ");
                        int numIDs = Integer.parseInt(seedToSoil[2]);

                        int firstSeed = Integer.parseInt(seedToSoil[1]);
                        int firstSoil = Integer.parseInt(seedToSoil[0]);

                        for (int i = 0; i < numIDs; i++) {

                            if (seeds.containsKey(firstSeed)) {
                                seeds.get(firstSeed).setSoil(firstSoil);
                            }

                            firstSeed++;
                            firstSoil++;
                        }
                    }
                } else if (line.contains("soil-to-fertilizer map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstSoil = Integer.parseInt(soilToFert[1]);
                        int firstFert = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getSoil() == firstSoil) {
                                    seed.setFertilizer(firstFert);
                                }
                            }

                            firstSoil++;
                            firstFert++;
                        }
                        for (Seed seed : seeds.values()) {
                            if (seed.getFertilizer() == 0) {
                                seed.setFertilizer(seed.getSoil());
                            }
                        }
                    }
                } else if (line.contains("fertilizer-to-water map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstFert = Integer.parseInt(soilToFert[1]);
                        int firstWater = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getFertilizer() == firstFert) {
                                    seed.setWater(firstWater);
                                }
                            }

                            firstFert++;
                            firstWater++;
                        }

                        for (Seed seed : seeds.values()) {
                            if (seed.getWater() == 0) {
                                seed.setWater(seed.getFertilizer());
                            }
                        }
                    }
                } else if (line.contains("water-to-light map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstWater = Integer.parseInt(soilToFert[1]);
                        int firstLight = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getWater() == firstWater) {
                                    seed.setLight(firstLight);
                                }
                            }

                            firstWater++;
                            firstLight++;
                        }

                        for (Seed seed : seeds.values()) {
                            if (seed.getLight() == 0) {
                                seed.setLight(seed.getWater());
                            }
                        }
                    }
                } else if (line.contains("light-to-temperature map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstLight = Integer.parseInt(soilToFert[1]);
                        int firstTemp = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getLight() == firstLight) {
                                    seed.setTemp(firstTemp);
                                }
                            }

                            firstLight++;
                            firstTemp++;
                        }

                        for (Seed seed : seeds.values()) {
                            if (seed.getTemp() == 0) {
                                seed.setTemp(seed.getLight());
                            }
                        }
                    }
                } else if (line.contains("temperature-to-humidity map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstTemp = Integer.parseInt(soilToFert[1]);
                        int firstHumidity = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getTemp() == firstTemp) {
                                    seed.setHumidity(firstHumidity);
                                }
                            }

                            firstTemp++;
                            firstHumidity++;
                        }

                        for (Seed seed : seeds.values()) {
                            if (seed.getHumidity() == 0) {
                                seed.setHumidity(seed.getTemp());
                            }
                        }
                    }
                } else if (line.contains("humidity-to-location map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        int firstHumidity = Integer.parseInt(soilToFert[1]);
                        int firstLoc = Integer.parseInt(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getHumidity() == firstHumidity) {
                                    seed.setLoc(firstLoc);
                                }
                            }

                            firstHumidity++;
                            firstLoc++;
                        }

                        for (Seed seed : seeds.values()) {
                            if (seed.getLoc() == 0) {
                                seed.setLoc(seed.getHumidity());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void part1() {
        getSeedsFromFile();
        int lowest = Integer.MAX_VALUE;
        for (Seed seed : seeds.values()) {
            if (seed.getLoc() < lowest) {
                lowest = seed.getLoc();
            }
        }

        System.out.println("Lowest location ID: " + lowest);
    }

    public static void main(String[] args) {
        System.out.println("Day 5 - Part 1");
        part1();
        System.out.println("Day 5 - Part 2");
    }

    private static class Seed {

        private int id, soil, fertilizer, water, light, temp, humidity, loc;

        Seed(int id) {
            this.id = id;
            this.soil = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSoil() {
            return soil;
        }

        public void setSoil(int soil) {
            this.soil = soil;
            this.fertilizer = soil;
        }

        public int getFertilizer() {
            return fertilizer;
        }

        public void setFertilizer(int fertilizer) {
            this.fertilizer = fertilizer;
            this.water = fertilizer;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
            this.light = water;
        }

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
            this.temp = light;
        }

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
            this.humidity = temp;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
            this.loc = humidity;
        }

        public int getLoc() {
            return loc;
        }

        public void setLoc(int loc) {
            this.loc = loc;
        }

        @Override
        public String toString() {
            return "Seed{" +
                    "id=" + id +
                    ", soil=" + soil +
                    ", fertilizer=" + fertilizer +
                    ", water=" + water +
                    ", light=" + light +
                    ", temp=" + temp +
                    ", humidity=" + humidity +
                    ", loc=" + loc +
                    '}';
        }
    }
}
