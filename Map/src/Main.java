public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> m = new LinkedListMap<>();
        m.remove(1);
        System.out.println("contains key = 1: " + m.containsKey(1));
        System.out.println("contains value = 11: " + m.containsValue(11));
        m.put(1, 11);
        m.put(2, 22);
        m.put(3, 33);
        m.put(4, 44);
        m.remove(1);
        System.out.println("contains key = 1: " + m.containsKey(1));
        System.out.println("contains value = 11: " + m.containsValue(11));
        System.out.println(m.toString());
        m.put(4, 444);
        System.out.println(m.toString());
    }
}
