package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItems;
    private LocalDate createDate;

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
        double totalAmount = lineItems.stream().mapToDouble(LineItem::totalAmount).sum();
        return totalAmount + getTotalSalesTax();
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
}
