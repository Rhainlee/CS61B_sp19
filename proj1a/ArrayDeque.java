public class ArrayDeque<T>{
    private static final int Factor = 2;
    private static final double R = 0.25;

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        for(int i = 0; i< other.size; i++){
            items[i] = (T)other.get(i);
        }
        size = other.size;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void reszie(int cap){
        T[] a = (T[]) new Object[cap];
        int len1 = nextLast;
        int len2 = items.length -nextFirst - 1;
        int destPos = cap - len2;

        System.arraycopy(items, 0, a, 0, len1);
        System.arraycopy(items, nextFirst + 1, a, destPos, len2);
        items = a;
        nextFirst = destPos - 1;
        nextLast = len1;
    }

    public void addFirst(T item){
        if(size == items.length){
            reszie(size * Factor);
        }
        items[nextFirst] = item;
        if(nextFirst == 0){
            nextFirst = items.length - 1;
        }
        else{
            nextFirst --;
        }

        size ++;
    }

    public void addLast(T item){
        if(size == items.length){
            reszie(size * Factor);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size ++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        int rIndex = (nextFirst + 1 + index) % items.length;
        return items[rIndex];
    }

    public T removeFirst(){

        if(isEmpty()){
            return null;
        }
        int rfirstIndex = (nextFirst + 1) % items.length;
        T returnItem = items[rfirstIndex];
        items[rfirstIndex] = null;
        size --;
        nextFirst = (nextFirst + 1) % items.length;
        if((double) size / items.length < R & items.length >= 16) {
            reszie(size * 4);
        }
        return returnItem;

    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        int rLastIndex = (nextLast - 1) % items.length;
        T returnItem = items[rLastIndex];
        items[rLastIndex] = null;
        size --;
        if(nextLast == 0){
            nextFirst = items.length - 1;
        }
        else{
            nextLast --;
        }
        if((double) size / items.length < R & items.length >= 16) {
            reszie(size * 4);
        }
        return returnItem;
    }

    public void printDeque(){
        int index = 0;
        while(index < size){
            System.out.print(get(index) + " ");
            index ++;
        }
        System.out.println();
    }

}