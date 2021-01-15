package app;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimalClientForm extends Frame {
    HashMap<String, String> availableFoodTypes = new HashMap<>();
    ArrayList<Checkbox> checkboxes = new ArrayList<>();
    ArrayList<Map.Entry<String, FoodDto>> animals = new ArrayList<>();
    ArrayList<Map.Entry<String, FoodDto>> foods = new ArrayList<>();

    // networking part
    Panel netWorkingPanel = new Panel();
    TextField hostTextField = new TextField();
    TextField portTextField = new TextField();
    Button connectButton = new Button("Подключиться");
    Button disconnectButton = new Button("Отключиться");
    Button exitButton = new Button("Выход");

    // listing part
    Panel listingPanel = new Panel();
    CheckboxGroup cbgListingGroup = new CheckboxGroup();
    //Scrollbar scrollbar = new Scrollbar();
    /*Checkbox cbViewAll; cbViewPredators, cbViewHerbivores, cbViewGrasses;*/
    Button viewListButton = new Button("Просмотреть");

    List foodsList = new List();

    // creation part
    Panel creationPanel = new Panel();
    Label creationLabel = new Label("Создание животного или травы");
    CheckboxGroup cbgCreationGroup = new CheckboxGroup();
    Button createAnimalButton = new Button("Создать");
    Choice foodTypeToCreateChoice = new Choice();
    Label nameLabel = new Label("Имя");
    TextField nameTextField = new TextField();
    Label massLabel = new Label("Масса");
    TextField massTextField = new TextField();

    // feeding part
    Panel feedingPanel = new Panel();
    Label feedingLabel = new Label("Покормить");
    Label animalTypeLabel = new Label("кого:");
    Choice animalToFeedChoice = new Choice();
    Label foodTypeLabel = new Label("чем:");
    Choice foodPreyChoice = new Choice();
    Button feedButton = new Button("Покормить");

    // status part
    Panel statusPanel = new Panel();
    Label statusLabel = new Label("Статус операции:");
    TextArea statusMessageTextArea = new TextArea();

    public AnimalClientForm(String title) throws HeadlessException {
        super(title);

        setSize(760, 760);
        setLayout(null);

        initialiseNetworkingPart();
        initialiseListingPart();
        initialiseCreationPart();
        initialiseFeedingPart();
        initialiseStatusPart();

        setVisible(true);
    }

    private void initialiseNetworkingPart() {
        netWorkingPanel.setBounds(30, 30, 700, 100);
        netWorkingPanel.setBackground(Color.LIGHT_GRAY);

        hostTextField.setBounds(10, 10, 120, 20);
        portTextField.setBounds(140, 10, 120, 20);
        connectButton.setBounds(10, 50, 120, 30);
        disconnectButton.setBounds(140, 50, 120, 30);
        exitButton.setBounds(270, 50, 120, 30);

        netWorkingPanel.add(hostTextField);
        netWorkingPanel.add(portTextField);
        netWorkingPanel.add(connectButton);
        netWorkingPanel.add(disconnectButton);
        netWorkingPanel.add(exitButton);
        netWorkingPanel.setLayout(null);

        add(netWorkingPanel);

        disconnectButton.setEnabled(false);

        hostTextField.setText("localhost");
        portTextField.setText("7070");
    }

    private void initialiseListingPart() {
        //cbViewAll = new Checkbox("Все", cbgListingGroup, false);
        /*
        cbViewPredators = new Checkbox("Хищники", cbgListingGroup, false);
        cbViewHerbivores = new Checkbox("Травоядные", cbgListingGroup, false);
        cbViewGrasses = new Checkbox("Трава", cbgListingGroup, false);
         */

        listingPanel.setBounds(30, 150, 700, 150);
        listingPanel.setBackground(new Color(226, 226, 226));

//        cbViewAll.setBounds(10, 0, 150, 50);
//        lastCheckboxCoordX = cbViewAll.getX();

        /*
        cbViewPredators.setBounds(160, 0, 150, 50);
        cbViewHerbivores.setBounds(310, 0, 150, 50);
        cbViewGrasses.setBounds(460, 0, 150, 50);
         */

        foodsList.setBounds(10, 50, 400, 80);
        viewListButton.setBounds(420, 100, 120, 30);

        //listingPanel.add(cbViewAll);
        /*
        listingPanel.add(cbViewPredators);
        listingPanel.add(cbViewHerbivores);
        listingPanel.add(cbViewGrasses);
         */
        listingPanel.add(viewListButton);
        listingPanel.add(foodsList);

        listingPanel.setLayout(null);

        add(listingPanel);

//        for (int i = 0; i < 30; i++)
//            list.add(Integer.toString(i));

        listingPanel.setEnabled(false);
    }

    private void initialiseCreationPart() {
        creationPanel.setBackground(new Color(226, 226, 226));
        creationPanel.setBounds(30, 320, 700, 130);

        creationLabel.setBounds(10, 10, 180, 30);
        foodTypeToCreateChoice.setBounds(200, 15, 120, 30);

        nameLabel.setBounds(10, 50, 30, 20);
        nameTextField.setBounds(50, 50, 120, 20);
        massLabel.setBounds(190, 50, 40, 20);
        massTextField.setBounds(240, 50, 120, 20);

        createAnimalButton.setBounds(10, 85, 120, 30);

        /*
        foodTypeToCreateChoice.add("Хищник");
        foodTypeToCreateChoice.add("Травоядное");
        foodTypeToCreateChoice.add("Трава");
         */

        creationPanel.add(creationLabel);
        creationPanel.add(createAnimalButton);
        creationPanel.add(foodTypeToCreateChoice);
        creationPanel.add(nameLabel);
        creationPanel.add(nameTextField);
        creationPanel.add(massLabel);
        creationPanel.add(massTextField);

        creationPanel.setLayout(null);
        add(creationPanel);


        creationPanel.setEnabled(false);
    }

    private void initialiseFeedingPart() {
        feedingPanel.setBackground(new Color(226, 226, 226));
        feedingPanel.setBounds(30, 470, 700, 120);

        feedingLabel.setBounds(10, 10, 180, 20);
        animalTypeLabel.setBounds(10, 40, 30, 20);
        animalToFeedChoice.setBounds(50, 40, 350, 30);
        foodTypeLabel.setBounds(370, 40, 30, 20);
        foodPreyChoice.setBounds(410, 40, 350, 30);
        feedButton.setBounds(10, 75, 120, 30);

        feedingPanel.add(feedingLabel);
        feedingPanel.add(animalTypeLabel);
        feedingPanel.add(animalToFeedChoice);
        feedingPanel.add(foodTypeLabel);
        feedingPanel.add(foodPreyChoice);
        feedingPanel.add(feedButton);

        feedingPanel.setLayout(null);
        add(feedingPanel);

        feedingPanel.setEnabled(false);
    }

    private void initialiseStatusPart() {
        statusPanel.setBackground(new Color(226, 226, 226));
        statusPanel.setBounds(30, 610, 700, 130);

        statusLabel.setBounds(10, 10, 100, 20);
        statusMessageTextArea.setBounds(120, 10, 570, 110);
        statusMessageTextArea.setBackground(new Color(210, 210, 210));
        statusMessageTextArea.setEditable(false);

        statusPanel.add(statusLabel);
        statusPanel.add(statusMessageTextArea);

        statusPanel.setLayout(null);
        add(statusPanel);

        statusPanel.setEnabled(false);
    }

    public void onConnect() {
        connectButton.setEnabled(false);
        disconnectButton.setEnabled(true);
        exitButton.setEnabled(false);

        listingPanel.setEnabled(true);
        creationPanel.setEnabled(true);
        feedingPanel.setEnabled(true);
        statusPanel.setEnabled(true);
    }

    public void onDisconnect() {
        connectButton.setEnabled(true);
        disconnectButton.setEnabled(false);
        exitButton.setEnabled(true);

        listingPanel.setEnabled(false);
        creationPanel.setEnabled(false);
        feedingPanel.setEnabled(false);
        statusPanel.setEnabled(false);
    }

    public void onExit() {
        this.dispose();
    }


    public void updateAvailableFoodTypes(Map<String, String> foodTypes) {
        this.availableFoodTypes.clear();
        this.availableFoodTypes.putAll(foodTypes);

        updateListingCheckboxes();

        updateFoodCreationChoice();
    }

    private void updateListingCheckboxes() {
        for (Checkbox cb : checkboxes) {
            listingPanel.remove(cb);
        }
        this.checkboxes.clear();

        int x = 10;
        final int offset = 150;
        Checkbox cb = new Checkbox("Все", cbgListingGroup, true);
        cb.setBounds(x, 0, 150, 50);
        this.checkboxes.add(cb);
        this.listingPanel.add(cb);

        for (String aft : this.availableFoodTypes.values()) {
            cb = new Checkbox(aft, cbgListingGroup, false);
            cb.setBounds(x += offset, 0, 150, 50);

            this.checkboxes.add(cb);
            this.listingPanel.add(cb);
        }
    }

    private void updateFoodCreationChoice() {
        this.foodTypeToCreateChoice.removeAll();
        for (String aft : this.availableFoodTypes.values())
            foodTypeToCreateChoice.add(aft);
    }

    public void updateFoodListing(Map<String, FoodDto> foodCollection) {
        if (foodCollection != null) {
            this.foodsList.removeAll();
            for (FoodDto food : foodCollection.values())
                this.foodsList.add(food.getInfo());
        }
    }

    public void updatePreyFoods(Map<String, FoodDto> allFoods) {
        this.foods.clear();
        this.foodPreyChoice.removeAll();

        this.foods.addAll(allFoods.entrySet());
        for (Map.Entry<String, FoodDto> foodDtoEntry : this.foods){
            this.foodPreyChoice.add(foodDtoEntry.getValue().getInfo());
        }
    }

    public void updateAnimals(Map<String, FoodDto> animals) {
        this.animals.clear();
        this.animalToFeedChoice.removeAll();

        this.animals.addAll(animals.entrySet());
        for (Map.Entry<String, FoodDto> animalDtoEntry : this.animals)
            this.animalToFeedChoice.add(animalDtoEntry.getValue().getInfo());
    }

    public HashMap<String, String> getAvailableFoodTypes() {
        return availableFoodTypes;
    }

    public ArrayList<Map.Entry<String, FoodDto>> getAnimals() {
        return animals;
    }

    public ArrayList<Map.Entry<String, FoodDto>> getFoods() {
        return foods;
    }

    public TextField getHostTextField() {
        return hostTextField;
    }

    public TextField getPortTextField() {
        return portTextField;
    }

    public Button getConnectButton() {
        return connectButton;
    }

    public Button getDisconnectButton() {
        return disconnectButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public CheckboxGroup getCbgListingGroup() {
        return cbgListingGroup;
    }

//    public Checkbox getCbViewAll() {
//        return cbViewAll;
//    }

/*    public Checkbox getCbViewPredators() {
        return cbViewPredators;
    }

    public Checkbox getCbViewHerbivores() {
        return cbViewHerbivores;
    }

    public Checkbox getCbViewGrasses() {
        return cbViewGrasses;
    }*/

    public Button getViewListButton() {
        return viewListButton;
    }

    public List getFoodsList() {
        return foodsList;
    }

    public CheckboxGroup getCbgCreationGroup() {
        return cbgCreationGroup;
    }

    public Button getCreateAnimalButton() {
        return createAnimalButton;
    }

    public Choice getFoodTypeToCreateChoice() {
        return foodTypeToCreateChoice;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getMassTextField() {
        return massTextField;
    }

    public Choice getAnimalToFeedChoice() {
        return animalToFeedChoice;
    }

    public Choice getFoodPreyChoice() {
        return foodPreyChoice;
    }

    public Button getFeedButton() {
        return feedButton;
    }

    public TextArea getStatusMessageTextArea() {
        return statusMessageTextArea;
    }
}