package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 迷宫GUI
 * 
 * @author Song
 *
 */
public class GuiDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("C:\\Users\\Song\\Desktop\\mazetext.txt");
		MazePane mazePane = new MazePane(250, 250, file);

		Scene scene = new Scene(mazePane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

		/*
		 * //画出迷宫的网格 //临时画的线,分界线左边画迷宫，右边画按钮 Line centerLine = new Line();
		 * centerLine.startXProperty().bind(primaryStage.widthProperty().divide( 2));
		 * centerLine.endXProperty().bind(centerLine.startXProperty());
		 * centerLine.setStartY(0);
		 * centerLine.endYProperty().bind(primaryStage.heightProperty());
		 * pane.getChildren().add(centerLine);
		 */

	}
}

/**
 * 迷宫面板
 * 
 */
class MazePane extends GridPane {

	private int[][] mazePath;// 表示迷宫的情况，0表示不通，1表示通

	private double startX;// 起始坐标X

	private double startY;// 起始坐标Y

	private double endX;// 终点坐标X

	private double endY;// 终点坐标X

	/**
	 * 不带参数创造长度宽度都为400的默认构造方法
	 */
	public MazePane() {

	}

	/**
	 * 根据文件生成迷宫面板的构造方法
	 * 
	 * @param width
	 * @param height
	 */
	public MazePane(int width, int height, File file) {
		setWidth(width);
		setHeight(height);
		paint(file);
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

	public int[][] getMazePath() {
		return mazePath;
	}

	public void setMazePath(int[][] mazePath) {
		this.mazePath = mazePath;
	}

	/**
	 * 根据迷宫文件生成一个正方形迷宫
	 * 
	 * @param w
	 * @param h
	 */
	public void paint(File file) {
		ArrayList<Integer> list = Arithmetic.getMazeData(file); // 读取迷宫数据保存到链表中
		int size = (int) Math.sqrt(list.size());
		System.out.println("mazeSize:" + size);
		setMazePath(Arithmetic.toMaze(list, size, size));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mazePath[i][j] == 0) // 0表示为墙
				{
					this.add(new WallPane(), i, j);
				} else
					this.add(new PathPane(), i, j);

			}

		}

	}

}

/**
 * 墙壁面板，画出迷宫的墙壁
 * 
 * @author WangSong
 *
 */
class WallPane extends Pane {

	/** 墙面板的宽度设置为50 */
	private final double width = 50;

	/** 墙面板的高度设置为50 */
	private final double height = 50;

	/**
	 * 没有参数的构造方法
	 */
	public WallPane() {
		setWidth(width);
		setHeight(height);
		paint();
	}

	/**
	 * 添加墙的照片
	 */
	public void paint() {

		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String imagePath = path + "\\pictureData\\GreenWall.png";
		System.out.println(imagePath);
		Image image = new Image("file:" + imagePath);
		System.out.println("read image succeed:" + !image.isError());
		ImageView imageView = new ImageView(image);
		imageView.fitHeightProperty().bind(this.heightProperty());
		imageView.fitWidthProperty().bind(this.widthProperty());

		/*
		 * 开始用直线画的立方体作为砖，也被图片代替 Polygon polygon = new Polygon(); ObservableList<Double>
		 * points = polygon.getPoints(); points.add(width * 0.2); points.add(0.0);
		 * 
		 * points.add(width); points.add(0.0);
		 * 
		 * points.add(width); points.add(height * 0.8);
		 * 
		 * points.add(width * 0.8); points.add(height);
		 * 
		 * points.add(0.0); points.add(height);
		 * 
		 * points.add(0.0); points.add(height * 0.2);
		 * 
		 * Line line1 = new Line(0, height * 0.2, width * 0.8, height * 0.2);
		 * line1.setStrokeWidth(2);
		 * 
		 * Line line2 = new Line(width, 0, width * 0.8, height * 0.2);
		 * line2.setStrokeWidth(2);
		 * 
		 * Line line3 = new Line(width * 0.2, height * 0.2, width * 0.8, height);
		 * line3.setStrokeWidth(2); polygon.setFill(Color.DARKSEAGREEN);
		 */

		getChildren().clear();
		getChildren().add(imageView);
	}

}

/**
 * 路面板
 * 
 * @author Song
 *
 */
class PathPane extends Pane {

	/** 路面板的宽度设置为50 */
	private final double width = 50;

	/** 路面板的高度设置为50 */
	private final double height = 50;

	private int x; // 迷宫面板网格的横坐标

	private int y; // 迷宫面板网格的纵坐标

	/**
	 * 没有参数的构造方法 默认的x,y都为-1，返回一个空的面板
	 */
	public PathPane() {
		x = -1;
		y = -1;
		setWidth(width);
		setHeight(height);
		paint();
	}

	/**
	 * 带面板坐标的构造方法
	 */
	public PathPane(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	/**
	 * 画出路的图片
	 */
	public void paint() {

		// 在起始坐标和终点坐标各加一个路标表示入口和出口

		/*
		 * Image startImage = new Image("file:C:\\Users\\Song\\Desktop\\path.png");
		 * ImageView imageView = new ImageView(startImage);
		 * imageView.fitHeightProperty().bind(this.heightProperty());
		 * imageView.fitWidthProperty().bind(this.widthProperty());
		 * 
		 * 
		 * getChildren().clear(); getChildren().add(imageView);
		 */
	}
}
