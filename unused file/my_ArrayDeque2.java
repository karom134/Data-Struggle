public class my_ArrayDeque2<T> {
    public T[] array;
    private int size;
    private int head;
    private int tail;
    private int capacity;

    @SuppressWarnings("unchecked")
    public my_ArrayDeque2() {
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.head = 0;
        this.tail = 1;
        this.capacity = 8;
    }

    public int EndtoEnd(int n) {
        if (n == -1) {
            return this.capacity - 1;
        } else if (n == this.capacity) {
            return 0;
        } else {
            return n;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(T item) {
        size++;
        if (isFull()) {
            resize(2 * this.capacity);
        }
        this.array[this.head] = item;
        this.head = EndtoEnd(this.head - 1);
    }

    public void addLast(T item) {
        size++;
        if (isFull()) {
            resize(2 * this.capacity);
        }
        this.array[this.tail] = item;
        this.tail = EndtoEnd(this.tail + 1);
    }

    public void removeFirst() {
        if (this.size <= this.capacity / 2) {
            resize(this.capacity / 2);
        }
        if (isEmpty()) {
            System.out.println("impossible to remove from empty");
        } else {
            size--;
            this.head = EndtoEnd(this.head + 1);
        }
    }

    public void removeLast() {
        if (this.size <= this.capacity / 2) {
            resize(this.capacity / 2);
        }
        if (isEmpty()) {
            System.out.println("impossible to remove from empty");
        } else {
            size--;
            this.tail = EndtoEnd(this.tail - 1);
        }
    }

    public String toString() {
        String tmp = "";
        for (int idx = this.head + 1; idx < this.head + this.size + 1; idx++) {
            int i = idx % this.capacity;
            if (idx == this.head + 1) {
                tmp = tmp + this.array[i];
            } else {
                tmp = tmp + " " + this.array[i];
            }
        }
        return tmp;
    }

    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        return this.array[(this.head + 1 + index) % this.capacity];
    }

    public void resize(int new_cap) {
        int count = 0;
        @SuppressWarnings("unchecked")
        T[] tmp = (T[]) new Object[new_cap];
        for (int idx = this.head + 1; idx < this.head + this.size + 1; idx++) {
            int i = idx % this.capacity;
            tmp[count] = this.array[i];
            count++;
        }
        this.head = new_cap - 1;
        this.tail = this.size - 1;
        this.array = tmp;
        this.capacity = new_cap;
    }

    public T checker(int n) {
        return this.array[n];
    }

    public static void main(String args[]) {
        my_ArrayDeque2<String> test = new my_ArrayDeque2<String>();
        test.addLast("g");
        test.addLast("h");
        test.addFirst("j");
        test.addFirst("k");
        test.addFirst("l");
        test.addFirst("q");
        test.addFirst("w");
        test.addFirst("e");
        test.addLast("s");
        System.out.println(test.size());
        System.out.println(test.get(5));
        System.out.println(test.toString());
        System.out.println(test.isFull());
        System.out.println(test.head);
        System.out.println(test.tail);
        System.out.println(test.capacity);
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        System.out.println(test.size());
        System.out.println(test.get(2));
        System.out.println(test.toString());
        System.out.println(test.isFull());
        System.out.println(test.head);
        System.out.println(test.tail);
        System.out.println(test.capacity);
    }
}
