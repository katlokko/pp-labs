package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactorialGenerator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите натуральное число n: ");
            String input = scanner.nextLine();

            try {
                int n = Integer.parseInt(input);

                if (n <= 0) {
                    throw new IllegalArgumentException("Число должно быть натуральным.");
                }

                List<Long> factorials = generateFactorials(n);
                System.out.println("Первые " + n + " факториалов: " + factorials);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: введите целое число.");
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static List<Long> generateFactorials(int n) {
        if (n < 1 || n == 0) {
            throw new IllegalArgumentException("Число должно быть натуральным.");
        }

        List<Long> factorials = new ArrayList<>();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorials.add(factorial);
        }
        return factorials;
    }
}
