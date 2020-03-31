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

    public BestFirst() {

    }

    public BestFirst(Point Start, Point End) {
        this.End = End;
        this.Start = Start;
        list = new ArrayList<>();
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

    public void algoRun() {
        boolean found = false;
        Node startNode = new Node(Start);
        list.add(startNode);
        Node endNode = null;
        while (found != true && list.size() != 0) {
            Node current = list.remove(0);
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
            sortArray();
        }
        Node tempNode = endNode;
        while(tempNode != null){
            System.out.println("element:"+tempNode.pos);
            tempNode = tempNode.parent;
        }
        
        
        
    }

}
