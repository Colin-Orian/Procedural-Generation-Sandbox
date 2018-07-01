import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;
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
  /*Randomly assigns a rgb value to each pixel. The width and height must equal
  *each other (i.e. a square).
  *The jump must be a power of 2 (i.e. 1, 2, 4, 16, etc.).
  *@param width: The width of the image
  *@param height: The height of the image
  *@param jump: How many pixels will be coloured for each itteration. For example,
  *a jump of 4 will colour a 4 X 4 square of pixels each itteration.
  */
  public void randomRGB(int jump){
    Color colours[][] = new Color[this.WIDTH][this.HEIGHT];
    Random random = new Random();
    for(int x = 0; x < this.WIDTH; x+=jump){
      for(int y = 0; y < this.HEIGHT; y+=jump){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        graphics.setColor(new Color(r,g,b));
        graphics.drawRect(x, y, jump, jump);
        graphics.fillRect(x,y, jump, jump);
      }
    }
  }

  public void fillBackground(int r, int g, int b){
    graphics.setColor(new Color(r, g, b));
    graphics.fillRect(0,0, this.WIDTH, this.HEIGHT);

  }
  /*Uses Perlin Noise to generate the colours of the image.
  *Algorithm found here: http://flafla2.github.io/2014/08/09/perlinnoise.html
  *The width and height must equal each other (i.e. a square).
  *The jump must be a power of 2 (i.e. 1, 2, 4, 16, etc.).
  *@param width: The width of the image
  *@param height: The height of the image
  *@param jump: How many pixels will be coloured for each itteration. For example,
  *a jump of 4 will colour a 4 X 4 square of pixels each itteration.
  */
  public void perlin(int jump){
    Color colours[][] = new Color[this.WIDTH][this.HEIGHT];
    for(int x = 0; x < this.WIDTH; x+=jump){
      for(int y = 0; y < this.HEIGHT; y+=jump){

        double r = 255 *PerlinNoise.octavePerlin(x+0.934,y+0.1234, 10, 2.4);
        double g = 255 *PerlinNoise.octavePerlin(x+0.432,y, 5, 4.2);
        double b = 255 *PerlinNoise.octavePerlin(x+0.8,y+0.91, 3, 7);
        graphics.setColor(new Color((int)r,(int)g,(int)b));
        graphics.drawRect(x, y, jump, jump);
        graphics.fillRect(x,y, jump, jump);
      }
    }
  }

  public BufferedImage getBufferedImage(){return image;}
}
