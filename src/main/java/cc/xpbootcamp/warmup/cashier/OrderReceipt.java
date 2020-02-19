package cc.xpbootcamp.warmup.cashier;

import java.time.format.TextStyle;
import java.util.Locale;

public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append("===== 老王超市，值得信赖 ======\n");

        output.append(printDataInformation());

        output.append(printLineItemInformation());

        output.append(printPriceInformation());

        return output.toString();
    }

    private String printDataInformation() {
        StringBuilder result = new StringBuilder();

        result.append(order.getCreateDate().getYear()).append("年");
        result.append(order.getCreateDate().getMonthValue()).append("月");
        result.append(order.getCreateDate().getDayOfMonth()).append("日，");
        result.append(order.getCreateDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA));
        result.append("\n");

        return result.toString();
    }

    private String printLineItemInformation() {
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

    private String printPriceInformation() {
        StringBuilder result = new StringBuilder();

        result.append("Sales Tax").append('\t').append(order.getTotalSalesTax()).append("\n");
        result.append("Total Amount").append('\t').append(order.getTotalAmount()).append("\n");

        return result.toString();
    }
}