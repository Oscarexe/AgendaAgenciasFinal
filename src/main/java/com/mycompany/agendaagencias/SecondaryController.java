/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Agenciaspublicitarias;
import com.mycompany.agendaagencias.entities.Ambito;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    
    @FXML
    private ComboBox<Ambito> comboBoxAmbito;
    
    @FXML
    private DatePicker datepickerFecha ;

    
    
    
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
       
       //Boolean para ver si los datos son correctos
       boolean errorFormato = false;
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
       
       
        //Objetos de tabla relacionada: Ambito
        if(comboBoxAmbito.getValue() != null) {
            agencia.setAmbito(comboBoxAmbito.getValue());
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Debe indicar un ambito");
            alert.showAndWait();
            errorFormato = true;
        }
        
        //PrecioHora
        if(!textFieldHora.getText().isEmpty()) {
            try {
                agencia.setPreciohora(BigDecimal.valueOf(Double.valueOf(textFieldHora.getText()).doubleValue()));
            } catch(NumberFormatException ex) {
                errorFormato = true;
                Alert alert = new Alert(AlertType.INFORMATION, "PrecioHora no válido");
                alert.showAndWait();
                textFieldHora.requestFocus();
            }    
        }
        
        //Trabajos Realizados
        if(!textFieldTrabajosRealizados.getText().isEmpty()) {
            try {
                agencia.setTrabajosrealizados(Integer.valueOf(textFieldTrabajosRealizados.getText()));
            } catch(NumberFormatException ex) {
                errorFormato = true;
                Alert alert = new Alert(AlertType.INFORMATION, "Número de hijos no válido");
                alert.showAndWait();
                textFieldTrabajosRealizados.requestFocus();
            }
        }       
        //CobraDespServicio(boolean)
        agencia.setCobradespservicio(checkBoxCobro.isSelected());
        
        //AñoCreacion (fecha)
        if(datepickerFecha.getValue() != null) {
            LocalDate localDate = datepickerFecha.getValue();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            agencia.setAnnocreacion(date);
        } else {
            agencia.setAnnocreacion(null);
        }
       
        // Guardar en la base de datos
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
        
        
        
        // Paso 11
        
        //Cobro (boolean)
        if (agencia.getCobradespservicio()!= null) {
            checkBoxCobro.setSelected(agencia.getCobradespservicio());
        }
        
        //Fecha
        if (agencia.getAnnocreacion()!= null) {
            Date date = agencia.getAnnocreacion();
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            datepickerFecha.setValue(localDate);
        }
        
        //preciohora
        if (agencia.getPreciohora()!= null) {
            textFieldHora.setText(agencia.getPreciohora().toString());
        } 
        
        //TrabajosRealizados
        if(agencia.getTrabajosrealizados()!= null) {
            textFieldTrabajosRealizados.setText(agencia.getTrabajosrealizados().toString());
        }
        
        //Busqueda en la BD para rellenar el ComboBox Ambito
        Query queryAmbitoFindAll = entityManager.createNamedQuery("Ambito.findAll");
        List listAmbito = queryAmbitoFindAll.getResultList();
        comboBoxAmbito.setItems(FXCollections.observableList(listAmbito));
        //En caso de que el objeto Persona tenga asignada alguna Provincia, 
        //se deberá seleccionar para que se muestre directamente en el ComboBox.
        if (agencia.getAmbito() != null) {
            comboBoxAmbito.setValue(agencia.getAmbito());
        }
        
        //Como se mostraran los datos en el comboBox
        comboBoxAmbito.setCellFactory((ListView<Ambito> l) -> new ListCell<Ambito>() {
            @Override
            protected void updateItem(Ambito ambito, boolean empty) {
                super.updateItem(ambito, empty);
                if (ambito == null || empty) {
                    setText("");
                } else {
                    setText(ambito.getCodigo() + "-" + ambito.getAmbito());
                }
            }
        });
        
        //
        comboBoxAmbito.setConverter(new StringConverter<Ambito>() {
            @Override
            public String toString(Ambito ambito) {
                if (ambito == null) {
                    return null;
                } else {
                    return ambito.getCodigo() + "-" + ambito.getAmbito();
                }
            }
            @Override
            public Ambito fromString(String userId) {
                return null;
            }
        });
    }
    
    
    
    
}
