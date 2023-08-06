package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	Stage martstage = new Stage();
	Stage locstage = new Stage();
	Stage statstage = new Stage();

	String fileName = "myBtselem.csv";
	File f;

	TableView locTV = new TableView<>();
	TableView martTV = new TableView<>();
	TableView avlBackwardTV = new TableView<>();
	TableView avlLevelByLevelTV = new TableView<>();

	NodeDLL nodedll;
	NodeDLL current;

	NodeStack nodesll;

	DLL myDLL = new DLL();

	Martyrs mart;
	AVLTree1 avlTree1;
	AVLTree2 avlTree2;
	NodeAVL2 node2;

	public void start(Stage primaryStage) throws IOException {
		Stage stage = new Stage();

		Pane pane = new Pane();

		Text title = new Text("Third Project");
		pane.getChildren().add(title);
		title.setLayoutX(80);
		title.setLayoutY(100);
		title.setFont(new Font(45));
		title.setFill(Color.AZURE);
		title.setStroke(Color.LIGHTPINK);

		// f.createNewFile();
		FileChooser fileChooser = new FileChooser();
		Button loadFile = new Button("Load File");
		// loadFile.setPrefSize(120, 43);
		loadFile.setFont(new Font(18));

		loadFile.setOnAction(e -> {
			f = fileChooser.showOpenDialog(stage);

			try {
				ReadFile();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		Button locButton = new Button("Location Screen");
		// locButton.setPrefSize(120, 43);
		locButton.setFont(new Font(18));

		HBox firstButtons = new HBox(40);
		firstButtons.getChildren().addAll(loadFile, locButton);
		firstButtons.setLayoutX(55);
		firstButtons.setLayoutY(150);
		pane.getChildren().add(firstButtons);

		locButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					locationScreen();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.close();
			}
		});

		Scene scene = new Scene(pane, 400, 250);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	public void ReadFile() throws ParseException, FileNotFoundException {
		int a = 0;
		Scanner read = new Scanner(f);

		while (read.hasNext()) {
			String[] array = read.nextLine().split(",");

			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(array[3]);

			if (array[0] == "")
				array[0] = "No name";
			if (array[1] == "")
				array[1] = "-1";
			if (array[2] == "")
				array[2] = "palestine";
			if (array[3] == "")
				array[3] = "1995/01/01";
			if (array[4] == "")
				array[4] = "M";

			if (myDLL.isExisting(array[2])) {
				((Location) myDLL.get(array[2]).getElement()).getList()
						.addFirst(new Martyrs(array[0].trim(), Integer.parseInt(array[1]), array[2], date, array[4]));

			} else {
				myDLL.addSorted(new Location(array[2]));
				((Location) myDLL.get(array[2]).getElement()).getList()
						.addFirst(new Martyrs(array[0].trim(), Integer.parseInt(array[1]), array[2], date, array[4]));

			}

		}

	}

	public void saveFile() throws NumberFormatException, FileNotFoundException, ParseException {
		FileChooser filechooser = new FileChooser();
		File file = filechooser.showSaveDialog(new Stage());

		if (file != null) {
			try (PrintWriter writer = new PrintWriter(file)) {
				NodeDLL temp = myDLL.getfirst();
				for (int i = 0; i < myDLL.getSize(); i++) {
					NodeStack temp2 = ((Location) temp.getElement()).getList().getFirst();
					for (int j = 0; j < ((Location) myDLL.get(((Location) temp.getElement()).getLoc()).getElement())
							.getList().getSize(); j++) {
						while (temp2 != null) {
							writer.print(((Martyrs) temp2.getElement()).toString());
							temp2 = temp2.getNext();
						}
					}
					temp = temp.getNext();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void locationScreen() throws ParseException {

		locstage.setTitle("Location");
		Pane pane = new Pane();

		Button b = new Button("Save");
		b.setLayoutX(520);
		b.setLayoutY(440);
		pane.getChildren().add(b);
		b.setOnAction(e -> {
			try {
				saveFile();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		TextField search = new TextField();
		search.setPromptText("Search :");
		search.setLayoutX(22);
		search.setLayoutY(20);
		pane.getChildren().add(search);

		search.textProperty().addListener((observable, oldValue, newValue) -> {
			String searchText = newValue.trim().toLowerCase();
			if (!searchText.isEmpty()) {
				ObservableList<Location> filteredList = FXCollections.observableArrayList();
				for (Location p : (ObservableList<Location>) locTV.getItems()) {
					if (p.getLoc().toLowerCase().contains(searchText)) {
						filteredList.add(p);
					}
				}
				locTV.setItems(filteredList);
			} else {
				// Show all items if search text is empty
				showLocations();
			}
		});

		showLocations();
		pane.getChildren().add(locTV);

		Button insert = new Button("Insert");
		Button update = new Button("Update");
		Button delete = new Button("Delete");
		Button next = new Button("Next");
		Button prev = new Button("Prev");
		Button sear = new Button("Search");

		HBox but = new HBox(20);
		but.getChildren().addAll(prev, insert, update, delete, sear, next);
		pane.getChildren().add(but);
		but.setLayoutX(30);
		but.setLayoutY(420);

		current = myDLL.getfirst();
		Text cityName = new Text(current.getElement().toString());
		cityName.setLayoutX(200);
		cityName.setFont(new Font(20));
		cityName.setLayoutY(38);
		pane.getChildren().add(cityName);

		next.setOnAction(e -> {
			System.out.println(current.getElement().toString());
			current = current.getNext();
			showLocations();
			cityName.setText(current.getElement().toString());
		});

		prev.setOnAction(e -> {
			current = current.getPrev();
			showLocations();
			cityName.setText(current.getElement().toString());
		});

		TextField newloc = new TextField();
		newloc.setPromptText("Insert : ");

		insert.setOnAction(e -> {
			if (!newloc.getText().trim().isEmpty() && !(myDLL.isExisting(newloc.getText().trim()))) {
				myDLL.addSorted(new Location(newloc.getText().trim()));
			}
			newloc.clear();
			showLocations();
			myDLL.printList();
		});

		TextField updateloc = new TextField();
		updateloc.setPromptText("Update :");

		update.setOnAction(e -> {
			System.out.println("1111111111");
			Location l = (Location) locTV.getSelectionModel().getSelectedItem();
			if (l != null) {
				System.out.println("2222222222");
				if (!updateloc.getText().trim().isEmpty() && myDLL.isExisting(l.getLoc())) {
					System.out.println("333333333333333");
					SLL s = ((Location) myDLL.get(l.getLoc()).getElement()).getList();

					AVLTree1 a1 = ((Location) myDLL.get(l.getLoc()).getElement()).getAvl1();
					myDLL.addSorted(new Location(updateloc.getText().trim()));
					myDLL.printList();
					NodeStack temp = s.getFirst();
					for (int i = 0; i < s.getSize(); i++) {
						((Martyrs) temp.getElement()).setLocation(updateloc.getText().trim());
						temp = temp.getNext();
					}
					((Location) myDLL.get(updateloc.getText().trim()).getElement()).setList(s);
					((Location) myDLL.get(updateloc.getText().trim()).getElement()).setAvl1(a1);
					myDLL.remove(myDLL.get(l.getLoc()).getElement());

				}
			}
			updateloc.clear();
			showLocations();
		});

		delete.setOnAction(e -> {
			Location l = (Location) locTV.getSelectionModel().getSelectedItem();
			if (l != null) {
				if (myDLL.isExisting(l.getLoc())) {
					Location l2 = (Location) myDLL.get(l.getLoc()).getElement();
					myDLL.remove(l2);
				}
			}
			showLocations();
			myDLL.printList();
		});

		sear.setOnAction(e -> {
			String str = cityName.getText();
			locstage.hide();
			try {
				// to fill the avl tree 1
				avlTree1 = new AVLTree1();
				nodesll = ((Location) myDLL.get(str).getElement()).getList().getFirst();
				for (int i = 0; i < ((Location) myDLL.get(str).getElement()).getList().getSize(); i++) {
					avlTree1.insertNode(((Martyrs) nodesll.getElement()));
					nodesll = nodesll.getNext();
				}

				martyrsScreen(str);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		VBox Vbut = new VBox(20);
		Vbut.getChildren().addAll(newloc, updateloc);
		Vbut.setLayoutX(380);
		Vbut.setLayoutY(70);
		pane.getChildren().add(Vbut);

		Scene scene = new Scene(pane, 600, 500);
		locstage.setScene(scene);
		locstage.setResizable(false);
		locstage.show();
	}

	public void showLocations() {
		locTV.refresh();
		locTV.getItems().clear();
		locTV.getColumns().clear();
		locTV.setEditable(true);
		locTV.setPrefSize(298, 300);
		locTV.setLayoutX(20);
		locTV.setLayoutY(60);

		TableColumn<Location, String> loc = new TableColumn<Location, String>("City");
		loc.setPrefWidth(300);
		loc.setCellValueFactory(new PropertyValueFactory<Location, String>("loc"));

		locTV.getColumns().add(loc);

		nodedll = myDLL.getfirst();
		for (int i = 0; i < myDLL.getSize(); i++) {
			Location location = (Location) nodedll.getElement();
			locTV.getItems().add(location);
			nodedll = nodedll.getNext();
		}

		locTV.refresh();

	}

	public void martyrsScreen(String str) throws ParseException {

		martstage.setTitle("Martyrs");
		Pane pane = new Pane();

		showMartyrs(str);
		pane.getChildren().add(martTV);

		TextField search = new TextField();
		search.setPromptText("Search :");
		search.setLayoutX(50);
		search.setLayoutY(20);
		pane.getChildren().add(search);

		search.textProperty().addListener((observable, oldValue, newValue) -> {
			String searchText = newValue.trim().toLowerCase();
			if (!searchText.isEmpty()) {
				ObservableList<Martyrs> filteredList = FXCollections.observableArrayList();
				for (Martyrs p : (ObservableList<Martyrs>) martTV.getItems()) {
					if (p.getName().toLowerCase().contains(searchText)) {
						filteredList.add(p);
					}
				}
				martTV.setItems(filteredList);
			} else {
				try {
					showMartyrs(str);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		Button back = new Button("Back");
		Button insert = new Button("Insert");
		Button update = new Button("Update");
		Button deletem = new Button("Delete");
		Button stat = new Button("Statistics");

		HBox martButtons = new HBox(30);
		pane.getChildren().add(martButtons);
		martButtons.getChildren().addAll(back, insert, update, deletem, stat);
		martButtons.setLayoutX(60);
		martButtons.setLayoutY(420);

		back.setOnAction(e -> {
			martstage.close();
			locstage.show();
		});

		insert.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage addMartStage = new Stage();
				Pane addMartPane = new Pane();

				Label name = new Label("Name");
				name.setFont(new Font(17));
				TextField name_ = new TextField();
				name_.setPromptText("Name :");

				Label age = new Label("Age");
				age.setFont(new Font(17));
				TextField age_ = new TextField();
				age_.setPromptText("Age :");

				Label dateOfDead = new Label("DateOfDead");
				dateOfDead.setFont(new Font(17));
				TextField dateOfDead_ = new TextField();
				dateOfDead_.setPromptText("Date Of Dead :");

				Label gender = new Label("Gender");
				gender.setFont(new Font(17));
				TextField gender_ = new TextField();
				gender_.setPromptText("M/F :");

				Button aadd = new Button("Add");
				aadd.setFont(new Font(17));
				aadd.setLayoutX(50);
				aadd.setLayoutY(350);

				VBox martData = new VBox(10);
				addMartPane.getChildren().add(martData);
				martData.setLayoutX(50);
				martData.setLayoutY(50);
				martData.setPrefSize(280, 300);

//-------------------------------add new martyrs-------------------------------
				aadd.setOnAction(e -> {
					if (!name_.getText().isEmpty() && !age_.getText().isEmpty() && !dateOfDead_.getText().isEmpty()
							&& !gender_.getText().isEmpty()) {
						Date d = null;
						try {
							d = new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDead_.getText().trim());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						mart = new Martyrs(name_.getText().trim(), Integer.parseInt(age_.getText().trim()), str, d,
								gender_.getText().trim());

						if (((Location) myDLL.get(str).getElement()).getList().isExisting(mart) == false) {
							((Location) myDLL.get(str).getElement()).getList().addFirst(mart);
							((Location) myDLL.get(str).getElement()).getList().printList();
							System.out.println("===========");

							avlTree1.insertNode(mart);
							addMartStage.close();
							try {
								showMartyrs(str);
								//System.out.println("avl =  " + avlTree1.toString());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});

				martData.getChildren().addAll(name, name_, age, age_, dateOfDead, dateOfDead_, gender, gender_, aadd);

				Scene addMartScene = new Scene(addMartPane, 400, 500);
				addMartStage.setScene(addMartScene);
				addMartStage.setResizable(false);
				addMartStage.show();
			}
		});

		// -------------------update mart info.-------------------------------------
		update.setOnAction(e -> {
			System.out.println("update");
			Martyrs m = (Martyrs) martTV.getSelectionModel().getSelectedItem();
			if (m != null) {
				NodeAVL1 node = avlTree1.findNode(m);
				if (node != null) {
					

					/////////////

					Stage addMartStage = new Stage();
					Pane addMartPane = new Pane();

					Label name = new Label("Name");
					name.setFont(new Font(17));
					TextField name_ = new TextField();
					name_.setText(node.getData().getName());

					Label age = new Label("Age");
					age.setFont(new Font(17));
					TextField age_ = new TextField();
					age_.setText(node.getData().getAge() + "");

					Label dateOfDead = new Label("DateOfDead");
					dateOfDead.setFont(new Font(17));
					TextField dateOfDead_ = new TextField();
					dateOfDead_.setText((node.getData().getDateOfDead()));

					Label gender = new Label("Gender");
					gender.setFont(new Font(17));
					TextField gender_ = new TextField();
					gender_.setText(node.getData().getGender());

					Button uupp = new Button("Update");
					uupp.setFont(new Font(17));
					uupp.setLayoutX(50);
					uupp.setLayoutY(350);

					VBox martData = new VBox(10);
					addMartPane.getChildren().add(martData);
					martData.setLayoutX(50);
					martData.setLayoutY(50);
					martData.setPrefSize(280, 300);

					// -------------------------------Update martyrs-------------------------------
					uupp.setOnAction(event -> {
						if (!name_.getText().isEmpty() && !age_.getText().isEmpty() && !dateOfDead_.getText().isEmpty()
								&& !gender_.getText().isEmpty()) {
							Date d = null;
							try {
								d = new SimpleDateFormat("yyyy/MM/dd").parse(dateOfDead_.getText().trim());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							mart = new Martyrs(name_.getText().trim(), Integer.parseInt(age_.getText().trim()), str, d,
									gender_.getText().trim());

							if (((Location) myDLL.get(str).getElement()).getList().isExisting(mart) == false) {

								((Location) myDLL.get(str).getElement()).getList().addFirst(mart);
								System.out.println("===========");

								avlTree1.insertNode(mart);
								addMartStage.close();
								try {
									showMartyrs(str);
									System.out.println("avl =  " + avlTree1.toString());
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						}
						
						avlTree1.deleteNode(m);
						((Location) myDLL.get(str).getElement()).getList().remove(m);
						System.out.println(avlTree1.toString());
						((Location) myDLL.get(str).getElement()).getList().printList();
						martTV.getItems().remove(m);
					});

					martData.getChildren().addAll(name, name_, age, age_, dateOfDead, dateOfDead_, gender, gender_,
							uupp);

					Scene addMartScene = new Scene(addMartPane, 400, 500);
					addMartStage.setScene(addMartScene);
					addMartStage.setResizable(false);
					addMartStage.show();
				}
			}

		});

//----------------delete mart info.------------------------------------
		deletem.setOnAction(event -> {
			Martyrs m = (Martyrs) martTV.getSelectionModel().getSelectedItem();
			if (m != null) {
				NodeAVL1 node = avlTree1.findNode(m);
				if (node != null) {
					avlTree1.deleteNode(m);
					((Location) myDLL.get(str).getElement()).getList().remove(m);
					// System.out.println(avlTree1.toString());
					((Location) myDLL.get(str).getElement()).getList().printList();
					martTV.getItems().remove(m);
				}
			}
		});

		stat.setOnAction(e -> {
			// To fill the AVL tree 2
			
				avlTree2 = new AVLTree2();
			
				nodesll = ((Location) myDLL.get(str).getElement()).getList().getFirst();
				for (int i = 0; i < ((Location) myDLL.get(str).getElement()).getList().getSize(); i++) {
					DateStack dateStack = null;
					try {
						dateStack = new DateStack(new SimpleDateFormat("MM/dd/yyyy")
								.parse(((Martyrs) nodesll.getElement()).getDateOfDead()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (avlTree2.findNode(dateStack) == null) {
						avlTree2.insertNode(dateStack);
					}
					nodesll = nodesll.getNext();
				}

				// To fill the stack in AVL tree 2
				nodesll = ((Location) myDLL.get(str).getElement()).getList().getFirst();
				for (int i = 0; i < ((Location) myDLL.get(str).getElement()).getList().getSize(); i++) {
					Martyrs martyr = (Martyrs) nodesll.getElement();
					DateStack dateStack = null;
					try {
						dateStack = new DateStack(new SimpleDateFormat("MM/dd/yyyy").parse(martyr.getDateOfDead()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					NodeAVL2 node2 = avlTree2.findNode(dateStack);
					if (node2 != null && dateStack.getDate().equals(((DateStack) node2.getData()).getDate())) {
						((DateStack) node2.getData()).getStack().addLast(martyr);
					}
					((DateStack) node2.getData()).getStack().printList();
					nodesll = nodesll.getNext();
				}

				try {
					statScreen(str);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		});

		Scene scene = new Scene(pane, 600, 500);
		martstage.setScene(scene);
		martstage.setResizable(false);
		martstage.show();
	}

	public void showMartyrs(String s) throws ParseException {
		martTV.refresh();
		martTV.getItems().clear();
		martTV.getColumns().clear();
		martTV.setEditable(true);
		martTV.setLayoutX(50);
		martTV.setLayoutY(70);
		martTV.setPrefSize(480, 300);
		TableColumn<Martyrs, String> name = new TableColumn<Martyrs, String>("Name");
		name.setPrefWidth(180);

		TableColumn<Martyrs, Integer> age = new TableColumn<Martyrs, Integer>("Age");
		age.setPrefWidth(90);

		TableColumn<Martyrs, String> dateOfDead = new TableColumn<Martyrs, String>("Date");
		dateOfDead.setPrefWidth(150);

		TableColumn<Martyrs, String> gender = new TableColumn<Martyrs, String>("Gander");
		gender.setPrefWidth(80);

		name.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Martyrs, Integer>("age"));
		dateOfDead.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("dateOfDead"));
		gender.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("gender"));

		martTV.getColumns().addAll(name, age, dateOfDead, gender);

		// to fill table view
		martTV.getItems().clear();
		avlTree1.insertIntoMartTable(avlTree1.getRoot(), martTV);

		martTV.refresh();

	}

	public void statScreen(String str) throws ParseException {
		statstage.setTitle("Statistics( " + str + " )");
		Pane pane = new Pane();

		Text numOFmart = new Text(
				"Number-Of Martyrs : " + ((Location) myDLL.get(str).getElement()).getList().getSize());
		pane.getChildren().add(numOFmart);
		numOFmart.setLayoutX(20);
		numOFmart.setLayoutY(40);
		numOFmart.setFont(new Font(20));

		Text avl1 = new Text("AVL 1 Level-By-Level");
		avl1.setFont(new Font(20));
		pane.getChildren().add(avl1);
		avl1.setLayoutX(410);
		avl1.setLayoutY(90);

		Text avl2 = new Text("AVL 2 Backward");
		avl2.setFont(new Font(20));
		pane.getChildren().add(avl2);
		avl2.setLayoutX(20);
		avl2.setLayoutY(90);

		MartyrsBackward(str);
		pane.getChildren().add(avlBackwardTV);

		MartyrsLevelByLevel(str);
		pane.getChildren().add(avlLevelByLevelTV);

		Text avl1H = new Text("The height of the 1st AVL tree = " + avlTree1.getHeigh());
		avl1H.setFont(new Font(20));
		pane.getChildren().add(avl1H);
		avl1H.setLayoutX(410);
		avl1H.setLayoutY(430);

		Text avl2H = new Text("The height of the 2nd AVL tree = " + avlTree2.getHeigh());
		avl2H.setFont(new Font(20));
		pane.getChildren().add(avl2H);
		avl2H.setLayoutX(20);
		avl2H.setLayoutY(430);

		Text maxsize = new Text();
		maxsize.setFont(new Font(20));
		pane.getChildren().add(maxsize);
		maxsize.setLayoutX(20);
		maxsize.setLayoutY(500);
		
		if(avlTree2.getRoot() != null)
			maxsize.setText("The date that had the maximum number of martyrs is  " + avlTree2.maxSizeStack().getData().getDate());
		else 
			maxsize.setText("The date that had the maximum number of martyrs is 00/00/0000");

		Scene scene = new Scene(pane, 800, 600);
		statstage.setScene(scene);
		statstage.setResizable(false);
		statstage.show();

	}

	public void MartyrsBackward(String s) throws ParseException {
		avlBackwardTV.refresh();
		avlBackwardTV.getItems().clear();
		avlBackwardTV.getColumns().clear();
		avlBackwardTV.setEditable(true);
		avlBackwardTV.setLayoutX(20);
		avlBackwardTV.setLayoutY(120);
		avlBackwardTV.setPrefSize(370, 270);
		TableColumn<Martyrs, String> name = new TableColumn<Martyrs, String>("Name");
		name.setPrefWidth(130);

		TableColumn<Martyrs, Integer> age = new TableColumn<Martyrs, Integer>("Age");
		age.setPrefWidth(70);

		TableColumn<Martyrs, String> dateOfDead = new TableColumn<Martyrs, String>("Date");
		dateOfDead.setPrefWidth(120);

		TableColumn<Martyrs, String> gender = new TableColumn<Martyrs, String>("Gander");
		gender.setPrefWidth(70);

		name.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Martyrs, Integer>("age"));
		dateOfDead.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("dateOfDead"));
		gender.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("gender"));

		avlBackwardTV.getColumns().addAll(name, age, dateOfDead, gender);

		// to fill table view
		avlBackwardTV.getItems().clear();
		System.out.println(s);
		if (avlTree2 == null) {

		} else
			avlTree2.avl2Backward(avlTree2.getRoot(), avlBackwardTV);
		System.out.println("THE STACK IS ");
		avlBackwardTV.refresh();

	}

	public void MartyrsLevelByLevel(String s) throws ParseException {
		avlLevelByLevelTV.refresh();
		avlLevelByLevelTV.getItems().clear();
		avlLevelByLevelTV.getColumns().clear();
		avlLevelByLevelTV.setEditable(true);
		avlLevelByLevelTV.setLayoutX(410);
		avlLevelByLevelTV.setLayoutY(120);
		avlLevelByLevelTV.setPrefSize(370, 270);
		TableColumn<Martyrs, String> name = new TableColumn<Martyrs, String>("Name");
		name.setPrefWidth(130);

		TableColumn<Martyrs, Integer> age = new TableColumn<Martyrs, Integer>("Age");
		age.setPrefWidth(70);

		TableColumn<Martyrs, String> dateOfDead = new TableColumn<Martyrs, String>("Date");
		dateOfDead.setPrefWidth(120);

		TableColumn<Martyrs, String> gender = new TableColumn<Martyrs, String>("Gander");
		gender.setPrefWidth(70);

		name.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Martyrs, Integer>("age"));
		dateOfDead.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("dateOfDead"));
		gender.setCellValueFactory(new PropertyValueFactory<Martyrs, String>("gender"));

		avlLevelByLevelTV.getColumns().addAll(name, age, dateOfDead, gender);

		// to fill table view
		avlLevelByLevelTV.getItems().clear();
		avlTree1.avl1LevelByLevel(avlTree1.getRoot(), avlLevelByLevelTV);
		System.out.println(avlTree1.getHeigh());

		avlLevelByLevelTV.refresh();

	}
}
