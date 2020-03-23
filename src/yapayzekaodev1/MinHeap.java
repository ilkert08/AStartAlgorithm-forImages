
package yapayzekaodev1;

import java.awt.Point;
import javax.swing.text.Position;

public class MinHeap {
    public Node[] Heap; 
    public int size; 
    public int maxsize; 
    private int XX;
    private int YY;
    private static final int FRONT = 1; 
  
    public MinHeap(int maxsize, int width, int height) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        this.XX = width;
        this.YY = height;
        Heap = new Node[this.maxsize + 1]; 
        for (int i = 0; i < Heap.length; i++) {
            Heap[i] = new Node();            
        }
        Heap[0].var = 0.0; 
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
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
    int count2 = 0;
    // Function to heapify the node at pos 
    private void minHeapify(int pos) 
    { 
        System.out.println("pos:"+pos);
        // If the node is a non-leaf node and greater 
        // than any of its child 
         System.out.println("Heap:"+Heap[1].var);
        
        if (!isLeaf(pos)) { 
            if (Heap[pos].var > Heap[leftChild(pos)].var 
                || Heap[pos].var > Heap[rightChild(pos)].var) { 
  
                // Swap with the left child and heapify 
                // the left child 
                if (Heap[leftChild(pos)].var < Heap[rightChild(pos)].var) { 
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
    public void insert(double element, Point p, Node par) 
    {   
        if(p.x <XX-1 && p.y < YY-1){
        if (size >= maxsize) {           
            return; 
        } 
        
        Heap[++size].var = element; 
        Heap[size].pos = p;
        Heap[size].parent = par;
        int current = size; 
        
        while (Heap[current].var < Heap[parent(current)].var) {            
            swap(current, parent(current)); 
            current = parent(current); 
        } 
      }
    } 
  
    // Function to print the contents of the heap 
    public void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i] 
                             + " LEFT CHILD : " + Heap[2 * i] 
                             + " RIGHT CHILD :" + Heap[2 * i + 1]); 
            System.out.println(); 
        } 
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
        Node popped = Heap[FRONT]; 
        Heap[FRONT] = Heap[size--]; 
        minHeapify(FRONT); 
        return popped; 
    } 
    
    public Node[] takeArray(){
        return Heap;
    }
    
    
    
}
