import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    // get cookie from .env
    private static final Dotenv dotenv = Dotenv.load();
    private static final String cookie = dotenv.get("SESSION");

    private static void getInput(int dayString) {
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
        for (int i = 1; i <= 3; i++) {
            getInput(i);
        }
    }

    public static void main(String[] args) {
        getAllInputs();
    }
}
