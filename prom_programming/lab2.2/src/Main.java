import java.io.*;
import java.util.ArrayList;
import java.util.List;

class SymmetricMatrix {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";

        int[][] matrix = readMatrixFromFile(inputFilePath);

        if (matrix != null) {
            System.out.println("Исходная матрица:");
            printMatrix(matrix);

            // замена локальных минимумов на 0
            replaceLocalMinima(matrix);

            System.out.println("Матрица после замены локальных минимумов:");
            printMatrix(matrix);

            // проверка симметричности
            boolean isSymmetric = isSymmetric(matrix);
            System.out.println("Становится ли матрица симметричной? " + isSymmetric);
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

    public static void replaceLocalMinima(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLocalMinimum(matrix, i, j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static boolean isLocalMinimum(int[][] matrix, int row, int col) {
        int value = matrix[row][col];
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && (i != row || j != col)) {
                    if (matrix[i][j] <= value) {
                        return false; // если есть сосед, который не больше, это не локальный минимум
                    }
                }
            }
        }
        return true; // локальный минимум
    }

    public static boolean isSymmetric(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // если матрица не квадратная, она не может быть симметричной
        if (rows != cols) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false; // если элементы не равны, матрица не симметрична
                }
            }
        }
        return true; // матрица симметрична
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