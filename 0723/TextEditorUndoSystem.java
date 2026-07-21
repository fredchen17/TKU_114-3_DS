import java.util.Stack;

public class TextEditorUndoSystem {

    private StringBuilder text = new StringBuilder();
    private Stack<String> history = new Stack<>();

    public void addText(String newText) {
        history.push(text.toString());
        text.append(newText);
        System.out.println("新增文字: \"" + newText + "\"");
    }

    public void deleteChars(int count) {
        if (count <= 0) {
            return;
        }
        history.push(text.toString());
        int len = text.length();
        if (count >= len) {
            text.setLength(0);
        } else {
            text.delete(len - count, len);
        }
        System.out.println("刪除最後 " + count + " 個字元");
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("沒有歷史紀錄可以 Undo！");
            return;
        }
        text = new StringBuilder(history.pop());
        System.out.println("執行 Undo 成功！");
    }

    public void showText() {
        System.out.println("當前文字內容: \"" + text.toString() + "\"");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 測試 1: 空歷史 Undo 測試 ===");
        editor.showText();
        editor.undo();

        System.out.println("\n=== 測試 2: 文字編輯操作 ===");
        editor.addText("Hello");
        editor.showText();

        editor.addText(" World");
        editor.showText();

        editor.addText("!");
        editor.showText();

        editor.deleteChars(1);
        editor.showText();

        System.out.println("\n=== 測試 3: 連續 Undo 三次驗證 ===");
        System.out.println("--- 第一次 Undo ---");
        editor.undo();
        editor.showText();

        System.out.println("--- 第二次 Undo ---");
        editor.undo();
        editor.showText();

        System.out.println("--- 第三次 Undo ---");
        editor.undo();
        editor.showText();

        System.out.println("--- 第四次 Undo (邊界測試) ---");
        editor.undo();
        editor.showText();
    }
}