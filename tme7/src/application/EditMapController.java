package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
					rect.setHeight(25);
					rect.setWidth(25);
					rect.setFill(Color.WHITE);
					cells[row][col] = Cell.EMP;
					GridPane.setColumnIndex(rect, row);
					GridPane.setRowIndex(rect, col);
					mapGrid.getChildren().addAll(rect);
				}
			}
			addGridEvent();
		}
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
						((Rectangle) item).setFill(Color.BLACK);
						cells[row][col] = Cell.WLL;
					}
					if (dnoRadio.isSelected()) {
						if (row > 1 && row < h && col > 1 && col < w && cells[row - 1][col].equals(Cell.WLL)
								&& cells[row + 1][col].equals(Cell.WLL)) {
							Line door = new Line(0.0f, 0.0f, 23.0f, 23.0f);
							door.setStroke(Color.BROWN);
							GridPane.setRowIndex(door, row);
							GridPane.setColumnIndex(door, col);
							mapGrid.getChildren().addAll(door);
						} else {
							infoLabel.setText("Les cases nord et sud ne sont pas des murs");
						}
					}
					if (dncRadio.isSelected()) {
						if (row > 1 && row < h && col > 1 && col < w && cells[row - 1][col].equals(Cell.WLL)
								&& cells[row + 1][col].equals(Cell.WLL)) {
							Line door = new Line(0.0f, 0.0f, 0.0f, 23.0f);
							door.setStroke(Color.AQUA);
							GridPane.setRowIndex(door, row);
							GridPane.setColumnIndex(door, col);
							mapGrid.getChildren().addAll(door);
						} else {
							infoLabel.setText("Les cases nord et sud ne sont pas des murs");
						}

					}
					
					if (dwoRadio.isSelected()) {
						if (row > 1 && row < h && col > 1 && col < w && cells[row][col +1].equals(Cell.WLL)
								&& cells[row][col+1].equals(Cell.WLL)) {
							Line door = new Line(0.0f,0.0f,23.0f,23.0f);
							door.setStroke(Color.AQUA);
							GridPane.setRowIndex(door, row);
							GridPane.setColumnIndex(door, col);
							mapGrid.getChildren().addAll(door);
						} else {
							infoLabel.setText("Les cases est et ouest ne sont pas des murs");
						}

					}
					
					if (dwcRadio.isSelected()) {
						if (row > 1 && row < h && col > 1 && col < w && cells[row][col+1].equals(Cell.WLL)
								&& cells[row][col+1].equals(Cell.WLL)) {
							Line door = new Line(0.0f,0.0f,23.0f,0.0f);
							door.setStroke(Color.AQUA);
							GridPane.setRowIndex(door, row);
							GridPane.setColumnIndex(door, col);
							mapGrid.getChildren().addAll(door);
						} else {
							infoLabel.setText("Les cases est et ouest ne sont pas des murs");
						}

					}

				}
			});
		});
	}

}
