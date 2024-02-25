import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два арабских или два римских числа: ");
        String expression = scanner.nextLine().replaceAll(" ", "");
        System.out.println(calc(expression));
    }

    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        boolean checkRoman;
        String operation;
        String result;

        String[] operands = input.split("[+\\-*/]");

        if (operands.length < 2) {
            throw new Exception("Строка не является математической операцией, должно быть два операнда!");
        }
        if (operands.length > 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        operation = selectedOperation(input);
        if (operation == null) {
            throw new Exception("Неподдерживаемая математическая операция");
        }

        if (RomanNumbers.checkRoman(operands[0]) && RomanNumbers.checkRoman(operands[1])) {
            number1 = RomanNumbers.convertIntoArabian(operands[0]);
            number2 = RomanNumbers.convertIntoArabian(operands[1]);
            checkRoman = true;
        } else if (!RomanNumbers.checkRoman(operands[0]) && !RomanNumbers.checkRoman(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            checkRoman = false;
        } else {
            throw new Exception("используются одновременно разные системы счисления, числа должны быть в одном формате");
        }

        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны принимать на вход от 1 до 10 включительно, не более");
        }

        int arabianNumber = arithmeticExpression(number1, number2, operation);

        if (checkRoman) {
            if (arabianNumber <= 0) {
                throw new Exception("В римской системе счисления нет символя обозначения нуля или отрицательных чисел");
            }
            result = RomanNumbers.convertIntoRoman(arabianNumber);
        } else {
            result = String.valueOf(arabianNumber);
        }
        return result;
    }

    public static String selectedOperation(String symbol) {
        if (symbol.contains("+")) {
            return "+";
        }
        if (symbol.contains("-")) {
            return "-";
        }
        if (symbol.contains("*")) {
            return "*";
        }
        if (symbol.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }

    public static int arithmeticExpression(int a, int b, String operation) throws ArithmeticException {
        if (operation.equals("+")) {
            return a + b;
        }
        if (operation.equals("-")) {
            return a - b;
        }
        if (operation.equals("*")) {
            return a * b;
        } else {
            if (b == 0) {
                throw new ArithmeticException("Деление на ноль невозможно");
            }
            return a / b;
        }
    }
}

class RomanNumbers {
    public static String[] romanNumbersArray = new String[]{"0", "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV",
            "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
            "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV",
            "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV",
            "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV",
            "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean checkRoman(String value) {
        for (int i = 0; i < romanNumbersArray.length; i++) {
            if (value.equals(romanNumbersArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertIntoArabian(String roman) {
        for (int i = 0; i < romanNumbersArray.length; i++) {
            if (roman.equals(romanNumbersArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertIntoRoman(int arabianNumbers) {
        return romanNumbersArray[arabianNumbers];
    }
}
