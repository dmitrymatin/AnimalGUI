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
        boolean connected = GeneralClientController.sendConnectRequest();
        if (!connected)
            return;

        Map<String, String> availableFoodTypes = GeneralClientController.sendGetFoodTypesRequest();

        form.updateAvailableFoodTypes(availableFoodTypes);
        updateFoodsDataOnFormFeedingPart();

        form.onConnect();
    }

    private void disconnectHandler() {
        GeneralClientController.sendDisconnectRequest();

        form.onDisconnect();
    }

    private void listingPartHandler() {
        Checkbox selected = form.getCbgListingGroup().getSelectedCheckbox();
        Map<String, FoodDto> foodCollection = GeneralClientController.sendGetFoodsRequest(selected.getLabel());

        form.updateFoodListing(foodCollection);
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

        updateFoodsDataOnFormFeedingPart();
    }

    private void feedingPartHandler() {
        String animalToFeedChoice = form.getAnimalToFeedChoice().getSelectedItem();
//        int animalToFeedChoiceIndex = form.getAnimalToFeedChoice().getSelectedIndex();

        String preyChoice = form.getFoodPreyChoice().getSelectedItem();
//        int preyChoiceIndex = form.foodPreyChoice.getSelectedIndex();

        String animalToFeedKey = "";
        for (String key : form.getAnimals().keySet()){
            if (animalToFeedChoice.equals(form.getAnimals().get(key).getInfo())){
                animalToFeedKey = key;
                break;
            }
        }

        String preyChoiceKey = "";
        for (String key : form.getFoods().keySet()){
            if (preyChoice.equals(form.getFoods().get(key).getInfo())){
                preyChoiceKey = key;
                break;
            }
        }

        GeneralClientController.sendFeedRequest(animalToFeedKey, preyChoiceKey);

        updateFoodsDataOnFormFeedingPart();
    }


    private void updateFoodsDataOnFormFeedingPart() {
        Map<String, FoodDto> allFoods = GeneralClientController.sendGetFoodsRequest("Все");
        Map<String, FoodDto> animals = GeneralClientController.sendGetFoodsRequest("Животные");

        // get all foods and animals to populate comboboxes in feeding part
        form.updatePreyFoods(allFoods);

        form.updateAnimals(animals);
    }
}
