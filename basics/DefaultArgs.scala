

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int =
    if (n <=1) acc
    else trFact(n - 1, n * acc)

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080) = println(s"$format $width $height")

  savePicture(800, 600)
  savePicture()
  savePicture(width = 800)

  /*
   * 1. pass in every leading argument
   * 2. name the arguments
   */

  savePictiure(height = 600, width = 800, format = "bmp")

}
