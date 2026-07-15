import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String originalText = readValidInput(scanner);
        System.out.println("=== 分析結果 ===");

        System.out.println("原始字元數：" + originalText.length());
        String trimmedText = originalText.trim();
        System.out.println("有效字元數 (trim後):" + trimmedText.length());

        String[] words = splitIntoWords(trimmedText);
        System.out.println("單字數量：" + words.length);

        int vowelCount = countVowels(trimmedText);
        System.out.println("英文字母母音 (a, e, i, o, u) 總數：" + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字：" + longestWord);

        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = scanner.next();
        int keywordCount = countKeywordOccurrences(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 :" + keywordCount);

        scanner.close();
    }

    public static String readValidInput(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("請輸入一行非空白文字:");
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("【錯誤】輸入不可為空或全空白，請重新輸入");
        }
        return input;
    }

    public static String[] splitIntoWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }
        return text.trim().split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeywordOccurrences(String[] words, String keyword) {
        if (words == null || keyword == null || keyword.trim().isEmpty()) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}