import java.util.Scanner;
import java.util.Arrays;

public class Game {
  public static void main(String[] args) {
    System.out.println("Welcome to Tick-Tack-Turk!");
    Player player = new Player();
    Scanner in = new Scanner(System.in);
    while(player.isDone()) {
      System.out.print("Current board state: \n\t" + player.current);
      player.transition(validate(in.next()));
    }
    System.out.println("Game completed at state: \n\t" + player.current);
    if(player.draw()) {
      System.out.println("Its a draw");
    } else {
      System.out.println("You lose");
    } 
  }
  
  static void printState(State current) {
    String name = current.name();
    String[][] board = new String[3][3];
    for(int i = 2; i < name.length(); i+=3) {
      board[Integer.parseInt(name.substring(i-1,i))][Integer.parseInt(name.substring(i,i+1))] = name.substring(i-2, i-1);
    }
    System.out.println(Arrays.deepToString(board));
  }
  
  
  static int[] validate(String input) {
    if(input.length() == 3 && input.charAt(0) == 'O') {
      int[] rowCol = new int[2];
      rowCol[0] = Integer.parseInt(input.substring(1,2));
      rowCol[1] = Integer.parseInt(input.substring(2,3));
      return rowCol;
    } else {
      throw new IllegalArgumentException("Invalid input.  Only valid input follows the format O[row][col]");
    }
  }
} 
