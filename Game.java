import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//+90 545 244 88 98

public  class Game extends window implements KeyListener , ActionListener {
    private boolean gameOver = false;
    private String winnerText = "";
    private int ballspx = 7;
    private  int ballspy =7 ;
    private int yourscore = 0;
    private int aiscore = 0;
    private int maxScore = 5;
    private JLabel yourscorelabel;
    private JLabel aiscorelabel;
    private JPanel scorepanel;
    private int x1 = 10;
    private int y1 = 150;
    private int x2 = 545;
    private int y2 = 150;
    private int balllx = 200;
    private int bally = 200;
    private  Timer timer;
    private  String direction = "STOP";
    public Game(){

        scorepanel = new JPanel();

        yourscorelabel = new JLabel("Your Score: 0");
        aiscorelabel = new JLabel("Ai Score: 0");
        yourscorelabel.setForeground(Color.WHITE);
        aiscorelabel.setForeground(Color.WHITE);
        scorepanel.setLayout(new FlowLayout());
        scorepanel.add(yourscorelabel);
        scorepanel.add(Box.createHorizontalStrut(100));
        scorepanel.add(aiscorelabel);
        scorepanel.setBackground(Color.BLACK);

        this.add(scorepanel,BorderLayout.NORTH);
        this.add(new Panel());
        this.setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane().setForeground(Color.BLACK);
        Music.backgroundmusic();
        timer = new Timer(5,this);
        timer.start();

    }

    class Panel extends JPanel {

        @Override
        protected void paintComponent(Graphics g){

            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.WHITE);
            g.drawLine(0,0,600,0);

            g.setColor(Color.CYAN);
            g.fillRect(x1,y1,30,100);
            g.setColor(Color.WHITE);
            g.drawRect(x1,y1,30,100);

            g.setColor(Color.RED);
            g.fillRect(x2,y2,30,100);
            g.setColor(Color.WHITE);
            g.drawRect(x2,y2,30,100);


            g.setColor(Color.WHITE);
            g.drawOval(balllx,bally,15,15);
            g.setColor(Color.WHITE);
            g.fillOval(balllx,bally,15,15);

            if (gameOver) {
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics metrics = g.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(winnerText)) / 2;
                int y = getHeight() / 2;
                g.drawString(winnerText, x, y);
            }

        }
    }
    class Pane extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(x2,y2,30,100);
            g.setColor(Color.BLACK);
            g.drawRect(x2,y2,30,100);
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W){


            if(y1==5 || y2==5){
                e.consume();
            }
            else{
                direction = "UP";

            }
        }
        else if (key == KeyEvent.VK_S){
            if(y1  == 500 || y2+10==500){
                e.consume();
            }
            else{
                direction = "DOWN";

            }
        }
        if (gameOver && key == KeyEvent.VK_R) {

            gameOver = false;
            winnerText = "";
            yourscore = 0;
            aiscore = 0;
            yourscorelabel.setText("Your Score: 0");
            aiscorelabel.setText("Ai Score: 0");
            x1 = 10;
            y1 = 150;
            x2 = 545;
            y2 = 150;
            resetBall();
            timer.start();
            repaint();
        }
        if (key == KeyEvent.VK_Q){
            System.exit(0);
        }




        repaint();
    }
    @Override
    public  void keyReleased(KeyEvent e){
        int key = e.getKeyCode();


    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    // ball 200 , 200 de
    @Override
    public void actionPerformed(ActionEvent a){
        if (direction.equals("UP") && y1 > 0){
            y1 -=4;
        }
        else if (direction.equals("DOWN") && y1 + 160 < 500){
            y1+=4;
        }

        if (bally < y2 + 50 && y2 > 0) {
            y2 -= 7;
        } else if (bally > y2 + 50 && y2 + 100 < getHeight()-70) {
            y2 += 7;
        }

        balllx +=ballspx;
        bally +=ballspy;

        //x1 10 + 30 = 40 y1 = 150 , 100 =250 x2 =545 + 30 = 575 y2 = 150 height = 100
        //ballx = 200 bally=200 15 , 15
        //ballspy = 3 ballspx = 3

        Rectangle ball = new Rectangle(balllx, bally, 15, 15);
        Rectangle player = new Rectangle(x1, y1, 30, 100);
        Rectangle ai = new Rectangle(x2, y2, 30, 100);

        if (ball.intersects(player)){
            balllx= x1 + 30;
            ballspx*=-1;
            Music.hitmusic();
        }
        if (ball.intersects(ai)){
            balllx = x2 -15;
            ballspx*=-1;
            Music.hitmusic();
        }

        if (bally <= 0 || bally >= getHeight() -70) {
            ballspy *= -1;
            Music.hitmusic();
        }



        if (balllx < 0) {
            aiscore++;
            aiscorelabel.setText("Ai Score: " + aiscore);
            if (aiscore >= maxScore) {
                winnerText = "AI Wins! You Lose. Press R to Restart";
                gameOver = true;
                timer.stop();
            }
            resetBall();
        } else if (balllx >= getWidth()) {
            yourscore++;
            yourscorelabel.setText("Your Score: " + yourscore);
            if (yourscore >= maxScore) {
                winnerText = "You Win! Press R to Restart";
                gameOver = true;
                timer.stop();
            }
            resetBall();
        }


        repaint();
    }
    public void resetBall(){
        bally = 200;
        balllx=200;
        ballspx=(Math.random()<0.5) ? 7 : -7;
        ballspy = (Math.random() < 0.5) ? 7: -7;


    }









}

