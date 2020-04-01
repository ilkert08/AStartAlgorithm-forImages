package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class newStar {

    private final Point Start;
    private final Point End;
    BufferedImage img = null;
    public int width;
    public int height;
    private ArrayList<Node> visited;
    private MinHeap notVisited;
    private HeapSort hp;
    public int sayac = 0;

    public newStar(Point start, Point end) {
        this.Start = start;
        this.End = end;
        fileWorks();
        visited = new ArrayList<>();
        notVisited = new MinHeap(100000);
    }

    private void fileWorks() {
        File f = null;
        //read image 
        try {
            f = new File("red.png");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        //get image width and height 
        width = img.getWidth();
        height = img.getHeight();
        System.out.println("IMG" + width + ".." + height);

    }

    private double g(Point child) {
        int pixel;
        pixel = img.getRGB(child.x, child.y);
        int r = (pixel >> 16) & 0xff;
        
        // get green 
        // int g = (pixel >> 8) & 0xff;
        // get blue 
        //   int a = pixel & 0xff;
        return r;
    }

    private double h(Point child, Double gg) {
        double value;
        double normalizer;
        double visual; 
        double maxValue;
        int x0 = Start.x; int x1 = child.x;  int x2 = End.x;
        int y0 = Start.y; int y1 = child.y;  int y2 = End.y;
        
        /*//maxValue = Math.sqrt(Math.pow(x0 - x2, 2) + Math.pow(y0 - y2, 2));
        visual = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        //maxValue *= 256;
        value = visual * 100;
        return value;*/
        
        visual = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        /*
        visual -> gg ise
        256    -> x ?
        => x = 256 * gg / visual
        */
        value = gg * visual; //256 * gg / visual;
        return value;






        //normalizer = Math.sqrt(Math.pow(0 - width, 2) + Math.pow(0 - height, 2));
        //visual = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        //value = visual;
        //value = 256 * visual/normalizer; //0-256 arası bir heureistic deger doner.  
        //double xy = visual * gg / normalizer;
        //return xy;  



    }

    private void setColor() {
        BufferedImage img2 = null;
        File f = null;
        try {
            f = new File("red.png");
            img2 = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        int a = 250;
        int r = 0;
        int g = 0;
        int b = 250;

        //set the pixel value 
        int p = (a << 24) | (r << 16) | (g << 8) | b;
        Point pop;
        for (int i = 0; i < currentList.size(); i++) {
            pop = currentList.get(i);
            img2.setRGB(pop.x, pop.y, p);
        }

        a = 250;
        r = 255;
        g = 255;
        b = 0;

        p = (a << 24) | (r << 16) | (g << 8) | b;
        for (int i = 0; i < childList.size(); i++) {
            pop = childList.get(i);
            img2.setRGB(pop.x, pop.y, p);
        }

        //write image 
        try {
            f = new File("reddit.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void endPrint() {
        System.out.println("Sonuc Bulundu");
        Node temp2 = new Node();
        temp2.f = Double.MAX_VALUE;
        for (Node temp : visited) {
            if (temp.pos.equals(End) && temp.f < temp2.f) {
                temp2 = temp;
            }
        }
        BufferedImage img2 = null;
        File f = null;
        try {
            f = new File("red.png");
            img2 = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        int a = 250;
        int r = 0;
        int g = 0;
        int b = 250;

        //set the pixel value 
        int p = (a << 24) | (r << 16) | (g << 8) | b;

        System.out.println("T:" + temp2.parent.pos);
        while (temp2.parent != null) {
            System.out.println("(x,y):(" + temp2.pos.x + "," + temp2.pos.y + ")");
            img2.setRGB(temp2.pos.x, temp2.pos.y, p);
            temp2 = temp2.parent;

        }
        try {
            f = new File("Final.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }

        setColor();
    }

    public ArrayList<Point> currentList = new ArrayList<>();
    public ArrayList<Point> childList = new ArrayList<>();

    public void algoRun() {
        System.out.println("Size" + notVisited.getSize());
        notVisited.insert(new Node(Start, 0, 0, 0));
        int maxIter = 300000;  //10K
        int iterNo = 0;
        System.out.println("Girdi mi");

        while (notVisited.getSize() > 0) {
            long startTime = System.nanoTime();
            iterNo++;
            System.out.println("Iter:" + iterNo);
            Node current = notVisited.remove();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("D1:" + duration);
            //notVisited.minHeap();

            /* for (int i = 0; i < notVisited.size(); i++) {
                if (notVisited.get(i).f < current.f) {
                    current = notVisited.get(i);
                    currentIndex = i;

                }
            }*/
            
            

 /*
            System.out.println("Pos:" + current.pos);
            System.out.println("F:" + current.f);
            System.out.println("G:" + current.g);
            System.out.println("H:" + current.h);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("");*/
 
 
 
 
            /////////MAX ITERATION SITUATION///////////                                            
            if (iterNo > maxIter) {
                System.out.println("Max Iteration!");
                System.out.println("size:" + currentList.size());
                setColor();
                return;
            }
            /////////MAX ITERATION SITUATION///////////

            currentList.add(current.pos);
            visited.add(current);
            
            
            
            
            
            
            ///////////Sonuc Bulunmasi Halinde Yapilan Tek seferlik islemler.
            if (current.pos.equals(End)) { //Sonuç bulduysa             
                endPrint();
                return;           
            }
            ///////////Sonuc Bulunmasi Halinde Yapilan Tek seferlik islemler.

            

            Node childeren[] = new Node[8];
            for (int i = 0; i < childeren.length; i++) {
                childeren[i] = new Node(); //initialization                 
            }
            childeren[0].pos = new Point(current.pos.x, current.pos.y + 1);
            childeren[1].pos = new Point(current.pos.x, current.pos.y - 1);
            childeren[2].pos = new Point(current.pos.x + 1, current.pos.y);
            childeren[3].pos = new Point(current.pos.x + 1, current.pos.y + 1);
            childeren[4].pos = new Point(current.pos.x + 1, current.pos.y - 1);
            childeren[5].pos = new Point(current.pos.x - 1, current.pos.y + 1);
            childeren[6].pos = new Point(current.pos.x - 1, current.pos.y - 1);
            childeren[7].pos = new Point(current.pos.x - 1, current.pos.y);

            boolean locked[] = new boolean[8];

            startTime = System.nanoTime();
            for (int i = 0; i < visited.size(); i++) {
                if (visited.get(i).pos.equals(childeren[0].pos) && locked[0] != true) {
                    locked[0] = true;
                }
                if (visited.get(i).pos.equals(childeren[1].pos) && locked[1] != true) {
                    locked[1] = true;
                }
                if (visited.get(i).pos.equals(childeren[2].pos) && locked[2] != true) {
                    locked[2] = true;
                }
                if (visited.get(i).pos.equals(childeren[3].pos) && locked[3] != true) {
                    locked[3] = true;
                }
                if (visited.get(i).pos.equals(childeren[4].pos) && locked[4] != true) {
                    locked[4] = true;
                }
                if (visited.get(i).pos.equals(childeren[5].pos) && locked[5] != true) {
                    locked[5] = true;
                }
                if (visited.get(i).pos.equals(childeren[6].pos) && locked[6] != true) {
                    locked[6] = true;
                }
                if (visited.get(i).pos.equals(childeren[7].pos) && locked[7] != true) {
                    locked[7] = true;
                }
            }

            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("D1.5:" + duration);

            int childIndex = 0;
            for (Node track : childeren) {
                boolean passage = false;
                /*   startTime = System.nanoTime();
                
                for (int i = 0; i < visited.size(); i++) {
                    if (visited.get(i).pos.equals(track.pos)) {
                        passage = true;
                        break;
                    }
                }
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                System.out.println("D2:"+duration); */
                if (locked[childIndex++] == true) {
                    passage = true;
                }

                startTime = System.nanoTime();
                if (passage != true
                        && //Cocuk Node Visited listesinde yoksa
                        track.pos.x > -1 && track.pos.y > -1
                        && //VE Cocuk Node legal bir adresteyse
                        track.pos.x < width && track.pos.y < height) {
                    track.g = current.g + g(track.pos);
                    track.h = h(track.pos, g(track.pos));
                    track.f = track.g + track.h;
                    boolean breakOut = false; //Dongu kirma degiskeni.                    
                    for (int i = 0; i < notVisited.getSize(); i++) {
                        if (track.g >= notVisited.Heap.get(i).g
                                && track.pos.equals(notVisited.Heap.get(i).pos)) {
                            breakOut = true;
                            break;
                        }
                    }
                    if (breakOut) {
                        breakOut = false;
                        continue;
                    } //notVisited'ta varsa continue.

                    //System.out.println("TRACK:" + track.pos);
                    track.parent = current;
                    notVisited.insert(track);
                    childList.add(track.pos);
                    endTime = System.nanoTime();
                    duration = (endTime - startTime);
                    System.out.println("D3:" + duration);
                }

            }
        }

    }

}
