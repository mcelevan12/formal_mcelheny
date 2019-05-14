import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Player {

  final static State START = State.X00;
  EnumMap<State, State[][]> delta;
  Set<State> accepting;
  Set<State> draw;
  State current;
  
  public Player() {
    current = START;
    delta = new EnumMap<>(State.class);
    delta.put(State.X00, new State[][] {
      {State.ERR, State.X00O01X11, State.X00O02X20},
      {State.X00O10X11, State.X00O11X22, State.X00O12X11},
      {State.X00O20X02, State.X00O21X11, State.X00O22X02}});
    //O top middle
    delta.put(State.X00O01X11, new State[][] {
        {State.ERR, State.ERR, State.X00O01X11O02X22},
        {State.X00O01X11O10X22, State.ERR, State.X00O01X11O12X22},
        {State.X00O01X11O20X22, State.X00O01X11O21X22, State.X00O01X11O22X20}});
    delta.put(State.X00O01X11O22X20, new State[][] {
        {State.ERR, State.ERR, State.X00O01X11O22X20O02X10},
        {State.X00O01X11O22X20O10X02, State.ERR, State.X00O01X11O22X20O21X10},
        {State.ERR, State.X00O01X11O22X20O21X10, State.ERR}});
    //O top right
    delta.put(State.X00O02X20, new State[][] {
        {State.ERR, State.X00O02X20O01X1O, State.ERR},
        {State.X00O02X20O10X22, State.X00O02X20O11X10, State.X00O02X20O12X10},
        {State.ERR, State.X00O02X20O21X10, State.X00O02X20O22X10}});
    delta.put(State.X00O02X20O10X22, new State[][] {
      {State.ERR, State.X00O02X20O10X22O10X21, State.ERR},
      {State.ERR, State.X00O02X20O10X22O11X21, State.X00O02X20O10X22O12X21},
      {State.ERR, State.X00O02X20O10X22O21X11, State.ERR}});
    //O middle right
    delta.put(State.X00O12X11, new State[][] {
      {State.ERR, State.X00O12X11O01X22, State.X00O12X11O02X22},
      {State.X00O12X11O10X22, State.ERR, State.ERR},
      {State.X00O12X11O20X22, State.X00O12X11O21X22, State.X00O12X11O22X02}});
    delta.put(State.X00O12X11O22X02, new State[][] {
      {State.ERR, State.X00O12X11O22X02O01X20, State.ERR},
      {State.X00O12X11O22X02O10X10, State.ERR, State.ERR},
      {State.X00O12X11O22X02O20X10, State.X00O12X11O22X02O21X10, State.ERR}});
    //O bot right
    delta.put(State.X00O22X02, new State[][] {
      {State.ERR, State.X00O22X02O01X20, State.ERR},
      {State.X00O22X02O10X01, State.X00O22X02O11X01, State.X00O22X02O12X01},
      {State.X00O22X02O20X01, State.X00O22X02O21X01, State.ERR}});
    delta.put(State.X00O22X02O01X20, new State[][] {
      {State.ERR, State.ERR, State.ERR},
      {State.X00O22X02O01X20O10X11, State.X00O22X02O01X20O11X10, State.X00O22X02O01X20O12X10},
      {State.ERR, State.X00O22X02O01X20O21X10, State.ERR}});
    //O bot middle
    delta.put(State.X00O21X11, new State[][] {
      {State.ERR, State.X00O21X11O01X22, State.X00O21X11O02X22},
      {State.X00O21X11O10X22, State.ERR, State.X00O21X11O12X22},
      {State.X00O21X11O20X22, State.ERR, State.X00O21X11O22X20}});
    delta.put(State.X00O21X11O22X20, new State[][] {
      {State.ERR, State.X00O21X11O22X20O01X10, State.X00O21X11O22X20O02X10},
      {State.X00O21X11O22X20O10X02, State.ERR, State.X00O21X11O22X20O12X10},
      {State.ERR, State.ERR, State.ERR}});
    //O bot left
    delta.put(State.X00O20X02, new State[][] {
      {State.ERR, State.X00O20X02O01X22, State.ERR},
      {State.X00O20X02O10X01, State.X00O20X02O11X01, State.X00O20X02O12X01},
      {State.ERR, State.X00O20X02O21X01, State.X00O20X02O22X01}});
    delta.put(State.X00O20X02O01X22, new State[][] {
      {State.ERR, State.ERR, State.ERR},
      {State.X00O20X02O01X22O10X12, State.X00O20X02O01X22O11X12, State.X00O20X02O01X22O12X11},
      {State.ERR, State.X00O20X02O01X22O21X12, State.ERR}});
    //O middle left
    delta.put(State.X00O10X11, new State[][] {
      {State.ERR, State.X00O10X11O01X22, State.X00O10X11O02X22},
      {State.ERR, State.ERR, State.X00O10X11O12X22},
      {State.X00O10X11O20X22, State.X00O10X11O10X22, State.X00O10X11O22X02}});
    delta.put(State.X00O10X11O22X02, new State[][] {
      {State.ERR, State.X00O10X11O22X02O01X20, State.ERR},
      {State.ERR, State.ERR, State.X00O10X11O22X02O12X01},
      {State.X00O10X11O22X02O20X01, State.X00O10X11O22X02O21X01, State.ERR}});
    //O middle middle
    delta.put(State.X00O11X22, new State[][] {
      {State.ERR, State.X00O11X22O01X21, State.X00O11X22O02X20},
      {State.X00O11X22O10X12, State.ERR, State.X00O11X22O12X10},
      {State.X00O11X22O20X02, State.X00O11X22O21X10, State.ERR}});
    delta.put(State.X00O11X22O01X21, new State[][] {
      {State.ERR, State.ERR, State.X00O11X22O01X21O02X20},
      {State.X00O11X22O01X21O10X20, State.ERR, State.X00O11X22O01X21O12X20},
      {State.X00O11X22O01X21O20X02, State.ERR, State.ERR}});
    delta.put(State.X00O11X22O01X21O20X02, new State[][] {
      {State.ERR, State.ERR, State.ERR},
      {State.X00O11X22O01X21O20X02O10X12, State.ERR, State.X00O11X22O01X21O20X02O12X10},
      {State.ERR, State.ERR, State.ERR}}); // X00O11X22O01X21O20X02O12X10
    delta.put(State.X00O11X22O02X20, new State[][] {
      {State.ERR, State.X00O11X22O02X20O01X10, State.ERR},
      {State.X00O11X22O02X20O10X21, State.ERR, State.X00O11X22O02X20O12X21},
      {State.ERR, State.X00O11X22O02X20O21X10, State.ERR}});
    delta.put(State.X00O11X22O12X10, new State[][] {
      {State.ERR, State.X00O11X22O12X10O01X20, State.X00O11X22O12X10O02X20},
      {State.ERR, State.ERR, State.ERR},
      {State.X00O11X22O12X10O20X02, State.X00O11X22O12X10O21X20, State.ERR}});
    delta.put(State.X00O11X22O12X10O20X02, new State[][] {
      {State.ERR, State.X00O11X22O12X10O20X02O01X21, State.ERR},
      {State.ERR, State.ERR, State.ERR},
      {State.ERR, State.X00O11X22O12X10O20X02O21X01, State.ERR}}); // X00O11X22O12X10O20X02O01X21
    delta.put(State.X00O11X22O21X10, new State[][] {
      {State.ERR, State.ERR, State.X00O11X22O21X10O02X20},
      {State.X00O11X22O21X10O10X02, State.ERR, State.X00O11X22O21X10O12X02},
      {State.X00O11X22O21X10O20X02, State.ERR, State.ERR}});
    delta.put(State.X00O11X22O21X10O02X20, new State[][] {
      {State.ERR, State.ERR, State.ERR},
      {State.X00O11X22O21X10O02X20O10X12, State.ERR, State.X00O11X22O21X10O02X20O12X10},
      {State.ERR, State.ERR, State.ERR}}); // X00O11X22O21X10O02X20O10X12
    delta.put(State.X00O11X22O20X02, new State[][] {
      {State.ERR, State.X00O11X22O20X02O01X12, State.ERR},
      {State.X00O11X22O20X02O10X01, State.ERR, State.X00O11X22O20X02O12X01},
      {State.ERR, State.X00O11X22O20X02O21X12, State.ERR}});
    delta.put(State.X00O11X22O10X12, new State[][] {
      {State.ERR, State.X00O11X22O10X12O01X02, State.X00O11X22O10X12O02X20},
      {State.ERR, State.ERR, State.ERR},
      {State.X00O11X22O10X12O10X02, State.X00O11X22O10X12O21X02, State.ERR}});
    delta.put(State.X00O11X22O10X12O02X20, new State[][] {
      {State.ERR, State.X00O11X22O10X12O02X20O01X21, State.ERR},
      {State.ERR, State.ERR, State.ERR},
      {State.ERR, State.X00O11X22O10X12O02X20O21X01, State.ERR}}); // X00O11X22O10X12O02X20O21X01
    accepting = new HashSet<>();
    accepting.add(State.ERR);
    draw = new HashSet<>();
    draw.add(State.X00O11X22O01X21O20X02O12X10);
    draw.add(State.X00O11X22O12X10O20X02O01X21);
    draw.add(State.X00O11X22O21X10O02X20O10X12);
    draw.add(State.X00O11X22O10X12O02X20O21X01);
    State [][] errorMatrix = new State[][]{{State.ERR, State.ERR, State.ERR}, {State.ERR, State.ERR, State.ERR}, {State.ERR, State.ERR, State.ERR}};
    for(State[][] stateM : delta.values()) {
      for(State[] stateA : stateM) {
        for(State value : stateA) {
          if(!delta.keySet().contains(value)) {
            delta.put(value, errorMatrix);
            accepting.add(value);
          }
        }
      }
    }
  }

  boolean draw() {
    return draw.contains(current);
  }
  
  void transition(int[] rowCol) {
    current = delta.get(current)[rowCol[0]][rowCol[1]];
  }
  
  boolean isDone() {
    return !accepting.contains(current);
  }
  
}
