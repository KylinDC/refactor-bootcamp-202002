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
        OrderReceipt orderReceipt = new OrderReceipt(new Order(new ArrayList<>()));

        String output = orderReceipt.printReceipt();

        assertThat(output, containsString("===== 老王超市，值得信赖 ======\n\n"));
    }

    @Test
    void should_print_order_date_information() {
        OrderReceipt orderReceipt = new OrderReceipt(new Order(new ArrayList<OrderItem>()));

        String output = orderReceipt.printReceipt();

        assertThat(output, containsString("年"));
        assertThat(output, containsString("月"));
        assertThat(output, containsString("日"));
        assertThat(output, containsString("星期"));
    }

    @Test
    void should_print_LineItem_information() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem("巧克力", new BigDecimal("21.50"), 2));
            add(new OrderItem("小白菜", new BigDecimal("10.00"), 1));
        }};
        Order order = new Order(orderItems);

        OrderReceipt orderReceipt = new OrderReceipt(order);
        String output = orderReceipt.printReceipt();

        assertThat(output, containsString("巧克力, 21.50 x 2, 43.00\n"));
        assertThat(output, containsString("小白菜, 10.00 x 1, 10.00\n"));
        assertThat(output, containsString("----------------------------\n"));
    }

    @Test
    void should_print_LineItem_and_SalesTax_information_when_not_discount_day() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem("巧克力", new BigDecimal("21.50"), 2));
            add(new OrderItem("小白菜", new BigDecimal("10.00"), 1));
        }};
        Order spyOrder = spy(new Order(orderItems));
        doReturn(false).when(spyOrder).isDiscountDay();

        OrderReceipt receipt = new OrderReceipt(spyOrder);
        String output = receipt.printReceipt();

        assertThat(output, containsString("税额:\t5.30\n"));
        assertThat(output, containsString("总价:\t58.30\n"));
    }

    @Test
    void should_print_LineItem_and_SalesTax_information_when_discount_day() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem("巧克力", new BigDecimal("21.50"), 2));
            add(new OrderItem("小白菜", new BigDecimal("10.00"), 1));
        }};
        Order spyOrder = spy(new Order(orderItems));
        doReturn(true).when(spyOrder).isDiscountDay();

        OrderReceipt receipt = new OrderReceipt(spyOrder);
        String output = receipt.printReceipt();

        assertThat(output, containsString("税额:\t5.30\n"));
        assertThat(output, containsString("折扣:\t1.17\n"));
        assertThat(output, containsString("总价:\t57.13\n"));
    }

}
