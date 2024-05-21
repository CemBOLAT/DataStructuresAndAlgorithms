import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

public class RandomInputGenerator {
    public static int MAX_STOCKS;
    public static int deleted[];
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

    public void generateRandomInputs(String inputFile, int numAdd, int numRemove, int numSearch, int numUpdate) {
        // Implement this method
        File file = new File(inputFile);
        MAX_STOCKS = numAdd;
        deleted = new int[MAX_STOCKS];

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
            for (int i = 0; i < MAX_STOCKS; i++) {
                do {
                    String symbol = generateRandomString(5);
                    if (!symbols.contains(symbol)) {
                        symbols.add(symbol);
                        writer.println("ADD " + symbol + " " + generateRandomPrice() + " " + generateRandomVolume() + " " + generateRandomMarketCap());
                        break;
                    }
                } while (true);
            }

            for (int i = 0; i < numRemove; i++) {
                do {
                    int nbr = getRandomNumberInRange(0, MAX_STOCKS);
                    if (deleted[nbr] != 1) {
                        //  get the element at that index in set
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("REMOVE " + symbol);
                        deleted[nbr] = 1;
                        break;
                    }
                } while (true);
            }

            for (int i = 0; i < numSearch; i++) {
               do {
                   int nbr = getRandomNumberInRange(0, MAX_STOCKS);
                   if (deleted[nbr] != 1) {
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("SEARCH " + symbol);
                        break;
                   }
               } while (true);
            }

            for (int i = 0; i < numUpdate; i++) {
               do {
                   int nbr = getRandomNumberInRange(0, MAX_STOCKS);
                   if (deleted[nbr] != 1) {
                        String symbol = (String) symbols.toArray()[nbr];
                        writer.println("UPDATE " + symbol + " " + generateRandomPrice() + " " + generateRandomVolume() + " " + generateRandomMarketCap());
                        break;
                   }
               } while (true);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java RandomInputGenerator <input_file> <numAdd> <numRemove> <numSearch> <numUpdate>");
            return;
        }

        RandomInputGenerator randomInputGenerator = new RandomInputGenerator();
        randomInputGenerator.generateRandomInputs(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
    }
}
