public class Patient {
    int number;
    String name;
    String department;

    public Patient(int number, String name, String department) {
        this.number = number;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "號碼: " + number + " | 姓名: " + name + " | 科別: " + department;
    }
}