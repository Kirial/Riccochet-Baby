/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.File;
import java.util.ArrayList;
import javafx.stage.Stage;
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
        ArrayList<String> filesInFolders = new ArrayList<>();
        for (String folder : args) {
            File dir = new File(folder);
            if (dir.isDirectory()) {
                File[] listFiles = dir.listFiles();
                for (File file : listFiles) {
                    if (file.isFile()) {
                        filesInFolders.add(folder + "\\" + file.getName());
                    }
                }
            }
        }
        

        int randomID = (int) (Math.random() * (filesInFolders.size()));

        try {
            BoardLoad board = new BoardLoad(filesInFolders.get(randomID));
            DrawBoard drow = new DrawBoard(board);

            JFrame frame = new JFrame();
            drow.owner = frame;
            frame.add(drow);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception E) {
            System.out.println("Error");
        }
    }
}
