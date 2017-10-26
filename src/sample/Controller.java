package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldCount;
    @FXML
    TextField textFieldPrice;

    @FXML
    Button buttonAdd;

    @FXML
    Label labelSum;

    @FXML
    TableColumn<Product, String> tableColumnName;
    @FXML
    TableColumn<Product, Double> tableColumnPrice;
    @FXML
    TableColumn<Product, Double> tableColumnSum;
    @FXML
    TableColumn<Product, Integer> tableColumnCount;

    @FXML
    TableView table;

    private boolean checkNuber;
    private ObservableList<Product> productList;

    @FXML
    public void initialize() {
        productList = FXCollections.observableArrayList();
        checkNuber = true;
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
        tableColumnSum.setCellValueFactory(new PropertyValueFactory<Product, Double>("sum"));
        try {
            table.setItems(productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        printSum(productList);
    }

    @FXML
    private void checkOnNumber(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        String s = textField.getText();
        if(s.isEmpty()){
            return;
        }
        try {
            Double.parseDouble(s);
            checkNuber = true;
        } catch (Exception e) {
            if ((keyEvent.getCode() != KeyCode.ENTER && checkNuber)) {
                textField.setText(s.replaceAll("^[0-9]*[.]?[0-9]+$", ""));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PARSE");
                alert.setHeaderText(null);
                alert.setContentText("You try entry bad number");
                alert.showAndWait();
                checkNuber = false;
            }
        }
    }

    @FXML
    private void addProduct(ActionEvent actionEvent) {
        if (!textFieldName.getText().isEmpty() && !textFieldCount.getText().isEmpty() || !textFieldPrice.getText().isEmpty()) {
            Product product = new Product(textFieldName.getText(), (int)Double.parseDouble(textFieldCount.getText()), Double.parseDouble(textFieldPrice.getText()));
            productList.add(product);
            setToTable(product);
            printSum(productList);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Waring");
            alert.setHeaderText(null);
            alert.setContentText("Check empty fields");
            alert.showAndWait();
        }
    }

    private void setToTable(Product product) {
        table.setItems(productList);
    }

    private void printSum(List<Product> sum) {
        double s = 0;
        for (Product product : sum) {
            s += product.getSum();
        }
        labelSum.setText("Total" + s);
    }
}
