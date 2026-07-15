public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount first = new BankAccount("A001", "fred", 5000);
        BankAccount second = new BankAccount("A002", "benson", 1000);

        System.out.println("--- 初始帳戶狀態 ---");
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("--- 存款與提款測試 ---");
        System.out.println("fred 存款 500 元：" + first.deposit(500));
        System.out.println("Benson 提款 300 元：" + second.withdraw(300));
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("--- 成功轉帳測試 ---");
        System.out.println("fred 轉帳 2000 元給 Benson" + first.transferTo(second, 2000));
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("--- 失敗轉帳測試 (超額) ---");
        System.out.println("Benson 轉帳 10000 元給 fred" + second.transferTo(first, 10000));
        System.out.println(first);
        System.out.println(second);
    }
}