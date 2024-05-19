
public class StockDataManager{

    private AVLTree root;

    public StockDataManager(){
        root = new AVLTree();
    }

    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = root.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            root.insert(newStock);
        }
    }

    // Remove a stock
    public void removeStock(String symbol) {
        root.delete(symbol);
    }

    // Search for a stock
    public Stock searchStock(String symbol) {
        return root.search(symbol);
    }

    public void updateStock(String symbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = root.search(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }
}
