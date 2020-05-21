package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Agenciaspublicitarias;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PrimaryController implements Initializable {
     private EntityManager entityManager; 
    @FXML
    private TableView<Agenciaspublicitarias> tableView;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaNombre;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaTeléfono;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaCorreo;

    public void initialize(URL url, ResourceBundle rb) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTeléfono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
    } 
    
    
    // Para trasladar el entity manager desde el app
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void cargarTodasAgencias() {
        Query queryPersonaFindAll = entityManager.createNamedQuery("Agenciaspublicitarias.findAll");
        List<Agenciaspublicitarias> listAgenciaspublicitarias = queryPersonaFindAll.getResultList();
        tableView.setItems(FXCollections.observableArrayList(listAgenciaspublicitarias));
    }
}
