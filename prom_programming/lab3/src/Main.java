import java.util.*;

class MostFrequentCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> charCount = new HashMap<>();
        String input;

        // считываю строки до пустой строки
        System.out.println("Введите текст (пустая строка для завершения):");
        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty()) {
                break; // выход из цикла при пустой строке
            }
            for (char c : input.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }

        // есть ли вообще символы
        if (charCount.isEmpty()) {
            System.out.println("Не введено ни одного символа.");
            scanner.close();
            return;
        }

        // нахождение максимального количества вхождений
        int maxCount = Collections.max(charCount.values());

        // вывод символов с максимальным количеством вхождений
        System.out.println("Символы, встречающиеся наиболее часто:");
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println(entry.getKey() + ": " + maxCount);
            }
        }

        scanner.close();
    }
}