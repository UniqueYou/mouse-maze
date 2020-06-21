package cn.edu.cqut.mazeMouse;

import java.io.File;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * 迷宫面板
 */
class MazePane extends GridPane {

    /**
     * 0表示墙
     */
    private final int WALL = 0;

    /**
     * 1表示路
     */
    private final int ROUND = 1;


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
     * @param mazeData
     */
    public MazePane(int[][] mazeData) {
        this();
        paint(mazeData);
    }

    /**
     * 输入迷宫的宽和高自动生成迷宫
     * @return
     */
    public void autoCreateMaze(int width,int height){
        RecursiveDivision recursiveDivision = new RecursiveDivision(width,height);
        paint(recursiveDivision.createMazeData());
    }

    /**
     * 根据迷宫数据生成迷宫
     */
    public void paint(File file) {
        paint(Arithmetic.createMazeData(file));

    }


    /**
     * 根据迷宫数据生成迷宫
     *
     * @param mazeData
     */
    public void paint(int[][] mazeData) {
        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[0].length; j++) {
                if (mazeData[i][j] == WALL) // 0表示为墙
                {

                    this.add(new WallPane(), j, i);
                } else
                    this.add(new PathPane(), j, i);
            }
        }
    }

}

/**
 * 墙壁面板，画出迷宫的墙壁
 *
 * @author WangSong
 */
class WallPane extends Pane {

    /**
     * 墙面板的宽度设置为20
     */
    private final double width = 20;

    /**
     * 墙面板的高度设置为20
     */
    private final double height = 20;

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
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.CADETBLUE);
        rectangle.widthProperty().bind(widthProperty());
        rectangle.heightProperty().bind(heightProperty());
        getChildren().clear();
        getChildren().add(rectangle);
    }

}

/**
 * 路面板
 *
 * @author Song
 */
class PathPane extends Pane {

    /**
     * 路面板的宽度设置为20
     */
    private final double width = 20;

    /**
     * 路面板的高度设置为20
     */
    private final double height = 20;


    /**
     * 没有参数的构造方法
     */
    public PathPane() {
        setWidth(width);
        setHeight(height);
        paint();
    }

    /**
     * 画出路的图片 用空白表示路
     */
    public void paint() {

    }
}

