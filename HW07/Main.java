import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static GUIVisualization addVisualization = new GUIVisualization("scatter", "Add Visualization");
    public static GUIVisualization searchVisualization = new GUIVisualization("scatter", "Search Visualization");
    public static GUIVisualization removeVisualization = new GUIVisualization("scatter", "Remove Visualization");
    public static GUIVisualization updateVisualization = new GUIVisualization("scatter", "Update Visualization");

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

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
        for (int i = 1; i <= 100000 / 5; i += 1000) {
            performPerformanceAnalysis(manager, i * 10);
        }

        addVisualization.setVisible(true);
        searchVisualization.setVisible(true);
        removeVisualization.setVisible(true);
        updateVisualization.setVisible(true);
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

        // Measure time for ADD operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        System.out.println("Average ADD time: " + (endTime - startTime) / size + " ns");
        addVisualization.addDataPoint(size, (endTime - startTime) / size);

        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Average SEARCH time: " + (endTime - startTime) / size + " ns");
        searchVisualization.addDataPoint(size, (endTime - startTime) / size);

        // Measure time for REMOVE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.removeStock("SYM" + i);
        }
        endTime = System.nanoTime();
        removeVisualization.addDataPoint(size, (endTime - startTime) / size);
        System.out.println("Average REMOVE time: " + (endTime - startTime) / size + " ns");

        // Measure time for UPDATE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.updateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        updateVisualization.addDataPoint(size, (endTime - startTime) / size);
        System.out.println("Average UPDATE time: " + (endTime - startTime) / size + " ns");
        addVisualization.repaint();
        searchVisualization.repaint();
        removeVisualization.repaint();
        updateVisualization.repaint();
    }
}
