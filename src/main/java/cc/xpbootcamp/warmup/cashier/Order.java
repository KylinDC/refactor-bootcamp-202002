package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static cc.xpbootcamp.warmup.cashier.Utils.financeFormatter;
import static java.time.DayOfWeek.WEDNESDAY;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<Product> products;
    private LocalDate createDate;
    private static final double DISCOUNT_RATE = 0.02;
    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(0);

    public Order(String customerName, String customerAddress, List<Product> products) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.products = products;
        createDate = LocalDate.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotalSalesTax() {
        return financeFormatter(products.stream()
                .map(Product::getSalesTax)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0)));
    }

    public BigDecimal getTotalAmount() {
        return financeFormatter(getOriginTotalAmount().subtract(getDiscount()));
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public boolean isDiscountDay() {
        return createDate.getDayOfWeek().equals(WEDNESDAY);
    }

    public BigDecimal getDiscount() {
        if (isDiscountDay()) {
            return financeFormatter(getOriginTotalAmount().multiply(new BigDecimal(DISCOUNT_RATE)));
        }
        return financeFormatter(DEFAULT_DISCOUNT);
    }

    private BigDecimal getOriginTotalAmount() {
        BigDecimal lineItemTotalAmount = products.stream()
                .map(Product::totalAmount)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        return financeFormatter(lineItemTotalAmount.add(getTotalSalesTax()));
    }
}
