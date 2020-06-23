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
 * ���ļ�ѡ���Թ����ݵ����
 * 
 * @author WangSong
 */
class FileCreatePane extends Pane {

	FileChooser fileChooser = null;// �ļ�ѡ����

	Label title = null;// ���ı���

	TextField filePath = null;// ����

	Button browseButton = null;// �����ť

	Button createButton = null;// �����Թ���ť

	/**
	 * 
	 * @param mazaPane:�Թ���Ϣ
	 */
	public FileCreatePane(MazePane mazaPane) {
		paint();
		run(mazaPane);
	}

	/**
	 * ��ӡ���
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
	 * �����¼�
	 * 
	 * @param mazePane:�Թ����
	 */
	public void run(MazePane mazePane) {
		// ��������ťѡ���ļ�
		browseButton.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(new Stage());
			filePath.setText(file.getPath());

		});

		// ��������Թ���ť���ɴ��ļ������Թ�
		createButton.setOnAction(e -> {
			mazePane.getChildren().clear();
			File file = new File(filePath.getText());
			mazePane.paint(file);

		});
	}
}