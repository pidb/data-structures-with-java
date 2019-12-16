public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(5);
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(4);
        arr.addLast(5);
        arr.addLast(6);
        arr.addFirst(11);
        arr.addFirst(22);
        arr.addFirst(33);
        arr.addFirst(44);
        arr.addFirst(55);
        arr.add(1, 555);
        arr.removeFirst();
        arr.removeFirst();
        arr.removeLast();
        arr.removeLast();
        System.out.println(arr.toString());
    }
}
