package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        // массив целых чисел от 1 до размерности
        Integer[] originalArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            originalArray[i] = i + 1;
        }

        // случайный порядок значений
        List<Integer> list = Arrays.asList(originalArray);
        Collections.shuffle(list);
        Integer[] shuffledArray = list.toArray(new Integer[0]);

        System.out.println("Исходный массив: " + Arrays.toString(shuffledArray));

        System.out.println("Выберите порядок сортировки:");
        System.out.println("1 - по возрастанию");
        System.out.println("2 - по убыванию");
        int choice = scanner.nextInt();

        // создали поток Sort
        SortThread sortThread = new SortThread(shuffledArray, choice);
        sortThread.start();

        // завершение потока Sort
        try {
            sortThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            writer.println("Исходный массив: " + Arrays.toString(originalArray)); // Исходный массив
            writer.println("Отсортированный массив: " + Arrays.toString(shuffledArray)); // Отсортированный массив
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        scanner.close();
    }
}

class SortThread extends Thread {
    private Integer[] array;
    private int choice;

    public SortThread(Integer[] array, int choice) {
        this.array = array;
        this.choice = choice;
    }

    @Override
    public void run() {
        if (choice == 1) {
            Arrays.sort(array); // сортировка по возрастанию
        } else if (choice == 2) {
            Arrays.sort(array, Comparator.reverseOrder()); // сортировка по убыванию
        } else {
            System.out.println("Некорректный выбор. Массив не отсортирован.");
        }
    }
}