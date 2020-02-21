package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static cc.xpbootcamp.warmup.cashier.Utils.financeFormatter;
import static java.time.DayOfWeek.WEDNESDAY;

public class Order {
    private List<OrderItem> orderItems;
    private LocalDate createDate;
    private static final double DISCOUNT_RATE = 0.02;
    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(0);

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        createDate = LocalDate.now();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getTotalSalesTax() {
        return financeFormatter(orderItems.stream()
                .map(OrderItem::getSalesTax)
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
        BigDecimal lineItemTotalAmount = orderItems.stream()
                .map(OrderItem::totalAmount)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        return financeFormatter(lineItemTotalAmount.add(getTotalSalesTax()));
    }
}
