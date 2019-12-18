public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        System.out.println(linkedList.toString());
        System.out.println("removeFirst(): " + linkedList.removeFirst() + ", " + linkedList.toString());
        System.out.println("removeLast(): " + linkedList.removeLast() + ", " + linkedList.toString());
        System.out.println("removeLast(): " + linkedList.removeLast() + ", " + linkedList.toString());
        System.out.println("removeLast(): " + linkedList.removeLast() + ", " + linkedList.toString());
        System.out.println("removeLast(): " + linkedList.removeLast() + ", " + linkedList.toString());
        System.out.println("contains 5: " + linkedList.contains(5));
        System.out.println("contains 3: " + linkedList.contains(3));
        System.out.println("contains 1: " + linkedList.contains(1));
        System.out.print("set(0, 5): ");
        linkedList.set(0, 5);
        System.out.println(linkedList.toString());
        System.out.print("set(2, 7): ");
        linkedList.set(2, 7);
        System.out.println(linkedList.toString());
        System.out.print("set(1, 11): ");
        linkedList.set(1, 11);
        System.out.println(linkedList.toString());
        System.out.println("getFirst(): " + linkedList.getFirst());
        System.out.println("getLast(): " + linkedList.getLast());
        System.out.println("get(1): " + linkedList.get(1));

    }
}
