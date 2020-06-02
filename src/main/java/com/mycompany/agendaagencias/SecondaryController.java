/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Agenciaspublicitarias;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author oskar
 */
public class SecondaryController implements Initializable {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldAño;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldProvincia;
    @FXML
    private TextField textFieldHora;
    @FXML
    private TextField textFieldSoporte;
    @FXML
    private TextField textFieldISBN;
    @FXML
    private TextField textFieldTrabajosRealizados;
    @FXML
    private TextField textFieldAmbito;
    @FXML
    private CheckBox checkBoxCobro;
    
    
    
    private Pane  rootContactosView;
    @FXML
    private AnchorPane rootDetalleView;

 
    private TableView tableViewPrevio;
    private Agenciaspublicitarias agencia;
    private EntityManager entityManager;
    private boolean nuevaAgencia;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   private void onActionButtonGuardar(ActionEvent event) {
       
       //Volver a la vista del primary
       StackPane rootMain = (StackPane)rootDetalleView.getScene().getRoot();
       rootMain.getChildren().remove(rootDetalleView);      
  
       rootContactosView.setVisible(true);
       
       //Almacenar o actualizar la base de datos
       agencia.setNombre(textFieldNombre.getText());
       agencia.setEmail(textFieldEmail.getText());
       agencia.setIsbn(textFieldISBN.getText());
       agencia.setTelefono(textFieldTelefono.getText());
       agencia.setSoporte(textFieldSoporte.getText());
       agencia.setProvincia(textFieldProvincia.getText());

        if(nuevaAgencia) {
            entityManager.persist(agencia);
        } else {
            entityManager.merge(agencia);
        }
        entityManager.getTransaction().commit();
    
        
        //actualizar los nuevos datos en el TableView
        int numFilaSeleccionada;
        if(nuevaAgencia) {
            tableViewPrevio.getItems().add(agencia);
            numFilaSeleccionada = tableViewPrevio.getItems().size() - 1;
            tableViewPrevio.getSelectionModel().select(numFilaSeleccionada);
            tableViewPrevio.scrollTo(numFilaSeleccionada);
        } else {
            numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
            tableViewPrevio.getItems().set(numFilaSeleccionada, agencia);
        }
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
        
    }
    
    
     
    public void setRootContactosView(Pane rootContactosView) {
        this.rootContactosView = rootContactosView;
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        //Volver a la vista del primary
       StackPane rootMain = (StackPane)rootDetalleView.getScene().getRoot();
       rootMain.getChildren().remove(rootDetalleView);      
  
       rootContactosView.setVisible(true);
       
       //Cancelar transacción y realizar rollback
       entityManager.getTransaction().rollback();

       int numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
       TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
       tableViewPrevio.getFocusModel().focus(pos);
       tableViewPrevio.requestFocus();
       
    }
    
    
    //Para que cualquiera otra clase (en este caso la clase de la vista lista) 
    //pueda asignar el TableView a esta clase de detalle
    public void setTableViewPrevio(TableView tableViewPrevio) {
    this.tableViewPrevio = tableViewPrevio;
    }
    
    //iniciará la transacción con la base de datos en el momento en que se use este método
    public void setAgencia(EntityManager entityManager, Agenciaspublicitarias agencia, boolean nuevaAgencia) {
    this.entityManager = entityManager;
    entityManager.getTransaction().begin();
    if(!nuevaAgencia) {
        this.agencia = entityManager.find(Agenciaspublicitarias.class, agencia.getId());
    } else {
        this.agencia = agencia;
    }
    this.nuevaAgencia = nuevaAgencia;
    }
    
    //mostrarDatos
    public void mostrarDatos() {
        textFieldNombre.setText(agencia.getNombre());
        textFieldProvincia.setText(agencia.getProvincia());
        textFieldTelefono.setText(agencia.getTelefono());
        textFieldEmail.setText(agencia.getEmail());
        textFieldSoporte.setText(agencia.getSoporte());
        textFieldISBN.setText(agencia.getIsbn());
        // Falta implementar el código para el resto de controles
    }
    
    
}
