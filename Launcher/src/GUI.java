import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private final Color background = new Color(49, 51, 56);
    private final Color buttonColor = new Color(88, 101, 242);
    private final Color buttonColorHovered = new Color(71, 86, 227);

    public GUI() {
        JFrame frame = new JFrame("Volt Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setBackground(background);

        JLabel label = new JLabel("Volt Client");
        label.setFont(new Font("Helvetica", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Launch");
        button.setFont(new Font("Helvetica", Font.BOLD, 16));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColorHovered);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColor);
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.launch();
        System.out.println("Launching Volt Client...");
        System.exit(1);
    }
}
