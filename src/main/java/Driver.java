public class Driver{
  public static void main(String[] args){
    ImageCreator creator = new ImageCreator(2048, 2048);
    creator.drawBSP(4, 1245);
    ImageSaver.save(creator.getBufferedImage(), "png", "rooms");

   }
}
