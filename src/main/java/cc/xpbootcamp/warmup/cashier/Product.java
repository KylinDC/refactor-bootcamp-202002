package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

import static cc.xpbootcamp.warmup.cashier.Utils.financeFormatter;

public class Product {
    private String description;
    private static final double DEFAULT_TAX_RATE = 0.10;
    private int quantity;
    private BigDecimal price;

    public Product(String description, BigDecimal price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return financeFormatter(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal totalAmount() {
        return financeFormatter(price.multiply(new BigDecimal(quantity)));
    }

    public BigDecimal getSalesTax() {
        return financeFormatter(totalAmount().multiply(new BigDecimal(DEFAULT_TAX_RATE)));
    }
}