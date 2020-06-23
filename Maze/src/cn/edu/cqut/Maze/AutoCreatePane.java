package cn.edu.cqut.Maze;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * �Զ������Թ����
 */
class AutoCreatePane extends Pane {

	Label widthLabel = null;// ��ʾ�����ȱ�ǩ

	Label heightLabel = null;// ��ʾ����߶ȱ�ǩ

	Label titleLabel = null;// �����ǩ

	Button createButton = null;// �����Թ���ť

	TextField widthText = null;// ��������

	TextField heightText = null;// �߶������

	public AutoCreatePane(MazePane mazePane) {
		paint();
		run(mazePane);
	}

	public void paint() {

		titleLabel = new Label("Enter the height and width of the maze,and enter the button:");
		titleLabel.setFont(Font.font(18));
		titleLabel.setLayoutX(0);
		titleLabel.setLayoutY(0);

		widthLabel = new Label("Width:");
		widthLabel.setPrefSize(60, 30);
		widthLabel.setLayoutY(40);
		widthLabel.setLayoutX(20);

		heightLabel = new Label("Height:");
		heightLabel.setPrefSize(60, 30);
		heightLabel.setLayoutY(40);
		heightLabel.setLayoutX(200);

		createButton = new Button("Create Maze");
		createButton.setPrefSize(150, 30);
		createButton.setLayoutY(40);
		createButton.setLayoutX(400);

		widthText = new TextField();
		widthText.setPrefSize(100, 30);
		widthText.setLayoutY(40);
		widthText.setLayoutX(90);

		heightText = new TextField();
		heightText.setPrefSize(100, 30);
		heightText.setLayoutY(40);
		heightText.setLayoutX(270);

		getChildren().clear();
		getChildren().addAll(titleLabel, widthLabel, widthText, heightLabel, heightText, createButton);

	}

	/**
	 * �����¼�
	 */
	private void run(MazePane mazePane) {

		createButton.setOnAction(e -> {
			String width = new String(widthText.getText());
			String height = new String(heightText.getText());
			if (width.equals("") || height.equals("")) {
				mazePane.getChildren().clear();
				mazePane.autoCreateMaze(51, 51);// ���û�������Ĭ������51x51���Թ�
			} else {
				mazePane.getChildren().clear();
				mazePane.autoCreateMaze(new Integer(width), new Integer(height));
			}
		});
	}
}
