package cn.edu.cqut.Maze;

import java.io.File;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 迷宫面板
 * 
 * @author WangSong
 */
class MazePane extends GridPane {

	/** 0表示墙 */
	private final int WALL = 0;

	/** 1表示路 */
	private final int ROUND = 1;

	private int[][] mazeData = null;// 迷宫数据

	/**
	 * 不带参数创造长度宽度都为700的默认构造方法
	 */
	public MazePane() {
		setWidth(700);
		setHeight(700);
	}

	/**
	 * 根据文件生成迷宫面板的构造方法
	 */
	public MazePane(File file) {
		this();
		paint(file);
	}

	/**
	 * 根据迷宫数据生成迷宫
	 *
	 * @param mazeData:迷宫面板
	 */
	public MazePane(int[][] mazeData) {
		this();
		paint(mazeData);
	}

	/**
	 * 根据迷宫的宽和高自动生成迷宫
	 * 
	 * @param width:迷宫的宽
	 * @param height:迷宫的高
	 */
	public void autoCreateMaze(int width, int height) {
		RecursiveDivision recursiveDivision = new RecursiveDivision(width, height);
		paint(recursiveDivision.createMazeData());
		mazeData = recursiveDivision.getMazeData();
	}

	/**
	 * 根据迷宫文件生成迷宫
	 * 
	 * @param file:迷宫文件
	 */
	public void paint(File file) {
		this.mazeData = Arithmetic.createMazeData(file);
		paint(mazeData);
	}

	/**
	 * 根据迷宫数据生成迷宫
	 *
	 * @param mazeData:迷宫面板
	 */
	public void paint(int[][] mazeData) {
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
				if (mazeData[i][j] == WALL) // 0表示为墙
				{
					this.add(new WallPane(), j, i);
				} else if (mazeData[i][j] == ROUND) {
					this.add(new RoundPane(), j, i);
				} else
					this.add(new VisitedPane(), j, i);
			}
		}
	}

	/**
	 * 得到迷宫数据
	 * 
	 * @return
	 */
	public int[][] getMazeData() {
		// 必须进行深度复制，不能改变迷宫本来的数据
		int[][] temp = new int[mazeData.length][mazeData[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = mazeData[i][j];
			}
		}
		return temp;
	}

}

/**
 * 路面板
 *
 * @author WangSong
 */
class RoundPane extends Pane {

	/** 路面板的宽度设置为20 */
	private final double WIDTH = 20;

	/** 路面板的高度设置为20 */
	private final double HEIGHT = 20;

	/**
	 * 没有参数的构造方法
	 */
	public RoundPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * 用空白表示路
	 */
	public void paint() {

	}

}

/**
 * 墙面板，画出迷宫的墙壁
 *
 * @author WangSong
 */
class WallPane extends Pane {

	/**
	 * 墙面板的宽度设置为20
	 */
	private final double WIDTH = 20;

	/**
	 * 墙面板的高度设置为20
	 */
	private final double HEIGHT = 20;

	/**
	 * 没有参数的构造方法
	 */
	public WallPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * 添加墙的照片
	 */
	public void paint() {
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.CADETBLUE);
		rectangle.widthProperty().bind(widthProperty());
		rectangle.heightProperty().bind(heightProperty());
		getChildren().clear();
		getChildren().add(rectangle);
	}

}

/**
 * 走过的路面板
 * 
 * @author WangSong
 *
 */
class VisitedPane extends Pane {

	/**
	 * 面板的宽度设置为20
	 */
	private final double WIDTH = 20;

	/**
	 * 面板的高度设置为20
	 */
	private final double HEIGHT = 20;

	/**
	 * 没有参数的构造方法
	 */
	public VisitedPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * 打印迷宫
	 */
	public void paint() {
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.RED);
		rectangle.widthProperty().bind(widthProperty());
		rectangle.heightProperty().bind(heightProperty());
		getChildren().clear();
		getChildren().add(rectangle);
	}

}
