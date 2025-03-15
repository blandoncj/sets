import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    showMenu();
  }

  public static void showMenu() {
    Scanner sc = new Scanner(System.in);
    String[] set;

    while (true) {
      System.out.println("Ingrese el tamaño del conjunto universal o 0 para salir: ");
      int universalSize = sc.nextInt();
      sc.nextLine();

      if (universalSize == 0) {
        break;
      }

      set = new String[universalSize];

      for (int i = 0; i < universalSize; i++) {
        System.out.println("Ingrese el elemento " + (i + 1) + " del conjunto universal: ");
        String element = sc.nextLine();
        set[i] = element;
      }

      System.out.println("¿existen subconjuntos? (s/n)");
      String answer = sc.nextLine();

      if (answer == "n") {
        break;
      }

      System.out.println(Arrays.toString(set));
    }
    sc.close();
  }
}
