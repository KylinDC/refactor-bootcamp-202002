package cc.xpbootcamp.warmup.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciGenerator {
    private static Map<Integer, Long> cache = new HashMap<>();

    public static long calculate(int position) {
        if (position < 1) {
            throw new IllegalArgumentException("The position should greater than 0");
        }

        if (position > 50) {
            throw new IllegalArgumentException("The position should less than 51");
        }

        if (position == 1 || position == 2) {
            return 1L;
        }

        return cache.computeIfAbsent(position, key -> calculate(position - 1) + calculate(position - 2));
    }
}
