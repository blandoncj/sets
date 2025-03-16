import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    showMenu();
  }

  public static void showMenu() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Ingrese el tamaño del conjunto universal: ");
    int universalSize = sc.nextInt();

    Set<Integer> universalSet = new HashSet<>();

    System.out.println("Ingrese los elementos del conjunto universal:");
    for (int i = 0; i < universalSize; i++) {
      System.out.println("Ingrese el elemento " + (i + 1) + ": ");
      int element = sc.nextInt();
      universalSet.add(element);
    }

    System.out.println("¿Cuántos subconjuntos desea ingresar?: ");
    int subsetsNumber = sc.nextInt();

    List<Set<Integer>> subsets = new ArrayList<>();

    for (int i = 0; i < subsetsNumber; i++) {
      System.out.println("¿Cuántos elementos tiene el subconjunto " + (i + 1) + "?: ");
      int subsetSize = sc.nextInt();

      Set<Integer> subset = new HashSet<>();

      for (int j = 0; j < subsetSize; j++) {
        System.out.println("Ingrese el elemento " + (j + 1) + " del subconjunto " + (i + 1) + ": ");
        int element = sc.nextInt();
        subset.add(element);
      }

      // verificar si el subconjunto pertenece o no al conjunto universal
      if (universalSet.containsAll(subset)) {
        System.out.println("El subconjunto " + (i + 1) + " pertenece al conjunto universal.");
        // verificar si el subconjunto es propio o no
        if (subset.size() < universalSize) {
          System.out.println("El subconjunto " + (i + 1) + " es un subconjunto propio.");
        } else {
          System.out.println("El subconjunto " + (i + 1) + " no es un subconjunto propio.");
        }
      } else {
        System.out.println("El subconjunto " + (i + 1) + " no pertenece al conjunto universal.");
      }

      subsets.add(subset);
    }

    System.out.println("Los subconjuntos son: " + subsets);

    System.out.println("El conjunto universal es: " + universalSet);

    sc.close();
  }
}
