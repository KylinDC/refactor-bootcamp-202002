package cc.xpbootcamp.warmup.cashier;

import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.DATE_FORMATTER;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.NEW_LINE;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.PRICE_INFORMATION_FORMATTER;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.PRICE_INFORMATION_FORMATTER_WITH_DISCOUNT;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.PRODUCT_FORMATTER;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.RECEIPT_HEADER;
import static cc.xpbootcamp.warmup.cashier.OrderReceiptConstant.SPLIT_LINE;

public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(printReceiptHeader());
        output.append(NEW_LINE);

        output.append(printDataInformation());
        output.append(NEW_LINE);

        output.append(printProductInformation());
        output.append(SPLIT_LINE);
        output.append(NEW_LINE);

        output.append(printPriceInformation());

        return output.toString();
    }

    private String printReceiptHeader() {
        return RECEIPT_HEADER;
    }

    private String printDataInformation() {
        return order.getCreateDate().format(DATE_FORMATTER);
    }

    private String printProductInformation() {
        StringBuilder result = new StringBuilder();

        for (Product product : order.getProducts()) {
            result.append(String
                    .format(PRODUCT_FORMATTER,
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.totalAmount()));
        }

        return result.toString();
    }

    private String printPriceInformation() {
        if (order.isDiscountDay()) {
            return String.format(PRICE_INFORMATION_FORMATTER_WITH_DISCOUNT,
                    order.getTotalSalesTax(),
                    order.getDiscount(),
                    order.getTotalAmount());
        }
        return String.format(PRICE_INFORMATION_FORMATTER,
                order.getTotalSalesTax(),
                order.getTotalAmount());
    }
}
