package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import services.Cell;

public class EditMapController {
	@FXML
	private TextField rowsText;

	@FXML
	private TextField columnsText;

	@FXML
	private Button generateButton;

	@FXML
	private GridPane mapGrid;

	@FXML
	private RadioButton wallRadio;

	@FXML
	private RadioButton dnoRadio;

	@FXML
	private RadioButton dncRadio;

	@FXML
	private RadioButton dwcRadio;

	@FXML
	private RadioButton dwoRadio;

	@FXML
	private Label infoLabel;

	private final ToggleGroup group = new ToggleGroup();
	private Cell[][] cells;
	private int w = 0;
	private int h = 0;

	public void init() {
		wallRadio.setToggleGroup(group);
		wallRadio.setSelected(true);

		dnoRadio.setToggleGroup(group);
		dncRadio.setToggleGroup(group);

		dwoRadio.setToggleGroup(group);
		dwcRadio.setToggleGroup(group);
	}

	public void generateEmptyMap(ActionEvent e) {
		h = Integer.valueOf(rowsText.getText());
		w = Integer.valueOf(columnsText.getText());
		if (h > 0 && w > 0) {
			cells = new Cell[h][w];

			for (int row = 0; row < h; row++) {
				for (int col = 0; col < w; col++) {
					Rectangle rect = new Rectangle();
					rect.setHeight(61);
					rect.setWidth(61);
					rect.setFill(Color.WHITE);
					cells[row][col] = Cell.EMP;
					Image wall = new Image(getClass().getResource("images/grass.png").toExternalForm());
					ImageView iv = new ImageView(wall);
					GridPane.setColumnIndex(iv, row);
					GridPane.setRowIndex(iv, col);
					mapGrid.getChildren().addAll(iv);
				}

			}
			cells[0][0] = Cell.IN;
			cells[h - 1][w - 1] = Cell.OUT;
			addGridEvent();
		}
		printCells();
		printInOut();
	}

	private void addGridEvent() {
		mapGrid.getChildren().forEach(item -> {
			item.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int row = GridPane.getRowIndex(item);
					int col = GridPane.getColumnIndex(item);
					System.out.println("Row " + row);
					System.out.println("Column " + col);
					if (wallRadio.isSelected()) {
						Image wall = new Image(getClass().getResource("images/wall1.png").toExternalForm());
						ImageView iv = new ImageView(wall);
						cells[row][col] = Cell.WLL;
						GridPane.setColumnIndex(iv, col);
						GridPane.setRowIndex(iv, row);
						mapGrid.getChildren().addAll(iv);
						printCells();
					}
					if (dnoRadio.isSelected()) {
						if (row >= 0 && row < h && col >= 0 && col < w) {
							System.err.println((row - 1) + " " + col + " " + cells[row - 1][col]);
							System.err.println((row + 1) + " " + col + " " + cells[row + 1][col]);
							if (cells[row - 1][col].equals(Cell.WLL) && cells[row + 1][col].equals(Cell.WLL)
									&& !cells[row][col].equals(Cell.IN) && !cells[row][col].equals(Cell.OUT)) {

								Image door = new Image(getClass().getResource("images/doorDNO.png").toExternalForm());
								ImageView iv = new ImageView(door);
								GridPane.setColumnIndex(iv, col);
								GridPane.setRowIndex(iv, row);
								mapGrid.getChildren().addAll(iv);
								cells[row][col] = Cell.DNO;
							}
						} else {
							infoLabel.setText("Les cases nord et sud ne sont pas des murs ou in out");
						}
					}
					if (dncRadio.isSelected()) {
						if (row >= 0 && row < h && col >= 0 && col < w && cells[row -1][col].equals(Cell.WLL)
								&& cells[row +1][col].equals(Cell.WLL) && !cells[row][col].equals(Cell.IN)
								&& !cells[row][col].equals(Cell.OUT)) {
							Image door = new Image(getClass().getResource("images/doorDNC.png").toExternalForm());
							ImageView iv = new ImageView(door);
							GridPane.setColumnIndex(iv, col);
							GridPane.setRowIndex(iv, row);
							mapGrid.getChildren().addAll(iv);
							cells[row][col] = Cell.DNC;
						} else {
							infoLabel.setText("Les cases nord et sud ne sont pas des murs ou in out");
						}

					}

					if (dwoRadio.isSelected()) {
						if (row >= 0 && row < h && col >= 0 && col < w && cells[row ][col+1].equals(Cell.WLL)
								&& cells[row][col+1].equals(Cell.WLL) && !cells[row][col].equals(Cell.IN)
								&& !cells[row][col].equals(Cell.OUT)) {
							Image door = new Image(getClass().getResource("images/doorDWO.png").toExternalForm());
							ImageView iv = new ImageView(door);
							GridPane.setColumnIndex(iv, col);
							GridPane.setRowIndex(iv, row);
							mapGrid.getChildren().addAll(iv);
							cells[row][col] = Cell.DWO;
						} else {
							infoLabel.setText("Les cases est et ouest ne sont pas des murs ou in out");
						}

					}

					if (dwcRadio.isSelected()) {
						if (row >= 0 && row < h && col >= 0 && col < w && cells[row][col+1].equals(Cell.WLL)
								&& cells[row][col-1].equals(Cell.WLL) && !cells[row][col].equals(Cell.IN)
								&& !cells[row][col].equals(Cell.OUT)) {
							Image door = new Image(getClass().getResource("images/door.png").toExternalForm());
							ImageView iv = new ImageView(door);
							GridPane.setColumnIndex(iv, col);
							GridPane.setRowIndex(iv, row);
							mapGrid.getChildren().addAll(iv);
							cells[row][col] = Cell.DWC;
						} else {
							infoLabel.setText("Les cases est et ouest ne sont pas des murs ou in out");
						}

					}

				}
			});
		});
	}

	public void printCells() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < h; i++) {
			string.append("\n");
			for (int j = 0; j < w; j++) {
				string.append("row " + i + " col " + j + " " + cells[i][j] + " | ");

			}
		}
		System.out.println(string.toString());
	}

	public void printInOut() {
		Image door = new Image(getClass().getResource("images/start.jpg").toExternalForm());
		ImageView iv = new ImageView(door);
		GridPane.setColumnIndex(iv, 0);
		GridPane.setRowIndex(iv, 0);
		mapGrid.getChildren().addAll(iv);

		Image door1 = new Image(getClass().getResource("images/finish.png").toExternalForm());
		ImageView iv1 = new ImageView(door1);
		GridPane.setColumnIndex(iv1, w - 1);
		GridPane.setRowIndex(iv1, h - 1);
		mapGrid.getChildren().addAll(iv1);
	}

}
