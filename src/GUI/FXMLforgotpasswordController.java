/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author jarrraya
 */
public class FXMLforgotpasswordController implements Initializable {

    @FXML
    private TextField tfemail_tel;
    @FXML
    private PasswordField pfnew_password;
    @FXML
    private PasswordField pfconfirm;
    @FXML
    private Button btupdate;
    @FXML
    private Button btsearch;
    @FXML
    private TextField tfverificationcode;
    int n;
    User u =new User();
    UserService us=new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btupdate.setVisible(false);
        pfconfirm.setVisible(false);
        pfnew_password.setVisible(false);
        tfverificationcode.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
    }    

    @FXML
    private void update(ActionEvent event) {
        
        if(tfverificationcode.getText().equals(String.valueOf(n)) && pfconfirm.getText().equals(pfnew_password.getText())){
            u.setPassword(pfnew_password.getText());
            us.update(u.getId(), u);
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" RestPassword ");
            alert.setContentText("You have successfully modified your password");
            alert.setHeaderText("Successful password modification");
           alert.showAndWait();
            
            try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/Authentification.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("forgot password");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }
    }

    @FXML
    private void search(ActionEvent event) {
        if(us.findByEmail(tfemail_tel.getText()).isEmpty()==false){
            u=us.findByEmail(tfemail_tel.getText()).get(0);
            Mailapi.send("testapimail63@gmail.com", "TESTapimail2022", u.getEmail(), "Forgot password", "This is your code for updating your password: "+n);
            tfemail_tel.setVisible(false);
            btsearch.setVisible(false);
            btupdate.setVisible(true);
            pfconfirm.setVisible(true);
            pfnew_password.setVisible(true);
            tfverificationcode.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email does not exist");
            alert.setTitle("Invalid email");
            alert.setContentText("Please check again the provided email");
            alert.showAndWait();
        }
    }
    
}
