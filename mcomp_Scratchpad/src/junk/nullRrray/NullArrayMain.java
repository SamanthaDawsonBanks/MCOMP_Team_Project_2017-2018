package junk.nullRrray;

public class NullArrayMain {

  public NullArrayMain() {
    // TODO Auto-generated constructor stub
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Bar[][] foo = new Bar[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (foo[i][j] == null) {
          System.out.print(" " + i + " " + j + " " + "is null");
        } else {
          System.out.print(" " + i + " " + j + " " + foo[i][j].getClass().toString());
        }
      }
      System.out.println();
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        foo[i][j] = new Bar();
        System.out.print(" " + i + " " + j + " " + foo[i][j].getClass().toString());

      }
      System.out.println();
    }
  }
}
