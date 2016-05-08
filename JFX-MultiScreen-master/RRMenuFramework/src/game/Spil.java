/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Family
 */
public class Spil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in, "windows-1252");
        String S = keyboard.nextLine();
        try {
            BoardLoad board = new BoardLoad(S);
            DrawBoard drow = new DrawBoard(board);
            
            JFrame frame = new JFrame();
            drow.owner = frame;
            frame.add(drow);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

        } catch (Exception E) {
            System.out.println("Error");
        }
    }
}
