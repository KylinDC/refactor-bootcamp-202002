package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.WEDNESDAY;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItems;
    private LocalDate createDate;
    private static final double DISCOUNT_RATE = 0.02;
    private static final double DEFAULT_DISCOUNT = 0d;

    public Order(String customerName, String customerAddress, List<LineItem> lineItems) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItems = lineItems;
        createDate = LocalDate.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalSalesTax() {
        return lineItems.stream().mapToDouble(LineItem::getSalesTax).sum();
    }

    public double getTotalAmount() {
        return getOriginTotalAmount() - getDiscount();
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public boolean isDiscountDay() {
        return createDate.getDayOfWeek().equals(WEDNESDAY);
    }

    public double getDiscount() {
        if (isDiscountDay()) {
            return Double.parseDouble(String.format("%.2f", getOriginTotalAmount() * DISCOUNT_RATE));
        }
        return DEFAULT_DISCOUNT;
    }

    private double getOriginTotalAmount() {
        double totalAmount = lineItems.stream().mapToDouble(LineItem::totalAmount).sum();
        return totalAmount + getTotalSalesTax();
    }
}
