package ru.nesferatos.fxsettings.tutorial1.settingsmodel;

import ru.nesferatos.fxsettings.Setting;
import ru.nesferatos.fxsettings.TreeItemValueProvider;

/**
 * Created by nesferatos on 09.01.2016.
 */
public class MailBox implements TreeItemValueProvider {

    @Setting
    private String username;

    @Setting
    private String host;

    @Setting(name = "description")
    private String desc;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getTreeItemValue() {
        if (username != null && host != null) {
            if (!username.isEmpty() || !host.isEmpty()) {
                return username + "@" + host;
            }
        }
        return "";
    }
}
