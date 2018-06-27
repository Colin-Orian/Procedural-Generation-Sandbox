import java.awt.Color;
import java.util.Random;
//A class that contains functions to generate a 2d array of pixel colours
public class ColourGenerator{
  /*Randomly assigns a rgb value to each pixel. The width and height must equal
  *each other (i.e. a square).
  *The jump must be a power of 2 (i.e. 1, 2, 4, 16, etc.).
  *@param width: The width of the image
  *@param height: The height of the image
  *@param jump: How many pixels will be coloured for each itteration. For example,
  *a jump of 4 will colour a 4 X 4 square of pixels each itteration.
  */
  public static Color[][] randomRGB(int width, int height, int jump){
    Color colours[][] = new Color[width][height];
    Random random = new Random();
    for(int x = 0; x < width; x+=jump){
      for(int y = 0; y < height; y+=jump){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        for(int i = 0; i < jump; i ++){
          for(int j = 0; j < jump; j ++){
            colours[x+i][y+j] = new Color(r,g,b);
          }
        }
      }
    }
    return colours;
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
  public static Color[][] perlinNoise(int width, int height, int jump){
    Color colours[][]
    return colours;
  }
}
