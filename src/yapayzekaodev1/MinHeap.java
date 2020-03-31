
package yapayzekaodev1;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.text.Position;

public class MinHeap {
    public ArrayList <Node> Heap; 
    public int size; 
    public int maxsize; 
    private static final int FRONT = 1; 
  
    public MinHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new ArrayList<>(this.maxsize+1); 
        for (int i = 0; i < this.maxsize+1; i++) {
            Heap.add(new Node());
            
        }
        Heap.add(new Node());
        Heap.get(0).f = 0.0; 
    } 
    
    public int getSize(){
        return this.size;
    }
  
    // Function to return the position of 
    // the parent for the node currently 
    // at pos 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Function to return the position of the 
    // left child for the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Function to return the position of 
    // the right child for the node currently 
    // at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Function that returns true if the passed 
    // node is a leaf node 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    // Function to swap two nodes of the heap 
    private void swap(int fpos, int spos) 
    { 
        Node tmp; 
        tmp = Heap.get(fpos); 
        Heap.set(fpos, Heap.get(spos)); 
        Heap.set(spos,tmp); 
    } 
    int count2 = 0;
    // Function to heapify the node at pos 
    private void minHeapify(int pos) 
    {         
        // If the node is a non-leaf node and greater 
        // than any of its child 
        // System.out.println("Heap:"+Heap[1].var);
        
        if (!isLeaf(pos)) { 
            if (Heap.get(pos).f > Heap.get(leftChild(pos)).f 
                || Heap.get(pos).f > Heap.get(rightChild(pos)).f) { 
  
                // Swap with the left child and heapify 
                // the left child 
                if (Heap.get(leftChild(pos)).f < Heap.get(rightChild(pos)).f) { 
                    swap(pos, leftChild(pos)); 
                    minHeapify(leftChild(pos)); 
                } 
  
                // Swap with the right child and heapify 
                // the right child 
                else { 
                    swap(pos, rightChild(pos)); 
                    minHeapify(rightChild(pos)); 
                } 
            } 
        } 
    } 
  
    // Function to insert a node into the heap 
    public void insert(Node element) 
    {   
        
        Heap.set(++size, element);        
        int current = size; 
        while (Heap.get(current).f < Heap.get(parent(current)).f) {            
            swap(current, parent(current)); 
            current = parent(current); 
        } 
      }
     
  
    // Function to print the contents of the heap 
    public void print() 
    { 
       /* for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i] 
                             + " LEFT CHILD : " + Heap[2 * i] 
                             + " RIGHT CHILD :" + Heap[2 * i + 1]); */
            System.out.println("Bu fonksiyon su anda kullanilamamaktadir."); 
         
    } 
  
    // Function to build the min heap using 
    // the minHeapify 
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 
  
    // Function to remove and return the minimum 
    // element from the heap 
    public Node remove() 
    { 
        Node popped = Heap.get(FRONT); 
        Heap.set(FRONT, Heap.get(size--)); 
        minHeapify(FRONT); 
        return popped; 
    } 
    
    public Node getMin() 
    { 
        return Heap.get(FRONT);
    } 
    
    public ArrayList<Node> takeArray(){
        return Heap;
    }
    
    
    
}
