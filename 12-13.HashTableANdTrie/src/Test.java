import hashtable.MyHashTable;
import java.security.SecureRandom;
import java.util.UUID;
import trie.Trie;

public class Test {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) {
        MyHashTable<String, Integer> table = new MyHashTable<>();
        testHashTable(table);

        Trie<Integer> tree = new Trie<>();
        testTrie(tree);

    }

    private static void testTrie(Trie<Integer> tree) {
        System.out.println("------Trie test-------");

        int startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            tree.insert(generateString(5), (int) (Math.random() * 1000));
        }
        int endTime = (int) System.currentTimeMillis() - startTime;
        System.out.println("time of adding 100000 elements " + endTime);
        startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            tree.get(generateString(5));
        }
        endTime = (int) System.currentTimeMillis() - startTime;
        System.out.println("time of getting 100000 elements " + endTime);
    }

    private static void testHashTable(MyHashTable<String, Integer> table) {
        System.out.println("------HashTable test-------");

        int startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            table.put(generateString(5), (int) (Math.random() * 1000));
        }
        int endTime = (int) System.currentTimeMillis() - startTime;
        System.out.println("time of adding 100000 elements " + endTime);
        startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            table.get(generateString(5));
        }
        endTime = (int) System.currentTimeMillis() - startTime;
        System.out.println("time of getting 100000 elements " + endTime);
    }

    public static String generateString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


}
