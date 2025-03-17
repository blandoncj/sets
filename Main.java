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

    System.out.print("Ingrese el tamaño del conjunto universal: ");
    int universalSize = sc.nextInt();

    Set<Object> universalSet = new HashSet<>();

    for (int i = 0; i < universalSize; i++) {
      System.out.print("Ingrese el elemento " + (i + 1) + ": ");
      universalSet.add(sc.next());
    }

    System.out.print("¿Cuántos subconjuntos desea ingresar?: ");
    int subsetsNumber = sc.nextInt();

    List<Set<Object>> subsets = new ArrayList<>();

    for (int i = 0; i < subsetsNumber; i++) {
      System.out.print("¿Cuántos elementos tiene el subconjunto " + (i + 1) + "?: ");
      int subsetSize = sc.nextInt();

      Set<Object> subset = new HashSet<>();
      for (int j = 0; j < subsetSize; j++) {
        System.out.print("Ingrese el elemento " + (j + 1) + " del subconjunto " + (i + 1) + ": ");
        subset.add(sc.next());
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
        subsets.add(subset);
      } else {
        System.out.println("El subconjunto " + (i + 1) + " no pertenece al conjunto universal.");
      }
    }

    System.out.println("El conjunto universal es: " + universalSet);
    System.out.println("Los subconjuntos son: " + subsets);

    // calcular y mostrar el conjunto potencia
    List<Set<Object>> powerSet = generatePowerSet(universalSet);
    System.out.println("El conjunto potencia es:");
    for (int i = 0; i < powerSet.size(); i++) {
      System.out.println((i + 1) + ": " + powerSet.get(i));
    }
    System.out.println("Número total de elementos en el conjunto potencia: " +
        powerSet.size());

    sc.close();
  }

  // método para generar el conjunto potencia
  public static <T> List<Set<T>> generatePowerSet(Set<T> originalSet) {
    List<Set<T>> powerSet = new ArrayList<>();
    // primero agregamos el conjunto vacío
    powerSet.add(new HashSet<>());

    // para cada elemento en el conjunto original
    for (T item : originalSet) {
      // creamos nuevos subconjuntos añadiendo el elemento actual a los conjuntos
      // existentes
      List<Set<T>> newSubsets = new ArrayList<>();

      for (Set<T> subset : powerSet) {
        Set<T> newSubset = new HashSet<>(subset);
        newSubset.add(item);
        newSubsets.add(newSubset);
      }

      // añadimos todos los nuevos subconjuntos al power set
      powerSet.addAll(newSubsets);
    }

    return powerSet;
  }
}
