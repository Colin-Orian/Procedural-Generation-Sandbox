import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Color;
/*An implementation of a Binary Spacing Partitioning (BSP) Tree.This will split
* an area randomly and repeat the process recursively on the resulting rectangles
* Adapted from: https://eskerda.com/bsp-dungeon-generation/
*/
public class BSPTree{
    private Node root;
    private Random random;
    private final double BOUND =  0.30;

    private int gridWidth;
    private int gridHeight;
    /*
    * @param width: The width of the largest rectangle
    * @param height: The height of the largest rectangle
    * @param seed: The seed that the random number generator will use
    */
    public BSPTree(int width, int height, long seed){
      random = new Random(seed);
      root = new Node(new Rectangle(0,0, width, height));
    }
    public BSPTree(int width, int height){
      random = new Random();
      root = new Node(new Rectangle(0,0, width, height));
    }


    /*Populates the tree with rectangles
    * @param iter: the number of iterations.
    *              The number of rooms will be 2^iter
    */
    public void popTree(int iter){
      splitNode(root, iter);
    }
    /*Creates a left and right child for the parent node. Gives a rectangle to
    * the children
    * @param parent: The parent node
    * @param iter: The current itteration
    */
    private void splitNode(Node parent, int iter){
      if(iter == 0){
        return;
      }
      Rectangle[] rectangles = genRectangle(parent.getRectangle());
      Node temp = new Node(rectangles[0]);
      parent.setLeftChild(temp);

      temp = new Node(rectangles[1]);
      parent.setRightChild(temp);

      splitNode(parent.getLeftChild(), iter-1);
      splitNode(parent.getRightChild(), iter-1);
    }

    /*Generates two rectangles that are created by spliting the parent rectangle
    * @param parent: The parent rectangle
    * @return: An array of 2 rectangles. One for each child node
    */
    private Rectangle[] genRectangle(Rectangle parent){
      Rectangle[] rectangles = new Rectangle[2];

      /*Determines if the parent is split horziontally or vertically
      *By having it alterinate between horizontal and vertical, it prevents vertically
      *long and narrow rectangles
      */
      if(parent.getWidth() > parent.getHeight()){
        /*How much you want to split the parent rectangle. As BOUND aproaches 0.5,
        * the farther the split can be away from the middle of the parent
        */
        double splitRatio = parent.getWidth() * this.BOUND;
        int splitWidth = random(parent.getWidth() /2 -splitRatio,
                                parent.getWidth() /2 + splitRatio);

        /*If you're spliting vertically,the first child rectangle
        * will have the same x,y co-ords and height as the parent. It will have a
        * random width between the ratio and the parent's width
        */
        rectangles[0] = new Rectangle(parent.getX(), parent.getY(),
                                      splitWidth, parent.getHeight());
        /*The second child rectangle will have the same height and y co-ords as
        * the parent. It will have the same x co-ords as the parent PLUS the split
        * width. The width will be the remaining width of the parent
        */
        rectangles[1] = new Rectangle(parent.getX()+splitWidth,parent.getY(),
                                      parent.getWidth()-splitWidth,
                                      parent.getHeight());
      }else{
        //How much you want to split the parent rectangle
        double splitRatio = parent.getHeight() * this.BOUND;
        int splitHeight = random(parent.getHeight() /2 -splitRatio,
                                 parent.getHeight() /2 + splitRatio);

        /*Uses the same concept as spliting vertically but changes height and y
        * co-ords instead of width and x co-ords
        */
        rectangles[0] = new Rectangle(parent.getX(), parent.getY(),
                                      parent.getWidth(), splitHeight);

        rectangles[1] = new Rectangle(parent.getX(), parent.getY()+splitHeight,
                                      parent.getWidth(),
                                      parent.getHeight()-splitHeight);
      }
      return rectangles;
    }

    /*Draws the rectangles onto a graphics instance
    * @param graphics: What you want the rectangles to be drawn on
    */
    public void drawTree(Graphics2D graphics){
      graphics.setColor(new Color(148, 0, 211));
      drawTree(graphics, root);
    }
    /*Recursively draws the rectangles for each node onto a graphics instance
    * @param graphics: What you want the rectangles to be drawn on
    * @param node: The current node
    */
    private void drawTree(Graphics2D graphics, Node node){
      Rectangle rect = node.getRectangle();
      graphics.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
      if(null != node.getLeftChild()){
        drawTree(graphics, node.getLeftChild());
      }
      if(null != node.getRightChild()){
        drawTree(graphics, node.getRightChild());
      }
    }
    //Returns a random number between min (inclusive) and max (exclusive)
    private int random(double min, double max){
      return (int)Math.floor(random.nextDouble() * (max - min + 1) + min);
    }
}
