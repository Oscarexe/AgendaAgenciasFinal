/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    }
    
}
