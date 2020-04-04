package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class YapayZekaOdev1 {

    public static void main(String[] args) {           

        long startTime = System.nanoTime();
        BestFirst bf = new BestFirst(new Point(0,0),new Point(800, 800));
        bf.algoRun();
        long endTime = System.nanoTime();
        long duration3 = (endTime - startTime) / 1000000;
        //mainScreen ms = new mainScreen();
        //ms.setVisible(true);
        
        startTime = System.nanoTime();
        BestFirst bf2 = new BestFirst(new Point(0,0),new Point(800, 800));
        bf.algoRunWithHeap();
        endTime = System.nanoTime();
        long duration4 = (endTime - startTime) / 1000000;
        
        
        
        
        
      /*  long startTime = System.nanoTime();
        newStar newAlgo = new newStar(new Point(0,0),new Point(800, 800));
        newAlgo.algoRun();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; 
        //8 Dakika 3 Saniye //1 min 36 sec
        
        long startTime2 = System.nanoTime();
        heaplessAStar heaplessAlgo = new heaplessAStar(new Point(0,0),new Point(800, 800));
        heaplessAlgo.algoRun();
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000; 
        //7 dakika 22 saniye. */
        
        
       // System.out.println("With Heap:"+ duration);     
       // System.out.println("Without Heap:"+ duration2);
       System.out.println("Best First without Heap:"+ duration3);
       System.out.println("Best First with Heap:"+ duration4);
        
        
    }
    
}
