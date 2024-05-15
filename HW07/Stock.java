
public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    public Stock(String symbol, double price, long volume, long marketCap) throws IllegalArgumentException {
        setSymbol(symbol);
        setPrice(price);
        setVolume(volume);
        setMarketCap(marketCap);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) throws IllegalArgumentException {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) throws IllegalArgumentException {
        if (volume < 0) {
            throw new IllegalArgumentException("Volume must be greater than or equal to 0");
        }
        this.volume = volume;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) throws IllegalArgumentException {
        if (marketCap < 0) {
            throw new IllegalArgumentException("Market cap must be greater than or equal to 0");
        }
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}