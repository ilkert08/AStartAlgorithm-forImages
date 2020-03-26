package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class aStarAlgorithm {
    private MinHeap mainHeap;
    private Node heapArray[];
    private Point Start;
    private Point End;
    BufferedImage img = null;
    Node lastNode;
    int count = 0;
    int width; 
    int height;
    int cccc =  0;
    Point dotArray[] = new Point[5000];
    
    
    
    
    public aStarAlgorithm(Point start, Point end) {
        this.Start = start;
        this.End = end;
        fileWorks();
        mainHeap = new MinHeap(900000,width,height);
        heapArray = new Node[500000];        
        
    }
    
    
    private void insert(Node par, Point p, Double gx, Double hx, int index){        
        heapArray[index].parent = par;
        heapArray[index].pos = p;
        Node temp;
        temp = par;
        double tmp = 0;
       /* while(temp !=null){
            tmp+=temp.g;
            temp= temp.parent;
        }*/
       try{
           tmp+=temp.parent.g;
       }catch(NullPointerException e){
           tmp+=0;
       }
        
       
      //  System.out.println("P:"+p +"GX:"+ gx);
        heapArray[index].g = gx; 
        heapArray[index].h = hx;
        heapArray[index].var = tmp + gx + hx;
      //  System.out.println("Ekle:"+ p.x+":"+p.y);
       // System.out.println("Kalan1:"+ h(heapArray[index].pos.x, End.x, heapArray[index].pos.y, End.y));
       
        BufferedImage img = null; 
        File f = null; 
        try
        { 
            f = new File("red.png"); 
            img = ImageIO.read(f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        } 
        
        
        int a = 250; 
        int r = 0; 
        int g = 250; 
        int b = 0; 
  
        //set the pixel value 
        int pp = (a<<24) | (r<<16) | (g<<8) | b;    
        try{
            img.setRGB(p.x,p.y , pp);
        }catch(Exception e){
            System.out.println("Pass");
        }
        
               
        //write image 
        try
        { 
            f = new File("red.png"); 
            ImageIO.write(img, "png", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e);     
        }    
       
       
       
       
       
    }
    
    
    private Node remove (){
        
        Node sendNode = new Node();       
        sendNode.parent = heapArray[0].parent;
        sendNode.pos = heapArray[0].pos;
        sendNode.var = heapArray[0].var;
        //System.out.println("Sil:"+sendNode.pos.x +":"+sendNode.pos.y);
        //System.out.println("Kalan2:"+ h(sendNode.pos.x, End.x, sendNode.pos.y, End.y));
        heapArray[0].parent = null;
        heapArray[0].pos = null;
        heapArray[0].var = Double.MAX_VALUE;
        
        BufferedImage img = null; 
        File f = null; 
        try
        { 
            f = new File("red.png"); 
            img = ImageIO.read(f); 
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
        img.setRGB(sendNode.pos.x,sendNode.pos.y , p);
               
        //write image 
        try
        { 
            f = new File("red.png"); 
            ImageIO.write(img, "png", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e);     
        }    
        
        
        
        /////////Silinen nodelar.
       /* if(cccc < 4999)
        dotArray[cccc++] = new Point(sendNode.pos);
      */
        
        
        
        /*  if(cccc == 4999){   
        BufferedImage img = null; 
        File f = null; 
        try
        { 
            f = new File("red.png"); 
            img = ImageIO.read(f); 
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
        
        
            for (int i = 0; i < cccc; i++) {
                 img.setRGB(dotArray[i].x, dotArray[i].y, p);
            }   
        //write image 
        try
        { 
            f = new File("red.png"); 
            ImageIO.write(img, "png", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e);     
        }    
                    
            cccc = 5500;
            //System.exit(0);
          
        } */

        System.out.println("C:"+cccc);
        if(cccc>250)
            System.exit(0);
        ////////Silinen nodelar
  
        return sendNode;
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
     
    }

    
    
    private double h(int x1, int x2, int y1, int y2){
        double value = 0;      
        value = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2)); 
       // System.out.println("H VALUE:"+value);
        return value;
    }
    
    private Double g(int x1, int x2, int y1, int y2){ 
        int pixelFirst =0;
        System.out.println("X2:"+x2+"Y2:"+y2);
        if((x2<width && y2<height) && (x2>0 && y2>0))          
        pixelFirst = img.getRGB(x2,y2);
        
        
        int red1 = (pixelFirst>>16) & 0xff;        
        red1 = 256-red1;                
        double value = Math.abs(red1);
       // System.out.println("G VALUE:"+value);
        return 5*value;              
      
    }
   /*
    public void algoRun(){

        boolean found = false; 
        int index=0;
        HeapSort hp = new HeapSort();
        for (int i = 0; i < heapArray.length; i++) {
            heapArray[i] = new Node();
            heapArray[i].var = Double.MAX_VALUE;
        }
        
        insert(null, Start, 0.0, h(Start.x, End.x, Start.y, End.y) , index++);
        hp.sort(heapArray);
        
    
        while (!found && index != 0){
            cccc++;
            
        if(cccc == 500){
            System.exit(0);
        }   System.out.print("Heap:");
            for (int i = 0; i < 10; i++) {
                System.out.print(" "+heapArray[i].var);
                
            }
            Node poppie = remove();
            hp.sort(heapArray);
            index--;
            
            Point pop = new Point(poppie.pos);
            
            if(pop.x == End.x && pop.y == End.y){
                found = true;  
                lastNode = poppie;
                System.out.println("BULDUK!");
                break;
            }
            if(pop.x > width || pop.y > height || pop.x <= 0 || pop.y <= 0 ||
                    mainHeap.getSize() == mainHeap.maxsize ){                
                    continue;
            } 
            
            index ++ ;
            //(x,y+1)
            insert(poppie, new Point(pop.x , pop.y + 10), 
                    g(pop.x, pop.x, pop.y, pop.y+10) , h(pop.x, End.x, pop.y +10 , End.y),index);
             
            index ++ ;
            //(x,y-1)
            insert(poppie, new Point(pop.x , pop.y - 10), 
                    g(pop.x, pop.x, pop.y, pop.y-10), h(pop.x, End.x, pop.y-10, End.y),index);
             
            index ++ ;
            //(x+1, y+1)
            insert(poppie, new Point(pop.x + 10, pop.y + 10), 
                    g(pop.x, pop.x+10, pop.y, pop.y+10) , h(pop.x+10, End.x, pop.y+10, End.y),index);
             
            index ++ ;
            //(x+1,y-1)
            insert(poppie, new Point(pop.x + 10 , pop.y - 10), 
                    g(pop.x, pop.x+10, pop.y, pop.y-10) , h(pop.x+10, End.x, pop.y-10, End.y),index);
             
            index ++ ;
            //(x+1,y)
            insert(poppie, new Point(pop.x +10 , pop.y), 
                    g(pop.x, pop.x+10, pop.y, pop.y) , h(pop.x+10, End.x, pop.y, End.y),index);
            
            index ++ ;
            //(x-1,y+1)
            insert(poppie, new Point(pop.x -10 , pop.y + 10), 
                    g(pop.x, pop.x-10, pop.y, pop.y+10) , h(pop.x-10, End.x, pop.y+10, End.y),index);
             
            index ++ ;
            //(x-1,y-1)
            insert(poppie, new Point(pop.x -10  , pop.y -10), 
                    g(pop.x, pop.x-10, pop.y, pop.y-10) , h(pop.x-10, End.x, pop.y-10, End.y),index);
             
            index ++ ;
            //(x-1,y)
            insert(poppie, new Point(pop.x -10 , pop.y), 
                    g(pop.x, pop.x-10, pop.y, pop.y) , h(pop.x-10, End.x, pop.y, End.y),index);
            hp.sort(heapArray);
        

        }
        
        Node writeArray[] = new Node[300];
        Node temp = lastNode;
        int index2=0;
        while(temp.parent != null){
            writeArray[index2] = new Node();
           // System.out.println("index:"+index2);
            writeArray[index2].pos = temp.pos;
            System.out.println("Node:"+temp.pos.x+ ":"+temp.pos.y);            
            temp = temp.parent;
            index2++;
        }
        
        
        BufferedImage img = null; 
        File f = null; 
        try
        { 
            f = new File("red.png"); 
            img = ImageIO.read(f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        } 
        
        
        int a = 200; 
        int r = 0; 
        int g = 250; 
        int b = 0; 
  
        //set the pixel value 
        int p = (a<<24) | (r<<16) | (g<<8) | b;
        for (int i = 0; i < index2; i++) {
            
            Point p1 = writeArray[i].pos;
            System.out.println("P:"+p1);
            img.setRGB(p1.x, p1.y, p);
          
        }
        
        //write image 
        try
        { 
            f = new File("red.png"); 
            ImageIO.write(img, "png", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e);     
        }
         
        
        

        
     } */
        
 }
    
    
    
    
       
    

