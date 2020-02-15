package cc.xpbootcamp.warmup.cashier;


public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append("======Printing Orders======\n");

        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

        output.append(printLineItemInformation(order));

        output.append("Sales Tax").append('\t').append(getTotalSalesTax(order));

        output.append("Total Amount").append('\t').append(getFinalTotalAmount(order));
        return output.toString();
    }

    private String printLineItemInformation(Order order) {
        StringBuilder result = new StringBuilder();

        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription());
            result.append('\t');
            result.append(lineItem.getPrice());
            result.append('\t');
            result.append(lineItem.getQuantity());
            result.append('\t');
            result.append(lineItem.totalAmount());
            result.append('\n');
        }

        return result.toString();
    }

    private double getTotalSalesTax(Order order) {
        return order.getLineItems().stream()
                .mapToDouble(LineItem::getSalesTax)
                .sum();
    }

    private double getFinalTotalAmount(Order order) {
        double totalAmount = order.getLineItems().stream()
                .mapToDouble(LineItem::totalAmount)
                .sum();
        return totalAmount + getTotalSalesTax(order);
    }
}