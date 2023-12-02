import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    private static void getInput(int Day) {
        // TODO: use the day to query the website for input data for the specific day
        String day = String.valueOf(Day);
        try {
            URL url = new URL("https://adventofcode.com/2023/day/" + day + "/input");
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }


    }
}
