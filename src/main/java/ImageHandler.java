import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.*;

//A class that contains an image and manipulates the image

public class ImageHandler{
  BufferedImage image;
  /*Creates an image with width and height
  *@param width: The width of the image
  *@param height: The height of the image
  */
  public ImageHandler(int width, int height){
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  /*Saves the image to the computer
  *@param fileFormat: The type of file to save
  *@param outName: Name of the file without the file extension
  */
  public void save(String fileFormat, String outName){
    if(!fileFormat.equals("png")&&
       !fileFormat.equals("jpg")&&
       !fileFormat.equals("gif")){
      System.err.println("Invalid file format! Valid formats: png, jpg, gif");
      return;
    }
    try{
      File outputFile = new File("output/" + outName +"."+fileFormat);
      ImageIO.write(image, fileFormat, outputFile);
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  /*Fills the entire image with the given colour
  *@param colour: The desired rgb colour
  */
  public void fill(Color colour){
    int rgb = colour.getRGB();
    for(int x = 0; x < image.getWidth(); x ++){
      for(int y = 0; y < image.getHeight(); y ++){
        image.setRGB(x, y, rgb);
      }
    }
  }
  /*Sets the colour of the pixel given by the 2d array
  *@param colours: a 2d array to represent pixel colours
  */
  public void colourPixels(Color[][] colours){
    for(int x = 0; x < image.getWidth(); x ++){
      for(int y = 0; y < image.getHeight(); y ++){
        image.setRGB(x, y, colours[x][y].getRGB());
      }
    }
  }
}
