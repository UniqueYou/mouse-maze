package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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

	/** 路表示为1 */
	public final int ROUND = 1;

	/** 墙表示为0 */
	public final int WALL = 0;

	/**
	 * 不带参数的构造方法
	 */
	public Arithmetic() {
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
		try {
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				list.add(in.nextInt());
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Arithmetic.toArray(list);
	}

	/**
	 * 将链表转换成整形二位数组
	 * 
	 * @param list
	 * @return int[][]
	 */
	public static int[][] toArray(ArrayList<Integer> list) {
		int size = (int) Math.sqrt(list.size());
		int[][] mazeData = new int[size][size];
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mazeData[i][j] = list.get(count++);
			}
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
 * 递归分割生成迷宫
 * 
 * @author WangSong
 *
 */
class RecursiveDivision extends Arithmetic {

	private int[][] mazeData = null;

	private int width;// 迷宫的宽度

	private int height;// 迷宫的高度

	public RecursiveDivision(int width, int height) {
		this.width = width % 2 + 1 == 0 ? width : width + 1;
		this.height = height % 2 + 1 == 0 ? height : height + 1;
		mazeData = new int[this.height][this.width];
	}

	@Override
	public int[][] creatMazeData() {

		// 初始化迷宫，给迷宫添加一圈外墙

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
					mazeData[y][x] = WALL;
				else
					mazeData[y][x] = ROUND;
			}
		}
		division(1, 1, width-2, height-2);

		return mazeData;
	}

	/**
	 * 递归分割画迷宫
	 * 
	 * @param startX:迷宫的起点横坐标
	 * @param startY:迷宫的起点纵坐标
	 * @param endX:迷宫的终点横坐标
	 * @param endY:迷宫的终点纵坐标
	 */
	private void division(int startX, int startY, int endX, int endY) {

		Random random = new Random();

		// 如果迷宫的宽度或者高度小于2了就不能再分割了
		if (endX - startX <2 || endY - startY <2)
			return;
		int posX,posY;
		/** x,y只能是偶数 */

		 posX = startX + 1 + random.nextInt((endX - startX) / 2)*2;// 纵向分割分割线的横坐标

			posY = startY + 1 + random.nextInt((endY - startY) / 2)*2;// 横向分割线的纵坐标

		for (int i = startX; i <=endX; i++) // 横向分割
			mazeData[posY][i] = WALL;

		for (int i = startY; i <=endY; i++) // 纵向分割
			mazeData[i][posX] = WALL;
		
		division(startX, startY, posX - 1, posY - 1);// 左下区域

		division(startX, posY + 1, posX - 1, endY);// 左上区域

		division(posX + 1, posY + 1, endX, endY);// 右上区域

		division(posX + 1, startY, endX, posY - 1);// 右下区域
		
		
		
		// 随机打开三扇门
		switch (random.nextInt(4)) {
		case 0:
			openDoor(startX, posY, posX - 1, posY); // 开左边的墙
			openDoor(posX, posY + 1, posX, endY); // 开上方的墙
			openDoor(posX + 1, posY, endX, posY); // 开右边的墙

			break;
		case 1:
			openDoor(posX, posY + 1, posX, endY); // 开上方的墙
			openDoor(posX + 1, posY, endX, posY); // 开右边的墙
			openDoor(posX, startY, posX, posY - 1);// 开下面的墙

			break;
		case 2:
			openDoor(posX + 1, posY, endX, posY); // 开右边的墙
			openDoor(posX, startY, posX, posY - 1);// 开下面的墙
			openDoor(startX, posY, posX - 1, posY); // 开左边的墙

			break;
		case 3:
			openDoor(posX, startY, posX, posY - 1);// 开下面的墙
			openDoor(startX, posY, posX - 1, posY); // 开左边的墙
			openDoor(posX, posY + 1, posX, endY); // 开上方的墙

			break;
		}

	}

	/**
	 * 在指定的一面墙上开一个随机的门
	 * 
	 * @param startX:迷宫开始的横坐标
	 * @param startY:迷宫开始的纵坐标
	 * @param endX：迷宫结束的横坐标
	 * @param endY：迷宫结束的纵坐标
	 */
	public void openDoor(int startX, int startY, int endX, int endY) {
		Random random = new Random();
		int x;// 开门的横坐标
		int y;// 开门的纵坐标
		
		// 墙是横着的
		if (startY == endY) {
			x = startX + random.nextInt((endX - startX) / 2 + 1) * 2;
			mazeData[startY][x] = ROUND;
		} else { // 墙是竖着的
			y = startY + random.nextInt((endY - startY) / 2 + 1) * 2;// 在奇数墙上开门
			mazeData[y][startX] = ROUND;
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
