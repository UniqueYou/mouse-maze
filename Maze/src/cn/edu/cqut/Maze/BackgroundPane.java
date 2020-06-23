package cn.edu.cqut.Maze;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * ����ͼƬ���
 * 
 * @author Song
 *
 */
public class BackgroundPane extends Pane {

	/**
	 * �޲ι��췽��
	 */
	public BackgroundPane() {
		paint();
	}

	/**
	 * ��ӡ���
	 */
	public void paint() {
		ImageView imageView = new ImageView("https://s1.ax1x.com/2020/06/23/NNhalT.th.jpg");
		imageView.fitHeightProperty().bind(heightProperty());
		imageView.fitWidthProperty().bind(widthProperty());
		getChildren().add(imageView);
	}
}
