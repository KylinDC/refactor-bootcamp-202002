package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void should_return_1_when_calculate_given_position_is_less_than_3(int position) {
        long result = FibonacciGenerator.calculate(position);
        assertEquals(1, result);
    }

    @Test
    void should_return_2_when_calculate_given_position_is_3() {
        long result = FibonacciGenerator.calculate(3);
        assertEquals(2, result);
    }

    @Test
    void should_return_3_when_calculate_given_position_is_4() {
        long result = FibonacciGenerator.calculate(4);
        assertEquals(3, result);
    }

    @Test
    void should_return_12586269025L_when_calculate_given_position_is_50() {
        long result = FibonacciGenerator.calculate(50);
        assertEquals(12586269025L, result);
    }

}
