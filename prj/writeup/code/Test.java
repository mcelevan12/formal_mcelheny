import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(in.hasNextLine()) {
      Player player = new Player();      
      for(String input : in.nextLine().split("(?<=\\G.{3})")) {
        player.transition(Player.validate(input));
      }
      System.out.println(player.isDone()?"accepted":"rejected");
    }
  }
}
