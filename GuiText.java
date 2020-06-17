package cn.edu.cqut.mazeMouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuiText extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		FunctionPane functionPane = new FunctionPane();
		Scene scene = new Scene(functionPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
