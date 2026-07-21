public class Course {
    private String id;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        enrolled++;
        return true;
    }

    public boolean drop() {
        if (enrolled <= 0) {
            return false;
        }
        enrolled--;
        return true;
    }

    @Override
    public String toString() {
        return String.format("課程代碼: %s | 課程名稱: %s | 人數: %d/%d | 狀態: %s", 
                id, name, enrolled, capacity, isFull() ? "額滿" : "可選課");
    }
}