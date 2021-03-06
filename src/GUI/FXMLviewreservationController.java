/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Booking;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.BookingService;
import GUI.FXMLguestController;
import javafx.scene.control.Alert;
import services.UserService;


/**
 * FXML Controller class
 *
 * @author Jihene
 */
 

public class FXMLviewreservationController implements Initializable {

    @FXML
    private TableView<Booking> listviewbooking;
    @FXML
    private TextField tfsearch;
    ObservableList<Booking> data=FXCollections.observableArrayList();
    BookingService bs=new BookingService();
    //User u=bs.findByIdguest(AuthentificationController.idglobal);
             //.findById(AuthentificationController.idglobal);
    @FXML
    private TableColumn<Booking, Integer> id;
    @FXML
    private TableColumn<Booking, Integer> idhost;
    @FXML
    private TableColumn<Booking, Integer> idguest;
    @FXML
    private TableColumn<Booking, Date> bookingdate;
    @FXML
    private TableColumn<Booking, Date> startdate;
    @FXML
    private TableColumn<Booking, Date> enddate;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService() ;
    User u=us.findById(AuthentificationController.idglobal);
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        recherche_avance();
        
               
    }    

    @FXML
    private void gotoaddstay(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLhost.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/GUI/newCascadeStyleSheet.css");
            stage.setTitle("reservation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLviewreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void disconnect(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void delete(ActionEvent event) {
        
        if(listviewbooking.getSelectionModel().getSelectedItem()!=null){
            bs.delete(listviewbooking.getSelectionModel().getSelectedItem().getId());
        }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" A Booking  was canceled");
            alert.setHeaderText("A Booking  was canceled");
            alert.setContentText("You have succesfully canceled your booking");
            alert.showAndWait();
        refreshlist();
        
        
    }
   
    public void refreshlist(){
        
       
        
         data.clear();
        //data=FXCollections.observableArrayList(bs.findByIdhost(iduser));//fil integration
        data=FXCollections.observableArrayList(bs.findByIdguest(u.getId()));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idhost.setCellValueFactory(new PropertyValueFactory<>("idhost"));
        idguest.setCellValueFactory(new PropertyValueFactory<>("idguest"));
        bookingdate.setCellValueFactory(new PropertyValueFactory<>("bookingdate"));
        startdate.setCellValueFactory(new PropertyValueFactory<>("firstDate"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("lastDate"));
        
        listviewbooking.setItems(data);
        
    }
    public void recherche_avance(){
        FilteredList<Booking> filtereddata=new FilteredList<>(data,b->true);
        tfsearch.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(b->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(String.valueOf(b.getId()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getIdguest()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(b.getIdhost()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(b.getFirstDate().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getLastDate().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(b.getBookingdate().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    return false;
                }
                
            });
        });
        
        listviewbooking.setItems(filtereddata);
    }

    @FXML
    private void sortbydate(ActionEvent event) {
        data=FXCollections.observableArrayList(bs.sortedByDate());
        listviewbooking.setItems(data);
                
    }
    
}
