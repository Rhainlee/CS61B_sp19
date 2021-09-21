import javax.sound.sampled.TargetDataLine;

public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> alist1 = new ArrayDeque<>();
        alist1.addFirst("hello");
        alist1.addFirst("world");
        alist1.addLast("lee");
        alist1.addLast("run");
        alist1.addLast("sheng");
        alist1.addFirst("hello");
        alist1.addFirst("world");
        alist1.addLast("lee");
        alist1.addLast("run");
        alist1.addLast("sheng");
        System.out.println(alist1.get(4));
        alist1.printDeque();
        alist1.removeFirst();
        alist1.removeLast();
        alist1.removeLast();
        alist1.removeLast();
        alist1.removeLast();
        alist1.removeLast();
        alist1.removeLast();
        ArrayDeque<String> alist2 = new ArrayDeque<>(alist1);
        alist2.removeLast();
        alist2.printDeque();
        alist1.printDeque();
        System.out.println(alist1.get(0));
    }
}