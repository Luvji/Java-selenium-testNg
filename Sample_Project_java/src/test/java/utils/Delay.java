package utils;

public class Delay {

    public static void delay(long sec) {
    sec = sec > 0 ? sec : 1;
    try {
      Thread.sleep((long) sec * 1000); // Sleep for the specified duration
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void delay() {
    delay(1); // Default to 1 second
  }

}
