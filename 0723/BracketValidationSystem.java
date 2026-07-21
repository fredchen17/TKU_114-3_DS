import java.util.Stack;

public class BracketValidationSystem {

    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatch(top, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean isMatch(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '[' && close == ']') return true;
        if (open == '{' && close == '}') return true;
        return false;
    }

    public static void test(String input) {
        System.out.println("輸入: \"" + input + "\" -> 驗證結果: " + (isValid(input) ? "合法" : "不合法"));
    }

    public static void main(String[] args) {
        System.out.println("=== 括號驗證測試 ===");
        
        test("");
        test("a + b");
        test("{ [ ( a + b ) * c ] }");
        test("( a + b ]");
        test("( a + b");
        test("a + b )");
        test("{(})");
        test("{[()]}()");
    }
}