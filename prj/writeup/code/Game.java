import java.util.Scanner;
import java.util.Arrays;

public class Game {
  public static void main(String[] args) {
    System.out.println("Welcome to Tick-Tack-Turk!");
    Player player = new Player();
    Scanner in = new Scanner(System.in);
    while(!player.isDone()) {
      System.out.print("Current board state: \n\t" + player.current);
      player.transition(Player.validate(in.next()));
    }
    System.out.println("Game completed at state: \n\t" + player.current);
    if(player.draw()) {
      System.out.println("Its a draw");
    } else {
      System.out.println("You lose");
    } 
  }
  

} 
