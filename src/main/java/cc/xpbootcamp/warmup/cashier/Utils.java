package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

public class Utils {
    public static BigDecimal financeFormatter(BigDecimal finance) {
        return finance.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
