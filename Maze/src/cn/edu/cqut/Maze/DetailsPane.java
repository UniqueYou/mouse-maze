package cn.edu.cqut.Maze;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * 显示信息面板 (显示栈内信息)
 * 
 * @author WangSong
 *
 */
public class DetailsPane extends Pane {

	/** 面板默认宽度为600 */
	private final int WIDTH = 600;

	/** 面板默认高度为400 */
	private final int HEIGHT = 400;

	TextArea text = null;

	/**
	 * 无参构造方法
	 */
	public DetailsPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
	}

	/**
	 * 显示面板
	 */
	public void paint() {

		text = new TextArea();
		text.setLayoutX(50);
		text.setLayoutY(50);
		text.prefWidthProperty().bind(widthProperty().subtract(100));
		text.prefHeightProperty().bind(heightProperty().subtract(100));
		text.setWrapText(true);
		text.setFont(Font.font("Georgia", 18));
		getChildren().clear();
		getChildren().add(text);
	}

	/**
	 * 设置文本框信息
	 */
	public void setStackText(String string) {
		text.setText(string);
	}

}
