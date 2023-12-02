import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class Main {
    // get cookie from .env
    private static final Dotenv dotenv = Dotenv.load();
    private static final String cookie = dotenv.get("SESSION");

    // What is todays date? (1-25) if after 5am, use today, if before 5am, use yesterday
    private static final Calendar cal = Calendar.getInstance();
    private static final int day = cal.get(Calendar.DAY_OF_MONTH);
    private static final int hour = cal.get(Calendar.HOUR_OF_DAY);
    private static final int currentChallenge = hour >= 5 ? day : day - 1;

    private static void getInput(int dayString) {
        System.out.println("Getting input for challenge " + dayString);
        // TODO: use the day to query the website for input data for the specific day
        String day = String.valueOf(dayString);
        try {
            URL url = new URL("https://adventofcode.com/2023/day/" + day + "/input");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Cookie", "session=" + cookie);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String input;
                StringBuilder sb = new StringBuilder();
                while ((input = br.readLine()) != null) {
                    sb.append(input);
                    sb.append("\r\n");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                String data = sb.toString();

                // write data to file
                String fileName = "src/main/resources/day" + dayString + ".txt";
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    fos.write(data.getBytes());
                }
            } catch (IOException e) {
                System.out.println("Error reading input data for day " + dayString);
                System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAllInputs() {
        for (int i = 1; i <= currentChallenge; i++) {
            getInput(i);
        }
    }

    private static void runAllDays() {
        try {
            Day1.main(null);
            Day2.main(null);
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getAllInputs();
        runAllDays();
    }
}
