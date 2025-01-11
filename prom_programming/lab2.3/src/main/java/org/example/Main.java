package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MatrixProcessor {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            List<List<Integer>> matrix = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                List<Integer> row = new ArrayList<>();
                for (String value : values) {
                    row.add(Integer.parseInt(value));
                }
                matrix.add(row);
            }
            reader.close();

            int n = matrix.size(); // Размер исходной матрицы

            if (n == 0) {
                System.out.println("Матрица пуста.");
                return;
            }

            // Находим минимальный элемент и его координаты
            int min = Integer.MAX_VALUE;
            int minRow = -1;
            int minCol = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    if (matrix.get(i).get(j) < min) {
                        min = matrix.get(i).get(j);
                        minRow = i;
                        minCol = j;
                    }
                }
            }

            // Создаем новую матрицу размером (n+1)x(n+1)
            int newSize = n + 1;
            int[][] newMatrix = new int[newSize][newSize];

            // Копируем элементы старой матрицы
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    if (i < n && j < n) {
                        newMatrix[i][j] = matrix.get(i).get(j);
                    }
                }
            }

            // Добавляем строку с дублированием строки минимального элемента
            for (int j = 0; j < n; j++) {
                newMatrix[n][j] = matrix.get(minRow).get(j);
            }

            // Добавляем столбец с дублированием столбца минимального элемента
            for (int i = 0; i < n; i++) {
                newMatrix[i][n] = matrix.get(i).get(minCol);
            }

            // Устанавливаем минимальный элемент на пересечении новой строки и нового столбца
            newMatrix[n][n] = min;

            System.out.println("Полученная матрица:");
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    System.out.print(newMatrix[i][j] + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата данных в файле: " + e.getMessage());
        }
    }
}
