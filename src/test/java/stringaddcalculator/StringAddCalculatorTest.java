package stringaddcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class StringAddCalculatorTest {

    @Test
    void splitAndSum_null_또는_빈문자() {
        int nullResult = StringAddCalculator.splitAndSum(null);
        int emptyResult = StringAddCalculator.splitAndSum("");
        assertThat(nullResult).isEqualTo(0);
        assertThat(emptyResult).isEqualTo(0);
    }

    @Test
    void splitAndSum_숫자하나() {
        int result = StringAddCalculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void splitAndSum_쉼표구분자() {
        int result = StringAddCalculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    void splitAndSum_쉼표_또는_콜론_구분() {
        int result = StringAddCalculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void splitAndSum_custom_구분자() {
        int result = StringAddCalculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void splitAndSum_negative() {
        assertThatThrownBy(() -> {
            StringAddCalculator.splitAndSum("-1,2,3");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void splitAndSum_not_number() {
        assertThatThrownBy(() -> {
            StringAddCalculator.splitAndSum("a,2,3");
        }).isInstanceOf(RuntimeException.class);
    }

    private static class StringAddCalculator {

        private static final String DEFAULT_REGX = ",|:";
        private static final String CUSTOM_REGX = "//(.)\n(.*)";
        public static final String CUSTOM_DELIMITER_START = "//";

        public static int splitAndSum(String expression) {
            if (isNullOrEmpty(expression)) {
                return 0;
            }
            return sum(split(expression));
        }

        private static boolean isNullOrEmpty(String expression) {
            return Optional.ofNullable(expression).isEmpty() || expression.isEmpty();
        }

        private static String[] split(String expression) {
            if (hasCustomDelimiter(expression)) {
                return splitByCustomDelimiter(expression);
            }

            return expression.split(DEFAULT_REGX);
        }

        private static boolean hasCustomDelimiter(String expression) {
            return expression.startsWith(CUSTOM_DELIMITER_START);
        }

        private static String[] splitByCustomDelimiter(String expression) {
            Matcher m = Pattern.compile(CUSTOM_REGX).matcher(expression);
            if (m.find()) {
                String customDelimiter = m.group(1);
                return m.group(2).split(customDelimiter);
            }

            return new String[]{};
        }

        private static int sum(String[] operands) {
            return Arrays.stream(operands)
                .mapToInt(StringAddCalculator::parseOperand)
                .sum();
        }

        private static int parseOperand(String operand) {
            if (checkNegative(operand)) {
                throw new RuntimeException();
            }
            return Integer.parseInt(operand);
        }

        private static boolean checkNegative(String operand) {
            return operand.startsWith("-");
        }
    }
}
