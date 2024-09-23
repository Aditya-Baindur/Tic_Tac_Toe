import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTac extends JFrame implements ActionListener {
    TicTacEvent tictac = new TicTacEvent(this);
    JPanel row1 = new JPanel();
    JButton[][] boxes = new JButton[4][4];
    JOptionPane win = new JOptionPane("Winner");
    ImageIcon back = new ImageIcon("cardback.jpg");
    private JButton exitButton;

    public TicTac() {
        super("Tic Tac Toe");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color of the panel to pale blue
        row1.setBackground(new Color(191, 216, 255)); // RGB values for pale blue

        GridLayout layout1 = new GridLayout(4, 4, 10, 10);
        row1.setLayout(layout1);
        
        // Create the buttons and add them to the panel
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                String name = Integer.toString(x * 4 + y + 1);
                boxes[x][y] = new JButton(name);
                boxes[x][y].setIcon(back);
                row1.add(boxes[x][y]);
            }
        }
        add(row1, BorderLayout.CENTER);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        add(exitButton, BorderLayout.NORTH);

        // Add action listeners to the buttons
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                boxes[x][y].addActionListener(tictac);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0); // Exit the program
        }
    }

    public static void main(String[] arguments) {
        TicTac frame = new TicTac();
    }
}
