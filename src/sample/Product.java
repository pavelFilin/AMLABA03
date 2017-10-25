package sample;

public class Product {
    private String name;
    private double price;
    private int count;
    private  double sum;

    public String getName() {
        return name;
    }

    public double getSum() {
        return price*count;
    }

    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("This string in empty");
        }

        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price less than 0");
        }

        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count less than 0");
        }

        this.count = count;
    }

    public Product(String name, int count, double price) {
        setName(name);
        setCount(count);
        setPrice(price);
    }
}
