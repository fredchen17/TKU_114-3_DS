public class PlaylistNode {
    String id;
    String name;
    PlaylistNode next;

    public PlaylistNode(String id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}