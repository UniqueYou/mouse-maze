package cn.edu.cqut.Maze;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 自动走迷宫面板
 */
class AutoRunMazePane extends Pane {

	/** 面板的宽度为600 */
	private final int width = 600;

	/** 面板的高度为400 */
	private final int height = 200;

	Button runButton = null;// 自动走迷宫的运行按钮

	Button fillButton = null;// 自动填充终点和起点的按钮

	TextField startXText = null;// 设置起点横坐标

	TextField startYText = null;// 设置开始纵坐标

	TextField endXText = null;// 设置终点横坐标

	TextField endYText = null;// 设置终点纵坐标

	Label xLabel = null;// x标签

	Label yLabel = null;// y标签

	Label startLabel = null;// 开始标签

	Label endLabel = null;// 结束标签

	Button detailsButton = null;// 细节信息按钮

	AutoRunMaze runMaze = null;// 自动走迷宫算法

	Stage stage = null;

	DetailsPane detailsPane = null;

	/**
	 * 不带参数的构造方法
	 */
	public AutoRunMazePane(MazePane mazePane) {
		setWidth(width);
		setHeight(height);
		paint();
		run(mazePane);
	}

	/**
	 * 输出面板
	 */
	public void paint() {

		// 运行按钮
		runButton = new Button("Run");
		runButton.setPrefSize(75, 30);
		runButton.setLayoutX(450);
		runButton.setLayoutY(110);

		fillButton = new Button("Fill");
		fillButton.setPrefSize(75, 30);
		fillButton.setLayoutX(450);
		fillButton.setLayoutY(70);

		// 开始横坐标输入框
		startXText = new TextField();
		startXText.setPrefSize(75, 10);
		startXText.setLayoutX(200);
		startXText.setLayoutY(70);

		// 开始纵坐标输入框
		startYText = new TextField();
		startYText.setPrefSize(75, 10);
		startYText.setLayoutX(330);
		startYText.setLayoutY(70);

		// 终点横坐标输入框
		endXText = new TextField();
		endXText.setPrefSize(75, 10);
		endXText.setLayoutX(200);
		endXText.setLayoutY(110);

		// 终点纵坐标输入框
		endYText = new TextField();
		endYText.setPrefSize(75, 10);
		endYText.setLayoutX(330);
		endYText.setLayoutY(110);

		// x标签
		xLabel = new Label("X");
		xLabel.setLayoutX(230);
		xLabel.setLayoutY(35);
		xLabel.setFont(Font.font(18));

		// y标签
		yLabel = new Label("Y");
		yLabel.setLayoutX(360);
		yLabel.setLayoutY(35);
		yLabel.setFont(Font.font(18));

		// 开始标签
		startLabel = new Label("Starting point:");
		startLabel.setFont(Font.font(18));
		startLabel.setLayoutX(50);
		startLabel.setLayoutY(75);

		// 结束标签
		endLabel = new Label("Ending  point：");
		endLabel.setFont(Font.font(18));
		endLabel.setLayoutX(50);
		endLabel.setLayoutY(115);

	}

	/**
	 * 设置事件
	 */
	public void run(MazePane mazePane) {

		// 设置默认起点和终点
		startXText.setText("0");
		startYText.setText("1");

		// 点击fill按钮自动填充
		fillButton.setOnAction(e -> {
			startXText.setText("0");
			startYText.setText("1");
			int[][] tempData = mazePane.getMazeData();
			endXText.setText(tempData[0].length - 1 + "");
			endYText.setText(tempData.length - 2 + "");
		});

		// 点击按钮生成迷宫路径
		runButton.setOnAction(e -> {

			String startX = startXText.getText();
			String startY = startYText.getText();
			String endX = endXText.getText();
			String endY = endYText.getText();
			// 没有输入不执行
			if (startX.equals("") || startY.equals("") || endX.equals("") || endY.equals("")) {
				return;
			}

			runMaze = new AutoRunMaze(mazePane.getMazeData(), new Integer(startX), new Integer(startY),
					new Integer(endX), new Integer(endY));
			mazePane.getChildren().clear();
			mazePane.paint(runMaze.getPathInfo());

			// 设置栈信息显示
			detailsPane.setStackText(runMaze.getStackInfo());
		});

	}

}
