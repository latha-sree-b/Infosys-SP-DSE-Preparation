import java.util.Scanner;

public class ValidParentheses {

    static int n;
    static char[] path;

    static void generate(int open, int close, int index) {

        // Step 1: If all positions are filled, print
        if (index == 2 * n) {
            System.out.println(new String(path));
            return;
        }

        // Step 2: Add opening bracket
        if (open < n) {
            path[index] = '(';

            generate(open + 1, close, index + 1);
        }

        // Step 3: Add closing bracket
        if (close < open) {
            path[index] = ')';

            generate(open, close + 1, index + 1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        path = new char[2 * n];

        generate(0, 0, 0);

        sc.close();
    }
}