public class Main {
    public static void main(String[] args) {
        TwoThreeTree<Integer, Integer> twoThreeTree = new TwoThreeTree<>();
        twoThreeTree.add(18, 1);
        twoThreeTree.add(20, 1);
        twoThreeTree.add(10, 1);
        twoThreeTree.add(15, 1);
        twoThreeTree.add(6, 1);
        twoThreeTree.add(7, 1);
        twoThreeTree.add(8, 1);
        twoThreeTree.add(25, 1);
        twoThreeTree.add(40, 1);
        twoThreeTree.add(70, 1);
        twoThreeTree.add(60, 1);
        /*
        *        insert 18:  18
        *        insert 20:  18, 20
        *        insert 10:  10, 18, 20 ->   18
        *                                  10  20
        *
        *        insert 15:                  15,18
        *                                  10     20
        *
        *        insert 6:                   15, 18
        *                                 6,10     20
        *
        *        insert 7:                   15, 18      ->    15, 18     ->  7, 15, 18     ->      15
        *                                6,7,10    20         7      20      6  10      20       7      18
        *                                                   6   10                             6   10      20
        *
        *
        *           10,18,20 ->
        *
        *                10
        *         6,15,18  20  ->
        *
        *           10            10, 15
        *         15   20   ->   6   18  20
        *        6  18
        *
        * */
    }
}
