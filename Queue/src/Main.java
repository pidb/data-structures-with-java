public class Main {
    public static void main(String[] args) {
//        Queue<Integer> arrayQueue = new ArrayQueue<>(5);
//        System.out.println("enqueue:");
//        for (int i = 0; i < 10; i++) {
//            arrayQueue.enqueue(i + 1);
//        }
//        System.out.println(arrayQueue.toString());
//        System.out.println("dequeue:");
//        while (!arrayQueue.isEmpty())
//            arrayQueue.dequeue();
//        System.out.println(arrayQueue.toString());

        Queue<Integer> circularQueue = new CircularQueue<>(4);
        circularQueue.enqueue(1);
        circularQueue.enqueue(2);
        circularQueue.enqueue(3);
        circularQueue.enqueue(4);
        circularQueue.enqueue(5);
        circularQueue.enqueue(6);
        circularQueue.enqueue(7);
        circularQueue.enqueue(8);
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        circularQueue.enqueue(9);
        circularQueue.enqueue(10);
        System.out.println(circularQueue.dequeue());

    }
}
