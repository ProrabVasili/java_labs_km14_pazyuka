import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator {
    public int add(String number) {
        if (number.length() == 0)
            return 0;

        String delimiters = "";
        String regex = "([+\\-!\\(\\){}\\[\\]^\"~*?:\\\\]|[&\\|])";
        if (number.charAt(0) == '/') {
            String[] part2 = number.split("\\\\n");
            Matcher m = Pattern.compile("(?<=\\[)[^]]+").matcher(part2[0]);
            while (m.find()) {
                String delimiter = m.group().replaceAll(regex, "\\\\$1");
                delimiters += delimiter+"|";
            }
            if (delimiters.length() == 0) {
                delimiters = part2[0].substring(2, part2[0].length());
                delimiters = delimiters.replaceAll(regex, "\\\\$1");
            }
            else
                delimiters = delimiters.substring(0, delimiters.length() - 1);
            number = part2[1];
        }
        else {
            delimiters = ",";
            number = number.replace("\\n", ",").replace(",,", " ,");
        }

        String[] numbers = number.split(delimiters);
        int s = 0, n;
        String negative = "";
        for (String num : numbers) {
            try {
                n = Integer.parseInt(num);
            }
            catch (NumberFormatException e) {
                System.out.print("You entered incorrect expression!");
                return -2;
            }
            if (n < 0)
                negative += n + " ";
            else if (n < 1001)
                s += n;
        }
        if (negative.length() != 0) {
            System.out.print("Недозволені від’ємні числа: " + negative);
            return -1;
        }
        else
            return s;
    }
}

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringCalculator calculator = new StringCalculator();
        String text = sc.nextLine();
        int answer = calculator.add(text);
        System.out.print(answer < 0 ? "":answer);
        sc.close();
    }
}
