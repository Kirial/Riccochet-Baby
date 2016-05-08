/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.io.*;
import java.util.LinkedList;

/**
 *
 * @author Family
 */
public class BoardLoad {

    private char[][] board;
    private int size;
    private Point goal;
    private Point[] robot;
    private boolean goalFound;
    private boolean[][] robotsAt;
    private LinkedList<int[]> moves;
    private String fileName;
    /**
     *
     * @param s
     * @throws Exception reads game file into char array
     */
    public BoardLoad(String s) throws Exception {
        fileName = s;
        reset();
    }
    public void reset()throws Exception{
        moves = new LinkedList<>();
        try {
            BufferedReader thisBoard = new BufferedReader(new FileReader(fileName));
            String sizeString = thisBoard.readLine();
            String robotsString = thisBoard.readLine();

            try {
                size = Integer.parseInt(sizeString);
                int robots = Integer.parseInt(robotsString);
                board = new char[size][size];
                robotsAt = getRobotsField(size);
                robot = new Point[robots];
            } catch (Exception number) {
                System.out.println("Header of File Error");
                throw new Exception();
            }

            String line = thisBoard.readLine();

            int count = 0;
            while (line != null || count < size) {
                for (int i = 0; i < size; i++) {
                    char thisChar = line.charAt(i);

                    switch (thisChar) {
                        case '#':
                        case ' ':
                            board[count][i] = line.charAt(i);
                            break;
                        case 'G':
                            goal = new Point(count, i);
                            board[count][i] = line.charAt(i);
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            if ((thisChar - 48) < robot.length) {
                                robot[thisChar - 48] = new Point(count, i);
                            }
                            robotsAt[count][i] = true;
                            board[count][i] = ' ';
                            break;
                        default:
                            System.out.println("Unknown Char Error");
                            throw new Exception();

                    }
                }
                line = thisBoard.readLine();
                count++;
            }
        } catch (IOException noFile) {
            System.out.println("File not found");
            throw new Exception();
        }
    }
    

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Directions/////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public void goLeft(int R) {
        Point finish = new Point(robot[R]);

        if (finish.y == 0) {
            return;
        }

        while (finish.y != 0) {
            if ((board[finish.x][finish.y - 1] != ' ' && board[finish.x][finish.y - 1] != 'G') || (robotsAt[finish.x][finish.y - 1])) {
                break;
            }
            finish.y--;
        }
        robotsAt[robot[R].x][robot[R].y] = false;
        robotsAt[finish.x][finish.y] = true;

        int[] move = new int[3];
        move[0] = R;
        move[1] = robot[R].x;
        move[2] = robot[R].y;
        moves.add(move);

        robot[R] = finish;
        finishTest(finish, R);

    }

    public void goRight(int R) {
        Point finish = new Point(robot[R]);

        if (finish.y == (size - 1)) {
            return;
        }

        while (finish.y != (size - 1)) {
            if ((board[finish.x][finish.y + 1] != ' ' && board[finish.x][finish.y + 1] != 'G') || (robotsAt[finish.x][finish.y + 1])) {
                break;
            }
            finish.y++;
        }
        robotsAt[robot[R].x][robot[R].y] = false;
        robotsAt[finish.x][finish.y] = true;

        int[] move = new int[3];
        move[0] = R;
        move[1] = robot[R].x;
        move[2] = robot[R].y;
        moves.add(move);

        robot[R] = finish;
        finishTest(finish, R);

    }

    public void goDown(int R) {
        Point finish = new Point(robot[R]);

        if (finish.x == (size - 1)) {
            return;
        }

        while (finish.x != (size - 1)) {
            if ((board[finish.x + 1][finish.y] != ' ' && board[finish.x + 1][finish.y] != 'G') || (robotsAt[finish.x + 1][finish.y])) {
                break;
            }
            finish.x++;
        }
        robotsAt[robot[R].x][robot[R].y] = false;
        robotsAt[finish.x][finish.y] = true;

        int[] move = new int[3];
        move[0] = R;
        move[1] = robot[R].x;
        move[2] = robot[R].y;
        moves.add(move);

        robot[R] = finish;
        finishTest(finish, R);

    }

    public void goUP(int R) {

        Point finish = new Point(robot[R]);

        if (finish.x == 0) {
            return;
        }

        while (finish.x != 0) {
            if ((board[finish.x - 1][finish.y] != ' ' && board[finish.x - 1][finish.y] != 'G') || (robotsAt[finish.x - 1][finish.y])) {
                break;
            }
            finish.x--;
        }
        robotsAt[robot[R].x][robot[R].y] = false;
        robotsAt[finish.x][finish.y] = true;

        int[] move = new int[3];
        move[0] = R;
        move[1] = robot[R].x;
        move[2] = robot[R].y;
        moves.add(move);

        robot[R] = finish;
        finishTest(finish, R);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// Return board /////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Returns board as a char
     *
     * @return
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * returns if goal was found
     */
    public boolean isFound() {
        return goalFound;
    }

    public int getSize() {
        return size;
    }

    public Point[] getRobots() {
        return robot;
    }

    public Point getGoal() {
        return goal;
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// Help functions ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private void finishTest(Point finish, int R) {
        if (finish.equals(goal) && R == 0) {
            goalFound = true;
            return;
        }
        goalFound = false;
    }

    public int getRobot(int x, int y) {
        for (int i = 0; i < robot.length; i++) {
            if (robot[i].x == x && robot[i].y == y) {
                return i;
            }
        }
        return -1;
    }

    private boolean[][] getRobotsField(int i) {
        boolean[][] field = new boolean[i][i];
        for (int x = 0; x < i; x++) {
            for (int y = 0; y < i; y++) {
                field[x][y] = false;
            }
        }
        return field;
    }

    void getLastMove() {
        if (moves.isEmpty()) {
            return;
        }
        
        int[] last = moves.getLast();
        robotsAt[robot[last[0]].x][robot[last[0]].y] = false;
        robotsAt[last[1]][last[2]] = true;

        robot[last[0]].x = last[1];
        robot[last[0]].y = last[2];
        moves.remove(moves.size()-1);
    }
}
