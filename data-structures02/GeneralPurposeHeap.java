public class GeneralPurposeHeap <T extends Comparable<T>> {
    private int capacity;
    private int amount;
    private T[] heap;

    public GeneralPurposeHeap(){
        this.capacity = 10;
        this.heap = (T[]) new Comparable[this.capacity + 1];
        this.amount = 0;
    }

    public GeneralPurposeHeap(int initialCapacity){
        this.capacity = initialCapacity+1;
        this.heap = (T[]) new Comparable[this.capacity];
        this.amount = 0;
    }

    public GeneralPurposeHeap(T[] initialData){
        this.capacity = initialData.length + 1;
        this.heap = (T[]) new Comparable[this.capacity];
        this.amount = initialData.length;
        for (int i = 0; i < initialData.length; i++){
            this.heap[i+1] = initialData[i];
        }
        buildHeap();
    }

    public void insert(T element){
        if (this.amount == this.capacity){
            resizeHeap(this.capacity);
        }
        this.amount++;
        this.heap[this.amount] = element;
        percUp(this.amount, element);
    }

    public T findMin(){
        if (this.amount == 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        return this.heap[1];
    }

    public int getSize(){
        return this.amount;
    }

    public T deleteMin(){
        if (this.amount == 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        T min = this.heap[1];
        this.heap[1] = this.heap[this.amount];
        this.amount--;
        percDown(1,this.heap[1]);
        return min;
    }

    public void mergeHeap(GeneralPurposeHeap <T> otherHeap){
        if(this.amount + otherHeap.getSize() >= this.capacity){
            resizeHeap(this.amount + otherHeap.amount);
        }
        T[] temp = otherHeap.getHeap();
        for (int i = 1; i <= otherHeap.getSize(); i++){
            this.heap[amount + i] = temp[i];
        }
        this.amount = this.amount + otherHeap.getSize();
        buildHeap();
    }

    public T[] getHeap() {
        return heap;
    }

    private void resizeHeap(int newCapacity){
        T[] newHeap = (T[]) new Comparable[newCapacity * 2 + 1];
        for (int i = 1; i <= this.amount; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
        this.capacity = newCapacity + 1;
    }

    private void buildHeap(){
        int n = this.amount;
        for(int i = n/2; i >=1; i--){
            percDown(i,this.heap[i]);
        }
    }

    private void percUp(int i, T element){
        int p = i / 2;
        if (i == 1) {
            this.heap[i] = element;
        } else if (this.heap[p].compareTo(element) <= 0) {
            this.heap[i] = element;
        } else {
            this.heap[i] = this.heap[p];
            percUp(p, element);
        }
    }

    private void percDown(int i, T element){
        if ((2 * i) > this.amount) {
            this.heap[i] = element;// because it's a leaf
        }
        if ((2 * i) == this.amount) { // it has exactly 1 child
            if (this.heap[2 * i].compareTo(element) < 0) {
                this.heap[i] = this.heap[2 * i];
                this.heap[2 * i] = element;
            } else {
                this.heap[i] = element;
            }
        }
        if ((2 * i) < this.amount) {// it has 2 children
            int j = 0;
            if (this.heap[2 * i].compareTo(this.heap[2 * i + 1]) < 0) {
                j = 2 * i;
            } else {
                j = 2 * i + 1;
            }
            if (this.heap[j].compareTo(element) < 0) {
                this.heap[i] = this.heap[j];
                percDown(j, element);
            } else {
                this.heap[i] = element;
            }
        }
    }
}


