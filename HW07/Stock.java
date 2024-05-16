
public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    public Stock(String symbol, double price, long volume, long marketCap) {
        setSymbol(symbol);
        setPrice(price);
        setVolume(volume);
        setMarketCap(marketCap);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol)  {
        //if (symbol == null || symbol.isEmpty()) {
        //    throw new IllegalArgumentException("Symbol cannot be null or empty");
        //}
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}