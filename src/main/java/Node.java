import java.util.Random;
import java.awt.Graphics2D;
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
  public void genRoom(Random random){
    room = new Room(rectangle, random);
  }
  public void drawRoom(Graphics2D graphics){
    room.drawRoom(graphics);
  }
  public void genPath(){
  }
  public void drawPath(Graphics2D graphics){
  }

  public Node getLeftChild(){return leftChild;}
  public Node getRightChild(){return rightChild;}
  public Rectangle getRectangle(){return rectangle;}
  public void setLeftChild(Node node){leftChild = node;}
  public void setRightChild(Node node){rightChild = node;}
}
