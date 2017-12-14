package de.main;

import de.main.data_management.ServerCommunication;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IndexController {

    public Label connectionStatus;
    public Label ipAdress;

    public void connect(ActionEvent actionEvent) throws UnknownHostException {

        connectionStatus.setText("Connecting...");

        ipAdress.setText(InetAddress.getLocalHost().toString());

        ServerCommunication.connect();
    }

    public void updateConnectionLabel(String state) {
        connectionStatus.setText(state);
    }
}
