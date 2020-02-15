package cc.xpbootcamp.warmup.fibonacci;

public class FibonacciGenerator {
    public static long calculate(int position) {
        if (position == 1 || position == 2) {
            return 1L;
        }
        return calculate(position - 1) + calculate(position - 2);
    }
}
