import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;


public class Main {
    public static List<Integer> dataPointsX = new ArrayList<>(); // List to store x-axis data points
    public static List<Long> addPointsY = new ArrayList<>(); // List to store y-axis data points
    public static List<Long> searchPointsY = new ArrayList<>(); // List to store y-axis data points  
    public static List<Long> removePointsY = new ArrayList<>(); // List to store y-axis data points
    public static List<Long> updatePointsY = new ArrayList<>(); // List to store y-axis data points

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
        for(int i = 2; i < 10; i++)
        {
            dataPointsX.add(i * 10000);
        }

        for(int i = 1; i < 10; i++)
        {
            performPerformanceAnalysis(manager, i * 10000, i != 1);
        }

        // Print the data points
        SwingUtilities.invokeLater(() -> {
            GUIVisualization addGui = new GUIVisualization("scatter", dataPointsX, addPointsY, "ADD Visualization");
            addGui.setVisible(true);

            GUIVisualization searchGui = new GUIVisualization("scatter", dataPointsX, searchPointsY, "SEARCH Visualization");
            searchGui.setVisible(true);

            GUIVisualization removeGui = new GUIVisualization("scatter", dataPointsX, removePointsY, "REMOVE Visualization");
            removeGui.setVisible(true);

            GUIVisualization updateGui = new GUIVisualization("scatter", dataPointsX, updatePointsY, "UPDATE Visualization");
            updateGui.setVisible(true);
        });
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

    private static void performPerformanceAnalysis(StockDataManager manager, int size, boolean firstRun) {
        long startTime, endTime;

        // Measure time for ADD operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        if (firstRun) {
            System.out.println("Average ADD time: " + (endTime - startTime) / size + " ns");
            addPointsY.add((endTime - startTime) / size);
        }

        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        if (firstRun){
            System.out.println("Average SEARCH time: " + (endTime - startTime) / size + " ns");
            searchPointsY.add((endTime - startTime) / size);
        }
        // Measure time for REMOVE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.removeStock("SYM" + i);
        }
        endTime = System.nanoTime();
        if (firstRun){
            System.out.println("Average REMOVE time: " + (endTime - startTime) / size + " ns");
            removePointsY.add((endTime - startTime) / size);
        }

        // Measure time for UPDATE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.updateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        if (firstRun){
            System.out.println("Average UPDATE time: " + (endTime - startTime) / size + " ns");
            updatePointsY.add((endTime - startTime) / size);
        }
        System.out.println("*".repeat(50));
    }
}
