import org.example.FactorialGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialGeneratorTest {
        @Test
        void testGenerateFactorialsValidInput() {
            List<Long> result = FactorialGenerator.generateFactorials(5);
            assertEquals(List.of(1L, 2L, 6L, 24L, 120L), result, "Факториалы рассчитаны неверно");
        }

        @Test
        void testGenerateFactorialsSingleElement() {
            List<Long> result = FactorialGenerator.generateFactorials(1);
            assertEquals(List.of(1L), result, "Факториал для 1 рассчитан неверно");
        }

        @Test
        void testGenerateFactorialsEdgeCase() {
            List<Long> result = FactorialGenerator.generateFactorials(2);
            assertEquals(List.of(1L, 2L), result, "Факториалы для 1 и 2 рассчитаны неверно");
        }

        @Test
        void testGenerateFactorialsZeroInput() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> FactorialGenerator.generateFactorials(0),
                    "Должно выбрасываться исключение для ненатуральных чисел"
            );
            assertEquals("Число должно быть натуральным.", exception.getMessage());
        }

        @Test
        void testGenerateFactorialsNegativeInput() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> FactorialGenerator.generateFactorials(-5),
                    "Должно выбрасываться исключение для отрицательных чисел"
            );
            assertEquals("Число должно быть натуральным.", exception.getMessage());
        }
    }
