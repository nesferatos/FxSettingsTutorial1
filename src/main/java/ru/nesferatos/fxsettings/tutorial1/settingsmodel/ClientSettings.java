package ru.nesferatos.fxsettings.tutorial1.settingsmodel;

import ru.nesferatos.fxsettings.Setting;
import ru.nesferatos.fxsettings.TreeItemValueProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nesferatos on 09.01.2016.
 */
public class ClientSettings implements TreeItemValueProvider{

    private static ClientSettings instance;

    private ClientSettings() {
    }

    public static ClientSettings getInstance() {
        if (instance == null) {
            instance = new ClientSettings();
        }
        return instance;
    }

    @Setting(registryName = "mailBoxes", name = "main mail box")
    private MailBox mainMailBox;

    @Setting(registryName = "mailBoxes", name = "hidden mail boxes")
    private List<MailBox> hiddenMailBoxes = new ArrayList();

    @Setting(name = "mail boxes", factoryName = "mailBoxFactory", isEditableField = false)
    private List<MailBox> mailBoxes = new ArrayList<>();

    @Override
    public String getTreeItemValue() {
        return "Settings";
    }
}
