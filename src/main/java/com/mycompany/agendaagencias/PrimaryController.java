package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Agenciaspublicitarias;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PrimaryController implements Initializable {
    //aqui se ponen las columnas con el fx:id que se le haya puesto en scene builder
     private EntityManager entityManager; 
    @FXML
    private TableView<Agenciaspublicitarias> tableView;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaNombre;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaTeléfono;
    @FXML
    private TableColumn<Agenciaspublicitarias, String> columnaCorreo;
    @FXML
    private TableColumn<?, ?> columnaAmbito;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldTelefono;

    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //aqui se usan las variables de las columnas relacionadas con la tabla creadas arriba
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTeléfono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaAmbito.setCellValueFactory(new PropertyValueFactory<>("ambito"));
        
        // no se porque da error esto si es copiado y pegado
       /* columnaAmbito.setCellValueFactory( 
            cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if 
            (cellData.getValue().getAMBITO() != null) {
            property.setValue(cellData.getValue().getAMBITO().getAmbito());
        }
        return property;
        });*/
                	
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

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    }
}
