package cn.edu.cqut.Maze;

import java.util.Stack;

/**
 * �Զ����Թ��㷨
 * 
 * @author Song
 *
 */
public class AutoRunMaze {

	private int[][] mazeData = null;// �Թ�����

	private int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };// ��һ���ߵķ���,�ֱ�Ϊ��������

	private int endX;// �Թ����յ�X

	private int endY;// �Թ����յ�Y

	private final int WALL = 0;

	private final int VISITED = 2;

	private int[][] visited;// ����Ѿ��߹���·

	private int sumStep;// һ���ߵĲ���

	Stack<int[]> path = new Stack<int[]>();// �����������·��

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
	 * ���Թ�
	 * 
	 * @param x�����ڵĺ�����
	 * @param y�����ڵ�������
	 */
	public void run(int x, int y) {

		visited[y][x] = VISITED;

		// �ݹ��������
		if (x == endX && y == endY)
			return;

		int nextStepX = x;
		int nextStepY = y;
		boolean flag = true;// �ж��Ƿ���·���ߵı�־

		// ѡ����һ��λ��
		for (int i = 0; i < 4; i++) {
			nextStepX = x + direction[i][0];
			nextStepY = y + direction[i][1];

			if (isOver(nextStepX, nextStepY))// �ж��Ƿ�Խ��
				continue;

			if (isWall(nextStepX, nextStepY))// �ж��Ƿ�Ϊǽ
				continue;

			if (isVisited(nextStepX, nextStepY))// �ж��Ƿ���ʹ�
				continue;
			flag = false;
			int[] nextStep = { nextStepX, nextStepY };
			path.push(nextStep);
			run(nextStepX, nextStepY);
			break;
		}

		// �����·����
		if (flag) {
			path.pop();
			int[] backStep = path.peek();
			run(backStep[0], backStep[1]);
		}
	}

	/**
	 * �ж�ĳ���Ƿ��Ѿ����ʹ�
	 * 
	 * @param x:��ǰ���ʵĺ�����
	 * @param y:��ǰ���ʵ�������
	 */
	public boolean isVisited(int x, int y) {
		return visited[y][x] == VISITED;
	}

	/**
	 * �ж�ĳ���ǲ���ǽ
	 */
	public boolean isWall(int x, int y) {
		return mazeData[y][x] == WALL;
	}

	/**
	 * �ж�ĳ���Ƿ�Խ��
	 */
	public boolean isOver(int x, int y) {
		// �ж���һ���������Ƿ�Խ��
		if (x < 0 || x > mazeData[0].length - 1)
			return true;
		// �ж���һ���������Ƿ�Խ��
		if (y < 0 || y > mazeData.length - 1)
			return true;
		return false;
	}

	/**
	 * ����·����Ϣ���Թ�������
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
	 * ����Թ�·��
	 * 
	 * @return
	 */
	public int[][] getPathInfo() {

		// ��ȿ�¡
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
	 * �õ�һ���ߵĲ���
	 * 
	 * @return
	 */
	public int getSumStep() {
		return sumStep;
	}
}
