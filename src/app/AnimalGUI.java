package app;

import java.awt.*;

public class AnimalGUI extends Frame {
    // networking part
    Panel netWorkingPanel = new Panel();
    Checkbox cbNetWork = new Checkbox("Сеть", false);

    // listing part
    Panel listingPanel = new Panel();
    CheckboxGroup cbgListingGroup = new CheckboxGroup();
    //Scrollbar scrollbar = new Scrollbar();
    Checkbox cbViewAll, cbViewPredators, cbViewHerbivores, cbViewGrasses;
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
    Choice animalTypeChoice = new Choice();
    Label foodTypeLabel = new Label("чем:");
    Choice foodTypeToFeedChoice = new Choice();
    Button feedButton = new Button("Покормить");

    // status part
    Panel statusPanel = new Panel();
    Label statusLabel = new Label("Статус операции:");
    TextArea statusMessageTextArea = new TextArea();


    public AnimalGUI(String title) throws HeadlessException {
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

        cbNetWork.setBounds(10, 10, 100, 100);
        netWorkingPanel.add(cbNetWork);
        netWorkingPanel.setLayout(null);

        add(netWorkingPanel);
    }

    private void initialiseListingPart() {
        cbViewAll = new Checkbox("Все", cbgListingGroup, false);
        cbViewPredators = new Checkbox("Хищники", cbgListingGroup, false);
        cbViewHerbivores = new Checkbox("Травоядные", cbgListingGroup, false);
        cbViewGrasses = new Checkbox("Трава", cbgListingGroup, false);

        listingPanel.setBounds(30, 150, 700, 150);
        listingPanel.setBackground(new Color(226, 226, 226));

        cbViewAll.setBounds(10, 0, 150, 50);
        cbViewPredators.setBounds(160, 0, 150, 50);
        cbViewHerbivores.setBounds(310, 0, 150, 50);
        cbViewGrasses.setBounds(460, 0, 150, 50);

        list.setBounds(10, 50, 300, 80);
        viewListButton.setBounds(320, 100, 120, 30);

        listingPanel.add(cbViewAll);
        listingPanel.add(cbViewPredators);
        listingPanel.add(cbViewHerbivores);
        listingPanel.add(cbViewGrasses);
        listingPanel.add(viewListButton);

        listingPanel.add(list);


        //scrollbar.setBounds(100, 100, 50, 140);
        //listingPanel.add(scrollbar);

        listingPanel.setLayout(null);

        add(listingPanel);

        for (int i = 0; i < 30; i++)
            list.add(Integer.toString(i));

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

        foodTypeToCreateChoice.add("Хищник");
        foodTypeToCreateChoice.add("Травоядное");
        foodTypeToCreateChoice.add("Трава");

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
    }

    private void initialiseFeedingPart() {
        feedingPanel.setBackground(new Color(226, 226, 226));
        feedingPanel.setBounds(30, 470, 700, 170);

        feedingLabel.setBounds(10, 10, 180, 20);
        animalTypeLabel.setBounds(10, 40, 30, 20);
        animalTypeChoice.setBounds(50, 40, 120, 30);
        foodTypeLabel.setBounds(170, 40, 30, 20);
        foodTypeToFeedChoice.setBounds(210, 40, 120, 30);
        feedButton.setBounds(10, 75, 120, 30);

        feedingPanel.add(feedingLabel);
        feedingPanel.add(animalTypeLabel);
        feedingPanel.add(animalTypeChoice);
        feedingPanel.add(foodTypeLabel);
        feedingPanel.add(foodTypeToFeedChoice);
        feedingPanel.add(feedButton);

        feedingPanel.setLayout(null);
        add(feedingPanel);
    }

    private void initialiseStatusPart() {
        statusPanel.setBackground(new Color(226, 226, 226));
        statusPanel.setBounds(30, 660, 700, 80);

        statusLabel.setBounds(10, 10, 100, 20);
        statusMessageTextArea.setBounds(120, 10, 570, 60);
        statusMessageTextArea.setBackground(new Color(210, 210, 210));

        statusPanel.add(statusLabel);
        statusPanel.add(statusMessageTextArea);

        statusPanel.setLayout(null);
        add(statusPanel);
    }
}