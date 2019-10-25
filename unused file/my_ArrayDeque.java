public class my_ArrayDeque<T> {
    private T[] thisarray;
    private int size;
    private int front;
    private int rear;
    private int MaxCapacity;

    @SuppressWarnings("unchecked")
    public my_ArrayDeque() {
        this.thisarray = (T[]) new Object[100];
        this.size = 0;
        this.front = 99;
        this.rear = 0;
        this.MaxCapacity = 100;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.MaxCapacity;
    }

    public void resize(int new_cap) {
        int idx2;
        @SuppressWarnings("unchecked")
        T[] tmp = (T[]) new Object[new_cap];
        if (this.front < this.rear) {
            for (int idx = this.front - this.MaxCapacity + 1; idx <= this.front; idx++) {
                if (idx < 0) {
                    idx2 = new_cap + idx;
                } else {
                    idx2 = idx;
                }
                tmp[idx2] = this.thisarray[idx];
            }
            this.rear = this.rear + new_cap - this.MaxCapacity;

        } else {
            for (int idx = this.front; idx < this.front + this.MaxCapacity; idx++) {
                if (idx < this.MaxCapacity) {
                    idx2 = new_cap - idx + front - 1;
                } else {
                    idx2 = idx % this.MaxCapacity;
                    idx = idx % this.MaxCapacity;
                }
                tmp[idx2] = this.thisarray[idx];
            }
            this.front = this.front + new_cap - this.MaxCapacity;
        }
        System.out.println(tmp);
        this.MaxCapacity = new_cap;
        this.thisarray = tmp;
    }

    public void addFirst(T item) {
        this.size++;
        if (isFull()) {
            int new_Maxcap = 2 * this.MaxCapacity;
            resize(new_Maxcap);
        }
        if (this.front <= this.rear) {
            this.front = this.front + 1;
            if (this.front >= this.MaxCapacity) {
                this.front = this.front - this.MaxCapacity;
            }
            this.thisarray[this.front] = item;
        } else {
            this.front = this.front - 1;
            if (this.front < 0) {
                this.front = this.MaxCapacity - this.front;
            }
            this.thisarray[this.front] = item;
        }
    }

    public void removeFirst() {
        this.size--;
        if (this.front <= this.rear) {
            this.front = this.front - 1;
            if (this.front < 0) {
                this.front = this.MaxCapacity - this.front;
            }
        } else {
            this.front = this.front + 1;
            if (this.front >= this.MaxCapacity) {
                this.front = this.front - this.MaxCapacity;
            }
        }
    }

    public void addLast(T item) {
        this.size++;
        if (isFull()) {
            int new_Maxcap = 2 * this.MaxCapacity;
            resize(new_Maxcap);
        }
        if (this.front <= this.rear) {
            this.rear = this.rear - 1;
            this.thisarray[this.rear] = item;
        } else {
            this.rear = this.rear + 1;
            this.thisarray[this.rear] = item;
        }
    }

    public void removeLast(T item) {
        this.size--;
        if (this.front <= this.rear) {
            this.rear = this.rear + 1;
            if (this.rear >= this.MaxCapacity) {
                this.rear = this.rear - this.MaxCapacity;
            } else {
                this.rear = this.rear - 1;
                if (this.rear < 0) {
                    this.rear = this.MaxCapacity - this.rear;

                }
            }

        }
    }
    public T get(int index){
        int count=0;
        if (this.front < this.rear) {
            for (int idx = this.front - this.size + 1; idx <= this.front; idx++){
                if(count==index){
                    return this.thisarray[idx];
                }
                count=count+1;
            }
        }
        else{
            for (int idx = this.front; idx < this.front + this.size; idx++) {
                if(count==index){
                    return this.thisarray[idx];
                }
                count=count+1;
            }
        }
    return null; 
    }
    public static void main(String args[]) {
        my_ArrayDeque<String> test= new my_ArrayDeque<String>();
        test.addFirst("a");
        test.addLast("b");
        test.addLast("c");
        test.addLast("c");
        test.addLast("c");
        test.addLast("c");
        System.out.println(test.thisarray[0]);

    }

}
