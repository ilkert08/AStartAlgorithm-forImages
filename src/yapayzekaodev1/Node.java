package yapayzekaodev1;
import java.awt.Point;



public class Node {
    public Point pos;
    public Node parent;
    public double g;
    public double h;
    public double f;
    public Node(){
        pos = new Point();
    }

    public Node(Point pos, double f, double g, double h) {
        this.pos = pos;
        this.g = g;
        this.h = h;
        this.f = f;
    }
    public Node(int x, int y){
        pos = new Point(x,y);
    }
    
    public Node(Point p){
        pos = p;
    }
    
    
}
