package app;

import java.awt.*;
import java.util.HashMap;

public class AnimalClientForm extends Frame {
    HashMap<String, String> availableFoodTypes = new HashMap<>();
    HashMap<String, String> animals = new HashMap<>();
    HashMap<String, String> foods = new HashMap<>();

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
    Checkbox cbViewAll; /*cbViewPredators, cbViewHerbivores, cbViewGrasses;*/
    int lastCheckboxCoordX;
    Button viewListButton = new Button("Просмотреть");

    List list = new List();

    // creation part
    Panel creationPanel = new Panel();
    Label creationLabel = new Label("Создание животного или травы");
    CheckboxGroup cbgCreationGroup = new CheckboxGroup();
    Checkbox cb5 = new Checkbox("fff", false);
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
        cbViewAll = new Checkbox("Все", cbgListingGroup, false);
        /*
        cbViewPredators = new Checkbox("Хищники", cbgListingGroup, false);
        cbViewHerbivores = new Checkbox("Травоядные", cbgListingGroup, false);
        cbViewGrasses = new Checkbox("Трава", cbgListingGroup, false);
         */

        listingPanel.setBounds(30, 150, 700, 150);
        listingPanel.setBackground(new Color(226, 226, 226));

        cbViewAll.setBounds(10, 0, 150, 50);
        lastCheckboxCoordX = cbViewAll.getX();
        /*
        cbViewPredators.setBounds(160, 0, 150, 50);
        cbViewHerbivores.setBounds(310, 0, 150, 50);
        cbViewGrasses.setBounds(460, 0, 150, 50);
         */

        list.setBounds(10, 50, 300, 80);
        viewListButton.setBounds(320, 100, 120, 30);

        listingPanel.add(cbViewAll);
        /*
        listingPanel.add(cbViewPredators);
        listingPanel.add(cbViewHerbivores);
        listingPanel.add(cbViewGrasses);
         */
        listingPanel.add(viewListButton);
        listingPanel.add(list);

        listingPanel.setLayout(null);

        add(listingPanel);

        for (int i = 0; i < 30; i++)
            list.add(Integer.toString(i));

        listingPanel.setEnabled(false);
    }

    private void initialiseCreationPart() {
        creationPanel.setBackground(new Color(226, 226, 226));
        creationPanel.setBounds(30, 320, 700, 130);

        creationLabel.setBounds(10, 10, 180, 30);
        cb5.setBounds(470, 10, 170, 50);
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
        creationPanel.add(cb5);
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
        feedingPanel.setBounds(30, 470, 700, 170);

        feedingLabel.setBounds(10, 10, 180, 20);
        animalTypeLabel.setBounds(10, 40, 30, 20);
        animalToFeedChoice.setBounds(50, 40, 120, 30);
        foodTypeLabel.setBounds(170, 40, 30, 20);
        foodPreyChoice.setBounds(210, 40, 120, 30);
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
        statusPanel.setBounds(30, 660, 700, 80);

        statusLabel.setBounds(10, 10, 100, 20);
        statusMessageTextArea.setBounds(120, 10, 570, 60);
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

    public void addCheckboxToCreationGroup(String foodType) {
        Checkbox cb = new Checkbox(foodType, cbgCreationGroup, false);

        final int offset = 150;
        cb.setBounds(lastCheckboxCoordX + offset, cbViewAll.getY(), cbViewAll.getWidth(), cbViewAll.getHeight());
        listingPanel.add(cb);

        lastCheckboxCoordX = cb.getX();
    }

    public HashMap<String, String> getAvailableFoodTypes() {
        return availableFoodTypes;
    }

    public HashMap<String, String> getAnimals(){
        return animals;
    }

    public HashMap<String, String> getFoods(){
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

    public Checkbox getCbViewAll() {
        return cbViewAll;
    }

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

    public List getList() {
        return list;
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