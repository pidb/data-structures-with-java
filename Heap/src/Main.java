import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new BinaryIndexTreeMaxHeap<>();
        int cnt = 10000;
        Random random = new Random();
        for (int i = 0; i < cnt; i++) {
            maxHeap.add(random.nextInt(0x7fff));
        }

        for (int i = 0; i < cnt; i++) {
            System.out.print(maxHeap.extractMax());
            System.out.print(",");
        }
    }
}
