public class Player {
  Matcher inputMatcher; // Alphabet
  EnumMap<State, State[][]> delta
  delta.put(State.V, new State[][] {
      {V0,  V1,  V2},
      {V3,  V4,  V5},
      {V6,  V7,  V8},}) //V4 outputs 00, all others are output at 11
  delta.put(State.V0, new State[][] {
      {ERR, V00, V01},
      {V02, ERR, V03},
      {V04, V05, V06},}) //V00:02, V01:01, V02:20, V03:22, V04:01, V05:22, V06:21
  delta.put(State.V00, new State[][] {
      {ERR, ERR, ERR},
      {V000, ERR, V001},    // Identical accepting states (v000 v001
      {V002, V003, V004},}) //V000:20WIN, V001:20WIN, V02:20, V03:22, V04:01, V05:22, V06:21

      

  public State delta(State state,/* char player,*/ int row, int col) {
    return delta.get(state)[row, col];
  }
    
