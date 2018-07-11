//A class to represent a node for a Binary Space Partitioning(BSP) Tree
public class Node{
  Rectangle rectangle;

  private Node leftChild;
  private Node rightChild;

  public Node(Rectangle rect){
    rectangle = rect;

    leftChild = null;
    rightChild = null;
  }

  public Node getLeftChild(){return leftChild;}
  public Node getRightChild(){return rightChild;}
  public Rectangle getRectangle(){return rectangle;}
  public void setLeftChild(Node node){leftChild = node;}
  public void setRightChild(Node node){rightChild = node;}
}
