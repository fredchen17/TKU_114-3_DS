public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 測試 1: 空串列操作 ===");
        playlist.printPlaylist();
        playlist.remove("S01");

        System.out.println("\n=== 測試 2: 新增歌曲 ===");
        playlist.addLast("S01", "Song A");
        playlist.addLast("S02", "Song B");
        playlist.addLast("S03", "Song C");
        playlist.addLast("S04", "Song D");
        playlist.printPlaylist();

        System.out.println("\n=== 測試 3: 代碼重複新增 ===");
        playlist.addLast("S02", "Duplicate Song");

        System.out.println("\n=== 測試 4: 依代碼搜尋 ===");
        PlaylistNode song = playlist.search("S03");
        if (song != null) {
            System.out.println("搜尋到歌曲: " + song.name);
        } else {
            System.out.println("找不到該歌曲！");
        }

        System.out.println("\n=== 測試 5: 刪除第一首 (S01) ===");
        playlist.remove("S01");
        playlist.printPlaylist();

        System.out.println("\n=== 測試 6: 刪除最後一首 (S04) ===");
        playlist.remove("S04");
        playlist.printPlaylist();

        System.out.println("\n=== 測試 7: 刪除不存在的歌曲 ===");
        playlist.remove("S99");
        playlist.printPlaylist();
    }
}