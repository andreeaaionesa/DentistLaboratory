package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.Patient;
import model.Appointment;
import services.Service;
import services.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {

    @FXML
        private TextField Name, Age, Cnp, Problem;
    @FXML
    private Text PId;

    @FXML
    private TextField Date, Hour;

    @FXML
    private TextField fname;

    @FXML
    private TextField fname2;

    @FXML
    private TableView<Patient> pResults;
    @FXML
    private TableView<Patient> pResults2;

    @FXML
    private TableView<Appointment> appointmentResults;


    private Service Services;

    public Controller() {

    }

    public void setService(Service service) {
        this.Services = service;
    }

   /* @FXML
   // public void initialize() {
        //StringConverter<LocalDate> converter = new StringConverter<>() {
           // @Override
           // public String toString(LocalDate date) {
              //  return (date != null) ? dateFormatter.format(date) : "";
          //  }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        };

        Date.setConverter(converter);
        Date.setValue(LocalDate.now());
        Date.setEditable(false);
    } */

    @FXML
    public void addpHandler(ActionEvent actionEvent) {
        String name = Name.getText();
        String age = Age.getText();
        String cnp = Cnp.getText();
        String problem = Problem.getText();
        if (checkString(name) && checkString(age) && checkString(cnp) && checkString(problem)) {
            try {
                int aged = Integer.parseInt(Age.getText());
                int id = Services.addP(name, aged, cnp, problem);
                PId.setText("Patient identification number is " + id);
                showNotification("Patient successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("The age must be a number! ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        Name.setText("");
        Age.setText("");
        Cnp.setText("");
        Problem.setText(" ");
    }

    private void showNotification(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private boolean checkString(String s) {
        return s == null || s.isEmpty() ? false : true;
    }

    @FXML
    public void getAllF(ActionEvent actionEvent) {
        List<Patient> results = Services.ar.findAllpatients();
        pResults.getItems().clear();
        pResults.getItems().addAll(results);
    }

    @FXML
    public void addAppointmentHandler(ActionEvent actionEvent) {
        int selectedRace = pResults2.getSelectionModel().getSelectedIndex();
        if (selectedRace < 0) {
            showNotification("You must select a patient first! ", Alert.AlertType.ERROR);
            return;
        }
        String tn = Date.getText();
        String c = Hour.getText();


        if (checkString(tn) && checkString(c)) {
            try {Patient p = pResults2.getItems().get(selectedRace);


                Services.addAppointment(tn,c,p);
                pResults2.getItems().remove(selectedRace);
                    showNotification("Appointment successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("went wrong ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void searchpHandler(ActionEvent actionEvent) {
        String searchName = fname.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<Patient> results = Services.getFByName(searchName);
        pResults2.getItems().clear();
        pResults2.getItems().addAll(results);
    }

    @FXML
    public void searchteamHandler(ActionEvent actionEvent) {
        String searchName = fname2.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<Appointment> results = Services.getTeamByRace(searchName);
        appointmentResults.getItems().clear();
        appointmentResults.getItems().addAll(results);
    }
}