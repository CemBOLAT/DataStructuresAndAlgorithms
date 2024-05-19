import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

public class RandomInputGenerator {
    public static int MAX_STOCKS = 1000;
    public static int deleted[] = new int[MAX_STOCKS];
    public static Set<String> symbols = new java.util.HashSet<>();

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
        return min + (int) (Math.random() * (max - min));
    }

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(getRandomNumberInRange(0, characters.length())));
        }
        return result.toString();
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

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            //Add 1000 random stocks
            for (int i = 0; i < 1000; i++) {
                do {
                    String symbol = generateRandomString(5);
                    if (!symbols.contains(symbol)) {
                        symbols.add(symbol);
                        writer.println("ADD " + symbol + " " + generateRandomPrice() + " " + generateRandomVolume() + " " + generateRandomMarketCap());
                        break;
                    }
                } while (true);
            }

            for (int i = 0; i < 300; i++) {
                do {
                    int nbr = getRandomNumberInRange(0, 1000);
                    if (deleted[nbr] != 1) {
                        //  get the element at that index in set
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("REMOVE " + symbol);
                        deleted[nbr] = 1;
                        break;
                    }
                } while (true);
            }

            for (int i = 0; i < 300; i++) {
               do {
                   int nbr = getRandomNumberInRange(0, 1000);
                   if (deleted[nbr] != 1) {
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("SEARCH " + symbol);
                        break;
                   }
               } while (true);
            }

            for (int i = 0; i < 300; i++) {
               do {
                   int nbr = getRandomNumberInRange(0, 1000);
                   if (deleted[nbr] != 1) {
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("UPDATE " + symbol + " " + generateRandomPrice() + " " + generateRandomVolume() + " " + generateRandomMarketCap());
                        break;
                   }
               } while (true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
