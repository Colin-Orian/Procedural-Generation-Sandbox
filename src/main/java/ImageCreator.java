import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.Random;
import java.lang.Math;
//A class that contains functions to generate a 2d array of pixel colours
public class ImageCreator{
  BufferedImage image;
  Graphics2D graphics;
  final int WIDTH;
  final int HEIGHT;
  public ImageCreator(int width, int height){
    this.WIDTH = width;
    this.HEIGHT = height;
    image = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);
    graphics = image.createGraphics();
  }
  /*Randomly assigns a rgb value to each pixel.
  *@param width: The width of the image
  *@param height: The height of the image
  *@param jump: How many pixels will be coloured for each itteration. For example,
  *a jump of 4 will colour a 4 X 4 square of pixels each itteration.
  */
  public void randomRGB(int xJump, int yJump){
    Color colours[][] = new Color[this.WIDTH][this.HEIGHT];
    Random random = new Random();
    for(int x = 0; x < this.WIDTH; x+=xJump){
      for(int y = 0; y < this.HEIGHT; y+=yJump){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        graphics.setColor(new Color(r,g,b));
        graphics.drawRect(x, y, xJump, yJump);
        graphics.fillRect(x,y, xJump, yJump);
      }
    }
  }
  /*Randomly assigns a rgb value to each pixel.
  *@param jump: What the x and y jump should be.
  */
  public void randomRGB(int jump){
    randomRGB(jump, jump);
  }


public void grid(int gridsAcross, int gridsDown){
  int gridWidth = this.WIDTH / gridsAcross;
  int gridHeight = this.HEIGHT / gridsDown;
  int horizonStart = 0;
  int verticalStart = 0;
  graphics.setColor(new Color(211, 211, 211));
  graphics.setStroke(new BasicStroke(5));
  for(int i = 0; i < gridsAcross; i++){
    graphics.drawLine(horizonStart, 0, horizonStart, this.HEIGHT);
    horizonStart += gridWidth;
  }
  for(int i = 0; i < gridsDown; i ++){
    graphics.drawLine(0,verticalStart,this.WIDTH, verticalStart);
    verticalStart += gridHeight;
  }
}

  /*Uses Perlin Noise to generate the colours of the image.
  *Algorithm found here: http://flafla2.github.io/2014/08/09/perlinnoise.html
  *@param width: The width of the image
  *@param height: The height of the image
  *@param jump: How many pixels will be coloured for each itteration. For example,
  *a jump of 4 will colour a 4 X 4 square of pixels each itteration.
  */
  public void perlin(int xJump, int yJump){
    Color colours[][] = new Color[this.WIDTH][this.HEIGHT];
    for(int x = 0; x < this.WIDTH; x+=xJump){
      for(int y = 0; y < this.HEIGHT; y+=yJump){

        double r = 255 *PerlinNoise.octavePerlin(x+0.934,y+0.1234, 10, 2.4);
        double g = 255 *PerlinNoise.octavePerlin(x+0.432,y, 5, 4.2);
        double b = 255 *PerlinNoise.octavePerlin(x+0.8,y+0.91, 3, 7);
        graphics.setColor(new Color((int)r,(int)g,(int)b));
        graphics.drawRect(x, y, xJump, yJump);
        graphics.fillRect(x,y, xJump, yJump);
      }
    }
  }
  /*Uses Perlin Noise to generate the colours of the image.
  *@param jump: What the x and y jump should be.
  */
  public void perlin(int jump){
    perlin(jump, jump);
  }

  public void drawBSP(int iter, long seed){
    graphics.setStroke(new BasicStroke(5));
    BSPTree tree = new BSPTree(this.WIDTH, this.HEIGHT, seed);
    tree.popTree(iter);
    tree.drawTree(graphics);
    tree.drawRooms(graphics);
    tree.drawPath(graphics);
  }

  public void drawBSP(int iter){
    graphics.setStroke(new BasicStroke(5));
    BSPTree tree = new BSPTree(this.WIDTH, this.HEIGHT);
    tree.popTree(iter);
    tree.drawTree(graphics);
    tree.drawRooms(graphics);
    tree.drawPath(graphics);

  }

  public BufferedImage getBufferedImage(){return image;}
}
