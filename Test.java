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
      frame.setResizable(false);
      frame.setSize(400, 400);

      JPanel panel = new JPanel(new GridBagLayout());
      panel.setBackground(Color.DARK_GRAY);
      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JLabel universalLabel = new JLabel("Ingrese el tamaño del conjunto universal:");
      universalLabel.setForeground(Color.LIGHT_GRAY);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      panel.add(universalLabel, gbc);

      JTextField universalSizeField = new JTextField(10);
      universalSizeField.setMaximumSize(new Dimension(200, 30));
      universalSizeField.setBackground(Color.GRAY);
      universalSizeField.setForeground(Color.WHITE);
      gbc.gridy = 1;
      panel.add(universalSizeField, gbc);

      JButton universalButton = createRoundedButton("Crear Conjunto Universal");
      gbc.gridy = 2;
      panel.add(universalButton, gbc);

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
      subsetsLabel.setForeground(Color.LIGHT_GRAY);
      gbc.gridy = 3;
      panel.add(subsetsLabel, gbc);

      JTextField subsetsField = new JTextField(10);
      subsetsField.setMaximumSize(new Dimension(200, 30));
      subsetsField.setBackground(Color.GRAY);
      subsetsField.setForeground(Color.WHITE);
      gbc.gridy = 4;
      panel.add(subsetsField, gbc);

      JButton subsetsButton = createRoundedButton("Crear Subconjuntos");
      gbc.gridy = 5;
      panel.add(subsetsButton, gbc);

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

      JButton powerSetButton = createRoundedButton("Calcular Conjunto Potencia");
      gbc.gridy = 6;
      panel.add(powerSetButton, gbc);

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

  private static JButton createRoundedButton(String text) {
    JButton button = new JButton(text) {
      @Override
      protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g2);
        g2.dispose();
      }
    };
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setBackground(Color.GRAY);
    button.setForeground(Color.WHITE);
    return button;
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
