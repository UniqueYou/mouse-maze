package cn.edu.cqut.Maze;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * 显示信息的面板 显示栈内信息
 * 
 * @author Song
 *
 */
public class DetailsPane extends Pane {

	private final int width = 600;

	private final int height = 400;

	TextArea text = null;

	public DetailsPane() {
		setWidth(width);
		setHeight(height);
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
