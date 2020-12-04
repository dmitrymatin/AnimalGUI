package app;

import java.awt.*;

public class AnimalGUI extends Frame {
    // networking part
    Panel netWorkingPanel = new Panel();
    Checkbox cbNetWork = new Checkbox("Сеть", false);

    // listing part
    Panel listingPanel = new Panel();
    CheckboxGroup cbgListingGroup = new CheckboxGroup();
    //Label allFoodsLabel, predatorsLabel, herbivoresLabel, grassLabel;
    Scrollbar scrollbar = new Scrollbar();
    Checkbox cbViewAll, cbViewPredators, cbViewHervibores, cbViewGrasses;

    List list = new List();

    // creation part
    Panel creationPanel = new Panel();
    CheckboxGroup cbgCreationGroup = new CheckboxGroup();
    Checkbox cb5;



    public AnimalGUI() throws HeadlessException {
        setSize(760, 700);
        setLayout(null);

        initialiseNetworkingPart();
        initialiseListingPart();
        initialiseCreationPart();

        setVisible(true);
    }

    private void initialiseNetworkingPart() {
        netWorkingPanel.setBounds(30, 30, 700, 100);
        netWorkingPanel.setBackground(Color.LIGHT_GRAY);

        cbNetWork.setBounds(10, 10 , 100, 100);
        netWorkingPanel.add(cbNetWork);
        netWorkingPanel.setLayout(null);

        add(netWorkingPanel);
    }

    private void initialiseListingPart() {
        cbViewAll = new Checkbox("Просмотреть всех", cbgListingGroup, false);
        cbViewPredators = new Checkbox("Просмотреть хищников", cbgListingGroup, false);
        cbViewHervibores = new Checkbox("Просмотреть травоядных", cbgListingGroup, false);
        cbViewGrasses = new Checkbox("Просмотреть травы", cbgListingGroup, false);

        listingPanel.setBackground(new Color(226, 226, 226));
        listingPanel.setBounds(30, 150, 700, 150);

        cbViewAll.setBounds(10, 0,  170, 50);
        cbViewPredators.setBounds(180, 0, 170, 50);
        cbViewHervibores.setBounds(350, 0, 170, 50);
        cbViewGrasses.setBounds(520, 0, 170, 50);

        list.setBounds(10, 50, 200, 80);

        listingPanel.add(cbViewAll);
        listingPanel.add(cbViewPredators);
        listingPanel.add(cbViewHervibores);
        listingPanel.add(cbViewGrasses);

        //scrollbar.setBounds(100, 100, 50, 140);

        listingPanel.add(list);
        listingPanel.add(scrollbar);

        listingPanel.setLayout(null);

        add(listingPanel);

        for (int i = 0; i < 30; i++)
            list.add(Integer.toString(i));

    }

    private void initialiseCreationPart() {
        cb5 = new Checkbox("fff", false);

        creationPanel.setBackground(new Color(226, 226, 226));
        creationPanel.setBounds(30, 350, 700, 150);
        cb5.setBounds(10, 10,  170, 50);

        creationPanel.add(cb5);

        creationPanel.setLayout(null);

        add(creationPanel);
    }
}