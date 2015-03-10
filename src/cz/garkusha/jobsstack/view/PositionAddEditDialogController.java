package cz.garkusha.jobsstack.view;

/**
 * Controller class to create new or edit details of a position.
 *
 * @author Konstantin Garkusha
 */

import cz.garkusha.jobsstack.MainApp;
import cz.garkusha.jobsstack.model.Position;
import cz.garkusha.jobsstack.model.PositionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

public class PositionAddEditDialogController {
    @FXML
    private TextField idField;
    @FXML
    private TextField resultField;
    @FXML
    private TextField companyField;
    @FXML
    private TextField jobTitleField;
    @FXML
    private TextField pathToThePDFField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField webField;
    @FXML
    private TextField personField;
    @FXML
    private DatePicker requestSentDateField;
    @FXML
    private DatePicker answerDateField;
    @FXML
    private TextArea conversationArea;

    private Stage dialogStage;
    private Position position;
    private boolean saveClicked = false;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the TableControllerLayout to give a reference to mainApp.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the position to be edited in the dialog.
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
        idField.setText(String.valueOf(position.getId()));
        resultField.setText(position.getResult());
        companyField.setText(position.getCompany());
        jobTitleField.setText(position.getJobTitle());
        pathToThePDFField.setText(position.getJobTitlePDF());
        locationField.setText(position.getLocation());
        webField.setText(position.getWeb());
        personField.setText(position.getPerson());
        requestSentDateField.setValue(position.getRequestSentDate());
        requestSentDateField.setShowWeekNumbers(false);
        requestSentDateField.setPromptText("dd.mm.yyyy");
        answerDateField.setValue(position.getAnswerDate());
        answerDateField.setShowWeekNumbers(false);
        answerDateField.setPromptText("dd.mm.yyyy");
        conversationArea.setText(position.getConversation());
    }

    /**
     * Sets the new position in the fill dialog.
     *
     * @param filledPosition
     */
    void setFilledPosition(Position filledPosition) {
        idField.setText(String.valueOf(filledPosition.getId()));
        resultField.setText(filledPosition.getResult());
        companyField.setText(filledPosition.getCompany());
        companyField.setPromptText("Can't find company name, need to fill this field manually");
        jobTitleField.setText(filledPosition.getJobTitle());
        jobTitleField.setPromptText("Can't find job title, need to fill this field manually");
        pathToThePDFField.setText(filledPosition.getJobTitlePDF());
        locationField.setText(filledPosition.getLocation());
        locationField.setPromptText("Can't find jobs location, need to fill this field manually");
        webField.setText(filledPosition.getWeb());
        personField.setText(filledPosition.getPerson());
        personField.setPromptText("Can't find contact person, You can to fill this field manually");
        requestSentDateField.setValue(filledPosition.getRequestSentDate());
        requestSentDateField.setPromptText("dd.mm.yyyy");
        answerDateField.setValue(filledPosition.getAnswerDate());
        answerDateField.setPromptText("dd.mm.yyyy");
        conversationArea.setText(filledPosition.getConversation());
        conversationArea.setPromptText("Here you can type all conversation with contact person");
    }


    /**
     * Returns true if the user clicked Save, false otherwise.
     *
     * @return
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            position.setId(Integer.parseInt(idField.getText()));
            position.setResult(resultField.getText());
            position.setCompany(companyField.getText());
            position.setJobTitle(jobTitleField.getText());
            position.setJobTitlePDF(pathToThePDFField.getText());
            position.setLocation(locationField.getText());
            position.setWeb(webField.getText());
            position.setPerson(personField.getText());
            position.setRequestSentDate(requestSentDateField.getValue());
            position.setAnswerDate(answerDateField.getValue());
            position.setConversation(conversationArea.getText());

            mainApp.setDataChanged(true);
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Called when the user clicks fill.
     */
    @FXML
    private void handleFill() {
        String url = webField.getText();
        Position filledPosition = PositionFactory.getNewPosition(url);
        setFilledPosition(filledPosition);
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (companyField.getText() == null || companyField.getText().length() == 0) {
            errorMessage += "No valid company name!\n";
        }
        if (jobTitleField.getText() == null || jobTitleField.getText().length() == 0) {
            errorMessage += "No valid jo title!\n";
        }
        /** TODO  file cheeking*/
        if (pathToThePDFField.getText() == null || pathToThePDFField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }
        if (locationField.getText() == null || locationField.getText().length() == 0) {
            errorMessage += "No valid location!\n";
        }
        if (webField.getText() == null || webField.getText().length() == 0) {
            errorMessage += "No valid url!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}
