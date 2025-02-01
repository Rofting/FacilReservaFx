package org.svalero.facilreservafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class RestaurantApp extends Application {
    private final ObservableList<RestaurantFx> restaurantFxList = FXCollections.observableArrayList();
    private final RestaurantService restaurantService = new RestaurantService();

    @Override
    public void start(Stage primaryStage) {
        TableView<RestaurantFx> tableView = new TableView<>();

        TableColumn<RestaurantFx, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<RestaurantFx, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<RestaurantFx, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<RestaurantFx, Integer> capacityColumn = new TableColumn<>("Capacity");
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<RestaurantFx, Integer> availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        tableView.getColumns().addAll(idColumn, nameColumn, addressColumn, capacityColumn, availableColumn);
        tableView.setItems(restaurantFxList);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurant List");
        primaryStage.show();

        fetchRestaurants(); // Llamada asíncrona a la API
    }

    private void fetchRestaurants() {
        restaurantService.fetchRestaurants().thenAccept(restaurants -> {
            if (restaurants != null) {
                restaurantFxList.addAll(restaurants);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}