public class Test {

    public static void main(String[] args) {
        MyHashTable table = new MyHashTable();

        table.put("dog", 1);
        table.remove("dog");
        table.put("dog", 12);
        table.put("dogw", 2);
        table.put("oeg", 3);
        table.put("ds1g", 4);
        table.put("do21g", 5);
        table.put("dogd", 6);
        table.put("doga", 7);
        table.put("dogxz", 8);

        int dog = (int) table.get("dog");
        if (dog != 12) {
            throw new RuntimeException("dog != 12");
        }
        table.put("dog12312312xz", 9);
        table.put("dog", 21);

        dog = (int) table.get("dog");
        if (dog != 21) {
            throw new RuntimeException("dog != 21");
        }


    }

}
