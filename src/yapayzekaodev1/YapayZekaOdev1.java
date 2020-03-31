package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class YapayZekaOdev1 {

    public static void main(String[] args) {           

        
        //BestFirst bf = new BestFirst(new Point(75,15),new Point(120, 120));
        //bf.algoRun();
        //mainScreen ms = new mainScreen();
        //ms.setVisible(true);
        
        
        
        
        
        long startTime = System.nanoTime();
        newStar newAlgo = new newStar(new Point(75,15),new Point(77, 138));
        newAlgo.algoRun();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; 
        //8 Dakika 3 Saniye 
       /* 
        long startTime2 = System.nanoTime();
        heaplessAStar heaplessAlgo = new heaplessAStar(new Point(75,15),new Point(400, 246));
        heaplessAlgo.algoRun();
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000; */
        //7 dakika 22 saniye.
        
        
        //System.out.println("With Heap:"+ duration);     
        //System.out.println("Without Heap:"+ duration2);
        
        
    }
    
}
