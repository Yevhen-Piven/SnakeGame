import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;

    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private int score = 0;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private boolean pause = true;
    private boolean congra = false;
    private Image backgroundImage;
    private Image PauseBreak;
    private Image gameOver;
    private Image hup;
    private Image hdown;
    private Image hleft;
    private Image hright;
    private Image ramka2;
    private Image lenta3;
    private Image cong;


    public GameField() {
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 77 - i * DOT_SIZE;
            y[i] = 110;
        }

        timer = new Timer(250, this);
        timer.start();
        score = 0;
        createApple();
    }

    public void createApple() {
        appleX = 29 + new Random().nextInt(20) * DOT_SIZE;
        appleY = 62 + new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages() {
        ImageIcon gr = new ImageIcon("grass.jpg");
        backgroundImage = gr.getImage();
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon pb = new ImageIcon("ppp.jpg");
        PauseBreak = pb.getImage();
        ImageIcon go = new ImageIcon("gameOver.jpg");
        gameOver = go.getImage();
        ImageIcon hu = new ImageIcon("heeadUp.png");
        hup = hu.getImage();
        ImageIcon hd = new ImageIcon("heeadDown.png");
        hdown = hd.getImage();
        ImageIcon hl = new ImageIcon("heeadLeft.png");
        hleft = hl.getImage();
        ImageIcon hr = new ImageIcon("heeadRight.png");
        hright = hr.getImage();
        ImageIcon r2 = new ImageIcon("ramka2.png");
        ramka2 = r2.getImage();
        ImageIcon l3 = new ImageIcon("lenta3.png");
        lenta3 = l3.getImage();
        ImageIcon co = new ImageIcon("cong.jpg");
        cong = co.getImage();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lenta3, 0, 0, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 12));
        g.drawString("Score: " + score, 300, 25);

        if (inGame && pause && up && !congra) {
            g.drawImage(backgroundImage, 29, 62, this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
            g.drawImage(hup, x[0], y[0], this);
        } else if (inGame && pause && down && !congra) {
            g.drawImage(backgroundImage, 29, 62, this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
            g.drawImage(hdown, x[0], y[0], this);
        } else if (inGame && pause && left && !congra) {
            g.drawImage(backgroundImage, 29, 62, this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
            g.drawImage(hleft, x[0], y[0], this);
        } else if (inGame && pause && right && !congra) {
            g.drawImage(backgroundImage, 29, 62, this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
            g.drawImage(hright, x[0], y[0], this);
        } else if (inGame && !pause && !congra) {
            g.drawImage(PauseBreak, 29, 62, this);
        } else if (inGame && congra) {
            g.drawImage(cong, 29, 62, this);
        } else {
            g.drawImage(gameOver, 29, 62, this);
        }

        g.drawImage(ramka2, 0, 34, this);
    }


    private void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }

    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;

            timer.stop();
            timer = new Timer(250 - dots * 2 / 5, this);
            timer.start();

            score = dots - 3;

            createApple();
        }
        if (dots == 396) {
            congra = true;
        }
        for (int i = 1; i < dots; i++) {
            if (x[i - 1] == appleX && y[i - 1] == appleY) {
                createApple();
            }

        }
    }


    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i - 1] && y[0] == y[i - 1]) {
                inGame = false;
                timer.stop();
                SnakeMonster sm = new SnakeMonster();
                sm.setVisible(true);
                sm.pack();
                JLabel Label = new JLabel("Начать заново?");
                JButton da = new JButton("Да");
                JButton net = new JButton("Нет");
                JPanel Panel = new JPanel(new FlowLayout());
                Panel.add(Label);
                Panel.add(da);
                Panel.add(net);
                sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sm.setLocationRelativeTo(null);
                sm.add(Panel);
                sm.setSize(250, 75);
                sm.setResizable(false);
                da.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        inGame = true;

                        loadImages();

                        initGame();
                        addKeyListener(new FieldKeyListener());
                        setFocusable(true);
                        sm.setVisible(false);





                    }
                });
                net.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        inGame = false;
                        sm.setVisible(false);


                    }
                });

            }
        }

//устанавливаем проходимые стенки
        if (x[0] == SIZE + 13 && right) {
            x[0] = 13;
            //    inGame = false; (устанавливает непроходимые стенки стенки)
        }
        if (x[0] == 29 && left) {
            x[0] = SIZE + 29;
        }
        if (y[0] == SIZE + 46 && down) {
            y[0] = 46;
        }
        if (y[0] == 62 && up) {
            y[0] = SIZE + 62;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && pause & !congra) {
            checkApple();
            checkCollisions();
            move();
        } else if (inGame && !pause & congra) {
            checkApple();
            checkCollisions();

        }

        repaint();
    }


    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_P && pause&&inGame ) {
                pause = false;
            } else if (key == KeyEvent.VK_P && !pause&&inGame) {
                pause = true;
            }
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;
            }

        }
    }
}


