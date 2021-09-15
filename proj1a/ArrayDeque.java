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
        items = (T[]) new Object[other.size()];
        for(int i = 0; i< other.size(); i++){
            items[i] = (T)other.get(i);
        }
        size = other.size();
        nextFirst = size - 1;
        nextLast = 0;
    }

    private void reszie(int cap){
        T[] newItems = (T[]) new Object[cap];
        for(int i = 0; i < size; i++){
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private int getIndex(int rawIndex){
        int returnIndex;
        if(rawIndex < 0){
            returnIndex = rawIndex % items.length + items.length;
        }
        else{
            returnIndex = rawIndex % items.length;
        }
        return returnIndex;
    }

    public void addFirst(T item){
        if(size == items.length){
            reszie(size * Factor);
        }
        items[nextFirst] = item;
        nextFirst = getIndex(nextFirst - 1);
        size ++;
    }

    public void addLast(T item){
        if(size == items.length){
            reszie(size * Factor);
        }
        items[nextLast] = item;
        nextLast = getIndex(nextLast + 1);
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
        return items[getIndex(nextFirst + index + 1)];
    }

    public T removeFirst(){

        if(isEmpty()){
            return null;
        }
        int firstIndex = getIndex(nextFirst + 1);
        T returnItem = items[firstIndex];
        items[firstIndex] = null;
        size --;
        nextFirst = firstIndex;
        if((double) size / items.length < R) {
            reszie(items.length / 2);
        }
        return returnItem;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        int LastIndex = getIndex(nextLast - 1);
        T returnItem = items[LastIndex];
        items[LastIndex] = null;
        size --;
        nextLast = LastIndex;
        if((double) size / items.length < R) {
            reszie(items.length / 2);
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