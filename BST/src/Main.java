import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(41);
        bst.add(20);
        bst.add(11);
        bst.add(29);
        bst.add(28);
        bst.add(65);
        bst.add(91);
        bst.add(72);
        bst.add(99);
        bst.add(18);

        bst.preOrderNR();
//        Random random = new Random();
//        for (int i = 0; i < 1000; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        bst.preOrder();
//        ArrayList<Integer> arr = new ArrayList<Integer>();
//        for (int i = 0; i < 1000; i++) {
//            arr.add(bst.removeMin());
//        }
//
//        System.out.println(arr.toString());
    }
}
