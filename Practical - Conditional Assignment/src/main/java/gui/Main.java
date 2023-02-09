package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage1) throws Exception {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(Main.class.getResource("ProgramChooserController.fxml"));
        Parent prgListParent = listLoader.load();
        Scene programListScene = new Scene(prgListParent, 800, 600);
        ProgramChooserController programChooserController = listLoader.getController();
        stage1.setScene(programListScene);
        stage1.show();

        FXMLLoader executorLoader = new FXMLLoader();
        executorLoader.setLocation(Main.class.getResource("ProgramExecutorController.fxml"));
        Parent prgExecutorParent = executorLoader.load();
        Scene programExecutorScene = new Scene(prgExecutorParent, 800, 600);
        ProgramExecutorController programExecutorController = executorLoader.getController();
        programChooserController.setProgramExecutorController(programExecutorController);
        Stage stage2 = new Stage();
        stage2.setScene(programExecutorScene);
        stage2.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}