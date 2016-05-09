package game;

import MenuFramework.ControlledScreen;
import MenuFramework.ScreensController;
import MenuFramework.ScreensFramework;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Family
 */
public class DrawBoard extends JPanel {

    private BoardLoad board;
    private int boardSize;
    private char[][] charBoard;
    private int boxSize = 50;
    private Dimension screenSize;
    private Point startClick;
    private Point endClick;
    private Point startPoint;
    public JFrame owner;
    private Graphics thisBoard;
    private JButton back;
    private JButton restart;
    private ScreensController ScreenParent;
    private JButton returnMain;
    private JButton exit;
    private JButton nextBoard;
    private int buttonWidth = 200;
    private int buttonHeight = 75;

    public DrawBoard(BoardLoad b) {
        board = b;
        startClick = new Point();
        endClick = new Point();
        boardSize = board.getSize();
        charBoard = board.getBoard();
        back = new JButton("Back");
        restart = new JButton("Reset");
        returnMain = new JButton("New Game");
        exit = new JButton("Exit");
        nextBoard = new JButton("Next Board");
        this.add(restart);
        this.add(back);
        this.add(exit);
        this.add(nextBoard);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startClick.x = e.getX();
                startClick.y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (board.isFound()) {
                    return;
                }
                endClick.x = e.getX();
                endClick.y = e.getY();

                getDirections();
                owner.repaint();

            }
        }
        );

        this.back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.isFound()) {

                    return;
                }

                board.getLastMove();
                owner.repaint();
            }
        });

        this.restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (board.isFound()) {
                        return;
                    }
                    board.reset();
                    owner.repaint();
                } catch (Exception ex) {
                    System.out.println("ERROR");
                }
            }
        });

        this.returnMain.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] folders = {"obligatorisk_synlig", "konkurrence_sjove", "konkurrence_random"};
                Spil.main(folders);
                owner.dispose();
            }
        });

        this.exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                owner.dispose();
            }
        });

        this.nextBoard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] folders = {"obligatorisk_synlig", "konkurrence_sjove", "konkurrence_random"};
                Spil.main(folders);
                owner.dispose();
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        screenSize = this.getSize();
        getBoxsize();
        thisBoard = g;
        super.paint(g);
        g.setColor(Color.black);
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                switch (charBoard[x][y]) {
                    case '#':
                        g.setColor(Color.black);
                        g.fillRect((startPoint.x + (y * boxSize)), (startPoint.y + (x * boxSize)), boxSize, boxSize);
                        break;
                    case ' ':
                        g.setColor(Color.white);
                        g.fillRect((startPoint.x + (y * boxSize)), (startPoint.y + (x * boxSize)), boxSize, boxSize);
                        break;
                    case 'G':
                        g.setColor(Color.green);
                        g.fillRect((startPoint.x + (y * boxSize)), (startPoint.y + (x * boxSize)), boxSize, boxSize);
                        break;
                    case '0':
                        g.setColor(Color.green);
                        g.fillOval((startPoint.x + (y * boxSize)), (startPoint.y + (x * boxSize)), boxSize, boxSize);
                        break;
                    default:
                        break;
                }
                g.setColor(Color.black);
                g.drawRect((startPoint.x + (y * boxSize)), (startPoint.y + (x * boxSize)), boxSize, boxSize);
            }
        }

        Point[] p = board.getRobots();
        if (board.isFound()) {
            
            this.add(returnMain);
            exit.setBounds((screenSize.width / 2) - (buttonWidth / 2), (screenSize.height / 2) - (buttonWidth / 2) + 40, buttonWidth, buttonHeight);
            returnMain.setBounds((screenSize.width / 2) - (buttonWidth / 2), (screenSize.height / 2) - (buttonWidth / 2) - 40, buttonWidth, buttonHeight);
            g.setColor(new Color(0, 54, 15));
        } else {
            g.setColor(Color.GREEN);
        }
        g.fillOval((startPoint.x + (p[0].y * boxSize)), (startPoint.y + (p[0].x * boxSize)), boxSize, boxSize);
        g.setColor(Color.red);
        for (int r = 1; r < p.length; r++) {
            g.fillOval((startPoint.x + (p[r].y * boxSize)), (startPoint.y + (p[r].x * boxSize)), boxSize, boxSize);
        }
        if (board.isFound()) {
            thisBoard.setColor(new Color(0, 0, 0, 200));
            thisBoard.fillRect(0, 0, screenSize.width, screenSize.height);
            this.remove(back);
            this.remove(restart);
            this.remove(nextBoard);
        } else {
            drawMenue(g);
        }

    }

    private void getDirections() {
        double diffX = endClick.x - startClick.x;
        double diffY = endClick.y - startClick.y;
        int startBoardY = (startClick.x - startPoint.x) / boxSize;
        int startBoardX = (startClick.y - startPoint.y) / boxSize;
        int pressedRobot = board.getRobot(startBoardX, startBoardY);
        if (pressedRobot < 0) {
            return;
        }

        double hyp = Math.sqrt(diffX * diffX + diffY * diffY);

        if (hyp < (boxSize / 2)) {
            return;
        }

        if (diffX == 0) {
            if (diffY <= 0) {
                board.goUP(pressedRobot);
                return;
            } else {
                board.goDown(pressedRobot);
                return;
            }
        }

        double angle = diffY / diffX;
        if (diffX <= 0) {
            if (angle < -1) {
                board.goDown(pressedRobot);
                return;
            }
            if (angle > 1) {
                board.goUP(pressedRobot);
                return;
            }

            board.goLeft(pressedRobot);

        } else {

            if (angle > 1) {
                board.goDown(pressedRobot);
                return;
            }
            if (angle < -1) {
                board.goUP(pressedRobot);
                return;
            }
            board.goRight(pressedRobot);
        }
    }

    private void getBoxsize() {
        boxSize = (screenSize.height - 10) / boardSize;
        int startX = (screenSize.width - (boxSize * boardSize)) / 2;
        int startY = ((screenSize.height) - (boxSize * boardSize) + 10) / 2;
        startPoint = new Point(startX, startY);
    }

    private void drawMenue(Graphics g) {
        restart.setBounds(startPoint.x - buttonWidth - 50, startPoint.y + 20, buttonWidth, buttonHeight);
        back.setBounds(startPoint.x + boxSize * boardSize + 50, startPoint.y + 20, buttonWidth, buttonHeight);
        exit.setBounds(startPoint.x - buttonWidth - 50, startPoint.y + 40 + buttonHeight, buttonWidth, buttonHeight);
        nextBoard.setBounds(startPoint.x + boxSize * boardSize + 50, startPoint.y + 40 + buttonHeight, buttonWidth, buttonHeight);
    }
}
