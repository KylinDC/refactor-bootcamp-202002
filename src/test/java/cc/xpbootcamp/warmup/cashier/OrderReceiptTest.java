package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class OrderReceiptTest {

    @Test
    void should_print_order_title() {
        OrderReceipt orderReceipt = new OrderReceipt(new Order(null, null, new ArrayList<LineItem>()));

        String output = orderReceipt.printReceipt();

        assertThat(output, containsString("老王超市，值得信赖"));
    }

    @Test
    void should_print_order_date_information() {
        OrderReceipt orderReceipt = new OrderReceipt(new Order(null, null, new ArrayList<LineItem>()));

        String output = orderReceipt.printReceipt();

        assertThat(output, containsString("年"));
        assertThat(output, containsString("月"));
        assertThat(output, containsString("日"));
        assertThat(output, containsString("星期"));
    }

    @Test
    void should_print_LineItem_and_SalesTax_information_when_not_discount_day() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", new BigDecimal("10.00"), 2));
            add(new LineItem("biscuits", new BigDecimal("5.00"), 5));
            add(new LineItem("chocolate", new BigDecimal("20.00"), 1));
        }};
        Order spyOrder = spy(new Order(null, null, lineItems));
        doReturn(false).when(spyOrder).isDiscountDay();

        OrderReceipt receipt = new OrderReceipt(spyOrder);
        String output = receipt.printReceipt();

        assertThat(output, containsString("milk\t10.00\t2\t20.00\n"));
        assertThat(output, containsString("biscuits\t5.00\t5\t25.00\n"));
        assertThat(output, containsString("chocolate\t20.00\t1\t20.00\n"));
        assertThat(output, containsString("Sales Tax\t6.51"));
        assertThat(output, containsString("Total Amount\t71.51"));
    }

    @Test
    void should_print_LineItem_and_SalesTax_information_when_discount_day() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", new BigDecimal("10.00"), 2));
            add(new LineItem("biscuits", new BigDecimal("5.00"), 5));
            add(new LineItem("chocolate", new BigDecimal("20.00"), 1));
        }};
        Order spyOrder = spy(new Order(null, null, lineItems));
        doReturn(true).when(spyOrder).isDiscountDay();

        OrderReceipt receipt = new OrderReceipt(spyOrder);
        String output = receipt.printReceipt();

        assertThat(output, containsString("milk\t10.00\t2\t20.00\n"));
        assertThat(output, containsString("biscuits\t5.00\t5\t25.00\n"));
        assertThat(output, containsString("chocolate\t20.00\t1\t20.00\n"));
        assertThat(output, containsString("Sales Tax\t6.51"));
        assertThat(output, containsString("Discount\t1.44"));
        assertThat(output, containsString("Total Amount\t70.07"));
    }

}
