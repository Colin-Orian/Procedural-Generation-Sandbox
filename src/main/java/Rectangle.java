//A class to represent a rectangle on a 2d grid
public class Rectangle{
  private int x;
  private int y;
  private int width;
  private int height;
  private int centerX;
  private int centerY;

  public Rectangle(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    centerX = this.x /2;
    centerY = this.y /2;
  }

  public int getX(){return x;}
  public int getY(){return y;}
  public int getCenterX(){return centerX;}
  public int getCenterY(){return centerY;}
  public int getWidth(){return width;}
  public int getHeight(){return height;}

}
