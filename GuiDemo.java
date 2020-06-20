package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
		setWidth(400);
		setHeight(400);
		paint();
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

	/**
	 * 根据迷宫数据生成迷宫
	 * 
	 * @param mazeData
	 */
	public MazePane(int width, int height, int[][] mazeData) {
		setWidth(width);
		setHeight(height);
		mazePath = mazeData;
		paint(mazeData);
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
	 */
	public void paint(File file) {
		paint(Arithmetic.creatMazeData(file));

	}

	/**
	 * 根据迷宫数据自动生成迷宫
	 */
	public void paint() {

	}

	/**
	 * 根据迷宫数据生成迷宫
	 * 
	 * @param mazeData
	 */
	public void paint(int[][] mazeData) {
//		setMazePath(mazeData);
		int size = mazeData.length;
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
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
//		Image image = new Image("https://s1.ax1x.com/2020/06/17/NVqjg0.png");// 墙的图片
//		if (image.isError()) // 网络异常，输出提示
//			System.out.println("error:	You can’t load images because you don’t have internet");
//		else
//			System.out.println("Picture load succuss!");
//		ImageView imageView = new ImageView(image);
//		imageView.fitHeightProperty().bind(heightProperty());
//		imageView.fitWidthProperty().bind(widthProperty());
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.CADETBLUE);
		rectangle.widthProperty().bind(widthProperty());
		rectangle.heightProperty().bind(heightProperty());

		getChildren().clear();
		getChildren().add(rectangle);
//		getChildren().add(imageView);
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
	 * 画出路的图片 用空白表示路
	 */
	public void paint() {

	}
}

