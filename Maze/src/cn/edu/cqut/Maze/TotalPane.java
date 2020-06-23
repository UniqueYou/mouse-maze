package cn.edu.cqut.Maze;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;;

/**
 * �ܵ����
 *
 * @author Song
 *
 */
class TotalPane extends Pane {

	/**
	 * ���Ŀ�Ϊ1300
	 */
	private final double WIDTH = 1300;

	/**
	 * ���ĸ�Ϊ700
	 */
	private final double HEIGHT = 800;

	int[][] mazaData;// �Թ�����

	MazePane mazePane = null;// �Թ����

	Pane functionPane = null;// �������

	Accordion accordion = null;// �����Թ�����

	AutoRunMazePane runMazePane = null;// �Զ����Թ����

	BackgroundPane backgroundPane = null;// �������

	/**
	 * û�в����Ĺ��췽��
	 */
	public TotalPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
		run();
	}

	/**
	 * ������
	 */
	public void paint() {
		getChildren().clear();

		/**
		 * �Թ��������ߣ���СΪ700 x 700
		 */
		mazePane = new MazePane();
		mazePane.autoCreateMaze(40, 40);
		mazePane.setPrefSize(700, 700);
		mazePane.setLayoutX(0);
		mazePane.setLayoutY(0);
		mazePane.setOpacity(0.8);

		/**
		 * ��������СΪ 600 x 700
		 */
		backgroundPane = new BackgroundPane();
		backgroundPane.setLayoutX(0);
		backgroundPane.setLayoutY(0);
		backgroundPane.setPrefSize(WIDTH + 20, HEIGHT);

		/**
		 * ����������ұߣ���СΪ600 x 700
		 */
		functionPane = new Pane();
		functionPane.setPrefSize(600, 700);
		functionPane.setOpacity(0.8);

		getChildren().addAll(backgroundPane, mazePane, functionPane);
		Text text1 = new Text(0, 30, "Create maze:");
		text1.setFont(Font.font(20));
		text1.setFill(Color.CORNFLOWERBLUE);

		Text text2 = new Text(0, 210, "Auto run maze:");
		text2.setFont(Font.font(20));
		text2.setFill(Color.CORNFLOWERBLUE);

		Text text3 = new Text(0, 400, "Stack information:");
		text3.setFont(Font.font(20));
		text3.setFill(Color.CORNFLOWERBLUE);

		functionPane.setLayoutX(700);
		functionPane.setLayoutY(0);

		// �Զ����Թ����
		runMazePane = new AutoRunMazePane(mazePane);
		functionPane.getChildren().addAll(text1, text2, text3, runMazePane);
		runMazePane.setLayoutX(0);
		runMazePane.setLayoutY(200);

	}

	/**
	 * /** �����¼�
	 */
	public void run() {
		// �����Թ����ֽ���
		accordion = new Accordion();
		accordion.setLayoutY(50);
		accordion.setLayoutX(0);
		accordion.setPrefWidth(600);
		AutoCreatePane autoCreatePane = new AutoCreatePane(mazePane);

		TitledPane titledPane1 = new TitledPane("Auto create maze", autoCreatePane);
		FileCreatePane fileCreatePane = new FileCreatePane(mazePane);

		TitledPane titledPane2 = new TitledPane("Create maze from maze data", fileCreatePane);
		accordion.getPanes().addAll(titledPane1, titledPane2);
		functionPane.getChildren().add(accordion);
	}
}
