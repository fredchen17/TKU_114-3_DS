import java.util.Stack;

public class BrowserUndoSystem {

    private Stack<String> history = new Stack<>();

    public void visit(String url) {
        history.push(url);
        System.out.println("前往頁面: " + url);
    }

    public void back() {
        if (history.size() <= 1) {
            System.out.println("沒有上一頁可以返回！");
            return;
        }
        String current = history.pop();
        System.out.println("離開頁面: " + current + "，返回到: " + history.peek());
    }

    public void showCurrent() {
        if (history.isEmpty()) {
            System.out.println("目前沒有開啟任何頁面。");
        } else {
            System.out.println("目前頁面: " + history.peek());
        }
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 測試 1: 空結構操作 ===");
        browser.showCurrent();
        browser.back();

        System.out.println("\n=== 測試 2: 8次操作測試 ===");
        browser.visit("google.com");
        browser.visit("github.com");
        browser.showCurrent();

        browser.visit("stackoverflow.com");
        browser.back();
        browser.showCurrent();

        browser.visit("youtube.com");
        browser.back();
        browser.back();
        browser.back();
    }
}