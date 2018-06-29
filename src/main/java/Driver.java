import java.awt.Color;
import java.util.Random;
public class Driver{
  public static void main(String[] args){
    int width = 2048;
    int height = 2048;
    ImageHandler imageHandler = new ImageHandler(width, height);
    Color colours[][] = ColourGenerator.perlin(width, height, 8);
    imageHandler.colourPixels(colours);
    imageHandler.save("jpg", "perlin");
   }
}
