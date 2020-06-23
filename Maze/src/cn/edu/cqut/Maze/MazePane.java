package cn.edu.cqut.Maze;

import java.io.File;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * �Թ����
 * 
 * @author WangSong
 */
class MazePane extends GridPane {

	/** 0��ʾǽ */
	private final int WALL = 0;

	/** 1��ʾ· */
	private final int ROUND = 1;

	private int[][] mazeData = null;// �Թ�����

	/**
	 * �����������쳤�ȿ�ȶ�Ϊ700��Ĭ�Ϲ��췽��
	 */
	public MazePane() {
		setWidth(700);
		setHeight(700);
	}

	/**
	 * �����ļ������Թ����Ĺ��췽��
	 */
	public MazePane(File file) {
		this();
		paint(file);
	}

	/**
	 * �����Թ����������Թ�
	 *
	 * @param mazeData:�Թ����
	 */
	public MazePane(int[][] mazeData) {
		this();
		paint(mazeData);
	}

	/**
	 * �����Թ��Ŀ�͸��Զ������Թ�
	 * 
	 * @param width:�Թ��Ŀ�
	 * @param height:�Թ��ĸ�
	 */
	public void autoCreateMaze(int width, int height) {
		RecursiveDivision recursiveDivision = new RecursiveDivision(width, height);
		paint(recursiveDivision.createMazeData());
		mazeData = recursiveDivision.getMazeData();
	}

	/**
	 * �����Թ��ļ������Թ�
	 * 
	 * @param file:�Թ��ļ�
	 */
	public void paint(File file) {
		this.mazeData = Arithmetic.createMazeData(file);
		paint(mazeData);
	}

	/**
	 * �����Թ����������Թ�
	 *
	 * @param mazeData:�Թ����
	 */
	public void paint(int[][] mazeData) {
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
				if (mazeData[i][j] == WALL) // 0��ʾΪǽ
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
	 * �õ��Թ�����
	 * 
	 * @return
	 */
	public int[][] getMazeData() {
		// ���������ȸ��ƣ����ܸı��Թ�����������
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
 * ·���
 *
 * @author WangSong
 */
class RoundPane extends Pane {

	/** ·���Ŀ������Ϊ20 */
	private final double WIDTH = 20;

	/** ·���ĸ߶�����Ϊ20 */
	private final double HEIGHT = 20;

	/**
	 * û�в����Ĺ��췽��
	 */
	public RoundPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * �ÿհױ�ʾ·
	 */
	public void paint() {

	}

}

/**
 * ǽ��壬�����Թ���ǽ��
 *
 * @author WangSong
 */
class WallPane extends Pane {

	/**
	 * ǽ���Ŀ������Ϊ20
	 */
	private final double WIDTH = 20;

	/**
	 * ǽ���ĸ߶�����Ϊ20
	 */
	private final double HEIGHT = 20;

	/**
	 * û�в����Ĺ��췽��
	 */
	public WallPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * ���ǽ����Ƭ
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
 * �߹���·���
 * 
 * @author WangSong
 *
 */
class VisitedPane extends Pane {

	/**
	 * ���Ŀ������Ϊ20
	 */
	private final double WIDTH = 20;

	/**
	 * ���ĸ߶�����Ϊ20
	 */
	private final double HEIGHT = 20;

	/**
	 * û�в����Ĺ��췽��
	 */
	public VisitedPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * ��ӡ�Թ�
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
