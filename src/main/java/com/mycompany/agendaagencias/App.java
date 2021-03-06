package com.mycompany.agendaagencias;

import com.mycompany.agendaagencias.entities.Ambito;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    EntityManager em ;
   
    
    @Override   
    public void start(Stage stage) throws IOException {
        StackPane rootMain = new StackPane();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Pane primary = fxmlLoader.load();
        rootMain.getChildren().add(primary);
        
        scene = new Scene(rootMain, 640, 480);
        
         // conexion con la base de datos
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgenciasPublicitariasPU"); // Comprobar nombre de la PU
        em = emf.createEntityManager();
        
        //Conexion con primary controller        
        PrimaryController primaryController = (PrimaryController) fxmlLoader.getController(); 
        primaryController.setEntityManager(em);
        primaryController.cargarTodasAgencias();
        
        // esto es un ejemplo para ver si sirve la conexion
        Query queryAmbitos = em.createNamedQuery("Ambito.findAll");
        List<Ambito> listAmbitos= queryAmbitos.getResultList(); 
        for (Ambito ambito : listAmbitos){
            System.out.println(ambito.getAmbito());
        }
        
        
        stage.setScene(scene);
        stage.show();

    }
//metodo stop
    @Override
    public void stop() throws Exception {
        em.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:BDAgendaAgencias;shutdown=true"); 
        } catch (SQLException ex) { 
        }        
    }
    public static void main(String[] args) {
        launch();
    }

}