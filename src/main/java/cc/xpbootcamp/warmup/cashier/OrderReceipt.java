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

        double totalSalesTax = 0d;
        double finalTotalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');

            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            finalTotalAmount += lineItem.totalAmount() + salesTax;
        }

        output.append("Sales Tax").append('\t').append(getTotalSalesTax(order));

        output.append("Total Amount").append('\t').append(finalTotalAmount);
        return output.toString();
    }

    private double getTotalSalesTax(Order order) {
        return order.getLineItems().stream()
                .mapToDouble(LineItem::totalAmount)
                .map(totalAmount -> totalAmount * 0.10)
                .sum();
    }
}