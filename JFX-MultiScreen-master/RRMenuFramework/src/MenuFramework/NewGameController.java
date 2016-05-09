/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuFramework;

import game.Spil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author taras
 */
public class NewGameController implements Initializable, ControlledScreen {

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void startGame(ActionEvent event) {
        String[] folders = {"obligatorisk_synlig", "konkurrence_sjove", "konkurrence_random"};
        Stage s = (Stage) myController.getScene().getWindow();
        s.hide();
        Spil.main(folders);


    }

    public void handleButtonAction(Event event) {
    }
}
