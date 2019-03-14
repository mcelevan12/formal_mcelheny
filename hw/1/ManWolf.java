import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class ManWolf {

  static final String LANGUAGE = "wgcn";
  // Wolf Goat Cabbage and Nothing
  static final State STARTINGSTATE = State.MWGC_;
  static final Set<State> FINISHINGSTATES
      = Collections.unmodifiableSet(Set.of(State._MWGC));
  State currentState;
  State[][] delta;

  ManWolf() {
    delta = defineDelta();
    currentState = STARTINGSTATE;
  }

  void transition(char transition) {
    currentState =  delta[stateIdx(currentState)][languageIdx(transition)];
  }

  boolean finished() {
    return FINISHINGSTATES.contains(currentState);
  }

  static int stateIdx(State state) {
    return state.ordinal();
  }

  static int languageIdx(char transition) {
    if(LANGUAGE.indexOf(transition) == -1) {
      throw new IllegalArgumentException("ERROR: only valid characters are \"" 
          + LANGUAGE + "\" '" + transition + "' not expected.");
    }
    return LANGUAGE.indexOf(transition);
  }

  static State[][] defineDelta() {
    State[][] delta = new State[State.values().length][LANGUAGE.length()];
    for(int stateIdx = 0; stateIdx < delta.length; stateIdx++) {
      for(int charIdx = 0; charIdx < delta[stateIdx].length; charIdx++) {
        delta[stateIdx][charIdx] = State.ERROR;
      }
    }

    delta[stateIdx(State.MWGC_)][languageIdx('g')] = State.WC_MG;

    delta[stateIdx(State.WC_MG)][languageIdx('n')] = State.MWC_G;
    delta[stateIdx(State.WC_MG)][languageIdx('g')] = State.MWGC_;

    delta[stateIdx(State.MWC_G)][languageIdx('n')] = State.WC_MG;
    delta[stateIdx(State.MWC_G)][languageIdx('w')] = State.C_MWG;
    delta[stateIdx(State.MWC_G)][languageIdx('c')] = State.W_MGC;

    delta[stateIdx(State.W_MGC)][languageIdx('c')] = State.MWC_G;
    delta[stateIdx(State.W_MGC)][languageIdx('g')] = State.MGW_C;

    delta[stateIdx(State.C_MWG)][languageIdx('w')] = State.MWC_G;
    delta[stateIdx(State.C_MWG)][languageIdx('g')] = State.MGC_W;

    delta[stateIdx(State.MGC_W)][languageIdx('g')] = State.C_MWG;
    delta[stateIdx(State.MGC_W)][languageIdx('c')] = State.C_MWG;

    delta[stateIdx(State.MGW_C)][languageIdx('w')] = State.G_MWC;
    delta[stateIdx(State.MGW_C)][languageIdx('g')] = State.W_MGC;

    delta[stateIdx(State.G_MWC)][languageIdx('n')] = State.MG_WC;
    delta[stateIdx(State.G_MWC)][languageIdx('c')] = State.MGC_W;
    delta[stateIdx(State.G_MWC)][languageIdx('w')] = State.MGW_C;

    delta[stateIdx(State.MG_WC)][languageIdx('n')] = State.G_MWC;
    delta[stateIdx(State.MG_WC)][languageIdx('g')] = State._MWGC;

    return delta;
  }

  public static void main(String[] args) {
    ManWolf mw = new ManWolf();
    for(State s : State.values()) {
      System.out.println(s + " " + Arrays.toString(mw.delta[s.ordinal()]));
    }
  }
}

enum State {
  ERROR,
  MWGC_,
  WC_MG,
  MWC_G,
  C_MWG,
  W_MGC,
  MGC_W,
  MGW_C,
  G_MWC,
  MG_WC,
  _MWGC,
}
