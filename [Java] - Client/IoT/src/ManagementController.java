import Windows.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagementController implements Initializable{
    @FXML private ImageView motorUp;
    @FXML private ImageView motorDown;
    @FXML private ImageView autoMode;
    @FXML private ImageView led;
    @FXML private TextArea helpLabel;
    @FXML private Button closeHelpLblBtn;


    private final ModelData modelData = ModelData.getInstance();

    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = modelData.getModel();
    }

    @FXML
    public void motorUpClicked() throws Exception{
        String state = model.writeMessage('u');
        if (state.equals("MOTORUPOFF")){
            motorUp.setImage(new Image("resources/motorUF.png"));
            motorDown.setDisable(false);
        }
        else if (state.equals("MOTORUPON")){
            motorUp.setImage(new Image("resources/motorUT.png"));
            motorDown.setDisable(true);
        }
        else if (state.equals("NOCONNECTION")){
            reconnect();
        }
    }
    @FXML
    public void motorDownClicked() throws Exception{
        String state = model.writeMessage('d');
        if (state.equals("MOTORDOWNOFF")){
            motorDown.setImage(new Image("resources/motorDF.png"));
            motorUp.setDisable(false);
        }
        else if (state.equals("MOTORDOWNON")){
            motorDown.setImage(new Image("resources/motorDT.png"));
            motorUp.setDisable(true);
        }
        else if (state.equals("NOCONNECTION")){
            reconnect();
        }
    }
    @FXML
    public void ledClicked() throws Exception{
        String state = model.writeMessage('l');
        if (state.equals("LEDOFF")){
            led.setImage(new Image("resources/ledF.png"));
        }
        else if (state.equals("LEDON")){
            led.setImage(new Image("resources/ledT.png"));
        }
        else if (state.equals("NOCONNECTION")){
            reconnect();
        }
    }
    @FXML
    public void autoModeClicked() throws Exception{
        String state = model.writeMessage('a');
        if (state.equals("AUTOMODEON")){
            autoMode.setImage(new Image("resources/ledT.png"));
            motorUp.setDisable(true);
            motorDown.setDisable(true);
            led.setDisable(true);
        }
        else if (state.equals("AUTOMODEOFF")){
            autoMode.setImage(new Image("resources/sample.png"));
            motorUp.setDisable(false);
            motorDown.setDisable(false);
            led.setDisable(false);
        }
        else if (state.equals("NOCONNECTION")){
            reconnect();
        }
    }

    @FXML
    public void getMessage(){
        System.out.println("Hello world!");
    }

    @FXML
    public void reconnect(){
        try{
            model.closeSocket();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage) motorUp.getScene().getWindow();
        stage.close();
        try{
            Platform.runLater( () -> {
                try {
                    new Main().start( new Stage() );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }catch(Exception e){
            System.out.println(e.getMessage());
            new FileLogger().writeLogs(e.getMessage());
        }
    }


    @FXML
    public void quitApp(){
        try{
            model.closeSocket();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage)motorUp.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openHelpLabel(){
        helpLabel.setOpacity(1.0);
        helpLabel.setDisable(false);
        closeHelpLblBtn.setOpacity(1.0);
        //closeHelpLblBtn.setDisable(false);
    }
    @FXML
    public void closeHelpLabel(){
        helpLabel.setOpacity(0.0);
        helpLabel.setDisable(true);
        closeHelpLblBtn.setOpacity(0.0);
        //closeHelpLblBtn.setDisable(true);
    }

}
