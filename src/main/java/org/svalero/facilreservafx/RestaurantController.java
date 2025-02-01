package org.svalero.facilreservafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RestaurantController {
    @FXML private TableView<RestaurantFx> tableView;
    @FXML private TableColumn<RestaurantFx, Long> idColumn;
    @FXML private TableColumn<RestaurantFx, String> nameColumn;
    @FXML private TableColumn<RestaurantFx, String> addressColumn;
    @FXML private TableColumn<RestaurantFx, Integer> capacityColumn;
    @FXML private TableColumn<RestaurantFx, Boolean> availableColumn;

    private final RestaurantService restaurantService = new RestaurantService();
    private final ObservableList<RestaurantFx> restaurantFxList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        tableView.setItems(restaurantFxList);
        fetchRestaurants();
    }

    private void fetchRestaurants() {
        restaurantService.fetchRestaurants().thenAccept(restaurants -> {
            if (restaurants != null) {
                restaurantFxList.addAll(restaurants);
            }
        });
    }
}
