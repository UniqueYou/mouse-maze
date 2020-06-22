package cn.edu.cqut.Maze;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
;

/**
 * 总的面板
 *
 * @author Song
 *
 */
class TotalPane extends Pane {

	/**
	 * 面板的宽为1200
	 */
	private final double WIDTH = 1200;

	/**
	 * 面板的高为700
	 */
	private final double HEIGHT = 800;
	
	int[][] mazaData;//迷宫数据

	MazePane mazePane = null;// 迷宫面板

	Pane functionPane = null;// 功能面板

	Accordion accordion = null;//生成迷宫界面
	
	AutoRunMazePane runMazePane = null;//自动走迷宫面板
	
	Button settingButton = null;//设置按钮
	
	


	public TotalPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
		run();
		help();
	}

	/**
	 * 输出面板
	 */
	public void paint() {

		/**
		 * 迷宫面板在左边，大小为700 x 700
		 */
		mazePane = new MazePane();
		mazePane.autoCreateMaze(40, 40);
		mazePane.setPrefSize(700, 700);
		mazePane.setLayoutX(0);
		mazePane.setLayoutY(0);

		/**
		 * 功能面板在右边，大小为600 x 700
		 */
		functionPane = new Pane();
		functionPane.setPrefSize(600, 700);

		Text text1 = new Text(0,30,"Create maze:");
		text1.setFont(Font.font(20));
		text1.setFill(Color.CORNFLOWERBLUE);

		Text text2 = new Text(0,210,"Auto run maze:");
		text2.setFont(Font.font(20));
		text2.setFill(Color.CYAN);
		text2.setFill(Color.CORNFLOWERBLUE);
		
		functionPane.setLayoutX(700);
		functionPane.setLayoutY(0);
		functionPane.getChildren().addAll(text1,text2);
		getChildren().clear();
		getChildren().addAll(mazePane, functionPane);
		
		//自动跑迷宫面板
		 runMazePane = new AutoRunMazePane(mazePane);
		functionPane.getChildren().add(runMazePane);
		runMazePane.setLayoutX(0);
		runMazePane.setLayoutY(200);



	}

	/**
	/**
	 * 设置事件
	 */
	public void run() {
		//生成迷宫部分界面
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
	
	/**
	 * 打开帮助按钮
	 */
	public void help() {
		settingButton = new Button();
		settingButton.setLayoutX(20);
		settingButton.setLayoutY(500);
		settingButton.setShape(new Rectangle());
		settingButton.setOnAction(e -> {
			
		});
		
		functionPane.getChildren().add(settingButton);
	}

}
