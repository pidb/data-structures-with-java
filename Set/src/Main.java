public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new BinarySearchSet<>();
        set.remove(1);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.remove(1);
        set.remove(2);
        set.remove(3);
        set.remove(4);
        System.out.println(set.toString());
        set.add(5);
        set.add(4);
        set.add(3);
        set.add(2);
        set.add(1);
        System.out.println(set.toString());
    }
}
