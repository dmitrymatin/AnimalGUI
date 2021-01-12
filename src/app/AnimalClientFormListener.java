package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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

    private void connectHandler() {
        Map<String, String> availableFoodTypes = GeneralClientController.sendConnectRequest();

        // initialising choice box and adding checkboxes dynamically
        form.getAvailableFoodTypes().putAll(availableFoodTypes);
        if (availableFoodTypes != null) {
            for (String foodType : availableFoodTypes.values()) {
                form.getFoodTypeToCreateChoice().add(foodType);
                form.addCheckboxToCreationGroup(foodType);
            }
        }

        updateFormData();

        form.onConnect();
    }

    private void disconnectHandler() {
        GeneralClientController.sendDisconnectRequest();

        form.onDisconnect();
    }

    private void listingPartHandler() {
        Checkbox selected = form.getCbgListingGroup().getSelectedCheckbox();
        Map<String, String> foodCollection = GeneralClientController.sendGetRequest(selected.getLabel());

        if (foodCollection != null) {
            for (String food : foodCollection.values())
                form.getList().add(food);
        }
    }

    private void creationPartHandler() {
        String choiceStr = form.getFoodTypeToCreateChoice().getSelectedItem();
        String foodName = form.getNameTextField().getText().trim();
        String foodMass = form.getMassTextField().getText().trim();
        String choiceKey = "";

        for (String key : form.getAvailableFoodTypes().keySet()) {
            if (choiceStr.equals(form.getAvailableFoodTypes().get(key))) {
                choiceKey = key;
                break;
            }
        }

        GeneralClientController.sendCreateRequest(choiceKey, foodName, foodMass);

        updateFormData();
    }

    private void feedingPartHandler() {
        String animalToFeedChoice = form.getAnimalToFeedChoice().getSelectedItem();
        String preyChoice = form.getFoodPreyChoice().getSelectedItem();

        String animalToFeedKey = "";
        for (String key : form.getAnimals().keySet()){
            if (animalToFeedChoice.equals(form.getAnimals().get(key))){
                animalToFeedKey = key;
                break;
            }
        }

        String preyChoiceKey = "";
        for (String key : form.getFoods().keySet()){
            if (preyChoice.equals(form.getFoods().get(key))){
                preyChoiceKey = key;
                break;
            }
        }

        GeneralClientController.sendFeedRequest(animalToFeedKey, preyChoiceKey);

        updateFormData();
    }


    private void updateFormData() {
        Map<String, String> allFoods = GeneralClientController.sendGetRequest("Все");
        Map<String, String> animals = GeneralClientController.sendGetRequest("Животные");

        // get all foods and animals to populate comboboxes in feeding part
        if (allFoods != null) {
            form.getFoods().putAll(allFoods);
            for (String food : allFoods.values())
                form.getFoodPreyChoice().add(food);
        }

        if (animals != null) {
            form.getAnimals().putAll(animals);
            for (String animal : animals.values())
                form.getAnimalToFeedChoice().add(animal);
        }
    }
}
