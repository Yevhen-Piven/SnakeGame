import javax.swing.*;

//(задания: пауза )
public class SnakeWindow extends JFrame {

    public SnakeWindow() {
        setTitle("Snake Monster");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(390, 445);
        setResizable(false);
        setLocationRelativeTo(null);
        add(new GameField());
        setVisible(true);
    }
    public static void main(String[] args) {
        SnakeWindow sw = new SnakeWindow();
    }
}
