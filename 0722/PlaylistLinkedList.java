public class PlaylistLinkedList {
    private PlaylistNode head;

    public boolean addLast(String id, String name) {
        if (search(id) != null) {
            System.out.println("新增失敗：歌曲代碼 [" + id + "] 重複！");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(id, name);
        if (head == null) {
            head = newNode;
            return true;
        }

        PlaylistNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        return true;
    }

    public PlaylistNode search(String id) {
        PlaylistNode cur = head;
        while (cur != null) {
            if (cur.id.equalsIgnoreCase(id)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean remove(String id) {
        if (head == null) {
            System.out.println("刪除失敗：播放清單是空的！");
            return false;
        }

        if (head.id.equalsIgnoreCase(id)) {
            head = head.next;
            System.out.println("成功刪除歌曲代碼: " + id);
            return true;
        }

        PlaylistNode cur = head;
        while (cur.next != null) {
            if (cur.next.id.equalsIgnoreCase(id)) {
                cur.next = cur.next.next;
                System.out.println("成功刪除歌曲代碼: " + id);
                return true;
            }
            cur = cur.next;
        }

        System.out.println("刪除失敗：找不到歌曲代碼 [" + id + "]");
        return false;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單是空的。");
            return;
        }

        System.out.println("--- 播放順序 ---");
        PlaylistNode cur = head;
        int count = 1;
        while (cur != null) {
            System.out.println(count + ". [" + cur.id + "] " + cur.name);
            cur = cur.next;
            count++;
        }
    }
}