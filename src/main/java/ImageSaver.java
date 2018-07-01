import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//A class that contains an image and manipulates the image

public class ImageSaver{
  /*Creates an image with width and height
  *@param width: The width of the image
  *@param height: The height of the image
  */
  /*Saves the image to the computer
  *@param fileFormat: The type of file to save
  *@param outName: Name of the file without the file extension
  */
  public static void save(BufferedImage image, String fileFormat, String outName){
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
}
