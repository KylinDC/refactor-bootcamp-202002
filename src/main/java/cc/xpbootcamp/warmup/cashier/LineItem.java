package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

public class LineItem {
    private String description;
    private static final double DEFAULT_TAX_RATE = 0.10;
    private int quantity;
    private BigDecimal price;

    public LineItem(String description, BigDecimal price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal totalAmount() {
        return price.multiply(new BigDecimal(quantity));
    }

    public BigDecimal getSalesTax() {
        return totalAmount().multiply(new BigDecimal(DEFAULT_TAX_RATE));
    }
}