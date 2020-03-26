package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class YapayZekaOdev1 {

    public static void main(String[] args) {           
        //aStarAlgorithm algo = new aStarAlgorithm(new Point(40,10), new Point(80,130));
        //algo.algoRun();
  
        newStar newAlgo = new newStar(new Point(75,15),new Point(63, 144));
        newAlgo.algoRun();
        
    }
    
}
