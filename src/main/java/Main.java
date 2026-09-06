import java.util.ArrayList;
import java.util.List;

public class Main {

    public static double calculator(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        // Парсинг строки
        StringBuilder sb = new StringBuilder(); // буфер для записи числа
        for (char c : expression.toCharArray()) {
            if ("+-*/".indexOf(c) != -1) { //Проверка, являетс ли текущий элемент строки знаком
                numbers.add(Double.parseDouble(sb.toString())); // добавление буфера в спсиок чисел
                operators.add(c); // добавление самого знака в список знаков
                sb.setLength(0); // чистка буфера
            } else {
                sb.append(c); // добавление новой чифры в буфера
            }
        }
        numbers.add(Double.parseDouble(sb.toString()));

        // выполнение умножения и деления
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                double left = numbers.get(i);
                double right = numbers.get(i + 1);

                if (op == '/' && right == 0) {
                    return Double.NaN; // при наличии деления на ноль calculator досрочно завершает работу
                }

                double res = (op == '*') ? (left * right) : (left / right);

                numbers.set(i, res);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; // Смещение индекса назад после удаления числа
            }
        }

        // выполнение сложения и вычитания
        double result = numbers.get(0); // переход из List<Double> к double
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double next = numbers.get(i + 1);
            if (op == '+') result += next;
            if (op == '-') result -= next;
        }

        return result;
    }

    public static List<String> validator(List<String> expressions) {
        List<String> validExpressions = new ArrayList<>();
        return validExpressions;
    }

    public static List<String> generator(int number, String currentString) {
        List<String> results = new ArrayList<>();

        if (number == 0) {
            results.add(currentString);
            return results;
        }

        int divisor = (int) Math.pow(10, (int) Math.log10(number));
        int firstDigit = number / divisor;
        int remainingNumber = number % divisor;

        if (!currentString.isEmpty() && "+-*/".indexOf(currentString.charAt(currentString.length() - 1)) == -1) {
            results.addAll(generator(number, currentString + '+'));
            results.addAll(generator(number, currentString + '-'));
            results.addAll(generator(number, currentString + '*'));
            results.addAll(generator(number, currentString + '/'));
        }

        results.addAll(generator(remainingNumber, currentString + firstDigit));

        return results;
    }
    public static void main(String[] args) {
        System.out.println(generator(123456789, ""));
    }
}
