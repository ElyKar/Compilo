/* Generated By:JavaCC: Do not edit this line. Yaka.java */
import java.io.IOException;

public class Yaka implements YakaConstants {
        public static TabIdent tabIdent = new TabIdent();
        public static Expression expression = new Expression();
        public static Declaration declaration = new Declaration();
        public static int line = 1;
        public static YVM yvm;

        static {
                try {
                        yvm = new YVM();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



  public static void main(String args[]) {
    Yaka analyseur;
    java.io.InputStream input;

    if (args.length==1) {
      System.out.println(args[args.length-1] + ": ");
      try {
        input = new java.io.FileInputStream(args[args.length-1]+".yaka");
      } catch (java.io.FileNotFoundException e) {
        System.out.println("Fichier introuvable.");
        return;
      }
    } else if (args.length==0) {
      System.out.println("Lecture sur l'entree standard...");
      input = System.in;
    } else {
      System.out.println("Usage: java Gram [fichier]");
      return;
    }
    try {
      analyseur = new Yaka(input);
      analyseur.analyse();
                System.out.println("analyse syntaxique r\u00e9ussie!");
          if(expression.typesEmpty())
                System.out.println("analyse s\u00e9mantique r\u00e9ussie!");
          else {
                System.out.println("analyse s\u00e9mantique rat\u00e9e!");
                System.out.println(expression.types);
         }
    } catch (ParseException e) {
      String msg = e.getMessage();
      msg = msg.substring(0,msg.indexOf("\u005cn"));
      System.out.println("Erreur de syntaxe : "+msg);
    }
  }

/**************************************/
/********debut de la grammaire ********/
/**************************************/
  static final public void analyse() throws ParseException {
    jj_consume_token(PROGRAM);
    jj_consume_token(ident);
    bloc();
    jj_consume_token(EPROGRAM);
  }

  static final public void bloc() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONST:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      declConst();
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      declVar();
    }
    suiteExpr();
  }

  static final public void declConst() throws ParseException {
    jj_consume_token(CONST);
    defConst();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 50:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      jj_consume_token(50);
      defConst();
    }
    jj_consume_token(51);
  }

  static final public void defConst() throws ParseException {
    jj_consume_token(ident);
            declaration.setNextAffectation(YakaTokenManager.identRead);
    jj_consume_token(EQU);
    valConst();
  }

  static final public void valConst() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case integer:
      jj_consume_token(integer);
             declaration.putConst(YakaTokenManager.intRead);
      break;
    case ident:
      jj_consume_token(ident);
                 declaration.putConst(YakaTokenManager.identRead);
      break;
    case TRUE:
      jj_consume_token(TRUE);
                 declaration.putConst(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
             declaration.putConst(false);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void declVar() throws ParseException {
    jj_consume_token(VAR);
    type();
    jj_consume_token(ident);
           declaration.setVar(YakaTokenManager.identRead);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 50:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      jj_consume_token(50);
      jj_consume_token(ident);
               declaration.setVar(YakaTokenManager.identRead);
    }
    jj_consume_token(51);
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
          declaration.setNextType(Type.INTEGER);
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
              declaration.setNextType(Type.BOOLEAN);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Syntaxe des instructions.
 */
  static final public void suiteExpr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case NOT:
    case MINUS:
    case integer:
    case ident:
    case 52:
      expression();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 51:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_5;
        }
        jj_consume_token(51);
                     expression.finLigne();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TRUE:
        case FALSE:
        case NOT:
        case MINUS:
        case integer:
        case ident:
        case 52:
          expression();
          break;
        default:
          jj_la1[7] = jj_gen;
          ;
        }
      }
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
  }

/*
 * Expression .
 */
  static final public void expression() throws ParseException {
    simpleExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQU:
    case DIF:
    case INF:
    case EINF:
    case SUP:
    case ESUP:
      opRel();
      simpleExpr();
   expression.operation();
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
  }

  static final public void simpleExpr() throws ParseException {
    term();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_6;
      }
      opAdd();
      term();
    expression.operation();
    }
  }

  static final public void term() throws ParseException {
    factor();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case DIV:
      case MUL:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_7;
      }
      opMul();
      factor();
   expression.operation();
    }
  }

  static final public void factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case integer:
    case ident:
    case 52:
      primary();
      break;
    case NOT:
    case MINUS:
      opNeg();
      primary();
     expression.operation();
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void primary() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case integer:
    case ident:
      value();
      break;
    case 52:
      jj_consume_token(52);
      expression();
      jj_consume_token(53);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void value() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case integer:
      jj_consume_token(integer);
             expression.pushOperand(YakaTokenManager.intRead);
      break;
    case ident:
      jj_consume_token(ident);
                 expression.pushOperand(YakaTokenManager.identRead);
      break;
    case TRUE:
      jj_consume_token(TRUE);
                 expression.pushOperand(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
                 expression.pushOperand(false);
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opRel() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQU:
      jj_consume_token(EQU);
                 expression.pushOperator(Constant.EQU);
      break;
    case DIF:
      jj_consume_token(DIF);
                 expression.pushOperator(Constant.DIF);
      break;
    case INF:
      jj_consume_token(INF);
                 expression.pushOperator(Constant.INF);
      break;
    case EINF:
      jj_consume_token(EINF);
                 expression.pushOperator(Constant.EINF);
      break;
    case SUP:
      jj_consume_token(SUP);
                 expression.pushOperator(Constant.SUP);
      break;
    case ESUP:
      jj_consume_token(ESUP);
                 expression.pushOperator(Constant.ESUP);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opAdd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
                  expression.pushOperator(Constant.PLUS);
      break;
    case MINUS:
      jj_consume_token(MINUS);
              expression.pushOperator(Constant.MINUS);
      break;
    case OR:
      jj_consume_token(OR);
                  expression.pushOperator(Constant.OR);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opMul() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MUL:
      jj_consume_token(MUL);
              expression.pushOperator(Constant.MUL);
      break;
    case DIV:
      jj_consume_token(DIV);
                  expression.pushOperator(Constant.DIV);
      break;
    case AND:
      jj_consume_token(AND);
                  expression.pushOperator(Constant.AND);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opNeg() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
      jj_consume_token(MINUS);
              expression.pushOperator(Constant.NEG);
      break;
    case NOT:
      jj_consume_token(NOT);
                  expression.pushOperator(Constant.NOT);
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public YakaTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80000,0x200,0x0,0x120000,0x0,0x8100,0x0,0x1120000,0x1120000,0x0,0x400000,0x800000,0x1120000,0x120000,0x120000,0x0,0x400000,0x800000,0x1000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x40000,0x14000,0x40000,0x0,0x80000,0x114010,0x114010,0x1f80,0x18,0x60,0x114010,0x114000,0x14000,0x1f80,0x18,0x60,0x10,};
   }

  /** Constructor with InputStream. */
  public Yaka(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Yaka(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new YakaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Yaka(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new YakaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Yaka(YakaTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(YakaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[54];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 54; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
