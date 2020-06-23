package cn.edu.cqut.Maze;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;;

/**
 * 总的面板
 *
 * @author Song
 *
 */
class TotalPane extends Pane {

	/**
	 * 面板的宽为1300
	 */
	private final double WIDTH = 1300;

	/**
	 * 面板的高为700
	 */
	private final double HEIGHT = 800;

	int[][] mazaData;// 迷宫数据

	MazePane mazePane = null;// 迷宫面板

	Pane functionPane = null;// 功能面板

	Accordion accordion = null;// 生成迷宫界面

	AutoRunMazePane runMazePane = null;// 自动走迷宫面板

	BackgroundPane backgroundPane = null;// 背景面板

	/**
	 * 没有参数的构造方法
	 */
	public TotalPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
		run();
	}

	/**
	 * 输出面板
	 */
	public void paint() {
		getChildren().clear();

		/**
		 * 迷宫面板在左边，大小为700 x 700
		 */
		mazePane = new MazePane();
		mazePane.autoCreateMaze(40, 40);
		mazePane.setPrefSize(700, 700);
		mazePane.setLayoutX(0);
		mazePane.setLayoutY(0);
		mazePane.setOpacity(0.8);

		/**
		 * 背景面板大小为 600 x 700
		 */
		backgroundPane = new BackgroundPane();
		backgroundPane.setLayoutX(0);
		backgroundPane.setLayoutY(0);
		backgroundPane.setPrefSize(WIDTH + 20, HEIGHT);

		/**
		 * 功能面板在右边，大小为600 x 700
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

		// 自动跑迷宫面板
		runMazePane = new AutoRunMazePane(mazePane);
		functionPane.getChildren().addAll(text1, text2, text3, runMazePane);
		runMazePane.setLayoutX(0);
		runMazePane.setLayoutY(200);

	}

	/**
	 * /** 设置事件
	 */
	public void run() {
		// 生成迷宫部分界面
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
