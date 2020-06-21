package cn.edu.cqut.mazeMouse;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * �ܵ����
 *
 * @author Song
 *
 */
class TotalPane extends Pane {

	/**
	 * ���Ŀ�Ϊ1200
	 */
	private final double WIDTH = 1200;

	/**
	 * ���ĸ�Ϊ700
	 */
	private final double HEIGHT = 700;

	MazePane mazePane = null;// �Թ����

	Pane functionPane = null;// �������

	Accordion accordion1 = null;


	public TotalPane() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		paint();
		run();
	}

	/**
	 * ������
	 */
	public void paint() {

		/**
		 * �Թ��������ߣ���СΪ700 x 700
		 */
		mazePane = new MazePane();
		mazePane.autoCreateMaze(40, 40);
		mazePane.setPrefSize(700, 700);
		mazePane.setLayoutX(0);
		mazePane.setLayoutY(0);

		/**
		 * ����������ұߣ���СΪ600 x 700
		 */
		functionPane = new Pane();
		functionPane.setPrefSize(600, 700);

		Label label1 = new Label("Create maze:");
		label1.setFont(Font.font(18));
		
		functionPane.setLayoutX(700);
		functionPane.setLayoutY(0);
		functionPane.getChildren().add(label1);
		getChildren().clear();
		getChildren().addAll(mazePane, functionPane);



	}

	/**
	/**
	 * �����¼�
	 */
	public void run() {
		accordion1 = new Accordion();
		accordion1.setLayoutY(50);
		accordion1.setLayoutX(0);
		accordion1.setPrefWidth(600);
		AutoCreatePane autoCreatePane = new AutoCreatePane(mazePane);
		TitledPane titledPane1 = new TitledPane("Auto create maze",autoCreatePane);
		
		FileCreatePane fileCreatePane = new FileCreatePane(mazePane);
		
		TitledPane titledPane2 = new TitledPane("Create maze from maze data",fileCreatePane);
		accordion1.getPanes().addAll(titledPane1, titledPane2);
		functionPane.getChildren().add(accordion1);

	}

}

/**
 * �Զ������Թ����
 */
class AutoCreatePane extends Pane {

	Label widthLabel = null;// ��ʾ�����ȱ�ǩ

	Label heightLabel = null;// ��ʾ����߶ȱ�ǩ

	Label titleLabel = null;// �����ǩ

	Button createButton = null;// �����Թ���ť

	TextField widthText = null;// ��������

	TextField heightText = null;// �߶������

	public AutoCreatePane(MazePane mazePane) {
		paint();
		run(mazePane);
	}

	public void paint() {

		titleLabel = new Label("Enter the height and width of the maze,and enter the button:");
		titleLabel.setFont(Font.font(18));
		titleLabel.setLayoutX(0);
		titleLabel.setLayoutY(0);

		widthLabel = new Label("Width:");
		widthLabel.setPrefSize(60, 30);
		widthLabel.setLayoutY(40);
		widthLabel.setLayoutX(20);

		heightLabel = new Label("Height:");
		heightLabel.setPrefSize(60, 30);
		heightLabel.setLayoutY(40);
		heightLabel.setLayoutX(200);

		createButton = new Button("Create Maze");
		createButton.setPrefSize(150, 30);
		createButton.setLayoutY(40);
		createButton.setLayoutX(400);

		widthText = new TextField();
		widthText.setPrefSize(100, 30);
		widthText.setLayoutY(40);
		widthText.setLayoutX(90);

		heightText = new TextField();
		heightText.setPrefSize(100, 30);
		heightText.setLayoutY(40);
		heightText.setLayoutX(270);

		getChildren().clear();
		getChildren().addAll(titleLabel, widthLabel, widthText, heightLabel, heightText, createButton);

	}
	
	/**
	 * �����¼�
	 */
	
	private void run(MazePane mazePane) {
		
		createButton.setOnAction(e->{		
			String width = new String(widthText.getText());
			String height = new String(heightText.getText());
			if(width==""||height=="")
			{
			System.out.println("Please enter  width and height");	
			}else {
				
				mazePane.getChildren().clear();
				mazePane.autoCreateMaze(new Integer(width),new Integer(height));
			}
		
		});
	}

}

/**
 * ���ļ�ѡ���Թ����ݵ����
 */
class FileCreatePane extends Pane{
	 
	FileChooser fileChooser = null;// �ļ�ѡ����
	
	Label title = null;
	
	TextField filePath = null;
	
	Button browseButton = null;
	
	Button createButton = null;
	
	public FileCreatePane(MazePane mazePane) {
		paint();
		run(mazePane);
	}
	
	/**
	 * ��ӡ���
	 */
	public void paint() {
		
		title = new Label("Choose your data file:");
		title.setFont(Font.font(18));
		title.setLayoutX(0);
		title.setLayoutY(0);
		
		browseButton = new Button("Browse");
		browseButton.setLayoutX(320);
		browseButton.setLayoutY(50);
		
		filePath = new TextField();
		filePath.setLayoutX(0);
		filePath.setLayoutY(50);
		filePath.setPrefWidth(300);
		filePath.setText("C:\\Users\\Song\\Desktop\\MazeData.txt");	
		fileChooser = new FileChooser();
		
		createButton = new Button("Create Maze");
		createButton.setLayoutX(450);
		createButton.setLayoutY(50);
		
		getChildren().clear();
		getChildren().addAll(title,filePath,browseButton,createButton);
	}
	
	/**
	 * �����¼�
	 * 
	 * @param mazePane
	 */
	public void run(MazePane mazePane) {
		browseButton.setOnAction(e -> {
			File file = fileChooser.showOpenDialog(new Stage());
			filePath.setText(file.getPath());

		});	
		
		createButton.setOnAction(e->{
			mazePane.getChildren().clear();
			mazePane.paint(new File(filePath.getText()));
		});
		
		
	}
	
	
}


