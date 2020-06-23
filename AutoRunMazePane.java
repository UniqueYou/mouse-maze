package cn.edu.cqut.Maze;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * �Զ����Թ������
 * 
 * @author Song
 *
 */
class AutoRunMazePane extends Pane {

	/** ���Ŀ��Ϊ600 */
	private final int WIDTH = 600;

	/** ���ĸ߶�Ϊ400 */
	private final int HEIGHT = 200;

	Button runButton = null;// �Զ����Թ������а�ť

	Button fillButton = null;// �Զ�����յ�����İ�ť

	TextField startXText = null;// ������������

	TextField startYText = null;// ���ÿ�ʼ������

	TextField endXText = null;// �����յ������

	TextField endYText = null;// �����յ�������

	Label xLabel = null;// x��ǩ

	Label yLabel = null;// y��ǩ

	Label startLabel = null;// ��ʼ��ǩ

	Label endLabel = null;// ������ǩ

	TextArea stackText = null;// ջ��Ϣ��ʾ

	Text stepSum = null;// �ܹ��ߵĲ���

	AutoRunMaze runMaze = null;// �Զ����Թ��㷨

	/**
	 * 
	 * @param mazePane:�Թ����
	 */
	public AutoRunMazePane(MazePane mazePane) {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
		run(mazePane);
	}

	/**
	 * ������
	 */
	public void paint() {

		// ���а�ť
		runButton = new Button("Run");
		runButton.setPrefSize(75, 30);
		runButton.setLayoutX(450);
		runButton.setLayoutY(110);

		fillButton = new Button("Fill");
		fillButton.setPrefSize(75, 30);
		fillButton.setLayoutX(450);
		fillButton.setLayoutY(70);

		// ��ʼ�����������
		startXText = new TextField();
		startXText.setPrefSize(75, 10);
		startXText.setLayoutX(200);
		startXText.setLayoutY(70);

		// ��ʼ�����������
		startYText = new TextField();
		startYText.setPrefSize(75, 10);
		startYText.setLayoutX(330);
		startYText.setLayoutY(70);

		// �յ�����������
		endXText = new TextField();
		endXText.setPrefSize(75, 10);
		endXText.setLayoutX(200);
		endXText.setLayoutY(110);

		// �յ������������
		endYText = new TextField();
		endYText.setPrefSize(75, 10);
		endYText.setLayoutX(330);
		endYText.setLayoutY(110);

		// x��ǩ
		xLabel = new Label("X");
		xLabel.setLayoutX(230);
		xLabel.setLayoutY(35);
		xLabel.setFont(Font.font(18));

		// y��ǩ
		yLabel = new Label("Y");
		yLabel.setLayoutX(360);
		yLabel.setLayoutY(35);
		yLabel.setFont(Font.font(18));

		// ��ʼ��ǩ
		startLabel = new Label("Starting point:");
		startLabel.setFont(Font.font(18));
		startLabel.setLayoutX(50);
		startLabel.setLayoutY(75);

		// ������ǩ
		endLabel = new Label("Ending  point��");
		endLabel.setFont(Font.font(18));
		endLabel.setLayoutX(50);
		endLabel.setLayoutY(115);

		// ջ����Ϣ��ʾ
		stackText = new TextArea();
		stackText.setLayoutX(10);
		stackText.setLayoutY(220);
		stackText.setPrefSize(580, 250);
		stackText.setWrapText(true);
		stackText.setFont(Font.font("Georgia", 18));

		// �ߵĲ���
		stepSum = new Text(0, 490, "Step sum:");
		stepSum.setFill(Color.BLUE);
		stepSum.setFont(Font.font(20));

		getChildren().clear();
		getChildren().addAll(startLabel, endLabel, startXText, endXText, startYText, endYText, runButton, xLabel,
				yLabel, fillButton, stackText, stepSum);

	}

	/**
	 * �����¼�
	 */
	public void run(MazePane mazePane) {

		// ����Ĭ�������յ�
		startXText.setText("0");
		startYText.setText("1");

		// ���fill��ť�Զ����
		fillButton.setOnAction(e -> {
			startXText.setText("0");
			startYText.setText("1");
			int[][] tempData = mazePane.getMazeData();
			endXText.setText(tempData[0].length - 1 + "");
			endYText.setText(tempData.length - 2 + "");
		});

		// �����ť�����Թ�·��
		runButton.setOnAction(e -> {

			String startX = startXText.getText();
			String startY = startYText.getText();
			String endX = endXText.getText();
			String endY = endYText.getText();
			// û�����벻ִ��
			if (startX.equals("") || startY.equals("") || endX.equals("") || endY.equals("")) {
				return;
			}

			// �Զ����Թ��㷨
			runMaze = new AutoRunMaze(mazePane.getMazeData(), new Integer(startX), new Integer(startY),
					new Integer(endX), new Integer(endY));
			mazePane.getChildren().clear();
			mazePane.paint(runMaze.getPathInfo());

			// ����ջ��Ϣ��ʾ
			stackText.setText(runMaze.getStackInfo());
			stepSum.setText("Step sum:" + runMaze.getSumStep());
		});

	}

}
