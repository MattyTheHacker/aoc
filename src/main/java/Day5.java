import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Day5 {

    private static final Path inputPath = Paths.get("src/main/resources/day5.txt");

    private static final HashMap<Long, Seed> seeds = new HashMap<>();

    private static void getSeedsFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("seeds:")) {
                    line = line.replaceAll("\\s+", " ");
                    String seedsLine = line.split(":")[1].trim();
                    String[] seedIDs = seedsLine.split(" ");
                    for (String seedID : seedIDs) {
                        long id = Long.parseLong(seedID);
                        seeds.put(id, new Seed(id));
                    }
                } else if (line.contains("seed-to-soil map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }

                        line = line.replaceAll("\\s+", " ");
                        String[] seedToSoil = line.split(" ");
                        int numIDs = Integer.parseInt(seedToSoil[2]);

                        long firstSeed = Long.parseLong(seedToSoil[1]);
                        long firstSoil = Long.parseLong(seedToSoil[0]);

                        for (int i = 0; i < numIDs; i++) {

                            if (seeds.containsKey(firstSeed)) {
                                seeds.get(firstSeed).setSoil(firstSoil);
                                System.out.println("Set seed " + firstSeed + " to soil " + firstSoil);
                            }

                            firstSeed++;
                            firstSoil++;
                        }
                    }
                    System.out.println("Completed seed-to-soil map");
                } else if (line.contains("soil-to-fertilizer map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstSoil = Long.parseLong(soilToFert[1]);
                        long firstFert = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getSoil() == firstSoil) {
                                    seed.setFertilizer(firstFert);
                                    System.out.println("Set seed " + seed.getId() + " to fertilizer " + firstFert);
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
                    System.out.println("Completed soil-to-fertilizer map");
                } else if (line.contains("fertilizer-to-water map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstFert = Long.parseLong(soilToFert[1]);
                        long firstWater = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getFertilizer() == firstFert) {
                                    seed.setWater(firstWater);
                                    System.out.println("Set seed " + seed.getId() + " to water " + firstWater);
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
                    System.out.println("Completed fertilizer-to-water map");
                } else if (line.contains("water-to-light map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstWater = Long.parseLong(soilToFert[1]);
                        long firstLight = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getWater() == firstWater) {
                                    seed.setLight(firstLight);
                                    System.out.println("Set seed " + seed.getId() + " to light " + firstLight);
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
                    System.out.println("Completed water-to-light map");
                } else if (line.contains("light-to-temperature map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstLight = Long.parseLong(soilToFert[1]);
                        long firstTemp = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getLight() == firstLight) {
                                    seed.setTemp(firstTemp);
                                    System.out.println("Set seed " + seed.getId() + " to temp " + firstTemp);
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
                    System.out.println("Completed light-to-temperature map");
                } else if (line.contains("temperature-to-humidity map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstTemp = Long.parseLong(soilToFert[1]);
                        long firstHumidity = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getTemp() == firstTemp) {
                                    seed.setHumidity(firstHumidity);
                                    System.out.println("Set seed " + seed.getId() + " to humidity " + firstHumidity);
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
                    System.out.println("Completed temperature-to-humidity map");
                } else if (line.contains("humidity-to-location map:")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("")) {
                            break;
                        }
                        line = line.replaceAll("\\s+", " ");

                        String[] soilToFert = line.split(" ");
                        int numToGo = Integer.parseInt(soilToFert[2]);

                        long firstHumidity = Long.parseLong(soilToFert[1]);
                        long firstLoc = Long.parseLong(soilToFert[0]);

                        for (int i = 0; i < numToGo; i++) {
                            for (Seed seed : seeds.values()) {
                                if (seed.getHumidity() == firstHumidity) {
                                    seed.setLoc(firstLoc);
                                    System.out.println("Set seed " + seed.getId() + " to location " + firstLoc);
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
                        System.out.println("Completed humidity-to-location map");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void part1() {
        // measure time taken
        long startTime = System.nanoTime();
        getSeedsFromFile();
        long lowest = Integer.MAX_VALUE;
        for (Seed seed : seeds.values()) {
            if (seed.getLoc() < lowest) {
                lowest = seed.getLoc();
            }
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Lowest location ID: " + lowest);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }

    public static void main(String[] args) {
        System.out.println("Day 5 - Part 1");
        part1();
        System.out.println("Day 5 - Part 2");
    }

    private static class Seed {

        private long id, soil, fertilizer, water, light, temp, humidity, loc;

        Seed(long id) {
            this.id = id;
            this.soil = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getSoil() {
            return soil;
        }

        public void setSoil(long soil) {
            this.soil = soil;
            this.fertilizer = soil;
        }

        public long getFertilizer() {
            return fertilizer;
        }

        public void setFertilizer(long fertilizer) {
            this.fertilizer = fertilizer;
            this.water = fertilizer;
        }

        public long getWater() {
            return water;
        }

        public void setWater(long water) {
            this.water = water;
            this.light = water;
        }

        public long getLight() {
            return light;
        }

        public void setLight(long light) {
            this.light = light;
            this.temp = light;
        }

        public long getTemp() {
            return temp;
        }

        public void setTemp(long temp) {
            this.temp = temp;
            this.humidity = temp;
        }

        public long getHumidity() {
            return humidity;
        }

        public void setHumidity(long humidity) {
            this.humidity = humidity;
            this.loc = humidity;
        }

        public long getLoc() {
            return loc;
        }

        public void setLoc(long loc) {
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
