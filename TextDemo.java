package cn.edu.cqut.mazeMouse;

import java.io.File;
import java.util.ArrayList;

/**
 * 用于测试类的
 * @author Song
 * 
 */
public class TextDemo {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Song\\Desktop\\mazetext.txt");
	   Arithmetic arithmetic  = new Arithmetic(25,25);
	   
	   ArrayList<Integer> list = arithmetic.getMazeData(file);
	   arithmetic.createMaze(arithmetic.toMaze(list,25,25));
	}

}
