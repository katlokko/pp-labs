import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MatrixNorm {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";

        int[][] A = readMatrixFromFile(inputFilePath);

        if (A != null) {
            System.out.println("Матрица:");
            printMatrix(A);

            double norm = calculateNorm(A);

            System.out.println("Норма матрицы = " + norm);
        }
    }

    public static int[][] readMatrixFromFile(String filePath) {
        List<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                int[] row = new int[values.length];
                for (int j = 0; j < values.length; j++) {
                    row[j] = Integer.parseInt(values[j]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }

        // преобразуем список строк в массив
        return rows.toArray(new int[rows.size()][]);
    }

    public static double calculateNorm(int[][] matrix) {
        int maxSum = 0;

        // проходим по каждой строке матрицы
        for (int[] row : matrix) {
            int rowSum = 0;

            // сумма значений элементов строки
            for (int value : row) {
                rowSum += Math.abs(value);
            }

            // сравниваем с текущим максимумом
            if (rowSum > maxSum) {
                maxSum = rowSum;
            }
        }
        return maxSum;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%d ", value);
            }
            System.out.println();
        }
    }
}