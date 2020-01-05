import java.util.Random;

public class Main {

    public static boolean isAVLTree(AVLTree<Integer, Integer> avl) {
        return true;
    }


    public static void main(String[] args) {
        int count = 100000;
        Random random = new Random();
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < count; i++) {
            avlTree.put(random.nextInt(count), random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < count/4; i++) {
            avlTree.remove(random.nextInt(count));
        }

        if (!avlTree.isBalance()) {
            System.out.println("unbalance!");
        } else  {
            System.out.println("balanced!");
        }

        System.out.println(avlTree.size());
    }
}
