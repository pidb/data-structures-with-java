import java.util.Random;

public class Main {
    public static void main(String[] args) {

//        int cnt = 10000;
//        Random random = new Random();
//        for (int i = 0; i < cnt; i++) {
//            maxHeap.add(random.nextInt(0x7fff));
//        }
//
//        for (int i = 0; i < cnt; i++) {
//            System.out.print(maxHeap.extractMax());
//            System.out.print(",");
//        }

        Integer[] nums = {2, 7, 26, 25, 19, 17, 1, 90, 3, 6};
        MaxHeap<Integer> maxHeap = new BinaryIndexTreeMaxHeap<Integer>(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(maxHeap.extractMax());
            System.out.print(" ");
        }
    }
}
