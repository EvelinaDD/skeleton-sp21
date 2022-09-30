package deque;

public class ArrayDeque<T> implements Deque<T> {
    private int lengths = 8;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private boolean isSparse(){
        if (items.length > 2 * lengths && size < (items.length / (lengths/2))){
            return true;
        }
        return false;
    }

    private boolean isFull(){
        return size == items.length;
    }

    private int plusOne(int index){
        return (index+1) % items.length;
    }

    private int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }

    public void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for(int newIndex = 0; newIndex < size; newIndex++){
            newArray[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }


    public ArrayDeque(){
        items  = (T[]) new Object[lengths];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    public ArrayDeque(T t){
        items  = (T[]) new Object[lengths];
        items[2] = t;
        nextFirst = 3;
        nextLast = 4;
        size = 1;
    }

    @Override
    public void addFirst(T t){
        if (isFull()){
            resize(items.length * 2);
            lengths *= 2;
        }
        items[nextFirst] = t;
        nextFirst -= 1;
        if (nextFirst < 0){
            nextFirst = lengths - 1;
        }
        size += 1;
    }
    @Override
    public void addLast(T t){
        if (isFull()){
            resize(items.length * 2);
            lengths *= 2;
        }
        items[nextLast] = t;
        nextLast += 1;
        if (nextLast >= lengths){
            nextLast = 0;
        }
        size += 1;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        for(int i = 0; i < lengths; i++){
            if (items[i] != null){
                System.out.println(items[i]);
                System.out.println(" ");
            }
        }
    }
    @Override
    public T removeFirst(){
        if(isSparse()){
            resize(items.length / 2);
            lengths /= 2;
        }
        nextFirst = plusOne(nextFirst);
        T t = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return t;
    }
    @Override
    public T removeLast(){
        if(isSparse()){
            resize(items.length / 2);
            lengths /= 2;
        }
        nextLast = minusOne(nextLast);
        T t = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return t;
    }
    @Override
    public T get(int index){
        if (index < 0 || index > size - 1){
            return null;
        }
        int itemIndex = (nextFirst + index) % (items.length-1);
        T test = items[itemIndex];
        return items[itemIndex];
    }
    @Override
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }





}
