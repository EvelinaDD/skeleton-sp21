package deque;

public class LinkedListDeque<Eve> implements Deque <Eve> {
    private static class Node<T>{
        private T item;
        private Node<T> previous;
        private Node<T> next;
        public Node(T i, Node<T> p, Node<T>n){
            item = i;
            previous = p;
            next = n;
        }
    }

    private Node<Eve> head = new Node<>(null, null, null);
    private int size;

    public LinkedListDeque(){
        head.item = null;
        head.next = head;
        head.previous = head;
        size = 0;
    }
    public LinkedListDeque(Eve item){
        head.next = new Node<>(item, head, head);
        head.previous = head.next;
        size = 1;
    }

    @Override
    public void addFirst(Eve item){
        head.next = new Node<>(item, head, head.previous);
        head.next.next.previous = head.next;
        size += 1;
    }
    @Override
    public void addLast(Eve item){
        head.previous = new Node<>(item, head.previous, head);
        head.previous.previous.next = head.previous;
        size += 1;
    }
    @Override
    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        return false;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        if (isEmpty()){
            System.out.println("The deque is empty");
        }
        Node<Eve> p = head.next;
        while(p != head){
            System.out.println(p.item);
            System.out.println(" ");
            p = p.next;
        }
    }
    @Override
    public Eve removeFirst(){
        if (isEmpty()){
            return null;
        }
        Eve p = head.next.item;
        head.next = head.next.next;
        head.next.previous = head;
        size -= 1;
        return p;
    }
    @Override
    public Eve removeLast(){
        if (isEmpty()){
            return null;
        }
        Eve p = head.previous.item;
        head.previous = head.previous.previous;
        head.previous.next = head;
        size -= 1;
        return p;
    }
    @Override
    public Eve get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        Node<Eve> p = head;
        for(int i = 0; i < index; i += 1){
            p = p.next;
        }
        return p.item;
    }




}
