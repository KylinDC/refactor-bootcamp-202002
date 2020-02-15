package cc.xpbootcamp.warmup.fibonacci;

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

}
