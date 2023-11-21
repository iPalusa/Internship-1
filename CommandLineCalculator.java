import java.util.Scanner;

public class CommandLineCalculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Command Line Calculator");

            System.out.println("Enter first number:");
            double num1 = scanner.nextDouble();
            System.out.println("Enter Second Number:");
            double num2 = scanner.nextDouble();

            System.out.println("Select an Operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Modulus (%)");
            System.out.println("6. Power (^)");
            System.out.println("7. Square Root (√)");
            System.out.println("8. Factorial (!)");
            System.out.println("9. Logarithm (log)");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();

            double result = 0;

            switch (choice) {
                case 1:
                    result = num1 + num2;
                    break;
                case 2:
                    result = num1 - num2;
                    break;
                case 3:
                    result = num1 * num2;
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("Error: Cannot divide with Zero!");
                        continue; // Restart the Loop
                    }
                    break;
                case 5:
                    result = num1 % num2;
                    break;
                case 6:
                    result = Math.pow(num1, num2);
                    break;
                case 7:
                    result = Math.sqrt(num1);
                    break;
                case 8:
                    result = factorial((int) num1);
                    break;
                case 9:
                    result = Math.log(num1);
                    break;
                case 10:
                    System.out.println("Exiting Calculator. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid operation. Please enter from 1-10:");
                    continue;
            }
            System.out.println("Result: " + result);
        }
    }

    private static double factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
