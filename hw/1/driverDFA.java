import java.util.Scanner;

public class driverDFA {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String word = in.next();
    ManWolf mw = new ManWolf();
    for(int i = 0; i < word.length(); i++) {
      mw.transition(word.charAt(i));
    }
    if(mw.finished()) {
      System.out.println("That is a solution.");
    } else {
      System.out.println("That is not a solution.");
    }
  }
}

