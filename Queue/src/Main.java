import java.util.Random;
import java.util.Scanner;

public class Main {

    public static double getQueueExecutedSecond(Queue<Integer> q, int opCount) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(opCount));
        }

        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        System.out.println(q.toString());

        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        Scanner in = new Scanner(System.in);
        for (;;) {
            String s = in.nextLine();
            if (s.equals("dequeue")) {
                System.out.println("dequeue: " + queue.dequeue());
                System.out.println(queue.toString());
            } else {
                Integer element = Integer.parseInt(s);
                queue.enqueue(element);
                System.out.println(queue.toString());
            }
        }
    }
}
