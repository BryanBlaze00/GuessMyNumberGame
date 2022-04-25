import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
/**
 * @author Bryan Blaze
 * @Game Guess My Number Challenge
 */
public class GuessMyNumberGui implements ActionListener, KeyListener {
    JFrame frame = new JFrame("Guess My Number Game - By Blaze");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Welcome to the game.");
    JButton button = new JButton("Start Game");
    JTextField input = new JTextField();
    JLabel image = new JLabel();
    ImageIcon fire = new ImageIcon("./images/fire.png");
    ImageIcon ice = new ImageIcon("./images/ice.png");
    ImageIcon win = new ImageIcon("./images/win.png");
    ImageIcon question = new ImageIcon("./images/guess.png");
    ImageIcon logo = new ImageIcon("./images/logo.png");
    Image icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");
    String toNum;
    int number = 0, guess = 0, lastGuess = 0, guessCount = 1, gDiff = 0, lgDiff = 0;

    // Run Game
    GuessMyNumberGui( ) {
        buildGui();
    }
    public static void main(String[] args) { new GuessMyNumberGui(); }

    public void buildGui( ) {
        // Create Frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.setBounds(500,300 ,800 , 400);

        // Create panel add to frame
        frame.getContentPane().add(panel);
        panel.setPreferredSize(new Dimension(800 , 400));
        panel.setVisible(true);
        panel.setBackground(Color.gray);

        // Create label add to panel
        label.setPreferredSize(new Dimension(700 , 50));
        label.setForeground(Color.black);
        panel.add(label);
        label.setVisible(true);

        // Add Button to panel
        button.setPreferredSize(new Dimension(100 , 50));
        button.addActionListener(this);
        panel.add(button);
        button.setVisible(true);

        // Create JTextArea add to panel
        input.setPreferredSize(new Dimension(100 , 25));
        input.addKeyListener(this);
        panel.add(input);
        input.setVisible(true);

        // Add image panel
        panel.add(image);
        image.setPreferredSize(new Dimension(250 , 250));
        image.setVisible(true);
        image.setIcon(logo);

        // Makes GUI visible
        frame.pack();
        frame.setVisible(true);
        input.requestFocus(); // Selects JTextArea
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == input) button.doClick();

        // Starts game
        if (button.getText().equalsIgnoreCase("Start Game") || button.getText().equalsIgnoreCase("Play again?")) {
            button.setText("Guess");
            label.setText("Guess a number between 1-1000! Enter a number and click the guess button.");
            label.setForeground(Color.black);
            panel.setBackground(Color.gray);
            image.setIcon(question);
            Random random = new Random();
            number = random.nextInt(1000);
        }
        // Get the guess
        if (!input.getText().isEmpty()) {
            toNum = input.getText();
            guess = Integer.parseInt(toNum);
            guessCount++;
            Calculations(guess , lastGuess , number);
        }
        // Checks guesses
        if (guessCount == 2 || guess != 0 && guess != number && gDiff < lgDiff || (gDiff + lgDiff) == 0 && guess > number) {
            if (guess > number) {
                label.setText(guess + " is TOO HIGH! But you're getting WARMER! Enter guess number: " + guessCount);
                panel.setBackground(Color.red);
                label.setForeground(Color.black);
                image.setIcon(fire);
                lastGuess = guess;
            }
            else if (guess != 0) {
                label.setText(guess + " is TOO LOW! But you're getting WARMER! Enter guess number: " + guessCount);
                panel.setBackground(Color.red);
                label.setForeground(Color.black);
                image.setIcon(fire);
                lastGuess = guess;
            }
        }
        else if (guess < number && guess != 0) {
            label.setText(guess + " is TOO LOW! And you're getting COLDER! Enter guess number: " + guessCount);
            label.setForeground(Color.white);
            panel.setBackground(Color.blue);
            image.setIcon(ice);
            lastGuess = guess;
        }
        else if (guess != 0 && guess != number) {
            label.setText(guess + " is TOO HIGH! And you're getting COLDER! Enter guess number: " + guessCount);
            label.setForeground(Color.white);
            panel.setBackground(Color.blue);
            image.setIcon(ice);
            lastGuess = guess;
        }
        else if (guess == number) {
            label.setText(guess + " is the number! You WON on " + guessCount + " guesses!!!");
            panel.setBackground(Color.green);
            label.setForeground(Color.black);
            button.setText("Play again?");
            image.setIcon(win);
            input.setText(null);
            guess = lastGuess = guessCount = gDiff = lgDiff = 0;
            guessCount++;
        }
        input.selectAll();
    }
    private void Calculations(int guess2 , int lastGuess2 , int number2) {
        if (guess2 > number2) gDiff = guess2 - number2;
        else gDiff = number2 - guess2;
        if (lastGuess2 > number2) lgDiff = lastGuess2 - number2;
        else lgDiff = number2 - lastGuess2;
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            button.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }
}
