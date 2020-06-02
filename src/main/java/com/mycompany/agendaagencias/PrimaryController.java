package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Agenciaspublicitarias;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class PrimaryController implements Initializable {
    private Agenciaspublicitarias agenciaSeleccionada;
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
    private TableColumn<Agenciaspublicitarias, String> columnaAmbito;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private AnchorPane rootContactosView;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //aqui se usan las variables de las columnas relacionadas con la tabla creadas arriba
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTeléfono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaAmbito.setCellValueFactory(new PropertyValueFactory<>("ambito"));
        
        // no se porque da error esto si es copiado y pegado
        columnaAmbito.setCellValueFactory( 
            cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if 
            (cellData.getValue().getAmbito() != null) {
            property.setValue(cellData.getValue().getAmbito().getAmbito());
        }
        return property;
        });
       
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            agenciaSeleccionada = newValue;
            if (agenciaSeleccionada != null) {
                textFieldNombre.setText(agenciaSeleccionada.getNombre());
                textFieldTelefono.setText(agenciaSeleccionada.getTelefono());
            } else {
                textFieldNombre.setText("");
                textFieldTelefono.setText("");
            }
        });
                	
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
        if (agenciaSeleccionada != null) {
            agenciaSeleccionada.setNombre(textFieldNombre.getText());
            agenciaSeleccionada.setTelefono(textFieldTelefono.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(agenciaSeleccionada);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().set(numFilaSeleccionada, agenciaSeleccionada);
            TablePosition pos = new TablePosition(tableView, numFilaSeleccionada, null);
            tableView.getFocusModel().focus(pos);
            tableView.requestFocus();
        }
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            SecondaryController detalleViewController = (SecondaryController) fxmlLoader.getController(); 
            detalleViewController.setRootContactosView(rootContactosView);
            // Ocultar la vista de la lista
            rootContactosView.setVisible(false);
            
            //pasar los objetos correspondientes desde la vista al detalle.
            detalleViewController.setTableViewPrevio(tableView);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootContactosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) { //tengo duda si la clase PrimaryController va ai
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    AmbitoSeleccionado = new Ambito();
    detalleViewController.setPersona(entityManager, personaSeleccionada, true);
        
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        

        
        try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            SecondaryController detalleViewController = (SecondaryController) fxmlLoader.getController(); 
            detalleViewController.setRootContactosView(rootContactosView);
            // Ocultar la vista de la lista
            rootContactosView.setVisible(false);
            
            //pasar los objetos correspondientes desde la vista al detalle.
            detalleViewController.setTableViewPrevio(tableView);

            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootContactosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
        } catch (IOException ex) { //tengo duda si la clase PrimaryController va ai
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
    }
    
    
}
