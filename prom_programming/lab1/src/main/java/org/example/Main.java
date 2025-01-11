package org.example;

import java.util.Scanner;

class calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите значение x (-1 < x < 1): ");
        double x = scanner.nextDouble();
        if (x <= -1 || x >= 1) {
            System.out.println("Ошибка: x должен быть в пределах (-1, 1)");
            return;
        }

        System.out.print("Введите значение k (натуральное число): ");
        int k = scanner.nextInt();
        if (k <= 0) {
            System.out.println("Ошибка: k должно быть натуральным числом");
            return;
        }

        //точность ε
        double epsilon = Math.pow(10, -k);

        double term = 2 * x; //первое число
        double sum = term;   // сумма всего ряда
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            n += 2; //след нечет число
            term = (2 * Math.pow(x, n)) / n;
            sum += term;
        }

        double standartValue = Math.log((1 + x) / (1 - x));

        // Вывод результатов
        System.out.printf("Приближенное значение суммы ряда: %.3f\n", sum);
        System.out.printf("Точное значение через стандартную функцию: %.3f\n", standartValue);
        System.out.printf("Абсолютная погрешность: %.3f\n", Math.abs(sum - standartValue));

        scanner.close();
    }
}
