package ru.nesferatos.fxsettings.tutorial1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nesferatos.fxsettings.*;
import ru.nesferatos.fxsettings.tutorial1.settingsmodel.ClientSettings;
import ru.nesferatos.fxsettings.tutorial1.settingsmodel.MailBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller implements Initializable {
    @FXML
    public BorderPane pane;
    @FXML
    public MenuItem settingsMenuItem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        class MailBoxSetting {
            @Setting
            String address;

            @Override
            public String toString() {
                return "mailbox setting";
            }
        }

        FactoryRegistry.getInstance().register("mailBoxFactory", new SettingsFactory<MailBox, MailBoxSetting>() {
            final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

            @Override
            public MailBox createProduct(MailBoxSetting settingObj, PropertyTreeItem parent) throws ValidationException {
                MailBox mailBox = new MailBox();

                if (settingObj.address == null || settingObj.address.isEmpty())
                    throw new ValidationException("email address cannot be empty");

                Matcher matcher = emailPattern.matcher(settingObj.address);
                if (!matcher.matches())
                    throw new ValidationException("email not valid");

                String username = settingObj.address.split("@")[0];
                String host = settingObj.address.split("@")[1];
                mailBox.setUsername(username);
                mailBox.setHost(host);
                SettingsRegistry.getInstance().put("mailBoxes", mailBox);
                return mailBox;
            }

            @Override
            public MailBoxSetting getProductCreateParams(PropertyTreeItem parent) {
                return new MailBoxSetting();
            }
        });

        FxSettings fxSettings = new FxSettings(ClientSettings.getInstance());
        settingsMenuItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(fxSettings, 700, 500));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });


    }
}
