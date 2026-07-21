public class Equipment {
    private String id;
    private String name;
    private boolean isAvailable;

    public Equipment(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "代碼: " + id + " | 名稱: " + name + " | 狀態: " + (isAvailable ? "可借用" : "已借出");
    }
}