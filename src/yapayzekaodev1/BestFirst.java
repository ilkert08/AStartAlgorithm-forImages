package yapayzekaodev1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class BestFirst {

    private Point Start;
    private Point End;
    private int width;
    private int height;
    BufferedImage img = null;
    private ArrayList<Node> list;
    private minHeapforBestFirst list2;
    Node endNode;

    public BestFirst() {

    }

    public BestFirst(Point Start, Point End) {
        this.End = End;
        this.Start = Start;
        endNode = null;
        list = new ArrayList<>();
        list2 = new minHeapforBestFirst(100000);
        fileWorks();
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

    private double h(Point child) {
        double value;
        double normalizer;
        double visual;
        int x1 = child.x;
        int x2 = End.x;
        int y1 = child.y;
        int y2 = End.y;
        normalizer = Math.sqrt(Math.pow(0 - width, 2) + Math.pow(0 - height, 2));
        visual = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        value = visual;
        return value;
    }

    private Node[] childNode(Node current) {
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
        return childeren;
    }

    private ArrayList<Node> sortArray() {
        Double min = list.get(0).h;
        int index;
        int size = list.size();

        // One by one move boundary of unsorted subarray  
        for (int i = 0; i < size - 1; i++) {
            // Find the minimum element in unsorted array  
            index = i;
            for (int j = i + 1; j < size; j++) {
                if (list.get(j).h < list.get(index).h) {
                    index = j;
                }
            }
            Node temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
        return list;
    }

    public void print() {
        BufferedImage img2 = null;
        File f = null;
        try {
            f = new File("red.png");
            img2 = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        int a = 255;
        int r = 100;
        int g = 100;
        int b = 100;

        //set the pixel value 
        int p = (a << 24) | (r << 16) | (g << 8) | b;
        Point pop;

        Node tempNode = endNode;
        while (tempNode != null) {
            pop = tempNode.pos;
            img2.setRGB(pop.x, pop.y, p);
            tempNode = tempNode.parent;
        }

        //write image 
        try {
            f = new File("bestfirst.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
        public void printforHeap() {
        BufferedImage img2 = null;
        File f = null;
        try {
            f = new File("red.png");
            img2 = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        int a = 255;
        int r = 100;
        int g = 100;
        int b = 100;

        //set the pixel value 
        int p = (a << 24) | (r << 16) | (g << 8) | b;
        Point pop;

        Node tempNode = endNode;
        while (tempNode != null) {
            pop = tempNode.pos;
            img2.setRGB(pop.x, pop.y, p);
            tempNode = tempNode.parent;
        }

        //write image 
        try {
            f = new File("bestfirstwithheap.png");
            ImageIO.write(img2, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void algoRun() {
        boolean found = false;
        Node startNode = new Node(Start);
        list.add(startNode);
        endNode = null;
        while (found != true && list.size() != 0) {
            Node current = list.get(0);
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).h < current.h){
                    current = list.get(i);
                    index = i;
                }             
            }
            
            list.remove(index);
            
            
            
            
            if (current.pos.equals(End)) {
                found = true;
                endNode = current;
            }

            Node[] childs = childNode(current);
            for (Node child : childs) {
                child.h = h(child.pos);
                child.parent = current;
                list.add(child);
            }
            //sortArray();
        }
        Node tempNode = endNode;
        while (tempNode != null) {
            System.out.println("element:" + tempNode.pos);
            tempNode = tempNode.parent;
        }
        print();

    }

    public void algoRunWithHeap() {
        boolean found = false;
        Node startNode = new Node(Start);
        list.add(startNode);
        endNode = null;
        while (found != true && !list.isEmpty()) {               
            Node current = list2.remove();
            if (current.pos.equals(End)) {
                found = true;
                endNode = current;
            }

            Node[] childs = childNode(current);
            for (Node child : childs) {
                child.h = h(child.pos);
                child.parent = current;
                list2.insert(child);
            }        
        }
        Node tempNode = endNode;
        while (tempNode != null) {
            System.out.println("element:" + tempNode.pos);
            tempNode = tempNode.parent;
        }
        printforHeap();
    }
}