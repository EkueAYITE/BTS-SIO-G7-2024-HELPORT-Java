package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametresController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValiderParametres;
    @javafx.fxml.FXML
    private TextField txtMail;
    @javafx.fxml.FXML
    private TextField txtMdp;
    @javafx.fxml.FXML
    private TextField txtTel;
    @javafx.fxml.FXML
    private TextField txtNiv;
    private  User user;


    @javafx.fxml.FXML
    public void btnValiderParametresClicked(ActionEvent actionEvent) {
        /*user = UserHolder.getInstance().getUser();
        txtMail.setText(user.getEmail());
        txtMdp.setText(user.getPassword());
        txtNiv.setText(user.getNiveau());
        txtTel.setText(user.getTelephone());*/


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserHolder.getInstance().getUser();
        txtMail.setText(user.getEmail());
        txtMdp.setText(user.getPassword());
        txtNiv.setText(user.getNiveau());
        txtTel.setText(user.getTelephone());
    }
}
