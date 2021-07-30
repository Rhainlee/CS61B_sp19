public class LinkedListDeque<T> {
    private class ItemNode{
        public ItemNode pre;
        public T item;
        public ItemNode next;

        public ItemNode(){
            pre = null;
            item = null;
            next = null;
        }
        public ItemNode(ItemNode p, T i, ItemNode n){
            pre = p;
            item = i;
            next = n;

        }
    }
    /*the first item is at sentinel.next*/
    private ItemNode sentinel;
    private int size;

    /*Creats an empty LinkedListDeque.*/
    public LinkedListDeque() {
        sentinel = new ItemNode();
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }
    /*
    public LinkedListDeque(T x){
        sentinel = new ItemNode();
        sentinel.pre = new ItemNode(sentinel, x, sentinel);
        sentinel.next = new ItemNode(sentinel, x, sentinel);
        size = 1;
    }
    */
    /*Creats a deep copy of other*/
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new ItemNode();
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
        for(int i = 0; i < other.size; i ++){
            addLast((T)other.get(i));
        }
    }

    public void addFirst(T item){
        ItemNode first = sentinel.next;
        sentinel.next = new ItemNode(sentinel, item, first);
        first.pre = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        ItemNode last = sentinel.pre;
        sentinel.pre = new ItemNode(last, item, sentinel);
        last.next = sentinel.pre;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }


    public void printDeque(){
        ItemNode p = this.sentinel;
        while (p.next != this.sentinel){
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(!isEmpty()){
            size--;
        }
        else{
            return null;
        }
        ItemNode first = sentinel.next;
        sentinel.next = first.next;
        sentinel.next.pre = sentinel;
        return first.item;
    }

    public T removeLast(){
        if(!isEmpty()){
            size--;
        }
        else{
            return null;
        }
        ItemNode last = sentinel.pre;
        sentinel.pre = last.pre;
        sentinel.pre.next = sentinel;
        return last.item;
    }

    public T get(int index){
        ItemNode p = sentinel;
        if(index < 0 || index >= size)
            return null;
        for(int i = 0; i <= index; i++){
            p = p.next;
        }
        return p.item;
    }
    /** Gets the item at the given index (recursively). */
    public T getRecursive(int index){
        if(index < 0 || index >= size)
            return null;
        return getRecursiveHelper(index, sentinel.next);
    }
    /** Helper method for getRecursive. */
    private T getRecursiveHelper(int index, ItemNode i){
        if(index == 0){
            return i.item;
        }
        return  getRecursiveHelper(index - 1, i.next);
    }
}