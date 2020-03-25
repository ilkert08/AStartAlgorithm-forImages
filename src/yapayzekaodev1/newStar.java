package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;


public class newStar {
    private final Point Start;
    private final Point End;
    BufferedImage img = null;
    public int width; 
    public int height;
    private ArrayList<Node> visited;
    private ArrayList<Node> notVisited;
    public int sayac = 0;
   public newStar(Point start, Point end){
       this.Start=start;
       this.End = end;
       fileWorks();
       visited = new ArrayList<>();
       notVisited = new ArrayList<>();     
   }
   
       private void fileWorks(){
        File f = null;   
        //read image 
        try
        { 
            f = new File("red.png"); 
            img = ImageIO.read(f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        } 
  
        //get image width and height 
        width = img.getWidth(); 
        height = img.getHeight();
           System.out.println("IMG"+width +".."+height);
           
     
    }
       
       
       private double g(Point child){
        int pixel;                     
        pixel = img.getRGB(child.x, child.y);
        int r = (pixel>>16) & 0xff;
        
        //Ulestirme Cabasi!
       /* double value;  
        int x1 = child.x;
        int x2 = End.x;
        int y1 = child.y;
        int y2 = End.y;          
        value = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
        r =  (int) value * r;*/
        return (256-r);
       }
       
       private double h(Point child, Double gg){
        double value;  
        double normalizer;
        double visual;
        int x1 = child.x;
        int x2 = End.x;
        int y1 = child.y;
        int y2 = End.y;
        normalizer =Math.sqrt(Math.pow(0-width,2)+Math.pow(0-height, 2));       
        visual = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
        value = visual; 
        
         //256 * visual/normalizer; //0-256 arası bir heureistic deger doner.  
        double xy = visual * gg / normalizer;
        return xy;
       }
       
       private void setColor(){
        BufferedImage img2 = null; 
        File f = null; 
        try
        { 
            f = new File("red.png"); 
            img2 = ImageIO.read(f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        } 
        
        
        int a = 250; 
        int r = 0; 
        int g = 0; 
        int b = 250; 
  
        //set the pixel value 
        int p = (a<<24) | (r<<16) | (g<<8) | b;  
           Point pop;
           for (int i = 0; i < currentList.size(); i++) {
               pop = currentList.get(i);
               img2.setRGB(pop.x, pop.y , p);
           }
           
           
           a = 250; 
           r = 255; 
           g = 255; 
           b = 0;
           
           p = (a<<24) | (r<<16) | (g<<8) | b;
           for (int i = 0; i < childList.size(); i++) {
               pop = childList.get(i);
               img2.setRGB(pop.x, pop.y , p);
           }
               
        //write image 
        try
        { 
            f = new File("reddit.png"); 
            ImageIO.write(img2, "png", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e);     
        } 
       }
       
       
       public ArrayList<Point> currentList = new ArrayList<>();
       public ArrayList<Point> childList = new ArrayList<>();
       public void algoRun(){
         
         notVisited.add(new Node(Start));
         notVisited.get(0).g = 0;
         notVisited.get(0).h = 0;
         notVisited.get(0).f = 0;
         int maxIter = 50000;  //10K
         int iterNo = 0;
         
         while(notVisited.size()>0){
             iterNo++;
             System.out.println("Iter:"+iterNo);
             Node current = notVisited.get(0);
             int currentIndex = 0;
             for (int i = 0; i < notVisited.size(); i++) {
                 if(notVisited.get(i).f < current.f ){
                     current = notVisited.get(i);
                     currentIndex = i;
                                         
                 }                 
             }
             
             System.out.println("Current:"+current.pos);
             System.out.println("Value:"+current.h);
             
                 
                     
            System.out.println("Pos:"+current.pos);
            System.out.println("F:"+current.f);
            System.out.println("G:"+current.g);
            System.out.println("H:"+current.h);   
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("");
                     
                    
               
                 
                 
                 
             
             currentList.add(current.pos);
             if(iterNo > maxIter){
                 System.out.println("Max Iteration!");
                 System.out.println("size:"+currentList.size());
                 setColor(); 
                 return;
             }
             notVisited.remove(currentIndex);
             visited.add(current);
                  
             if(current.pos.equals(End)){ //Sonuç bulduysa
                 
                 System.out.println("Sonuc Bulundu");                
                 System.out.println("K:"+current.pos);
                          Node temp2 = new Node();
         temp2.f = Double.MAX_VALUE;
         for(Node temp : visited){
             if(temp.pos.equals(End) && temp.f <temp2.f){
                 temp2 = temp;
             }
         }
         
        System.out.println("T:"+temp2.parent.pos);
        while(temp2.parent!=null){
             System.out.println("(x,y):("+temp2.pos.x+","+temp2.pos.y+")");
              temp2 = temp2.parent;
             
         }
                setColor();
                 return;
             }
             
             Node childeren[] = new Node[8];
             for (int i = 0; i < childeren.length; i++) {
                 childeren[i] = new Node(); //initialization                 
             }
             childeren[0].pos = new Point(current.pos.x, current.pos.y+1);
             childeren[1].pos = new Point(current.pos.x, current.pos.y-1);
             childeren[2].pos = new Point(current.pos.x+1, current.pos.y);
             childeren[3].pos = new Point(current.pos.x+1, current.pos.y+1);
             childeren[4].pos = new Point(current.pos.x+1, current.pos.y-1);
             childeren[5].pos = new Point(current.pos.x-1, current.pos.y+1);
             childeren[6].pos = new Point(current.pos.x-1, current.pos.y-1);
             childeren[7].pos = new Point(current.pos.x-1, current.pos.y);
             
             for(Node track : childeren){
                 boolean passage = false;
                 for (int i = 0; i < visited.size(); i++) {                     
                     if(visited.get(i).pos.equals(track.pos)){
                         passage = true;
                         break;
                     }         
                 }                
                 
                 if(passage)
                     continue;                         
                 
                 if( passage != true &&  //Cocuk Node Visited listesinde yoksa
                     track.pos.x > -1 && track.pos.y > -1 &&  //VE Cocuk Node legal bir adresteyse
                     track.pos.x < width && track.pos.y < height    ){  
                     track.g = current.g + g(track.pos);
                     track.h = h(track.pos , track.g);
                     track.f = track.g + track.h;                     
                     boolean breakOut = false; //Dongu kirma degiskeni.
                     
                     
                     for (int i = 0; i < notVisited.size(); i++) {                         
                         if(track.pos.equals(notVisited.get(i).pos)  
                                 && track.g >= notVisited.get(i).g){
                             breakOut = true;
                             break;
                         }
                     }
                     if(breakOut) {
                         breakOut = false;
                         continue;
                     } //notVisited'ta varsa continue.
                     
                     System.out.println("TRACK:"+track.pos);                   
                     track.parent = current;
                     notVisited.add(track);
                     childList.add(track.pos);
                 }  
                 //System.out.println("Childeren"+track.h); 
                 
             }        
         }
                     
         
       }
    
}
