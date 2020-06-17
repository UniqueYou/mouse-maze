package cn.edu.cqut.Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		MazePane mazePane = new MazePane();

		pane.getChildren().addAll();

		/*
		 * //画出迷宫的网格 //临时画的线,分界线左边画迷宫，右边画按钮 Line centerLine = new Line();
		 * centerLine.startXProperty().bind(primaryStage.widthProperty().divide(
		 * 2)); centerLine.endXProperty().bind(centerLine.startXProperty());
		 * centerLine.setStartY(0);
		 * centerLine.endYProperty().bind(primaryStage.heightProperty());
		 * pane.getChildren().add(centerLine);
		 */

	}
}

// 右边的功能面板
class ConditionPane extends Pane {

	private double w;
	private double h;

}

// 左边的迷宫面板
class MazePane extends Pane {
	private double w;// 面板宽度
	private double h;// 面板长度
	private double[] mazePath;// 表示迷宫的情况，0表示不通，1表示通
	private double startX;// 起始坐标X
	private double startY;// 起始坐标Y
	private double endX;// 终点坐标X
	private double endY;// 终点坐标X

	// 默认面板的构造方法
	public MazePane() {
		this(400, 400);
	}

	// 带宽度和高度的构造方法
	public MazePane(double width, double height) {
		this.w = width;
		this.h = height;
		setW(w);
		setH(h);
		paint();
	}

	// 输出面板
	public void paint() {
		getChildren().clear();
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		setWidth(w);
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		setHeight(h);
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}
}
