import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RandomInputGenerator {
    public static int MAX_STOCKS = 1000;
    public static int deleted[] = new int[MAX_STOCKS];

    public double generateRandomPrice() {
        return Math.random() * 1000;
    }

    public long generateRandomVolume() {
        return (long) (Math.random() * 1000000);
    }

    public long generateRandomMarketCap() {
        return (long) (Math.random() * 1000000000);
    }

    public int getRandomNumberInRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void generateRandomInputs(String inputFile) {
        // Implement this method
        File file = new File(inputFile);

        // create a new file if it does not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // write to the file

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            //Add 1000 random stocks
            for (int i = 0; i < 1000; i++) {
                writer.println("ADD " + "Symbol" + (i + 1) + " " + generateRandomPrice() + " " + generateRandomVolume() + " " + generateRandomMarketCap());
            }

            //for (int i = 0; i < 300; i++) {
            //    int nbr = getRandomNumberInRange(1, 1000);
            //    deleted[nbr] = 1;
            //    writer.println("REMOVE " + "Symbol" + nbr);
            //}
//
            //for (int i = 0; i < 300; i++) {
            //    do {
            //        int nbr = getRandomNumberInRange(1, 1000);
            //        if (deleted[nbr] != 1) {
            //            writer.println("SEARCH " + "Symbol" + nbr);
            //            break;
            //        }
            //    } while (true);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}