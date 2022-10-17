package projet;




public class Main{
  public static void main(String[] arguments) {  

    PWindow window = new PWindow("test1", 400, 400);

    PContext context1 = new PContext(300, 400);
    PContext context2 = new PContext(200, 400);
    window.addContext(context1);
    window.addContext(context2);

    window.setFullScreenMode();

    window.setWindowMode();

    while(true);
  }  
}