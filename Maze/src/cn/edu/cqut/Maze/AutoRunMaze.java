package cn.edu.cqut.Maze;

import java.util.Stack;

/**
 * 自动走迷宫算法
 * 
 * @author Song
 *
 */
public class AutoRunMaze {

	private int[][] mazeData = null;// 迷宫数据

	private int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };// 下一步走的方向,分别为右下左上

	private int endX;// 迷宫的终点X

	private int endY;// 迷宫的终点Y

	private final int WALL = 0;

	private final int VISITED = 2;

	private int[][] visited;// 标记已经走过的路

	private int sumStep;// 一共走的步数

	Stack<int[]> path = new Stack<int[]>();// 用来储存最后路径

	/**
	 * 
	 * 
	 * @param mazeData
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public AutoRunMaze(int[][] mazeData, int startX, int startY, int endX, int endY) {
		this.mazeData = mazeData;
		this.endX = endX;
		this.endY = endY;
		visited = new int[mazeData.length][mazeData[0].length];
		int[] first = { startX, startY };
		path.push(first);
		run(startX, startY);
	}

	/**
	 * 走迷宫
	 * 
	 * @param x：现在的横坐标
	 * @param y：现在的纵坐标
	 */
	public void run(int x, int y) {

		visited[y][x] = VISITED;

		// 递归结束条件
		if (x == endX && y == endY)
			return;

		int nextStepX = x;
		int nextStepY = y;
		boolean flag = true;// 判断是否无路可走的标志

		// 选择下一个位置
		for (int i = 0; i < 4; i++) {
			nextStepX = x + direction[i][0];
			nextStepY = y + direction[i][1];

			if (isOver(nextStepX, nextStepY))// 判断是否越界
				continue;

			if (isWall(nextStepX, nextStepY))// 判断是否为墙
				continue;

			if (isVisited(nextStepX, nextStepY))// 判断是否访问过
				continue;
			flag = false;
			int[] nextStep = { nextStepX, nextStepY };
			path.push(nextStep);
			run(nextStepX, nextStepY);
			break;
		}

		// 如果无路可走
		if (flag) {
			path.pop();
			int[] backStep = path.peek();
			run(backStep[0], backStep[1]);
		}
	}

	/**
	 * 判断某点是否已经访问过
	 * 
	 * @param x:当前访问的横坐标
	 * @param y:当前访问的纵坐标
	 */
	public boolean isVisited(int x, int y) {
		return visited[y][x] == VISITED;
	}

	/**
	 * 判断某点是不是墙
	 */
	public boolean isWall(int x, int y) {
		return mazeData[y][x] == WALL;
	}

	/**
	 * 判断某点是否越界
	 */
	public boolean isOver(int x, int y) {
		// 判断下一步横坐标是否越界
		if (x < 0 || x > mazeData[0].length - 1)
			return true;
		// 判断下一步纵坐标是否越界
		if (y < 0 || y > mazeData.length - 1)
			return true;
		return false;
	}

	/**
	 * 保存路径信息到迷宫数组中
	 * 
	 */
	public String getStackInfo() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			int[] pos = path.get(i);
			string.append(i + 1 + ": (" + pos[0] + "," + pos[1] + ")\n");
			sumStep = path.size();
		}
		return string.toString();
	}

	/**
	 * 获得迷宫路径
	 * 
	 * @return
	 */
	public int[][] getPathInfo() {

		// 深度克隆
		int[][] temp = new int[mazeData.length][mazeData[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = mazeData[i][j];
			}
		}

		for (int i = 0; i < path.size(); i++) {
			int[] pos = path.get(i);
			temp[pos[1]][pos[0]] = VISITED;
		}
		return temp;
	}

	/**
	 * 得到一共走的步数
	 * 
	 * @return
	 */
	public int getSumStep() {
		return sumStep;
	}
}
