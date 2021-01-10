package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalClientFormListener implements ActionListener {
    private final AnimalClientForm form;

    public AnimalClientFormListener(AnimalClientForm form) {
        this.form = form;

        this.form.getConnectButton().addActionListener(this);
        this.form.getDisconnectButton().addActionListener(this);
        this.form.getExitButton().addActionListener(this);

        this.form.getViewListButton().addActionListener(this);
        this.form.getCreateAnimalButton().addActionListener(this);
        this.form.getFeedButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.getConnectButton()) {
            connectHandler();
        } else if (e.getSource() == form.getDisconnectButton()) {
            disconnectHandler();
        } else if (e.getSource() == form.getExitButton()) {
            exitHandler();
        } else if (e.getSource() == form.getViewListButton()) {
            listingPartHandler();
        } else if (e.getSource() == form.getCreateAnimalButton()) {
            creationPartHandler();
        } else if (e.getSource() == form.getFeedButton()) {
            feedingPartHandler();
        }
    }

    private void exitHandler() {
        form.onExit();
    }

    private void disconnectHandler() {
        form.onDisconnect();
    }

    private void connectHandler() {
        GeneralClientController.connect();
        form.onConnect();
    }

    private void listingPartHandler() {
        Checkbox selected = form.getCbgListingGroup().getSelectedCheckbox();
        GeneralClientController.prepareListingRequest(selected.getLabel());
    }

    private void creationPartHandler() {
        String choiceStr = form.getFoodTypeToCreateChoice().getSelectedItem();
        String foodName = form.getNameTextField().getText().trim();
        String foodMass = form.getMassTextField().getText().trim();

        String queryString = "create";

        if (choiceStr.equals("хищник")) {
            queryString += (" -pred");
        } else if (choiceStr.equals("травоядное")) {
            queryString += (" -herb");
        } else if (choiceStr.equals("трава")) {
            queryString += (" -grass");
        }
        queryString += (" -name=" + foodName + " -mass=" + foodMass); // todo: validate mass is number?
    }

    private void feedingPartHandler() {
        //Object whoToFeed = form.getAnimalToFeedChoice().getSelectedObjects()[0]; // see: https://docs.oracle.com/javase/7/docs/api/java/awt/Choice.html#getSelectedObjects()
        //Object prey = form.getFoodPreyChoice().getSelectedObjects()[0];
        //if (whoToFeed instanceof )

        String whoToFeed = form.getAnimalToFeedChoice().getSelectedItem().trim();
        String prey = form.getFoodPreyChoice().getSelectedItem().trim();
    }
}
