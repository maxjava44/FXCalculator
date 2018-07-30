/*Copyright (C) 2018  Maximilian Gilsoul
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.*/

package FXCalculator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import FXCalculator.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import net.sourceforge.jdistlib.rng.RandomWELL44497b;

public class Main extends Application {
	final ToggleGroup group = new ToggleGroup();
	RadioButton radiobuttonint = new RadioButton("Integer"); // RadioButtons
	RadioButton radiobuttondouble = new RadioButton("Double");
	RadioButton radiobuttonlong = new RadioButton("Long");
	AnchorPane dialogPane = new AnchorPane(); // Anker-Layouts
	AnchorPane root = new AnchorPane();
	Stage dialogStage = new Stage(); // Szenen
	Scene scene = new Scene(root, 270, 300);
	Scene dialogScene = new Scene(dialogPane, 600, 200);
	Button buttongleich = new Button("=", 30.0, 30.0, 30.0); // Buttons
	Button buttonplus = new Button("+", 30.0, 30.0, 30.0);
	Button button0 = new Button("0", 30.0, 30.0, 30.0);
	Button button1 = new Button("1", 30.0, 30.0, 30.0);
	Button button2 = new Button("2", 30.0, 30.0, 30.0);
	Button button3 = new Button("3", 30.0, 30.0, 30.0);
	Button button4 = new Button("4", 30.0, 30.0, 30.0);
	Button button5 = new Button("5", 30.0, 30.0, 30.0);
	Button button6 = new Button("6", 30.0, 30.0, 30.0);
	Button button7 = new Button("7", 30.0, 30.0, 30.0);
	Button button8 = new Button("8", 30.0, 30.0, 30.0);
	Button button9 = new Button("9", 30.0, 30.0, 30.0);
	Button buttonminus = new Button("-", 30.0, 30.0, 30.0);
	Button buttongeteilt = new Button("/", 30.0, 30.0, 30.0);
	Button buttonmal = new Button("*", 30.0, 30.0, 30.0);
	Button buttonrandom = new Button("R", 30.0, 30.0, 30.0);
	Button buttonAC = new Button("D", 30.0, 30.0, 30.0);
	Button buttonPI = new Button("π", 30.0, 30.0, 30.0);
	Button buttondialog = new Button("Bestätigen", 90.0, 30.0, 30.0);
	Button buttonpunkt = new Button(".", 30.0, 30.0, 30.0);
	Button buttonroot = new Button("√", 30.0, 30.0, 30.0);
	Label lbl = new Label(""); // Textfelder
	Label lbldialog = new Label(
			"Geben sie die maximale Zahl des Zugfallsgeneratoren ein(Bitte geben sie nur Integer ein)");
	Rectangle rect = new Rectangle();
	VBox vertikaleButtonBox = new VBox(); // Vertikale Boxen
	VBox vertikaleButtonBox2 = new VBox();
	VBox vertikaleButtonBox3 = new VBox();
	VBox vertikaleButtonBox4 = new VBox();
	HBox buttonboxes = new HBox(); // Horionzentale Box
	VBox[] vertikaleButtonBoxes = { vertikaleButtonBox, vertikaleButtonBox2, vertikaleButtonBox3, vertikaleButtonBox4 };
	static RandomWELL44497b generator = new RandomWELL44497b(); // Zufallsgenerator
	TextField feld = new TextField();
	int zahlenart = 0;
	FXCalculator.Calculator calculator = new Calculator();
	int index = 1;
	int operation = 0;
	double ergebnis = 0.0;
	@Override
	public void start(Stage primaryStage) {
		try {
			// Oberfläche
			makeUI();
			primaryStage.setTitle("Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Logik
		buttonroot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(calculator.getnumber(index) > 0)
				{
					lbl.setText(String.valueOf(Math.sqrt(calculator.getnumber(index))));
					calculator.setnumber(String.valueOf(Math.sqrt(calculator.getnumber(index))), index);
				}
			}
		});
		
		buttonPI.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "3.14159");
				calculator.addnumber("3.14159", index);
			}
		});
		buttongleich.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(index == 2)
				{
					ergebnis = calculator.calculate(operation);
					lbl.setText("" + ergebnis);
					reset();
					calculator.addnumber(String.valueOf(ergebnis), index);
					ergebnis = 0.0;
				}
			}
		});
		buttonAC.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText("");
				reset();
			}
		});
		buttonpunkt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + ".");
				calculator.addnumber(".", index);
			}
		});
		button0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "0");
				calculator.addnumber("0", index);
			}
		});
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "1");
				calculator.addnumber("1", index);
			}
		});
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "2");
				calculator.addnumber("2", index);
			}
		});
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "3");
				calculator.addnumber("3", index);
			}
		});
		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "4");
				calculator.addnumber("4", index);
			}
		});
		button5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "5");
				calculator.addnumber("5", index);
			}
		});
		button6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "6");
				calculator.addnumber("6", index);
			}
		});
		button7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "7");
				calculator.addnumber("7", index);
			}
		});
		button8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "8");
				calculator.addnumber("8", index);
			}
		});
		button9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + "9");
				calculator.addnumber("9", index);
			}
		});
		buttonplus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + " + ");
				operation = 1;
				index = 2;
			}
		});
		buttonminus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + " - ");
				operation = 2;
				index = 2;
			}
		});
		buttonmal.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + " * ");
				operation = 3;
				index = 2;
			}
		});
		buttongeteilt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lbl.setText(lbl.getText() + " / ");
				operation = 4;
				index = 2;
			}
		});
		buttonrandom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AnchorPane.setTopAnchor(feld, 30.0);
				AnchorPane.setTopAnchor(lbldialog, 10.0);
				AnchorPane.setTopAnchor(radiobuttonint, 60.0);
				AnchorPane.setTopAnchor(radiobuttondouble, 80.0);
				AnchorPane.setTopAnchor(radiobuttonlong, 100.0);
				radiobuttonint.setUserData("Integer");
				radiobuttondouble.setUserData("Double");
				radiobuttonlong.setUserData("Long");
				AnchorPane.setBottomAnchor(buttondialog, 5.0);
				AnchorPane.setRightAnchor(buttondialog, 2.0);
				dialogStage.setScene(dialogScene);
				dialogStage.centerOnScreen();
				dialogStage.show();
			}
		});
		buttondialog.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					switch (zahlenart) {
					case 1:
						int n = Integer.parseInt(feld.getText());
					        int numberint = generator.nextInt(n);
						calculator.setnumber("", index);
						calculator.addnumber(String.valueOf(numberint), index);
						lbl.setText(lbl.getText() + numberint);
						break;
					case 2:
						double ndouble = Double.parseDouble(feld.getText());
						double numberdouble = generator.nextDouble() * ndouble;
						calculator.setnumber("", index);
						calculator.addnumber(String.valueOf(numberdouble), index);
						lbl.setText(lbl.getText() + numberdouble);
						break;
					case 3:
						long nlong = Long.parseLong(feld.getText());
						long numberlong = generator.nextLong(nlong);
						calculator.setnumber("", index);
						calculator.addnumber(String.valueOf(numberlong), index);
						lbl.setText(lbl.getText() + numberlong);
						break;
					default:
						int n2 = Integer.parseInt(feld.getText());
						int numberint2 = generator.nextInt(n2);
						calculator.setnumber("", index);
						calculator.addnumber(String.valueOf(numberint2), index);
						lbl.setText(lbl.getText() + numberint2);
						break;
					}
					if (group.getSelectedToggle() == null) {
						lbldialog.setText("Du musst einen Zahlentyp auswählen");
					} else {
						dialogStage.close();
					}
				} catch (NumberFormatException e1) {
					lbldialog.setText("Die Zahl ist kein Integer");
				}
			}
		});
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() == null) {
					lbldialog.setText("Du musst einen Zahlentyp auswählen");
				} else {
					if (group.getSelectedToggle().getUserData().toString() == "Integer") {
						zahlenart = 1;
					} else if (group.getSelectedToggle().getUserData().toString() == "Double") {
						zahlenart = 2;
					} else if (group.getSelectedToggle().getUserData().toString() == "Long") {
						zahlenart = 3;
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	void makeVBoxesReady(VBox[] vBoxes) {
		for (VBox box : vBoxes) {
			box.setSpacing(5);
			box.setPadding(new Insets(0, 10, 5, 10));
			box.setPrefWidth(30.0);
		}
	}

	void settoggleGroup(ToggleGroup g, RadioButton... radios) {
		for (RadioButton radio : radios) {
			radio.setToggleGroup(g);
		}
	}

	void setCSSStyle(String style, Node... nodes) {
		for (Node node : nodes) {
			node.getStyleClass().add(style);
		}
	}

	void makeUI() {
		settoggleGroup(group, radiobuttonint, radiobuttondouble, radiobuttonlong);
		dialogPane.getChildren().addAll(radiobuttondouble, radiobuttonint, radiobuttonlong, feld, lbldialog,
				buttondialog);
		makeVBoxesReady(vertikaleButtonBoxes);
		vertikaleButtonBoxes[0].getChildren().addAll(buttonrandom, buttonmal, buttonminus, buttonplus, buttongleich);
		vertikaleButtonBoxes[1].getChildren().addAll(buttonAC, buttongeteilt, button9, button6, button3);
		vertikaleButtonBoxes[2].getChildren().addAll(buttonpunkt, buttonPI, button8, button5, button2);
		vertikaleButtonBoxes[3].getChildren().addAll(buttonroot, button7, button4, button1, button0);
		buttonboxes.setSpacing(5);
		buttonboxes.setPadding(new Insets(0, 0, 10, 10));
		buttonboxes.getChildren().addAll(vertikaleButtonBox4, vertikaleButtonBox3, vertikaleButtonBox2,
				vertikaleButtonBox);
		AnchorPane.setBottomAnchor(buttonboxes, 5.0);
		AnchorPane.setRightAnchor(buttonboxes, 5.0);
		setCSSStyle("buttonStyle2", buttonPI, buttonAC, buttongeteilt, buttonmal, buttonminus, buttonplus, buttongleich,
				buttonrandom, buttonpunkt, buttonroot);
		AnchorPane.setTopAnchor(lbl, 22.0);
		AnchorPane.setRightAnchor(lbl, 17.0);
		rect.getStyleClass().add("rect");
		AnchorPane.setTopAnchor(rect, 10.0);
		AnchorPane.setRightAnchor(rect, 10.0);
		rect.setWidth(250.0);
		rect.setHeight(50.0);
		rect.setArcHeight(15.0);
		rect.setArcWidth(15.0);
		root.getChildren().add(rect);
		root.getChildren().add(lbl);
		root.getChildren().add(buttonboxes);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
	}

	void reset() {
		calculator.reset();
		index = 1;
		operation = 0;
	}
}
