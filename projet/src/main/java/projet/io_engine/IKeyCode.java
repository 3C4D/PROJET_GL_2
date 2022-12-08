package projet.io_engine;

import java.awt.event.KeyEvent;

/**
  * Interface regroupant différents codes de touches de clavier
*/
public interface IKeyCode{
  // Chiffres
  public final static int KEY_0 = KeyEvent.VK_0;
  public final static int KEY_1 = KeyEvent.VK_1;
  public final static int KEY_2 = KeyEvent.VK_2;
  public final static int KEY_3 = KeyEvent.VK_3;
  public final static int KEY_4 = KeyEvent.VK_4;
  public final static int KEY_5 = KeyEvent.VK_5;
  public final static int KEY_6 = KeyEvent.VK_6;
  public final static int KEY_7 = KeyEvent.VK_7;
  public final static int KEY_8 = KeyEvent.VK_8;
  public final static int KEY_9 = KeyEvent.VK_9;

  // Chiffres pavé numérique
  public final static int KEY_0_NP = KeyEvent.VK_NUMPAD0;
  public final static int KEY_1_NP = KeyEvent.VK_NUMPAD1;
  public final static int KEY_2_NP = KeyEvent.VK_NUMPAD2;
  public final static int KEY_3_NP = KeyEvent.VK_NUMPAD3;
  public final static int KEY_4_NP = KeyEvent.VK_NUMPAD4;
  public final static int KEY_5_NP = KeyEvent.VK_NUMPAD5;
  public final static int KEY_6_NP = KeyEvent.VK_NUMPAD6;
  public final static int KEY_7_NP = KeyEvent.VK_NUMPAD7;
  public final static int KEY_8_NP = KeyEvent.VK_NUMPAD8;
  public final static int KEY_9_NP = KeyEvent.VK_NUMPAD9;

  // Fonctions
  public final static int KEY_F1 = KeyEvent.VK_F1;
  public final static int KEY_F2 = KeyEvent.VK_F2;
  public final static int KEY_F3 = KeyEvent.VK_F3;
  public final static int KEY_F4 = KeyEvent.VK_F4;
  public final static int KEY_F5 = KeyEvent.VK_F5;
  public final static int KEY_F6 = KeyEvent.VK_F6;
  public final static int KEY_F7 = KeyEvent.VK_F7;
  public final static int KEY_F8 = KeyEvent.VK_F8;
  public final static int KEY_F9 = KeyEvent.VK_F9;
  public final static int KEY_F10 = KeyEvent.VK_F10;
  public final static int KEY_F11 = KeyEvent.VK_F11;
  public final static int KEY_F12 = KeyEvent.VK_F12;

  // Lettres A-Z
  public final static int KEY_A = KeyEvent.VK_A;
  public final static int KEY_B = KeyEvent.VK_B;
  public final static int KEY_C = KeyEvent.VK_C;
  public final static int KEY_D = KeyEvent.VK_D;
  public final static int KEY_E = KeyEvent.VK_E;
  public final static int KEY_F = KeyEvent.VK_F;
  public final static int KEY_G = KeyEvent.VK_G;
  public final static int KEY_H = KeyEvent.VK_H;
  public final static int KEY_I = KeyEvent.VK_I;
  public final static int KEY_J = KeyEvent.VK_J;
  public final static int KEY_K = KeyEvent.VK_K;
  public final static int KEY_L = KeyEvent.VK_L;
  public final static int KEY_M = KeyEvent.VK_M;
  public final static int KEY_N = KeyEvent.VK_N;
  public final static int KEY_O = KeyEvent.VK_O;
  public final static int KEY_P = KeyEvent.VK_P;
  public final static int KEY_Q = KeyEvent.VK_Q;
  public final static int KEY_R = KeyEvent.VK_R;
  public final static int KEY_S = KeyEvent.VK_S;
  public final static int KEY_T = KeyEvent.VK_T;
  public final static int KEY_U = KeyEvent.VK_U;
  public final static int KEY_V = KeyEvent.VK_V;
  public final static int KEY_W = KeyEvent.VK_W;
  public final static int KEY_X = KeyEvent.VK_X;
  public final static int KEY_Y = KeyEvent.VK_Y;
  public final static int KEY_Z = KeyEvent.VK_Z;

  // Flèches
  public final static int KEY_DOWN = KeyEvent.VK_DOWN;
  public final static int KEY_LEFT = KeyEvent.VK_LEFT;
  public final static int KEY_RIGHT = KeyEvent.VK_RIGHT;
  public final static int KEY_UP = KeyEvent.VK_UP;

  // Flèches numpad
  public final static int KEY_DOWN_NP = KeyEvent.VK_KP_DOWN;
  public final static int KEY_LEFT_NP = KeyEvent.VK_KP_LEFT;
  public final static int KEY_RIGHT_NP = KeyEvent.VK_KP_RIGHT;
  public final static int KEY_UP_NP = KeyEvent.VK_KP_UP;

  public final static int KEY_ALT = KeyEvent.VK_ALT;
  public final static int KEY_ALT_GRAPH = KeyEvent.VK_ALT_GRAPH;
  public final static int KEY_AMPERSAND = KeyEvent.VK_AMPERSAND;
  public final static int KEY_ASTERISK = KeyEvent.VK_ASTERISK;
  public final static int KEY_BACK_QUOTE = KeyEvent.VK_BACK_QUOTE;
  public final static int KEY_BACK_SLASH = KeyEvent.VK_BACK_SLASH;
  public final static int KEY_BACK_SPACE = KeyEvent.VK_BACK_SPACE;
  public final static int KEY_BRACELEFT = KeyEvent.VK_BRACELEFT;
  public final static int KEY_BRACERIGHT = KeyEvent.VK_BRACERIGHT;
  public final static int KEY_CANCEL = KeyEvent.VK_CANCEL;
  public final static int KEY_CAPS_LOCK = KeyEvent.VK_CAPS_LOCK;
  public final static int KEY_CIRCUMFLEX = KeyEvent.VK_CIRCUMFLEX;
  public final static int KEY_CLOSE_BRACKET = KeyEvent.VK_CLOSE_BRACKET;
  public final static int KEY_COLON = KeyEvent.VK_COLON;
  public final static int KEY_COMMA = KeyEvent.VK_COMMA;
  public final static int KEY_CONTROL = KeyEvent.VK_CONTROL;
  public final static int KEY_DELETE = KeyEvent.VK_DELETE;
  public final static int KEY_DIVIDE = KeyEvent.VK_DIVIDE;
  public final static int KEY_DOLLAR = KeyEvent.VK_DOLLAR;
  public final static int KEY_ENTER = KeyEvent.VK_ENTER;
  public final static int KEY_EQUALS = KeyEvent.VK_EQUALS;
  public final static int KEY_ESCAPE = KeyEvent.VK_ESCAPE;
  public final static int KEY_EURO_SIGN = KeyEvent.VK_EURO_SIGN;
  public final static int KEY_EXCLAMATION_MARK = KeyEvent.VK_EXCLAMATION_MARK;
  public final static int KEY_GREATER = KeyEvent.VK_GREATER;
  public final static int KEY_HELP = KeyEvent.VK_HELP;
  public final static int KEY_HOME = KeyEvent.VK_HOME;
  public final static int KEY_LEFT_PARENTHESIS = KeyEvent.VK_LEFT_PARENTHESIS;
  public final static int KEY_LESS = KeyEvent.VK_LESS;
  public final static int KEY_META = KeyEvent.VK_META;
  public final static int KEY_MINUS = KeyEvent.VK_MINUS;
  public final static int KEY_MODECHANGE = KeyEvent.VK_MODECHANGE;
  public final static int KEY_MULTIPLY = KeyEvent.VK_MULTIPLY;
  public final static int KEY_NONCONVERT = KeyEvent.VK_NONCONVERT;
  public final static int KEY_NUM_LOCK = KeyEvent.VK_NUM_LOCK;
  public final static int KEY_NUMBER_SIGN = KeyEvent.VK_NUMBER_SIGN;
  public final static int KEY_OPEN_BRACKET = KeyEvent.VK_OPEN_BRACKET;
  public final static int KEY_PAGE_DOWN = KeyEvent.VK_PAGE_DOWN;
  public final static int KEY_PAGE_UP = KeyEvent.VK_PAGE_UP;
  public final static int KEY_PLUS = KeyEvent.VK_PLUS;
  public final static int KEY_QUOTE = KeyEvent.VK_QUOTE;
  public final static int KEY_QUOTEDBL = KeyEvent.VK_QUOTEDBL;
  public final static int KEY_RIGHT_PARENTHESIS = KeyEvent.VK_RIGHT_PARENTHESIS;
  public final static int KEY_SEMICOLON = KeyEvent.VK_SEMICOLON;
  public final static int KEY_SHIFT = KeyEvent.VK_SHIFT;
  public final static int KEY_SLASH = KeyEvent.VK_SLASH;
  public final static int KEY_SPACE = KeyEvent.VK_SPACE;
  public final static int KEY_STOP = KeyEvent.VK_STOP;
  public final static int KEY_SUBTRACT = KeyEvent.VK_SUBTRACT;
  public final static int KEY_TAB = KeyEvent.VK_TAB;
  public final static int KEY_UNDERSCORE = KeyEvent.VK_UNDERSCORE;
}
