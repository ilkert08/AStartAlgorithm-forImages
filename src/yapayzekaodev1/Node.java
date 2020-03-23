package yapayzekaodev1;
import java.awt.Point;



public class Node {
    public Point pos;
    public double var;
    public Node parent;
    public double g;
    public double h;
    public double f;
    public Node(){
        pos = new Point();
    }
    public Node(int x, int y){
        pos = new Point(x,y);
    }
    
    public Node(Point p){
        pos = p;
    }
    public double iret(){
        return this.g + this.h;
    }
    
    
}
