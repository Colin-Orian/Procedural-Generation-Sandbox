import java.util.Random;
import java.awt.Graphics2D;

public class Room{
  private int x;
  private int y;
  private int width;
  private int height;

  public Room(Rectangle rect, Random random){
    x = rect.getX() + random.nextInt(rect.getWidth()/3);
    y = rect.getY() + random.nextInt(rect.getHeight()/3);

    width = rect.getWidth() - (x - rect.getX());
    height = rect.getHeight() - (y - rect.getY());

    width -= random.nextInt(width/3);
    height -= random.nextInt(height/3);
  }

  public void drawRoom(Graphics2D graphics){
    graphics.fillRect(x, y, width, height);
  }
}
