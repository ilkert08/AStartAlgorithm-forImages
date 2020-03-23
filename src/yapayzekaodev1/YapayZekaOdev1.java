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
        newStar newAlgo = new newStar(new Point(40,39),new Point(125, 38));
        newAlgo.algoRun();
        
    }
    
}
