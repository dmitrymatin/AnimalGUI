package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalClientFormListener implements ActionListener {
    private final AnimalClientForm form;

    public AnimalClientFormListener(AnimalClientForm form) {
        this.form = form;

        this.form.getViewListButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.getViewListButton()) {
            listingPartHandler();
        } else if (e.getSource() == form.getCreateAnimalButton()) {
            creationPartHandler();
        } else if (e.getSource() == form.getFeedButton()){
            feedingPartHandler();
        }
    }

    private void listingPartHandler() {
        Checkbox selected = form.getCbgListingGroup().getSelectedCheckbox();
        String queryString = "get";

        if (selected == form.getCbViewAll()) {
            // просмотр всех
            queryString += " -all";
        } else if (selected == form.getCbViewPredators()) {
            // просмотр хищников
            queryString += " -pred [-all]";
        } else if (selected == form.getCbViewHerbivores()) {
            // просмотр травоядных
            queryString += " -herb [-all]";
        } else if (selected == form.getCbViewGrasses()) {
            // просмотр травы
            queryString += " -grass [-all]";
        }
        GeneralClientController.sendRequest(queryString);
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

        GeneralClientController.sendRequest(queryString);
    }

    private void feedingPartHandler() {
        //Object whoToFeed = form.getAnimalToFeedChoice().getSelectedObjects()[0]; // see: https://docs.oracle.com/javase/7/docs/api/java/awt/Choice.html#getSelectedObjects()
        //Object prey = form.getFoodPreyChoice().getSelectedObjects()[0];
        //if (whoToFeed instanceof )

        String whoToFeed = form.getAnimalToFeedChoice().getSelectedItem().trim();
        String prey = form.getFoodPreyChoice().getSelectedItem().trim();

    }
}
