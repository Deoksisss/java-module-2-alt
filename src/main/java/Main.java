import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int calculator(String expression) {


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
