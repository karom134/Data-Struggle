// Assignment 2, Task 4.2
// Name: Natdanai Angumnuaysiri 6180519
// Collaborators: Friends
// Time Spent: 6:00 hrs



public class ArrayDeque<T> {

    private T[] ThisArray;
    private int size;

    public ArrayDeque(){
        ThisArray = (T[]) new Object[0];
        size = 0;
    }
/*resize*/
    public ArrayDeque(ArrayDeque<T> other){
        T[] temp =(T[]) new Object[other.size()];
        for(int i =0; i < other.size(); i++){
            temp[i] = other.get(i);
        }
        ThisArray = temp;
    }

    public int size(){
        return ThisArray.length;
    }

    public void addFirst(T t){
        T[] temp = (T[]) new Object[ThisArray.length + 1];

        for (int i = 0; i < ThisArray.length; i++){
            temp[i+1] = ThisArray[i];
        }

        temp[0] = t;
        ThisArray = temp;
    }

    public void addLast(T t) {
        T[] temp = (T[]) new Object[ThisArray.length + 1];

        // copy everything over to the new array
        for (int i = 0; i < ThisArray.length; i++) {
            temp[i] = ThisArray[i];
        }

        // add the new element
        temp[ThisArray.length] = t;
        ThisArray = temp;
    }

    public void remove(int index) {
        if (index < 0 || index >= ThisArray.length) return;
        T[] temp = (T[]) new Object[ThisArray.length - 1];

        boolean found = false;
        // copy everything over to the new element
        for (int i = 0; i < ThisArray.length; i++) {
            // don't copy if the indices are the same
            if (i == index) {
                found = true;
                continue;
            }
            temp[i - (found ? 1 : 0)] = ThisArray[i]; // it's i - 1 after the removed object so then it doesn't leave a gap and it doesn't go over the array's length
        }
        ThisArray = temp;
    }


    public void removeFirst(){
        remove(0);
    }

    public void removeLast(){
        remove(ThisArray.length-1);
    }

    public String toString() {
        String results = "";
        for(T elt : ThisArray) {
            results += elt.toString() + " ";
        }
        return results;
    }

    public T get(int index) {
        return ThisArray[index];
    }

    public boolean isEmpty(){
        return ThisArray.length == 0;
    }

}
