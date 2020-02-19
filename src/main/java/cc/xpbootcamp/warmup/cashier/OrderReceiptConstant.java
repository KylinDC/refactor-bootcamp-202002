package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OrderReceiptConstant {
    public static final char NEW_LINE = '\n';
    public static final String RECEIPT_HEADER = "===== 老王超市，值得信赖 ======\n";
    public static final String SPLIT_LINE = "-----------------------------------";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy年M月dd日，E\n", Locale.CHINA);
    public static final String PRODUCT_FORMATTER = "%s, %.2f x %d, %.2f\n";
    public static final String PRICE_INFORMATION_FORMATTER_WITH_DISCOUNT = "税额:\t%.2f\n折扣:\t%.2f\n总价:\t%.2f\n";
    public static final String PRICE_INFORMATION_FORMATTER = "税额:\t%.2f\n总价:\t%.2f\n";
}
