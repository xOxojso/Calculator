import java.util.*;

public class Main {
    private static final List<String> ROMAN = Arrays.asList("I", "II", "III", "VI", "V", "VI", "VII", "VIII", "IX", "X");

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws Exception {
        String[] strings = input.split(" ");
        if (strings.length != 3) {
            throw new Exception();
        }

        int firstNumber = 0;
        int secondNumber = 0;
        boolean isRoman = false;

        try {
            firstNumber = Integer.parseInt(strings[0]);
            secondNumber = Integer.parseInt(strings[2]);

            if (isValidValues(firstNumber, secondNumber)) {
                throw new Exception();
            }
        } catch (Exception e) {
            if (isSameParam(strings[0], strings[2], ROMAN)) {
                firstNumber = ROMAN.indexOf(strings[0]) + 1;
                secondNumber = ROMAN.indexOf(strings[2]) + 1;
                isRoman = true;
            } else {
                throw new Exception();
            }
        }

        int result = mathOperations(firstNumber, secondNumber, strings[1]);

        if (isRoman) {
            if (result < 1) {
                throw new Exception();
            }
            return Roman.toRoman(result);
        }
        return String.valueOf(result);
    }

    private static boolean isValidValues(int firstNumber, int secondNumber) {
        return (firstNumber < 0 || firstNumber > 10) || (secondNumber < 0 || secondNumber > 10);
    }

    private static boolean isSameParam(String firstValue, String secondValue, List<String> list) {
        return new HashSet<>(list).containsAll(Arrays.asList(firstValue, secondValue));
    }

    private static int mathOperations(int number1, int number2, String input) throws Exception {
        return switch (input) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;
            default -> throw new Exception();
        };
    }
}

class Roman {
    private static final TreeMap<Integer, String> INTEGER_ROMAN_MAP = new TreeMap<>();

    static {
        INTEGER_ROMAN_MAP.put(1000, "M");
        INTEGER_ROMAN_MAP.put(900, "CM");
        INTEGER_ROMAN_MAP.put(500, "D");
        INTEGER_ROMAN_MAP.put(400, "CD");
        INTEGER_ROMAN_MAP.put(100, "C");
        INTEGER_ROMAN_MAP.put(90, "XC");
        INTEGER_ROMAN_MAP.put(50, "L");
        INTEGER_ROMAN_MAP.put(40, "XL");
        INTEGER_ROMAN_MAP.put(10, "X");
        INTEGER_ROMAN_MAP.put(9, "IX");
        INTEGER_ROMAN_MAP.put(5, "V");
        INTEGER_ROMAN_MAP.put(4, "IV");
        INTEGER_ROMAN_MAP.put(1, "I");
    }

    public static String toRoman(int number) {
        int value = INTEGER_ROMAN_MAP.floorKey(number);
        if (number == value) {
            return INTEGER_ROMAN_MAP.get(number);
        }
        return INTEGER_ROMAN_MAP.get(value) + toRoman(number - value);
    }
}