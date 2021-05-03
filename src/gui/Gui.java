package gui;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import models.CustomerPhone;
import models.Drug;
import models.Employee;
import models.Factory;
import services.CustomerPhoneService;
import services.CustomerService;
import services.DrugService;
import services.EmployeeService;
import services.FactoryServices;
import services.ReportsGUI;
import services.SalesService;

public class Gui extends Application {

	/**
	 * 
	 * @param args
	 * 
	 */
	// Label object
	Label DrugIDLabel = new Label("Drug ID: ");
	Label FactoryIDLabel = new Label("Factory ID: ");
//		Label EmployeeIDLabel = new Label("Employee ID: ");
	Label DrugNameLabel = new Label("Drug Name: ");
	Label DrugNumberLabel = new Label("Drug Number: ");
	Label DrugDoseLabel = new Label("Drug Dose: ");
	Label DrugDiscripeLabel = new Label("Drug Discripe: ");
	Label DrugQuanintyLabel = new Label("Drug Quaninty: ");
	Label DrugSellingLabel = new Label("Drug Selling Price: ");
	Label DrugBuyingLabel = new Label("Drug Buying Price: ");
	Label DrugProductionLabel = new Label("Drug Production Date: ");
	Label DrugEndLabel = new Label("Drug End Date: ");
	Label DrugEntityName = new Label("Drug Table ");

	// TextField object
	TextField DrugIDField = new TextField();
	TextField FactoryIDField = new TextField();
//		TextField EmployeeIDField = new TextField();
	TextField DrugNameField = new TextField();
	TextField DrugNumberField = new TextField();
	TextField DrugDoseField = new TextField();
	TextField DrugQuanintyField = new TextField();
	TextField DrugDiscripeField = new TextField();
	TextField DrugProductionField = new TextField();
	TextField DrugEndField = new TextField();
	TextField DrugSellingField = new TextField();
	TextField DrugBuyingField = new TextField();
	TextField DrugFreeField = new TextField();

	// Button object
	Button InsertButton = new Button("insert");
	Button DeleteButton = new Button("Delete");
	Button SearchButton = new Button("Search");
	Button ShowButton = new Button("Show Data");
	Button ReturnButton = new Button("Return");

	// TextArea object
	TextArea Drugta = new TextArea();

	// Table object
	TableView table2 = new TableView();

	ObservableList<Drug> data2 = FXCollections.observableArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage start) throws Exception {

		// stage 1:login window
		start.setTitle("welcome");
		start.getIcons().add(new Image("file:icon.jpg"));

		Label l = new Label(" ELZAIN-PHARMACY");
		l.setTextFill(Color.WHITE);
		l.setPrefWidth(500);
		l.setFont(new Font("impact", 50));

		Label l1 = new Label("User Name ");
		l1.setTextFill(Color.BLACK);
		l1.setFont(new Font("Arial Rounded MT Bold", 22));
		TextField t1 = new TextField("root");

		Label l2 = new Label("Password ");
		l2.setTextFill(Color.BLACK);

		l2.setFont(new Font("Arial Rounded MT Bold", 22));

		PasswordField p = new PasswordField();
		p.setText("root@12345");

		HBox h1 = new HBox(10);
		h1.getChildren().addAll(l1, t1);

		HBox h2 = new HBox(23);
		h2.getChildren().addAll(l2, p);

		Button b = new Button("log in");// login in pharamacy data base App
		b.setPrefWidth(130);
		b.setFont(new Font("Arial Black", 15));

		Button b2 = new Button("log out");// close pharmacy data base App
		b2.setPrefWidth(130);
		b2.setFont(new Font("Arial Black", 15));
		b2.setOnAction(e -> {
			System.exit(0);
		});

		HBox h3 = new HBox(10);
		h3.getChildren().addAll(b, b2);
		h3.setAlignment(Pos.CENTER);

		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);

		l.setAlignment(Pos.CENTER);

		VBox v1 = new VBox(17);
		v1.getChildren().addAll(l, h1, h2, h3);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10));
		v1.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		Image im2 = new Image("file:ph.jpg");

		// create a background image
		BackgroundImage backgroundimage2 = new BackgroundImage(im2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		// create Background
		Background background2 = new Background(backgroundimage2);

		// set background
		v1.setBackground(background2);

		Scene sc1 = new Scene(v1, 600, 600);

		start.setScene(sc1);
		start.show();

		/**
		 * stage2:parts of pharamacy Employee Customer Factory Drug
		 */

		Stage stage2 = new Stage();
		Button employee = new Button("EMPLOYEES");
		employee.setPrefSize(200, 80);
		employee.setTextFill(Color.GREEN);
		employee.setFont(new Font("Bodoni MT Black", 15));

		Button customers = new Button("CUSTOMERS");
		customers.setFont(new Font("Bodoni MT Black", 15));
		customers.setPrefSize(200, 80);
		customers.setTextFill(Color.BLUE);
		customers.setOnAction(e -> {
			CustomerService.main(null);

		});

		Button factories = new Button("FACTORIES");
		factories.setFont(new Font("Bodoni MT Black", 15));
		factories.setPrefSize(200, 80);
		factories.setTextFill(Color.DARKGOLDENROD);

		Button drugs = new Button("DRUGS");
		drugs.setPrefSize(200, 80);
		drugs.setFont(new Font("Bodoni MT Black", 15));
		drugs.setTextFill(Color.DARKCYAN);

		Button cusPhone = new Button("Customer Phone");
		cusPhone.setPrefSize(200, 80);
		cusPhone.setFont(new Font("Bodoni MT Black", 15));
		cusPhone.setTextFill(Color.BLACK);

		Button sales = new Button("Sales");
		sales.setPrefSize(200, 80);
		sales.setFont(new Font("Bodoni MT Black", 15));
		sales.setTextFill(Color.BLUE);
		
		
		Button report=new Button("Report");
		report.setPrefSize(200, 80);
		report.setFont(new Font("Bodoni MT Black", 15));
		report.setTextFill(Color.BLUE);
		report.setOnAction(e->{
			ReportsGUI.main(null);
		});
		
		
		

		Button logout = new Button("LOG OUT");
		logout.setPrefSize(200, 80);
		logout.setFont(new Font("Bodoni MT Black", 15));
		logout.setTextFill(Color.RED);

		// exit from parts App,Back to login screen
		logout.setOnAction(e -> {
			stage2.close();
			start.show();
		});

		Image im = new Image("file:menue.jpg");

		// create a background image
		BackgroundImage backgroundimage = new BackgroundImage(im, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		// create Background
		Background background = new Background(backgroundimage);

		VBox v2 = new VBox(20);
		// set background
		v2.setBackground(background);
		v2.getChildren().addAll(l, employee, customers, factories, drugs, cusPhone, sales, report,logout);

		sales.setOnAction(e -> {
			SalesService.main(null);
		});

		v2.setAlignment(Pos.CENTER);
		Scene sc2 = new Scene(v2, 1500, 1500);
		stage2.setTitle("Menue");
		stage2.getIcons().add(new Image("file:meIcon.jpg"));

		stage2.setScene(sc2);

		b.setOnAction(e -> {// login to parts Screen
			stage2.show();
			start.close();
		});

		// impention for employee

		// table of employee
		TableView<Employee> tableEmployee = new TableView<>();
		tableEmployee.setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		// id
		TableColumn<Employee, Long> id = new TableColumn<>("Id");

		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		// name
		TableColumn<Employee, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));

		// salary
		TableColumn<Employee, Double> sal = new TableColumn<>("Salary");
		sal.setCellValueFactory(new PropertyValueFactory<>("salary"));

		// adress
		TableColumn<Employee, String> addres = new TableColumn<>("Address");
		addres.setCellValueFactory(new PropertyValueFactory<>("addres"));
		// gender
		TableColumn<Employee, String> gender = new TableColumn<>("Gender");
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		// date of birth
		TableColumn<Employee, String> dbirth = new TableColumn<>("Date of Birth");
		dbirth.setCellValueFactory(new PropertyValueFactory<>("datetOfBirth"));

		id.setPrefWidth(144);
		name.setPrefWidth(144);

		tableEmployee.getColumns().addAll(id, name, sal, addres, gender, dbirth);
		ObservableList<Employee> list1 = EmployeeService.getService().getAllEmployees();
		tableEmployee.setItems(list1);

		Stage emloyeeShow = new Stage();
		Label l0, l3, l4, l5, l6, l7;
		TextField t0, t3, t5, t6, t7;

		l0 = new Label("Id number");
		l0.setFont(new Font("impact", 17));
		l0.setTextFill(Color.BLUE);
		t0 = new TextField("");

		l3 = new Label("Name");
		l3.setTextFill(Color.BLUE);
		l3.setFont(new Font("impact", 17));
		t3 = new TextField("");

		l4 = new Label("Gender");
		l4.setTextFill(Color.BLUE);
		l4.setFont(new Font("impact", 17));
		ComboBox<String> combo = new ComboBox<>();
		combo.getItems().addAll("Male", "Female");

		l5 = new Label("Salary");
		l5.setTextFill(Color.BLUE);
		l5.setFont(new Font("impact", 17));
		t5 = new TextField();

		l6 = new Label("Addres");
		l6.setFont(new Font("impact", 17));
		t6 = new TextField();
		l6.setTextFill(Color.BLUE);

		l7 = new Label("DBirth");

		Label y, m, d;
		// years
		y = new Label("Y");
		y.setTextFill(Color.BLUE);
		ComboBox<Integer> years = new ComboBox<>();
		for (int i = 1900; i < 2020; i++) {
			years.getItems().add(i);
		}

		HBox year = new HBox(5);
		year.getChildren().addAll(y, years);

		// month
		m = new Label("M");
		m.setTextFill(Color.WHITE);
		ComboBox<Integer> months = new ComboBox<>();

		for (int i = 1; i < 13; i++) {
			months.getItems().add(i);
		}
		months.getSelectionModel().selectFirst();

		HBox month = new HBox(5);
		month.getChildren().addAll(m, months);

		// day
		d = new Label("D");
		d.setTextFill(Color.WHITE);
		ComboBox<Integer> days = new ComboBox<>();

		for (int i = 1; i < 32; i++) {
			days.getItems().add(i);
		}

		HBox day = new HBox(5);
		day.getChildren().addAll(d, days);

		VBox dateOfBirth = new VBox(10);
		dateOfBirth.getChildren().addAll(year, month, day);

		l7.setFont(new Font("impact", 17));
		l7.setTextFill(Color.WHITE);

		// id
		HBox h0 = new HBox(10);
		h0.getChildren().addAll(l0, t0);

		// name
		HBox hh3 = new HBox(30);
		hh3.getChildren().addAll(l3, t3);

		// gender
		HBox h4 = new HBox(20);
		h4.getChildren().addAll(l4, combo);

		// salary
		HBox h5 = new HBox(10);
		h5.getChildren().addAll(l5, t5);

		// addres
		HBox h6 = new HBox(10);
		h6.getChildren().addAll(l6, t6);

		// date of birth
		HBox h7 = new HBox(10);
		h7.getChildren().addAll(l7, dateOfBirth);

		VBox empv1 = new VBox(15);
		empv1.getChildren().addAll(h0, hh3, h4);

		VBox empv2 = new VBox(15);
		empv2.getChildren().addAll(h5, h6, h7);

		HBox emH = new HBox(60);
		emH.getChildren().addAll(empv1, empv2);
		emH.setPadding(new Insets(30));

		javafx.scene.control.TextArea empAr = new javafx.scene.control.TextArea();
		empAr.setPadding(new Insets(15));
		empAr.setFont(new Font("Arial", 20));

		// undo:log out from employee ,back to parts
		Button undo = new Button("log out");
		undo.setFont(new Font("impact", 17));
		undo.setPrefWidth(120);
		undo.setTextFill(Color.RED);
		undo.setAlignment(Pos.TOP_RIGHT);
		undo.setOnAction(e -> {
			emloyeeShow.close();
			stage2.show();
		});

		HBox emHH = new HBox(30);
		emHH.getChildren().addAll(emH, empAr, undo);

		HBox emh3 = new HBox(20);

		Button display, add, del, getInfo, avg;

		// display:get all employees as table.
		display = new Button("employees's list");
		display.setTextFill(Color.BLUE);
		display.setFont(new Font("impact", 17));
		display.setOnAction(e -> {
			try {
				ObservableList<Employee> list = EmployeeService.getService().getAllEmployees();
				tableEmployee.setItems(list);
			} catch (SQLException e1) {
				empAr.setText(e1.getMessage());
			}
		});

		// add:add an employee
		add = new Button("Add");
		add.setTextFill(Color.BLUE);
		add.setFont(new Font("impact", 17));
		add.setOnAction(e -> {
			if (t0.getText().length() == 0 || t3.getText().length() == 0 || t5.getText().length() == 0
					|| t6.getText().length() == 0 || years.getSelectionModel().isEmpty()
					|| months.getSelectionModel().isEmpty() || days.getSelectionModel().isEmpty()) {
				empAr.setText("Invalid Addtion,insert needed data!!");
			} else {

				try {
					String yrs = years.getSelectionModel().getSelectedItem() + "-";
					String mns = months.getSelectionModel().getSelectedItem() + "-";
					String dys = days.getSelectionModel().getSelectedItem() + "";
					String db = yrs + mns + dys;

					String s = EmployeeService.getService().insert(t0.getText(), t3.getText(), t5.getText(),
							t6.getText(), combo.getSelectionModel().getSelectedItem(), db);

					if (s.equals("employee added")) {
						empAr.setText(s);
						tableEmployee.getItems()
								.add(new Employee(t0.getText(), t3.getText(), Double.parseDouble(t5.getText()),
										t6.getText(), combo.getSelectionModel().getSelectedItem(), db));
					} else {
						empAr.setText(s);
					}

				} catch (SQLException e1) {
					empAr.setText(e1.getMessage());
				}
			}
		});

		avg = new Button("salaries's average");
		avg.setFont(new Font("impact", 17));
		avg.setTextFill(Color.GREEN);
		avg.setOnAction(e -> {
			try {
				empAr.setText("Salaries's average = " + EmployeeService.getService().getAvgSal());
			} catch (SQLException e1) {
				empAr.setText(e1.getMessage());
				e1.printStackTrace();
			}
		});

		del = new Button("Delete");
		del.setTextFill(Color.RED);
		del.setFont(new Font("impact", 17));

		del.setOnAction(e -> {
			if (t0.getText().length() == 0) {
				empAr.setText("Enter employee's id!!");
			} else {
				try {
					empAr.setText(EmployeeService.getService().deleteEmployee(t0.getText()));
				} catch (SQLException e1) {
					empAr.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		getInfo = new Button("search");
		getInfo.setFont(new Font("impact", 17));
		getInfo.setOnAction(e -> {
			if (t0.getText().length() == 0) {
				empAr.setText("Enter employee's id!!");
			} else {
				try {
					empAr.setText(EmployeeService.getService().getInfo(t0.getText()));
				} catch (SQLException e1) {
					empAr.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		Button clear = new Button("clear");
		clear.setFont(new Font("impact", 17));

		clear.setOnAction(e -> {
			try {
				empAr.setText(EmployeeService.getService().deleteAll());
			} catch (SQLException e1) {
				empAr.setText(e1.getMessage());
			}
		});

		Button ref = new Button("refresh");
		ref.setFont(new Font("impact", 17));

		ref.setOnAction(e -> {
			try {
				ObservableList<Employee> list = EmployeeService.getService().getAllEmployees();
				tableEmployee.setItems(list);
			} catch (SQLException e1) {
				empAr.setText(e1.getMessage());
			}
		});

		emh3.getChildren().addAll(display, add, del, getInfo, avg, clear, ref);

		VBox emV = new VBox(70);
		// Image imEm = new Image("file:p.jpg");

		// create a background image
		BackgroundImage imEmbackground = new BackgroundImage(im2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		// create Background
		Background imEmbackgroundbackground = new Background(imEmbackground);

		// set background
		emV.setBackground(imEmbackgroundbackground);
		// emh3.setAlignment(Pos.CENTER);
		emh3.setPadding(new Insets(15));
		Label emtableh = new Label("Employees");
		emtableh.setFont(new Font("impact", 22));
		emtableh.setTextFill(Color.WHEAT);
		emV.getChildren().addAll(emHH, emh3, emtableh, tableEmployee);
		emV.setPadding(new Insets(17));
		Scene employeeScene = new Scene(emV, 1700, 1700);
		emloyeeShow.setScene(employeeScene);
		emloyeeShow.getIcons().add(new Image("file:iconem.jpg"));

		emloyeeShow.setTitle("Employees");

		// show employee screen
		employee.setOnAction(e -> {
			emloyeeShow.show();
			stage2.close();
		});

		// factory
		TableView<Factory> tableFactory = new TableView<>();// table

		ObservableList<Factory> data3 = FXCollections.observableArrayList();

		// Declare ID Column and Where it should show from factory attribute with
		// possibility to change it from the table
		TableColumn<Factory, Long> IDColumn = new TableColumn<>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		IDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		IDColumn.setOnEditCommit((CellEditEvent<Factory, Long> t) -> {
			((Factory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
		});
		// Declare Name Column and Where it should show from factory attribute with
		// possibility to change it from the table
		TableColumn<Factory, String> NameColumn = new TableColumn<>("Name");
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		NameColumn.setOnEditCommit((CellEditEvent<Factory, String> t) -> {
			((Factory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});
		// Declare Phone Column and Where it should show from factory attribute with
		// possibility to change it from the table
		TableColumn<Factory, String> PhoneColumn = new TableColumn<>("Phone");
		PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		PhoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		PhoneColumn.setOnEditCommit((CellEditEvent<Factory, String> t) -> {
			((Factory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddres(t.getNewValue());
		});
		// Declare Address Column and Where it should show from factory attribute with
		// possibility to change it from the table
		TableColumn<Factory, String> AddressColumn = new TableColumn<>("Address");
		AddressColumn.setCellValueFactory(new PropertyValueFactory<>("addres"));
		AddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		AddressColumn.setOnEditCommit((CellEditEvent<Factory, String> t) -> {
			((Factory) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddres(t.getNewValue());
		});

		// Label object
		Label IDLabel = new Label("ID: ");
		Label NameLabel = new Label("Name: ");
		Label AddressLabel = new Label("Address: ");
		Label PhoneLabel = new Label("Phone: ");
		Label FactoryEntityName = new Label("Factory Table ");

		// TextField object
		TextField IDField = new TextField();
		TextField NameField = new TextField();
		TextField AddressField = new TextField();
		TextField PhoneField = new TextField();
		TextField FactoryFreeField = new TextField();

		// Button object
		Button InsertButton1 = new Button("insert");
		Button DeleteButton1 = new Button("Delete");
		Button SearchButton1 = new Button("Search");
		Button ShowButton1 = new Button("Show Data");

		// TextArea object
		TextArea Factoryta = new TextArea();

		// Table object

		tableFactory.setEditable(true);
		tableFactory.getColumns().addAll(IDColumn, NameColumn, PhoneColumn, AddressColumn);
		tableFactory.setItems(data3);

		// Table Size and columns widths
		tableFactory.setPrefSize(800, 300);
		tableFactory.setTranslateY(150);
		IDColumn.setPrefWidth(200);
		NameColumn.setPrefWidth(200);
		AddressColumn.setPrefWidth(200);
		PhoneColumn.setPrefWidth(200);

		// TextField Size
		IDField.setPrefSize(110, 20);
		NameField.setPrefSize(110, 20);
		AddressField.setPrefSize(110, 20);
		PhoneField.setPrefSize(110, 20);
		FactoryFreeField.setPrefSize(110, 20);
		Factoryta.setPrefSize(200, 50);

		PhoneLabel.setTranslateX(30);
		PhoneField.setTranslateX(55);

		// Focus Traversable down(TextField, Button)
		IDField.setFocusTraversable(false);
		AddressField.setFocusTraversable(false);
		PhoneField.setFocusTraversable(false);
		NameField.setFocusTraversable(false);
		FactoryFreeField.setFocusTraversable(false);
		InsertButton1.setFocusTraversable(false);
		DeleteButton1.setFocusTraversable(false);
		ShowButton1.setFocusTraversable(false);
		SearchButton1.setFocusTraversable(false);
		tableFactory.setFocusTraversable(false);
		Factoryta.setFocusTraversable(false);

		Factoryta.setTooltip(new Tooltip("By ID"));
		Factoryta.setPromptText("By ID");
		
		FactoryFreeField.setTooltip(new Tooltip("BY ID"));
		FactoryFreeField.setPromptText("By ID");

		// Factory Interface Form

		// Horizontal Labels and TextField(ID, Name)
		Button closeFac = new Button("Return");

		HBox hb1 = new HBox(45);
		hb1.getChildren().addAll(IDLabel, IDField, NameLabel, NameField, FactoryEntityName, Factoryta, closeFac);

		HBox hb2 = new HBox(15);
		hb2.getChildren().addAll(AddressLabel, AddressField, PhoneLabel, PhoneField);

		// Horizontal Buttons(insert, delete, show data in database, search)
		HBox hbF = new HBox(10);
		hbF.getChildren().addAll(InsertButton1, DeleteButton1, SearchButton1, FactoryFreeField, ShowButton1);
		// ShowButton.setTranslateX(20);
		// Buttonhb.setTranslateX(70);
		hbF.setAlignment(Pos.CENTER);

		VBox vb3 = new VBox(20);

		vb3.getChildren().addAll(hb1, hb2, hbF);

		// Group root = new Group();
		VBox vFactory = new VBox(20);
		vFactory.getChildren().addAll(vb3, tableFactory);
		
		Group FactoryGroup = new Group();
		FactoryGroup.getChildren().addAll(vb3, tableFactory);

		// root.getChildren().addAll(vb3, tableFactory);
		FactoryGroup.setTranslateX(50);

		Scene scene = new Scene(FactoryGroup, 1000, 600);

		Stage sFactory = new Stage();
		sFactory.setScene(scene);

		ShowButton1.setOnAction(e -> {
			ObservableList<Factory> list = FactoryServices.getService().getAllFacories();
			tableFactory.setItems(list);
		});

		InsertButton1.setOnAction(e -> {
			String idF = IDField.getText();
			String nameF = NameField.getText();
			String address = AddressField.getText();
			String phone = PhoneField.getText();
			if (!idF.isEmpty() && !nameF.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
				if (phone.matches("[0-9]{10}")) {
					Factory f = new Factory(Integer.parseInt(idF), nameF, address, phone);
					tableFactory.getItems().add(f);
					FactoryServices.getService().Insert(idF, nameF, phone, address);
					IDField.setText("");
					NameField.setText("");
					AddressField.setText("");
					PhoneField.setText("");
				} else
					new Alert(AlertType.WARNING, " Unmatches [0-9]{10}!!!").show();
			} else
				new Alert(AlertType.WARNING, " Empty Field!!").show();
		});

		SearchButton1.setOnAction(e -> {
			if (!FactoryFreeField.getText().isEmpty()) {
				String idFree = FactoryFreeField.getText().toString();
				try {
					Factoryta.setText(FactoryServices.getService().getInfo(idFree));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FactoryFreeField.setText("");
			} else
				new Alert(AlertType.WARNING, "Empty Field!!").show();
		});

		DeleteButton1.setOnAction(e -> {
			if (!FactoryFreeField.getText().isEmpty()) {
				String id2 = FactoryFreeField.getText().toString();
				Factoryta.setText(FactoryServices.getService().delete(id2));
				FactoryFreeField.setText("");
			} else
				new Alert(AlertType.WARNING, "Empty Field!!").show();
		});

		factories.setOnAction(e -> {
			sFactory.show();
			stage2.close();
		});

		closeFac.setOnAction(e -> {
			stage2.show();
			sFactory.close();
		});

		// stage of customers phone

		Label lcus = new Label("To insert or to delete any customer phone number :");

		Button INSERT = new Button("INSERT");
		INSERT.setTextFill(Color.BLUE);

		Button DELETE = new Button("DELETE");

		DELETE.setTextFill(Color.BLUE);

		Button print = new Button("Display");
		print.setTextFill(Color.BLUE);

		Button SEARCH = new Button("SEARCH ");
		SEARCH.setTextFill(Color.BLUE);

		HBox hh = new HBox(90);
		hh.getChildren().addAll(INSERT, DELETE, print, SEARCH);

		Label lcus1 = new Label("insert Customer's id:");
		TextField tf1 = new TextField();
		tf1.setPromptText("Insert Customer Id");
		HBox hcus1 = new HBox(13);
		hcus1.getChildren().addAll(lcus1, tf1);

		Label lcus2 = new Label("insert Customer's phoneNumber:");
		TextField tf2 = new TextField();
		tf2.setPrefWidth(190);
		tf2.setPromptText("Insert Customer phone number");
		HBox hcus2 = new HBox(13);
		hcus2.getChildren().addAll(lcus2, tf2);

		VBox vcus1 = new VBox(20);
		vcus1.getChildren().addAll(hcus1, hcus2);

		TextField tf3 = new TextField();
		tf3.setTranslateX(30);
		tf3.setPrefSize(170, 20);
		tf3.setPromptText("Insert Customer Id to search");

		VBox vb1 = new VBox(40);
		vb1.setPadding(new Insets(18));

		Button closCus = new Button("Log out");
		vb1.getChildren().addAll(lcus, vcus1, hh, closCus);

		DELETE.setOnAction(e -> {

			String cus_id = tf1.getText();
			String CPnumber = tf2.getText();

		});

		SEARCH.setOnAction(e -> {

		});

		// table of customer phone
		TableView<CustomerPhone> cusPh = new TableView<>();
		Label emty = new Label("no customers's phone");

		TableColumn<CustomerPhone, Integer> idcus = new TableColumn<>("Id");
		idcus.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<CustomerPhone, String> number = new TableColumn<>("Phone Number");
		number.setCellValueFactory(new PropertyValueFactory<>("pnumber"));

		idcus.setPrefWidth(130);
		number.setPrefWidth(150);

		cusPh.getColumns().addAll(idcus, number);

		print.setOnAction(e -> {
			ObservableList<CustomerPhone> list = CustomerPhoneService.getService().getCustomersPhone();
			cusPh.setItems(list);

		});

		INSERT.setOnAction(e -> {

			String phone = tf2.getText();
			String id1 = tf1.getText();

			CustomerPhoneService.getService().insert(id1, phone);
			cusPh.getItems().add(new CustomerPhone(Integer.parseInt(id1), phone));

		});

		DELETE.setOnAction(e -> {

			String cus_id = tf1.getText();
			String CPnumber = tf2.getText();
			CustomerPhoneService.getService().delete(cus_id, CPnumber);
			ObservableList<CustomerPhone> list = CustomerPhoneService.getService().getCustomersPhone();
			cusPh.setItems(list);

		});

		SEARCH.setOnAction(e -> {
			CustomerPhoneService.getService().search();

		});

		vb1.getChildren().add(cusPh);

		// Group root = new Group();
		Scene scene2 = new Scene(vb1, 900, 700);
		Stage s5 = new Stage();
		s5.setScene(scene2);

		cusPhone.setOnAction(e -> {
			stage2.close();

			s5.show();
		});

		closCus.setOnAction(e -> {
			s5.close();
			stage2.show();
		});

		// drug stage

		// Declare Drug ID, Factory ID, Employee ID Column and Where it should show from
		// factory attribute with possibility to change it from the table
		TableColumn<Drug, Integer> DrugIDColumn = new TableColumn<>("ID");
		DrugIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//		 DrugIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		DrugIDColumn.setOnEditCommit((CellEditEvent<Drug, Integer> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
		});

		TableColumn<Drug, Integer> FactoryIDColumn = new TableColumn<>("FID");
		FactoryIDColumn.setCellValueFactory(new PropertyValueFactory<>("FactoryID"));
//		 FactoryIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		FactoryIDColumn.setOnEditCommit((CellEditEvent<Drug, Integer> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFactoryID(t.getNewValue());
		});

		// Declare Drug Name, number, Dose, Discripe, and Quaninty Column and Where it
		// should show from factory attribute with possibility to change it from the
		// table
		TableColumn<Drug, String> DrugNameColumn = new TableColumn<>("Name");
		DrugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//	     DrugNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		DrugNameColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});

		TableColumn<Drug, String> DrugNumberColumn = new TableColumn<>("Number");
		DrugNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
//	     DrugNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		DrugNumberColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNumber(t.getNewValue());
		});

		TableColumn<Drug, String> DrugDoseColumn = new TableColumn<>("Dose");
		DrugDoseColumn.setCellValueFactory(new PropertyValueFactory<>("dose"));
//	     DrugDoseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		DrugDoseColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDose(t.getNewValue());
		});

		TableColumn<Drug, String> DrugDiscripeColumn = new TableColumn<>("Discripe");
		DrugDiscripeColumn.setCellValueFactory(new PropertyValueFactory<>("discripe"));
//	     DrugDiscripeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		DrugDiscripeColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDiscripe(t.getNewValue());
		});

		TableColumn<Drug, Long> DrugQuanintyColumn = new TableColumn<>("Quaninty");
		DrugQuanintyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//	     DrugQuanintyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		DrugQuanintyColumn.setOnEditCommit((CellEditEvent<Drug, Long> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
		});
		// Declare buying and selling Column and Where it should show from factory
		// attribute with possibility to change it from the table
		TableColumn<Drug, Double> SellingColumn = new TableColumn<>("SPrice($)");
		SellingColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
//	     SellingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		SellingColumn.setOnEditCommit((CellEditEvent<Drug, Double> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSellPrice(t.getNewValue());
		});

		TableColumn<Drug, Double> BuyingColumn = new TableColumn<>("BPrice($)");
		BuyingColumn.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
//	     BuyingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		BuyingColumn.setOnEditCommit((CellEditEvent<Drug, Double> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setBuyPrice(t.getNewValue());
		});
		// Declare Production and End Date Column and Where it should show from factory
		// attribute with possibility to change it from the table
		TableColumn<Drug, String> ProductionColumn = new TableColumn<>("PDate");
		ProductionColumn.setCellValueFactory(new PropertyValueFactory<>("proDate"));
		ProductionColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setProDate(t.getNewValue());
		});

		TableColumn<Drug, String> EndColumn = new TableColumn<>("EDate");
		EndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		EndColumn.setOnEditCommit((CellEditEvent<Drug, String> t) -> {
			((Drug) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEndDate(t.getNewValue());
		});

		table2.setEditable(true);
		table2.getColumns().addAll(DrugIDColumn, FactoryIDColumn, DrugNumberColumn, DrugDoseColumn, DrugDiscripeColumn,
				SellingColumn, BuyingColumn, DrugNameColumn, DrugQuanintyColumn, ProductionColumn, EndColumn);
		table2.setItems(data2);

		// Table Size and columns widths
		table2.setPrefSize(1100, 300);
		table2.setTranslateY(250);
//	     EmployeeIDColumn.setPrefWidth(80);
		DrugIDColumn.setPrefWidth(80);
		DrugQuanintyColumn.setPrefWidth(100);
		FactoryIDColumn.setPrefWidth(80);
		DrugNameColumn.setPrefWidth(80);
		DrugNumberColumn.setPrefWidth(100);
		DrugDoseColumn.setPrefWidth(80);
		DrugDiscripeColumn.setPrefWidth(100);
		SellingColumn.setPrefWidth(100);
		BuyingColumn.setPrefWidth(100);
		ProductionColumn.setPrefWidth(100);
		EndColumn.setPrefWidth(100);

		// TextField Size
		DrugIDField.setPrefSize(110, 20);
		FactoryIDField.setPrefSize(110, 20);
//	     EmployeeIDField.setPrefSize(110, 20);
		DrugNameField.setPrefSize(110, 20);
		DrugNumberField.setPrefSize(110, 20);
		DrugDoseField.setPrefSize(110, 20);
		DrugQuanintyField.setPrefSize(110, 20);
		DrugDiscripeField.setPrefSize(110, 20);
		DrugProductionField.setPrefSize(110, 20);
		DrugEndField.setPrefSize(110, 20);
		DrugFreeField.setPrefSize(110, 20);
		DrugSellingField.setPrefSize(110, 20);
		DrugBuyingField.setPrefSize(110, 20);
		Drugta.setPrefSize(200, 50);

		// Focus Traversable down(TextField, Button)
		DrugIDField.setFocusTraversable(false);
		FactoryIDField.setFocusTraversable(false);
//	     EmployeeIDField.setFocusTraversable(false);
		DrugNameField.setFocusTraversable(false);
		DrugNumberField.setFocusTraversable(false);
		DrugDoseField.setFocusTraversable(false);
		DrugQuanintyField.setFocusTraversable(false);
		DrugDiscripeField.setFocusTraversable(false);
		DrugProductionField.setFocusTraversable(false);
		DrugEndField.setFocusTraversable(false);
		DrugSellingField.setFocusTraversable(false);
		DrugBuyingField.setFocusTraversable(false);
		DrugFreeField.setFocusTraversable(false);
		InsertButton.setFocusTraversable(false);
		DeleteButton.setFocusTraversable(false);
		ShowButton.setFocusTraversable(false);
		SearchButton.setFocusTraversable(false);
		ReturnButton.setFocusTraversable(false);
		table2.setFocusTraversable(false);
		Drugta.setFocusTraversable(false);

		DrugFreeField.setTooltip(new Tooltip("BY ID"));
		DrugFreeField.setPromptText("By ID");

		Drugta.setTooltip(new Tooltip("is Found Or Deleted??"));
		Drugta.setPromptText("is Found Or Deleted??");

		// Factory Interface Form

		// Horizontal Labels and TextField(ID, Name)
		HBox hbb1 = new HBox(90);
		hbb1.getChildren().addAll(DrugIDLabel, DrugIDField, FactoryIDLabel, FactoryIDField, DrugEntityName, ReturnButton);
		FactoryIDLabel.setTranslateX(-73);
		FactoryIDField.setTranslateX(-124);
		ReturnButton.setTranslateX(50);
//	     EmployeeIDLabel.setTranslateX(-195);
//	     EmployeeIDField.setTranslateX(-250);

		HBox hbb2 = new HBox(70);
		hbb2.getChildren().addAll(DrugNameLabel, DrugNameField, DrugNumberLabel, DrugNumberField, Drugta);
		DrugNumberLabel.setTranslateX(-55);
		DrugNumberField.setTranslateX(-105);
		Factoryta.setTranslateX(-30);

		HBox hb3 = new HBox(75);
		hb3.getChildren().addAll(DrugDoseLabel, DrugDoseField, DrugDiscripeLabel, DrugDiscripeField, DrugQuanintyLabel,
				DrugQuanintyField);
		DrugDiscripeLabel.setTranslateX(-58);
		DrugDiscripeField.setTranslateX(-115);
		DrugQuanintyLabel.setTranslateX(-175);
		DrugQuanintyField.setTranslateX(-220);

		HBox hb4 = new HBox(15);
		hb4.getChildren().addAll(DrugProductionLabel, DrugProductionField, DrugEndLabel, DrugEndField, DrugSellingLabel,
				DrugSellingField, DrugBuyingLabel, DrugBuyingField);

		// Horizontal Buttons(insert, delete, show data in database, search)
		HBox Buttonhb1 = new HBox(27);
		Buttonhb1.getChildren().addAll(InsertButton, DeleteButton, SearchButton, DrugFreeField, ShowButton);
//	     ShowButton.setTranslateX(20);
		Buttonhb1.setTranslateX(70);

		VBox vbb1 = new VBox(20);
		vbb1.getChildren().addAll(hbb1, hbb2, hb3, hb4, Buttonhb1);

//	     data.add(new Drug(1, 2, 3, "r", "g", "g", 50, 90, "x", "q", new Date(2000,5,1),  new Date(2005,10,1)));
		Group root3 = new Group();
		root3.getChildren().addAll(vbb1, table2);
		root3.setTranslateX(50);

		Scene scne1 = new Scene(root3, 1200, 600);

		Stage ss2 = new Stage();
		ss2.setScene(scne1);

		ShowButton.setOnAction(e -> {
			ObservableList<Drug> list = DrugService.getService().getAllDrugs();
			table2.setItems(list);
		});

		InsertButton.setOnAction(e -> {
			String did = DrugIDField.getText();
			String fid = FactoryIDField.getText();
//	    	 String eid = EmployeeIDField.getText();
			String nameDr = DrugNameField.getText();
			String numDr = DrugNumberField.getText();
			String production = DrugProductionField.getText();
			String dose = DrugDoseField.getText();
			String descripe = DrugDiscripeField.getText();
			String selling_price = DrugSellingField.getText();
			String buying_price = DrugBuyingField.getText();
			String Dname = DrugNameField.getText();
			String quaninty = DrugQuanintyField.getText();
			String end_date = DrugEndField.getText();
			if (!did.isEmpty() && !fid.isEmpty() && !nameDr.isEmpty()) {
				Drug d2 = new Drug(Integer.parseInt(did), Integer.parseInt(fid), numDr, production, dose, descripe,
						Double.parseDouble(selling_price), Double.parseDouble(buying_price), Dname,
						Integer.parseInt(quaninty), end_date);
				table2.getItems().add(d2);
				DrugService.getService().Insert(did, fid, numDr, production, dose, descripe, selling_price,
						buying_price, Dname, quaninty, end_date);
				DrugIDField.setText("");
				FactoryIDField.setText("");
//		    	 EmployeeIDField.setText("");
				DrugNameField.setText("");
				DrugNumberField.setText("");
				DrugProductionField.setText("");
				DrugDoseField.setText("");
				DrugDiscripeField.setText("");
				DrugSellingField.setText("");
				DrugBuyingField.setText("");
				DrugNameField.setText("");
				DrugQuanintyField.setText("");
				DrugEndField.setText("");
			} else
				new Alert(AlertType.WARNING, " Drug, Factory, Employee ID and Drug name are important!!!").show();
		});

		SearchButton.setOnAction(e -> {
			if (!DrugFreeField.getText().isEmpty()) {
				String id3 = DrugFreeField.getText().trim();
				Drugta.setText(DrugService.getService().getInfo(id3));
				DrugFreeField.setText("");
			} else
				new Alert(AlertType.WARNING, "Empty Field!!").show();
		});

		DeleteButton.setOnAction(e -> {
			if (!DrugFreeField.getText().isEmpty()) {
				String id4 = DrugFreeField.getText().toString();
				Drugta.setText(DrugService.getService().delete(id4));
				DrugFreeField.setText("");
			} else
				new Alert(AlertType.WARNING, "Empty Field!!").show();
		});

		ReturnButton.setOnAction(e -> {
			ss2.close();
			stage2.show();
		});

		drugs.setOnAction(e -> {
			ss2.show();
			stage2.close();
		});

	}

}
