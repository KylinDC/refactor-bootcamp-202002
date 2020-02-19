package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.WEDNESDAY;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItems;
    private LocalDate createDate;
    private static final double DISCOUNT_RATE = 0.02;
    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(0);

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

    public BigDecimal getTotalSalesTax() {
        return financeFormatter(lineItems.stream()
                .map(LineItem::getSalesTax)
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
        BigDecimal lineItemTotalAmount = lineItems.stream()
                .map(LineItem::totalAmount)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        return financeFormatter(lineItemTotalAmount.add(getTotalSalesTax()));
    }

    private BigDecimal financeFormatter(BigDecimal finance) {
        return finance.setScale(2, BigDecimal.ROUND_UP);
    }
}
