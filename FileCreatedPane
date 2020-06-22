package cn.edu.cqut.Maze;

import java.io.File;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 从文件选则迷宫数据的面板
 */
class FileCreatePane extends Pane {

	FileChooser fileChooser = null;// 文件选择器

	Label title = null;

	TextField filePath = null;

	Button browseButton = null;

	Button createButton = null;

	public FileCreatePane(MazePane mazaPane) {
		paint();
		run(mazaPane);
	}

	/**
	 * 打印面板
	 */
	public void paint() {

		title = new Label("Choose your data file:");
		title.setFont(Font.font(18));
		title.setLayoutX(0);
		title.setLayoutY(0);

		browseButton = new Button("Browse");
		browseButton.setLayoutX(340);
		browseButton.setLayoutY(50);

		filePath = new TextField();
		filePath.setLayoutX(0);
		filePath.setLayoutY(50);
		filePath.setPrefWidth(300);
		filePath.setText("C:\\Users\\Song\\Desktop\\MazeData.txt");
		fileChooser = new FileChooser();

		createButton = new Button("Create Maze");
		createButton.setLayoutX(450);
		createButton.setLayoutY(50);

		getChildren().clear();
		getChildren().addAll(title, filePath, browseButton, createButton);
	}

	/**
	 * 设置事件
	 * 
	 * @param mazePane
	 */
	public void run(MazePane mazePane) {
		browseButton.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(new Stage());
			filePath.setText(file.getPath());

		});

		createButton.setOnAction(e -> {
			mazePane.getChildren().clear();
			mazePane.paint(new File(filePath.getText()));
		});

	}
}
