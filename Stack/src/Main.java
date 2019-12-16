public class Main {
    public static void main(String[] args) {
        Stack<Integer> arrayStack = new ArrayStack<Integer>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        System.out.println(arrayStack.toString());
        arrayStack.pop();
        arrayStack.pop();
        System.out.println(arrayStack.toString());
    }
}
