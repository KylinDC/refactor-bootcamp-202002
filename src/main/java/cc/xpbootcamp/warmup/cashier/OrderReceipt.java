package cc.xpbootcamp.warmup.cashier;


public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append("======Printing Orders======\n");

        output.append(printLineItemInformation(order));

        output.append("Sales Tax").append('\t').append(order.getTotalSalesTax());
        output.append("Total Amount").append('\t').append(order.getTotalAmount());

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
}