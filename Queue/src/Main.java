public class Main {
    public static void main(String[] args) {
        Queue<Integer> arrayQueue = new ArrayQueue<>(5);
        System.out.println("enqueue:");
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i + 1);
        }
        System.out.println(arrayQueue.toString());
        System.out.println("dequeue:");
        while (!arrayQueue.isEmpty())
            arrayQueue.dequeue();
        System.out.println(arrayQueue.toString());

    }
}
