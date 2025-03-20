import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Test {
  private static Set<Object> universalSet = new HashSet<>();
  private static List<Set<Object>> subsets = new ArrayList<>();

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Conjunto Universal y Subconjuntos");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 300);

      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

      JLabel universalLabel = new JLabel("Ingrese el tamaño del conjunto universal:");
      JTextField universalSizeField = new JTextField();
      JButton universalButton = new JButton("Crear Conjunto Universal");

      panel.add(universalLabel);
      panel.add(universalSizeField);
      panel.add(universalButton);

      universalButton.addActionListener(e -> {
        int universalSize = Integer.parseInt(universalSizeField.getText());
        universalSet.clear();
        for (int i = 0; i < universalSize; i++) {
          String element = JOptionPane.showInputDialog(frame, "Ingrese el elemento " + (i + 1) + ":");
          universalSet.add(element);
        }
        JOptionPane.showMessageDialog(frame, "Conjunto universal creado: " + universalSet);
      });

      JLabel subsetsLabel = new JLabel("¿Cuántos subconjuntos desea ingresar?");
      JTextField subsetsField = new JTextField();
      JButton subsetsButton = new JButton("Crear Subconjuntos");

      panel.add(subsetsLabel);
      panel.add(subsetsField);
      panel.add(subsetsButton);

      subsetsButton.addActionListener(e -> {
        int subsetsNumber = Integer.parseInt(subsetsField.getText());
        subsets.clear();
        for (int i = 0; i < subsetsNumber; i++) {
          int subsetSize = Integer
              .parseInt(JOptionPane.showInputDialog(frame, "¿Cuántos elementos tiene el subconjunto " + (i + 1) + "?"));
          Set<Object> subset = new HashSet<>();
          for (int j = 0; j < subsetSize; j++) {
            String element = JOptionPane.showInputDialog(frame,
                "Ingrese el elemento " + (j + 1) + " del subconjunto " + (i + 1) + ":");
            subset.add(element);
          }
          if (universalSet.containsAll(subset)) {
            subsets.add(subset);
            JOptionPane.showMessageDialog(frame, "El subconjunto " + (i + 1) + " pertenece al conjunto universal.");
          } else {
            JOptionPane.showMessageDialog(frame, "El subconjunto " + (i + 1) + " no pertenece al conjunto universal.");
          }
        }
      });

      JButton powerSetButton = new JButton("Calcular Conjunto Potencia");
      panel.add(powerSetButton);

      powerSetButton.addActionListener(e -> {
        List<Set<Object>> powerSet = generatePowerSet(universalSet);
        StringBuilder powerSetString = new StringBuilder("Conjunto potencia:\n");
        for (int i = 0; i < powerSet.size(); i++) {
          powerSetString.append((i + 1)).append(": ").append(powerSet.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(frame, powerSetString.toString());
      });

      frame.add(panel);
      frame.setVisible(true);
    });
  }

  public static <T> List<Set<T>> generatePowerSet(Set<T> originalSet) {
    List<Set<T>> powerSet = new ArrayList<>();
    powerSet.add(new HashSet<>()); // add empty set

    for (T item : originalSet) {
      List<Set<T>> newSubsets = new ArrayList<>();
      for (Set<T> subset : powerSet) {
        Set<T> newSubset = new HashSet<>(subset);
        newSubset.add(item);
        newSubsets.add(newSubset);
      }
      powerSet.addAll(newSubsets);
    }

    return powerSet;
  }
}
