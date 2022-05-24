package main;

public class TestClass {

    public static void main(String[] args) {
        VectorArray<Integer> test = new VectorArray<>(8);
        test.add(1);
        System.out.println(test);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test);
        test.add(9, 2);
        System.out.println(test);
        test.remove(1);
        System.out.println(test);
    }

}
