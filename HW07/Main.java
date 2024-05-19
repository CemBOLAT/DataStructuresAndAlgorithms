import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static RandomInputGenerator generator = new RandomInputGenerator();
    public static GUIVisualization visualization = new GUIVisualization("line");

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        generator.generateRandomInputs(args[0], 1000);
        visualization.setVisible(true);

        // Add lines for different operations
        visualization.addLine(Color.RED); // ADD operation
        visualization.addLine(Color.BLUE); // SEARCH operation
        visualization.addLine(Color.GREEN); // REMOVE operation

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform a simple performance analysis
        performPerformanceAnalysis(manager, 1000);
        performPerformanceAnalysis(manager, 1000);
        performPerformanceAnalysis(manager, 1000);
        performPerformanceAnalysis(manager, 1000);
        performPerformanceAnalysis(manager, 1000);
    }

    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void performPerformanceAnalysis(StockDataManager manager, int size) {
        long startTime, endTime;
        long totalAddTime = 0, totalSearchTime = 0, totalRemoveTime = 0;

        // Measure time for ADD operation
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            endTime = System.nanoTime();
            totalAddTime += (endTime - startTime);
        }
        long averageAddTime = totalAddTime / size;
        visualization.addDataPoint(0, manager.getSize(), averageAddTime);
		System.out.println("Average ADD time: " + averageAddTime + " ns");

        // Measure time for SEARCH operation
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.searchStock("SYM" + i);
            endTime = System.nanoTime();
            totalSearchTime += (endTime - startTime);
        }
        long averageSearchTime = totalSearchTime / size;
        visualization.addDataPoint(1, manager.getSize(), averageSearchTime);
        System.out.println("Average SEARCH time: " + averageSearchTime + " ns");

        // Measure time for REMOVE operation
        for (int i = 0; i < size; i++) {
            startTime = System.nanoTime();
            manager.removeStock("SYM" + i);
            endTime = System.nanoTime();
            totalRemoveTime += (endTime - startTime);
        }
        long averageRemoveTime = totalRemoveTime / size;
        visualization.addDataPoint(2, manager.getSize(), averageRemoveTime);
        System.out.println("Average REMOVE time: " + averageRemoveTime + " ns");

        System.out.println("***********************************");
        visualization.repaint();  // Update the visualization
    }
}
