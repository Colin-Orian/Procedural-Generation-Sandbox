/* An implementation of Perlin Noise created by Ken Perlin in 2002
*Sources: Understanding Perlin Noise: http://flafla2.github.io/2014/08/09/perlinnoise.html
*         Ken Perlin's implementation: https://mrl.nyu.edu/~perlin/noise/
*/
public class PerlinNoise{
  private static final int doublePermutation[] = new int[512];

  //Hash lookup table defined in Perlin's algorithm
  private static final int permutation[] = { 151,160,137,91,90,15,
   131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
   190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
   88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
   77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
   102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
   135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
   5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
   223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
   129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
   251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
   49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
   138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180
   };
   /*Also taken from Perlin's algorithm. Creates an array that contains 2
   copies of the set contained in the permutation variable.
   */
   static {
     for(int i = 0; i < 256; i++){
       doublePermutation[256+i] = doublePermutation[i] = permutation[i];
     }
   }
   /*Perlin defined this fade function.
   * It's meant to make the Linear Interpolation appear more natural.
   * Although I could use built in math functions like Math.exp(), I can reuse
   * previous calculations using this method
   * Function: 6*t^5 - 15*t^4 + 10*t^3
   *
   */
   private static double fade(double t){
     return t * t * t * (t * (t * 6 - 15) + 10);
   }
   /*Linear Interpolation
   */
   private static double lerp(double a, double b, double t){
     return a  + t * (b-a);
   }
   /*Finds the dot product of the vector xy with a pseudorandom gradient vector.
   *I found this on Stack Overflow and I still find it a bit confusion.
   * https://stackoverflow.com/questions/10729891/perlin-noise-gradient-function
   *From what I undestand, "(hash & 1) ? x : -x)" says:
   * "if variable "hash"'s right most bit is true, then x else -x"
   * The same logic applys to the next section except for the 2nd most right bit
   *@param hash: Hash to decided the random gradient
   *@param x: the x value
   *@param y: the y value
   */
   private static double grad(int hash, double x, double y){
     return ((hash & 1) == 1 ? x : -x) + ((hash & 2) == 2 ? y : -y);
   }

   /*Computes the noise at given [x,y] co-ordinate
   *@param x: an X co-ordinate on a 2d grid
   *@param y; an Y co-ordinate on a 2d grid
   */
   public static double noise(double x, double y){

     int xMod = (int)x % 255;
     int yMod = (int)y % 255;
     double xRemainder = x - (int)x;
     double yRemainder = y - (int)y;
     double xFade = fade(xRemainder);
     double yFade = fade(yRemainder);

     //Hash values coresponding to the vertices of the square.
     int bottomLeft, topLeft, topRight, bottomRight;
     bottomLeft = doublePermutation[doublePermutation[xMod] + yMod];
     topLeft = doublePermutation[doublePermutation[xMod] + (yMod+1)];
     topRight = doublePermutation[doublePermutation[(xMod+1)] + (yMod+1)];
     bottomRight = doublePermutation[doublePermutation[(xMod+1)] + yMod];

     /*
     First finds the dot product between the pseudorandom gradVector and the
     distance vector between the vertices and the original co-ord. Then
     interpolatates between the dot products.
     */
     double interBottom, interTop, interFinal;
     double temp1, temp2;
     //Interpolation between the bottom points
     temp1 = grad(bottomLeft, xRemainder, yRemainder);
     temp2 = grad(bottomRight, xRemainder-1, yRemainder);
     interBottom = lerp(temp1, temp2, xFade);
     //Interpolation between the top points
     temp1 = grad(topLeft, xRemainder, yRemainder-1);
     temp2 = grad(topRight, xRemainder-1, yRemainder-1);
     interTop = lerp(temp1, temp2, xFade);
     //Interpolation between the results and bound it between 0 and 1
     interFinal = lerp(interBottom, interTop, yFade);
     return (interFinal + 1)/2;
   }
}
