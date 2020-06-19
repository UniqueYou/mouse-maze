package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * 老鼠走迷宫的算法实现
 * 
 * 
 * @author WangSong
 * 
 * @Time 2020-6-16
 */

abstract class Arithmetic {

	/** 迷宫的大小固定为25 */
	public final int SIZE = 25;

	/** 路表示为1 */
	public final int ROUND = 1;

	/** 墙表示为0 */
	public final int WALL = 0;

	private int startX;// 迷宫起点的横坐标

	private int startY;// 迷宫起点的纵坐标

	private int endX;// 迷宫终点的横坐标

	private int endY;// 迷宫终点的纵坐标

	/**
	 * 根据迷宫的起点和终点生成迷宫
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public Arithmetic(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	/**
	 * 不带参数的构造方法 起点默认为(1,1),终点默认为(SIZE-2,SIZE-2)
	 */
	public Arithmetic() {
		this.startX = 1;
		this.startY = 1;
		this.endX = SIZE - 2;
		this.endY = SIZE - 2;
	}

	/**
	 * 根据不同的算法生成不同的迷宫数据
	 * 
	 * @return int[][]
	 */
	public abstract int[][] creatMazeData();

	/**
	 * 从本地文件中读取数据储存到int[][]中
	 * 
	 * @param file
	 * @return data
	 * @throws IOException
	 * @throws FileNotFoundException
	 *
	 */
	public static int[][] creatMazeData(File file) {
		ArrayList<Integer> list = new ArrayList<>();
		int[][] mazeData = null;
		int value;

		try (FileInputStream input = new FileInputStream(file);) {
			while ((value = input.read()) != -1)
				list.add(value - '0');
			mazeData = (int[][]) list.toArray();

		} catch (FileNotFoundException e) {
			System.out.println("Failed to import file:File not found");
		} catch (IOException e) {
			System.out.println("Input is filed");
		}
		return mazeData;
	}

	/**
	 * 
	 * 根据数据输出字符画迷宫用于测试,0表示墙，1表示路
	 * 
	 * @param data
	 */
	public static void printMaze(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] == 0)
					System.out.print("[]");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
}

/**
 * 回溯算法自动生成迷宫 深度优先
 * 
 * @author WangSong
 *
 */
class Backtrack extends Arithmetic {

	/**
	 * 不带参数的构造方法
	 */
	public Backtrack() {

	}

	/**
	 * 带起点和终点的构造方法
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public Backtrack(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}

	/**
	 * 得到迷宫数据
	 * 
	 * @return int[][]
	 */
	@Override
	public int[][] creatMazeData() {
		// 生成基础迷宫网格
		int[][] mazeData = new int[SIZE][SIZE];
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData.length; j++) {
				if (i % 2 == 1 && j % 2 == 1)
					mazeData[i][j] = 1;
			}
		}
		dig(mazeData, 1, 1);

		return mazeData;
	}

	/**
	 * 递归回溯 挖墙，将墙去掉
	 * 
	 * @param x
	 * @param y
	 */
	public void dig(int[][] mazeData, int x, int y) {
		Random rand = new Random();

		// 如果越界的话那么也不能在挖了

		// 如果是路的话，就不需要在挖墙了
		if (mazeData[x][y] == WALL) {

			// 随机选择一个方向开挖
			switch (rand.nextInt(4)) {
			case 0:
				dig(mazeData, x, y - 1); // 往上挖
				break;
			case 1:
				dig(mazeData, x, y + 1); // 往下挖
				break;
			case 2:
				dig(mazeData, x - 1, y); // 往左挖
				break;
			case 3:
				dig(mazeData, x + 1, y); // 往右挖
				break;
			}
		}
	}
}

/**
 * 递归分割生成迷宫
 * 
 * @author WangSong
 *
 */
class RecursiveDivision extends Arithmetic {

	private int[][] mazeData = new int[SIZE][SIZE];

	@Override
	public int[][] creatMazeData() {
		int[][] mazeData = new int[SIZE][SIZE];

		// 初始化迷宫，给迷宫添加一圈外墙
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[0].length; j++) {
				if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1)
					mazeData[i][j] = 0;
				else
					mazeData[i][j] = 1;
			}
		}
		division(0, 0,SIZE,SIZE);
		return mazeData;
	}

	/**
	 * 给迷宫分割画线
	 * 
	 * @param x:迷宫起点的X坐标
	 * @param y:迷宫终点的Y坐标
	 * @param width:迷宫的宽度
	 * @param height:迷宫的高度
	 */
	private void division(int startX, int startY, int endX, int endY) {
		Random random = new Random();
		int divisionX;// 分割墙的横坐标
		int divisionY;// 分割墙的纵坐标

		// 如果迷宫的宽度或者高度小于2了就不能再分割了
		if (endX - startX <= 2 || endY -startY <= 2)
			return;

		int x = startX + 1 + random.nextInt(endX - startX - 1);// 纵向分割分割线的横坐标
		int y = startY + random.nextInt(endY - startY - 1);// 横向分割线的纵坐标
		
		for (int i = startX; i < endX; i++) // 横向分割
			mazeData[startY][i] = WALL;

		for (int i = startY; i < startX; i++) // 纵向分割
			mazeData[i][startX] = WALL;


		division(startX, startY, endX - 1, endY - 1);
		division(startX + 1, startY + 1, endX, endY);
		division(startX + 1, startY, endX, endY - 1);
		division(startX, startY + 1, endX - 1, endY);

//		// 随机打开三扇门
//		switch (random.nextInt(4)) {
//		case 0:
//			openDoor(x - width, y, x - 1, y); // 开左边的墙
//			break;
//		case 1:
//			openDoor(x, y - height, x, y - 1); // 开上方的墙
//			break;
//		case 2:
//			openDoor(x + 1, y, x + width, y); // 开右边的墙
//			break;
//		case 3:
//			openDoor(x, y + 1, x, y + width);// 开下面的墙
//			break;
//		}
		

		// 左上角分割

		// 右上角分割

		// 左下角分割

		// 右下角分割

	}

	/**
	 * 在指定的一面墙上开一个随机的门
	 * 
	 * @param startX:墙开始的横坐标
	 * @param startY：墙开始的纵坐标
	 * @param endX：墙结束的横坐标
	 * @param endY：墙结束的纵坐标
	 */
	public void openDoor(int startX, int startY, int endX, int endY) {
		Random random = new Random();
		int x;// 开门的横坐标
		int y;// 开门的纵坐标

		// 墙是横着的
		if (startX == endX) {
			y = startY + random.nextInt((endY - startY) / 2 + 1) * 2;// 在奇数墙上开门
			mazeData[y][startX] = ROUND;
		} else { // 墙是竖着的
			x = startX + random.nextInt((endX - startX) / 2 + 1) * 2;// 在奇数墙上开门
			mazeData[startY][x] = ROUND;
		}
	}

	/**
	 * 获得迷宫数据
	 * 
	 * @return int[][]:迷宫数据
	 */
	public int[][] getMazeData() {
		return mazeData;
	}

}
