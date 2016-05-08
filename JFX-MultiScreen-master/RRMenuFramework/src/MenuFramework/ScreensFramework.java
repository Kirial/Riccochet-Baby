/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */ 

package MenuFramework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Angie
 */
public class ScreensFramework extends Application {
    
    public static String StartScreenID = "Start";
    public static String StartScreen = "StartScreen.fxml";

    public static String PlayScreenID = "Play";
    public static String PlayScreen = "PlayScreen.fxml";

    public static String PreviouslyPlayedID = "PrevPlayed";
    public static String PreviouslyPlayedScreen = "PrevPlayedScreen.fxml";

    public static String ExitID = "Exit";
    public static String ExitScreen = "ExitScreen.fxml";

    public static String CreatecustomID = "Load";
    public static String CreatecustomScreen = "CreatecustomScreen.fxml";

    public static String PlayerID = "Load";
    public static String PlayerScreen = "PlayerScreen.fxml";

    public static String ContinueID = "Load";
    public static String ContinueScreen = "ContinueScreen.fxml";

    public static String TopScoreID = "Load";
    public static String TopScoreScreen = "TopScoreScreen.fxml";

    public static String SinglePlayID = "Load";
    public static String SinglePlayScreen = "SinglePlayScreen.fxml";

    public static String TournamentID = "Load";
    public static String TournamentScreen = "TournamentScreen.fxml";

    public static String NewGameID = "Load";
    public static String NewGameScreen = "NewGameScreen.fxml";



    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.StartScreenID, ScreensFramework.StartScreen);
        mainContainer.loadScreen(ScreensFramework.PlayScreenID, ScreensFramework.PlayScreen);
        mainContainer.loadScreen(ScreensFramework.ExitID, ScreensFramework.ExitScreen);
        mainContainer.loadScreen(ScreensFramework.CreatecustomID, ScreensFramework.CreatecustomScreen);
        mainContainer.loadScreen(ScreensFramework.PlayerID, ScreensFramework.PlayerScreen);
        mainContainer.loadScreen(ScreensFramework.ContinueID, ScreensFramework.ContinueScreen);
        mainContainer.loadScreen(ScreensFramework.TopScoreID, ScreensFramework.TopScoreScreen);
        mainContainer.loadScreen(ScreensFramework.SinglePlayID, ScreensFramework.SinglePlayScreen);
        mainContainer.loadScreen(ScreensFramework.TournamentID, ScreensFramework.TournamentScreen);
        mainContainer.loadScreen(ScreensFramework.NewGameID, ScreensFramework.NewGameScreen);
        mainContainer.loadScreen(ScreensFramework.PreviouslyPlayedID, ScreensFramework.PreviouslyPlayedScreen);

        mainContainer.setScreen(ScreensFramework.StartScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
