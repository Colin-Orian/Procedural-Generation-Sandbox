public class Driver{
  public static void main(String[] args){
    ImageCreator creator = new ImageCreator(2048, 2048);
    creator.perlin(4);
    ImageSaver.save(creator.getBufferedImage(), "jpg", "refactorPerlin");
   }
}
