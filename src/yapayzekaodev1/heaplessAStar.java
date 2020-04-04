package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class heaplessAStar {

    private final Point Start;
    private final Point End;
    BufferedImage img = null;
    public int width;
    public int height;
    private ArrayList<Node> visited;
    private ArrayList<Node> notVisited;
    private HeapSort hp;
    public int sayac = 0;

    public heaplessAStar(Point start, Point end) {
        this.Start = start;
        this.End = end;
        fileWorks();
        visited = new ArrayList<>();
        notVisited = new ArrayList<>();
        // hp = new HeapSort();
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
        int g = (pixel >> 8) & 0xff;

        // get blue 
        int a = pixel & 0xff;
        return 256 - r;
    }

    private double h(Point child, Double gg) {
        double value;
        double normalizer;
        double visual;
        int x1 = child.x;
        int x2 = End.x;
        int y1 = child.y;
        int y2 = End.y;
        normalizer = Math.sqrt(Math.pow(0 - width, 2) + Math.pow(0 - height, 2));
        visual = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        value = gg * visual;

        //256 * visual/normalizer; //0-256 arası bir heureistic deger doner.  
        //double xy = visual * gg / normalizer;
        return value;
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
            f = new File("redditpp.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void endPrint() {
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
            f = new File("Finaltt.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }

        setColor();
    }

    private void printCurrent(Node current) {
        System.out.println("Pos:" + current.pos);
        System.out.println("F:" + current.f);
        System.out.println("G:" + current.g);
        System.out.println("H:" + current.h);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("");
    }

    private void sortArray() {
        Double min = notVisited.get(0).f;
        int index;
        int size = notVisited.size();

        // One by one move boundary of unsorted subarray  
        for (int i = 0; i < size - 1; i++) {
            // Find the minimum element in unsorted array  
            index = i;
            for (int j = i + 1; j < size; j++) {
                if (notVisited.get(j).f < notVisited.get(index).f) {
                    index = j;
                }
            }
            Node temp = notVisited.get(index);
            notVisited.set(index, notVisited.get(i));
            notVisited.set(i, temp);
        }
    }

    int partition(int low, int high) {
        Node pivot = notVisited.get(high);
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot 
            if (notVisited.get(j).f < pivot.f) {
                i++;

                // swap arr[i] and arr[j] 
                Node temp = notVisited.get(i);
                notVisited.set(i, notVisited.get(j));
                notVisited.set(j, temp);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot) 
        Node temp = notVisited.get(i+1);
        notVisited.set(i+1, notVisited.get(high));
        notVisited.set(high, temp);
        return i + 1;
    }

    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(low, high);

            // Recursively sort elements before 
            // partition and after partition 
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    public ArrayList<Point> currentList = new ArrayList<>();
    public ArrayList<Point> childList = new ArrayList<>();

    public void algoRun() {

        notVisited.add(new Node(Start));
        notVisited.get(0).g = 0;
        notVisited.get(0).h = 0;
        notVisited.get(0).f = 0;
        int maxIter = 500000;  //10K
        int iterNo = 0;

        while (notVisited.size() > 0) {
            iterNo++;
            System.out.println("Iter:" + iterNo);

            //sortArray(); // Selection sort!
            //sort(0, notVisited.size()-1);//QuickSort
            Node current = notVisited.get(0);
            int currentIndex = 0;

            
           for (int i = 0; i < notVisited.size(); i++) {
                if (notVisited.get(i).f < current.f) {
                   // System.out.println("Current:"+current.f +"get(i):"+notVisited.get(i).f+"i"+i);
                    current = notVisited.get(i);
                    currentIndex = i;
                }
            }
            //printCurrent(current);
            currentList.add(current.pos);

            /////////MAX ITERATION SITUATION///////////
            if (iterNo > maxIter) {
                System.out.println("Max Iteration!");
                System.out.println("size:" + currentList.size());
                setColor();
                return;
            }
            /////////MAX ITERATION SITUATION///////////

            notVisited.remove(currentIndex);
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

            int childIndex = 0;
            for (Node track : childeren) {
                boolean passage = false;

                if (locked[childIndex++] == true) {
                    passage = true;
                }

                if (passage != true
                        && //Cocuk Node Visited listesinde yoksa
                        track.pos.x > -1 && track.pos.y > -1
                        && //VE Cocuk Node legal bir adresteyse
                        track.pos.x < width && track.pos.y < height) {
                    track.g = current.g + g(track.pos);
                    track.h = h(track.pos, g(track.pos));
                    track.f = track.g + track.h;
                    boolean breakOut = false; //Dongu kirma degiskeni.

                    for (int i = 0; i < notVisited.size(); i++) {
                        if (track.g >= notVisited.get(i).g
                                && track.pos.equals(notVisited.get(i).pos)) {
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
                    notVisited.add(track);
                    childList.add(track.pos);
                }

            }
        }

    }

}
