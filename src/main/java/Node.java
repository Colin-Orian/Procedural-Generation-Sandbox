import java.util.Random;
import java.awt.Graphics2D;
import java.lang.Math;
//A class to represent a node for a Binary Space Partitioning(BSP) Tree
public class Node{
  private Rectangle rectangle;

  private Node leftChild;
  private Node rightChild;
  private Room room;
  private Rectangle path;

  public Node(Rectangle rect){
    rectangle = rect;

    leftChild = null;
    rightChild = null;

  }
  /*Generates a room
  *@param random: The desired Random Number Generator
  */
  public void genRoom(Random random){
    room = new Room(rectangle, random);
  }

  /*Draws a room onto a graphics instance
  *@param graphics: The graphics instance to draw on
  */
  public void drawRoom(Graphics2D graphics){
    room.drawRoom(graphics);
  }
  //Generates a path between the two children
  public void genPath(){
    Rectangle leftRect = leftChild.getRectangle();
    Rectangle rightRect = rightChild.getRectangle();
    //If the parent rectangle's width is greater than its height, then the children
    //are split vertically
    if(rectangle.getWidth() >= rectangle.getHeight()){
      //Path starts at the center of the left child, has the width of the distance
      //Between children and an arbitraty height
      path = new Rectangle(leftRect.getCenterX(), leftRect.getCenterY(),
                          Math.abs(rightRect.getCenterX() - leftRect.getCenterX()), 10);
    }else{
      //Same as above but with height
      path = new Rectangle(leftRect.getCenterX(), leftRect.getCenterY(),
                           10,Math.abs(rightRect.getCenterY() - leftRect.getCenterY()));

    }
  }
  /*Draws the path onto a graphics instance
  *@param graphics: The graphics instance to draw on
  */
  public void drawPath(Graphics2D graphics){
    Rectangle leftRect = leftChild.getRectangle();
    Rectangle rightRect = rightChild.getRectangle();
    if(rectangle.getWidth() >= rectangle.getHeight()){
      //The starting xy will be the path's xy values. The ending values will be
      //path's x value plus width and will have the same y value.
      graphics.drawLine(path.getX(), path.getY(), path.getX() + path.getWidth(),
                        path.getY());
    }else{
      graphics.drawLine(path.getX(), path.getY(), path.getX(), path.getHeight()
                        + path.getY());
    }
  }

  public Node getLeftChild(){return leftChild;}
  public Node getRightChild(){return rightChild;}
  public Rectangle getRectangle(){return rectangle;}
  public void setLeftChild(Node node){leftChild = node;}
  public void setRightChild(Node node){rightChild = node;}
}
