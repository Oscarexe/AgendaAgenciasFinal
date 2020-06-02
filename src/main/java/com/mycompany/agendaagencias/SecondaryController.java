/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Ambito;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
    private Ambito ambito;
    private EntityManager entityManager;
    private boolean nuevoAmbito;
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
    }
    
    
    //Para que cualquiera otra clase (en este caso la clase de la vista lista) 
    //pueda asignar el TableView a esta clase de detalle
    public void setTableViewPrevio(TableView tableViewPrevio) {
    this.tableViewPrevio = tableViewPrevio;
    }
    
    //iniciará la transacción con la base de datos en el momento en que se use este método
    public void setPersona(EntityManager entityManager, Ambito ambito, boolean nuevoAmbito) {
    this.entityManager = entityManager;
    entityManager.getTransaction().begin();
    if(!nuevoAmbito) {
        this.ambito = entityManager.find(Ambito.class, ambito.getId());
    } else {
        this.ambito = ambito;
    }
    this.nuevoAmbito = nuevoAmbito;
    }
    
}
