import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator {
    private String regex = "([$+\\-!\\(\\){}\\[\\]^\"~*?:\\\\.]|[&\\|])";

    class MyException extends Exception {
        MyException(String msg) {
            super(msg);
        }
    }

    private String split_delimiters(String allDelimiters, String selectedDelimiters) {
        Matcher m = Pattern.compile("(?<=\\[)[^]]+").matcher(allDelimiters);
        while (m.find()) {
            String delimiter = m.group().replaceAll(regex, "\\\\$1");
            selectedDelimiters += delimiter + "|";
        }
        if (selectedDelimiters.length() == 0) {
            selectedDelimiters = allDelimiters.substring(2, allDelimiters.length());
            selectedDelimiters = selectedDelimiters.replaceAll(regex, "\\\\$1");
        }
        else
            selectedDelimiters = selectedDelimiters.substring(0, selectedDelimiters.length() - 1);
        return selectedDelimiters;
    }

    private int summing(String[] numbers) throws MyException {
        int s = 0, n;
        String negative = "";
        for (String num : numbers) {
            try {
                n = Integer.parseInt(num);
            }
            catch (NumberFormatException e) {
                throw new MyException("You entered incorrect expression!");
            }
            if (n < 0)
                negative += n + " ";
            else if (n < 1001)
                s += n;
        }
        if (negative.length() != 0) {
            throw new MyException("Negative numbers aren't allowed: " + negative);
        }
        return s;
    }

    public int add(String number) throws MyException {
        if (number.length() == 0)
            return 0;
        String delimiters = "";
        if (number.charAt(0) == '/') {
            String[] part2 = number.split("\\\\n");
            if (part2.length == 1)
                return 0;
            number = part2[1];
            delimiters = split_delimiters(part2[0], delimiters);
        }
        else {
            delimiters = ",";
            number = number.replace("\\n", ",").replace(",,", " ,");
        }

        String[] numbers = number.split(delimiters);
        int sum = summing(numbers);
        return sum;
    }
}

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringCalculator calculator = new StringCalculator();
        String text = sc.nextLine();
        int answer = -1;
        try {
            answer = calculator.add(text);
        }
        catch(StringCalculator.MyException e) {
            e.printStackTrace();
        }
        System.out.print(answer < 0 ? "":answer);
        sc.close();
    }
}
