import java.io.*;
import java.util.ArrayList;

public class Main {


    private static int readSize(String filepath) {
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(filepath);
            if (file.isFile() && file.exists()) {
                reader = new InputStreamReader(new FileInputStream(file));
                bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                return Integer.parseInt(line);
            }

            return 0;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    private static double testUF(String filepath, UF uf) {
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        ;
        try {
            File file = new File(filepath);
            if (file.isFile() && file.exists()) {
                reader = new InputStreamReader(new FileInputStream(file));
                bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>(Integer.parseInt(line));
                while ((line = bufferedReader.readLine()) != null) {
                    String[] pairs = line.split(" ");
                    int p = Integer.parseInt(pairs[0]);
                    int q = Integer.parseInt(pairs[1]);
                    ArrayList<Integer> arr = new ArrayList<>(2);
                    arr.add(p);
                    arr.add(q);
                    arrayLists.add(arr);
                }

                long startTime = System.nanoTime();
                for (ArrayList<Integer> arr : arrayLists) {
                    Integer p = arr.get(0);
                    Integer q = arr.get(1);
                    if (!uf.connected(p, q)) {
                        uf.union(p, q);
                    }
                }
                long endTime = System.nanoTime();
                double time = (endTime - startTime) / 1000000000.0;
                System.out.println(uf.count() + " components");
                return time;
            }
            return 0.00;

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0.00;
    }

    public static void main(String[] args) {
        String filepath = "/home/xyc/workspace/code/java/play-with-data-structures/UF/src/largeUF.txt";
//        UF quickFind = new QuickFind(readSize(filepath));
//        double t1 = testUF(filepath, quickFind);
//        System.out.println("QuickFind time: " + t1 + "s");
//
//        UF quickUnion = new QuickUnion(readSize(filepath));
//        double t2 = testUF(filepath, quickUnion);
//        System.out.println("QuickUnion time: " + t2 + "s");

        UF quickUnionSizeWeight = new QuickUnionSizeWeighted(readSize(filepath));
        double t3= testUF(filepath, quickUnionSizeWeight);
        System.out.println("QuickUnionSizeWeighted time: " + t3 + "s");

        UF quickUnionRankWeighted = new QuickUnionRankWeighted(readSize(filepath));
        double t4= testUF(filepath, quickUnionRankWeighted);
        System.out.println("QuickUnionRankWeighted time: " + t4 + "s");

        UF quickUnionPathCompression = new QuickUnionPathCompression(readSize(filepath));
        double t5= testUF(filepath, quickUnionPathCompression);
        System.out.println("QuickUnionPathCompression time: " + t5 + "s");
    }
}
