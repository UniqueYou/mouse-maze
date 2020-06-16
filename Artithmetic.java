package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author WangSong
 * @description 老鼠走迷宫的算法实现
 * @Time 2020-6-16
 */
public class Arithmetic {

    private int width;//迷宫的宽度

    private int height;//迷宫的高度


    public static void main(String[] args) {
	File file = new File("")
    }


    /**
     * @param file
     * @return data
     * @description 从本地文件中读取数据储存到ArrayList中
     */
    public ArrayList<Integer> getMazeData(File file) {
        ArrayList<Integer> data = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(file);
            while (in.hasNext()) {
                data.add(in.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to import file:File not found");
            System.out.println("Detail:\n" + e.getMessage());
        } finally {
            assert in != null;
            in.close();
        }
        return data;
    }

    /**
     * @param list
     * @return data
     * @description 按照迷宫的宽度和长度进行抓换
     * 例如迷宫的长度为8,宽度为10就将ArrayList转换成double[8][10]
     */
    protected int[][] toMaze(ArrayList list) {
        int count = 0;  //从list的0位开始转换
        int[][] data = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = (int) list.get(count++);
            }
        }
        return data;
    }


    /**
     * @return
     * @description 自动生成迷宫
     */
    public void createMaze() {


    }

    /**
     * @param data
     * @description 据数据输出字符画迷宫用于测试
     */
    public void createMaze(double[][] data) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (data[i][j] == 0)
                    System.out.print('*');
                else System.out.print(' ');
                System.out.println();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
