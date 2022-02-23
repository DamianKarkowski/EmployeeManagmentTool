import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.lang.Integer.parseInt;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
        }


    private static void createAndShowGui() {
        Map<Integer,employee> MapEmployee = new HashMap<>();
        idEmployee id = new idEmployee();

        // nowy Jframe
        JFrame mainFrame = new JFrame();


        // Ustawienia okna
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setVisible(true);
        mainFrame.setSize(1000,500);
        mainFrame.setLocation(screenSize.width/4,screenSize.height/4);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // nowe panele
        JPanel panel = new JPanel();
        JPanel addPanel = new JPanel();
        JPanel delPanel = new JPanel();
        JPanel showAllPanel = new JPanel();
        JPanel findEmployeePanel = new JPanel();
        JPanel editPanel = new JPanel();
        JPanel findSolaryPanel = new JPanel();
        JPanel showOnePanel = new JPanel();
        JPanel readPanel = new JPanel();
        JPanel savePanel = new JPanel();

        // ustawienia panel
        panel.setVisible(true);
        mainFrame.setContentPane(panel);

        // tablica paneli
        JPanel[] tabPanel = {panel,addPanel,delPanel,showAllPanel,findEmployeePanel,editPanel,showOnePanel,readPanel,savePanel};

        // nowe lebale
        JLabel label = new JLabel("Witaj");
        JLabel labelPAdd = new JLabel("Stanowiska: it(4000-6000), manager(2000-4000)");
        JLabel labelEdit = new JLabel("Zmień: name,surname,position,seniority,solary");
        JLabel labelFind = new JLabel("Znajdź: name,surname,position,seniority,solary");

        // dodawanie JLabel do panali
        panel.add(label);
        addPanel.add(labelPAdd);
        editPanel.add(labelEdit);
        findEmployeePanel.add(labelFind);

        // nowe JTextField
        JTextField namePAdd = new JTextField("Podaj imię");
        JTextField surnamePAdd = new JTextField("Podaj nazwisko");
        JTextField positionPAdd = new JTextField("Podaj stanowisko");
        JTextField seniorityPAdd = new JTextField("Podaj staż");
        JTextField solaryPAdd = new JTextField("Podaj pensje");

        JTextField idPracownikaPDel = new JTextField("Podaj id pracownika");

        JTextField choiceValuePFind = new JTextField("Podaj po czym chcesz szykać");
        JTextField valuePFind = new JTextField("Podaj wartość");

        JTextField idPracownikaPEdit = new JTextField("Podaj id pracownika");
        JTextField choiceValuePEdit = new JTextField("Podaj co chcesz zmienić");
        JTextField valuePEdit = new JTextField("Podaj wartość");

        JTextField characterPFindSolary = new JTextField("Wpisz znak <,>,<=,>=,=");
        JTextField solaryFind = new JTextField("Podaj pensje");


        JTextField idPracownikaPOne = new JTextField("Podaj id pracownika");

        // dodawanie JTextFiled do panali
        addPanel.add(namePAdd);
        addPanel.add(surnamePAdd);
        addPanel.add(positionPAdd);
        addPanel.add(seniorityPAdd);
        addPanel.add(solaryPAdd);

        delPanel.add(idPracownikaPDel);

        findEmployeePanel.add(choiceValuePFind);
        findEmployeePanel.add(valuePFind);

        editPanel.add(choiceValuePEdit);
        editPanel.add(idPracownikaPEdit);
        editPanel.add(valuePEdit);

        findSolaryPanel.add(characterPFindSolary);
        findSolaryPanel.add(solaryFind);

        showOnePanel.add(idPracownikaPOne);


        // nowe JTextArea
        JTextArea textAreaShowAll = new JTextArea();
        JTextArea textAreaShowOne = new JTextArea();
        JTextArea textAreaFind = new JTextArea();
        JTextArea textAreaFindSolary = new JTextArea();
        JTextArea textAreaErrorAdd = new JTextArea();
        JTextArea textAreaRead = new JTextArea();
        JTextArea textAreaDel = new JTextArea();
        JTextArea textAreaErrorEdit = new JTextArea();
        JTextArea textAreaErrorSave = new JTextArea();
        JTextArea texrAreaErrorRead = new JTextArea();

        // dodawanie do paneli
        showAllPanel.add(textAreaShowAll);
        showOnePanel.add(textAreaShowOne);
        findEmployeePanel.add(textAreaFind);
        findSolaryPanel.add(textAreaFindSolary);
        addPanel.add(textAreaErrorAdd);
        readPanel.add(textAreaRead);
        delPanel.add(textAreaDel);
        editPanel.add(textAreaErrorEdit);
        savePanel.add(textAreaErrorSave);
        readPanel.add(texrAreaErrorRead);

        // tworzenie JTable showALL
        String[] headers = {"ID","Imię","Nazwisko","Stanowisko","Staż","Pensja"};
        DefaultTableModel TableModel = new DefaultTableModel(0,0) {
            Class[] types = {Integer.class,String.class,String.class,String.class,Integer.class,Integer.class};
            public Class getColumnClass(int columnIndex){
                return types[columnIndex];
            }
        };
        TableModel.setColumnIdentifiers(headers);
        JTable tableAll = new JTable(TableModel);
        tableAll.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tableAll);
        showAllPanel.add(scrollPane);


        // nowe przyciski
        JButton addButton = new JButton("Dodaj");
        JButton backButtonPAdd = new JButton("Wróć");
        JButton delButton = new JButton("Usuń");
        JButton backButtonPDel = new JButton("Wróć");
        JButton backButtonPShowAll = new JButton("Wróć");
        JButton backButtonPFind = new JButton("Wróć");
        JButton backButtonPEdit = new JButton("Wróć");
        JButton backButtonPShowOne = new JButton("Wróć");
        JButton backButtonPFindSolary = new JButton("Wróć");
        JButton backButtonPSave = new JButton("Wróć");
        JButton backButtonPRead = new JButton("Wróć");
        JButton showOneButton = new JButton("Wyświetl");
        JButton editButton = new JButton("Edytuj");
        JButton findButton = new JButton("Znajdz");
        JButton findSolaryButton = new JButton("Wyszukaj");

        // dodwanie przycisków do addPanel
        addPanel.add(addButton);
        addPanel.add(backButtonPAdd);

        // dodawanie przycisków do delPanel
        delPanel.add(delButton);
        delPanel.add(backButtonPDel);

        // dodawanie przycisków do showAllPanel
        showAllPanel.add(backButtonPShowAll);


        // dodawanie przycisków do editPanel
        editPanel.add(editButton);
        editPanel.add(backButtonPEdit);

        // dodawanie przycisków showOnePanel
        showOnePanel.add(showOneButton);
        showOnePanel.add(backButtonPShowOne);

        // dodawanie przycisków do findPanel
        findEmployeePanel.add(findButton);
        findEmployeePanel.add(backButtonPFind);

        // dodawanie przycisków do findSolaryPanel
        findSolaryPanel.add(findSolaryButton);
        findSolaryPanel.add(backButtonPFindSolary);

        //dodanie przycisku do savePanel oraz readPanel
        savePanel.add(backButtonPSave);
        readPanel.add(backButtonPRead);


        // funkcjonalność przycisków
        addButton.addActionListener(e1 -> {
            employee tmp = addEmployeeFunction(namePAdd.getText(), surnamePAdd.getText(), positionPAdd.getText(), seniorityPAdd.getText(), solaryPAdd.getText());
            if(tmp!=null) {
                MapEmployee.put(id.getId(), tmp);
                TableModel.addRow(new Object[]{tmp.getName(), tmp.getSurname(), tmp.getPosition(), Integer.toString(tmp.getSeniority()), Integer.toString(tmp.getSolary())});
                textAreaErrorAdd.setText("Dodano pomyślnie");
            }
            else textAreaErrorAdd.setText("Wystąpił błąd przy dodawaniu");

        });

        backButtonPAdd.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPDel.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPShowAll.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPFind.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPEdit.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPShowOne.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPFindSolary.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPSave.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));
        backButtonPRead.addActionListener(e1 -> backButtonFunction(mainFrame, panel, tabPanel));

        delButton.addActionListener(e -> {
            if(checkID(MapEmployee, idPracownikaPDel.getText())) {
                MapEmployee.remove(parseInt(idPracownikaPDel.getText()));
                textAreaDel.setText("Usunięto pomyślnie");
            }else
                textAreaDel.setText("Wystąpił błąd przy usuwaniu sprawdź id");
        });

        showOneButton.addActionListener(e -> {

            if(checkID(MapEmployee, idPracownikaPOne.getText()))
                textAreaShowOne.setText(MapEmployee.get(parseInt(idPracownikaPOne.getText())).toString());
            else
                textAreaShowOne.setText("Brak pracownika o tym id");


        });

        editButton.addActionListener(e -> editFunction(MapEmployee, idPracownikaPEdit, choiceValuePEdit, valuePEdit, textAreaErrorEdit));

        findButton.addActionListener(e -> {
            String stringFindEmplyee ="";
            findFunction(MapEmployee, valuePFind,choiceValuePFind, textAreaFind,stringFindEmplyee);
                });
        findSolaryButton.addActionListener(e -> {
            employee tmp;
            Iterator<Map.Entry<Integer, employee>> iterator = MapEmployee.entrySet().iterator();
            String stringFindSolary="";
            if(intCheck(solaryFind.getText())) {
                switch (characterPFindSolary.getText()) {
                    case ">": {
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, employee> entry = iterator.next();
                            tmp = entry.getValue();
                            if (parseInt(solaryFind.getText()) < tmp.getSolary())
                                stringFindSolary += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                        }
                    }
                    break;
                    case "<": {
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, employee> entry = iterator.next();
                            tmp = entry.getValue();
                            if (parseInt(solaryFind.getText()) > tmp.getSolary())
                                stringFindSolary += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                        }
                    }
                    break;
                    case ">=": {
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, employee> entry = iterator.next();
                            tmp = entry.getValue();
                            if (parseInt(solaryFind.getText()) <= tmp.getSolary())
                                stringFindSolary += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                        }
                    }
                    break;
                    case "<=": {
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, employee> entry = iterator.next();
                            tmp = entry.getValue();
                            if (parseInt(solaryFind.getText()) >= tmp.getSolary())
                                stringFindSolary += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                        }
                    }
                    break;
                    case "=": {
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, employee> entry = iterator.next();
                            tmp = entry.getValue();
                            if (parseInt(solaryFind.getText()) == tmp.getSolary())
                                stringFindSolary += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                        }
                    }
                    break;
                    default:

                }
                if (!stringFindSolary.isEmpty())
                    textAreaFindSolary.setText(stringFindSolary);
                else
                    textAreaFindSolary.setText("Brak wyników sprawdź dane");
            }else
                textAreaFindSolary.setText("Brak wyników sprawdź dane");
            stringFindSolary=null;

        });

        // nowe JMenuBar
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        // nowe JMenu
        JMenu menuShow = new JMenu("Wyświetl");
        JMenu menuEmployee = new JMenu("Pracownicy");
        JMenu menuFile = new JMenu("File");

        // dodawanie JMenu do JMenuBar
        menuBar.add(menuShow);
        menuBar.add(menuEmployee);
        menuBar.add(menuFile);

        // nowe JMenuItem
        JMenuItem showOne = new JMenuItem("Wyswietl jednego pracownika");
        JMenuItem showAll = new JMenuItem("Wyswietl wszystkich pracownikow");

        JMenuItem addEmployee = new JMenuItem("Dodaj pracownika");
        JMenuItem delEmployee = new JMenuItem("Usuń pracownika");
        JMenuItem editEmployee = new JMenuItem("Zmień dane pracownika");
        JMenuItem findEmployee = new JMenuItem("Znajdź  pracownika");
        JMenuItem findSolary = new JMenuItem("Wyszukaj po pensji");

        JMenuItem saveEmployee = new JMenuItem("Zapisz pracowników");
        JMenuItem loadEmployee = new JMenuItem("Wczytaj pracowników");

        // dodawanie do menuShow
        menuShow.add(showOne);
        menuShow.add(showAll);

        // dodawanie do menuEmployee
        menuEmployee.add(addEmployee);
        menuEmployee.add(delEmployee);
        menuEmployee.add(editEmployee);
        menuEmployee.add(findEmployee);
        menuEmployee.add(findSolary);

        // dodawanie do menuFile
        menuFile.add(saveEmployee);
        menuFile.add(loadEmployee);

        // ustawienia addEmployee
        addEmployee.addActionListener(e -> {
            panelsOff(tabPanel);
            namePAdd.setText("Podaj imię");
            surnamePAdd.setText("Podaj nazwisko");
            positionPAdd.setText("Podaj stanowisko");
            seniorityPAdd.setText("Podaj staż");
            solaryPAdd.setText("Podaj pensje");
            textAreaErrorAdd.setText("");
            mainFrame.setContentPane(addPanel);
            addPanel.setVisible(true);

        });

        //ustawienia delEmployee
        delEmployee.addActionListener(e -> {
            panelsOff(tabPanel);
            idPracownikaPDel.setText("Podaj id");
            textAreaDel.setText("");
            mainFrame.setContentPane(delPanel);
            delPanel.setVisible(true);

        });

        // ustawienia showAll
        showAll.addActionListener(e -> {
            panelsOff(tabPanel);

            mainFrame.setContentPane(showAllPanel);
            showAllPanel.setVisible(true);

            TableModel.getDataVector().removeAllElements();
            TableModel.setDataVector(tableAllContent(MapEmployee),headers);



        });

        // ustawienia findEmployee
        findEmployee.addActionListener(e -> {
            panelsOff(tabPanel);
            choiceValuePFind.setText("Podaj po czym chcesz szukać");
            valuePFind.setText("Podaj wartość");
            mainFrame.setContentPane(findEmployeePanel);
            findEmployeePanel.setVisible(true);
        });

        // ustawienia editEmployee
        editEmployee.addActionListener(e -> {
            panelsOff(tabPanel);
            choiceValuePEdit.setText("Podaj co chcesz zmienić");
            valuePEdit.setText("Wpisz wartość");
            idPracownikaPEdit.setText("Podaj id");
            mainFrame.setContentPane(editPanel);
            editPanel.setVisible(true);
        });

        // ustawienia findSolary
        findSolary.addActionListener(e -> {
            panelsOff(tabPanel);
            characterPFindSolary.setText("Wpisz znak <,>,<=,>=,=");
            solaryFind.setText("Podaj pensje");
            textAreaFindSolary.setText("");
            mainFrame.setContentPane(findSolaryPanel);
            findEmployeePanel.setVisible(true);
        });

        // ustawienia showOne
        showOne.addActionListener(e -> {
            panelsOff(tabPanel);
            mainFrame.setContentPane(showOnePanel);
            showOnePanel.setVisible(true);

            idPracownikaPOne.setText("Podaj id");
            textAreaShowOne.setText("");
        });

        // ustawienia saveEmployee
        saveEmployee.addActionListener(e -> {

            panelsOff(tabPanel);
            mainFrame.setContentPane(savePanel);
            savePanel.setVisible(true);

            textAreaErrorSave.setText("Zapisano pomyślnie");
            FileWriter fw;
            employee tmp;
            Iterator<Map.Entry<Integer, employee>> iterator = MapEmployee.entrySet().iterator();
                try{
                    fw = new FileWriter("SaveEmployee.txt",false);
                    while (iterator.hasNext()) {
                        Map.Entry<Integer, employee> entry = iterator.next();
                        tmp=entry.getValue();
                        fw.write(entry.getKey()+" "+tmp.toString() + "\r\n");
                        fw.flush();

                    }
                    fw.close();

                }catch (Exception e1){
                    textAreaErrorSave.setText("Wystąpił błąd podczas zapisu");
                }
        });

        // ustawienia loadEmployee
        loadEmployee.addActionListener(e -> {
            panelsOff(tabPanel);
            mainFrame.setContentPane(readPanel);
            readPanel.setVisible(true);
            BufferedReader br;

            String line;
            String[] partsLine;
            String readDate="";
            String readDateError="Wystąpił błąd przy:"+"\r\n";
            employee tmp;
            int idRead;
            try{
                br = new BufferedReader(new FileReader("SaveEmployee.txt"));

                while(br.ready()){
                    line = br.readLine();
                    partsLine = line.split(" ");
                    if(checkID(MapEmployee,partsLine[0])){
                        idRead = parseInt(partsLine[0]);
                        tmp = addEmployeeFunction(partsLine[1],partsLine[2],partsLine[3],partsLine[4],partsLine[5]);
                        if(tmp!=null) {
                            MapEmployee.replace(idRead,tmp);
                            TableModel.addRow(new Object[]{tmp.getName(), tmp.getSurname(), tmp.getPosition(), Integer.toString(tmp.getSeniority()), Integer.toString(tmp.getSolary())});
                            readDate+=line+"\r\n";
                        }
                        else readDateError+=line+"\r\n";
                    }else {
                        tmp = addEmployeeFunction(partsLine[1],partsLine[2],partsLine[3],partsLine[4],partsLine[5]);
                        if(tmp!=null) {
                            MapEmployee.replace(id.getId(),tmp);
                            TableModel.addRow(new Object[]{tmp.getName(), tmp.getSurname(), tmp.getPosition(), Integer.toString(tmp.getSeniority()), Integer.toString(tmp.getSolary())});
                            readDate+=line+"\r\n";
                        }
                        else readDateError+=line+"\r\n";
                    }
                }
            }catch (Exception e2){

            }
            textAreaRead.setText(readDate);
            if(!readDateError.equals("Wystąpił błąd przy:"+"\r\n"))
                texrAreaErrorRead.setText(readDateError);
            else
                texrAreaErrorRead.setText("");
        });
    }

    private static void panelsOff(JPanel[] tabPanel) {
        for (JPanel p : tabPanel) {
            p.setVisible(false);
        }
    }

    private static boolean checkID(Map<Integer, employee> MapEmployee, String idPracownika) {
        if(intCheck(idPracownika)){
           for( int key : MapEmployee.keySet()){

               if(key==parseInt(idPracownika))
                   return true;
           }
        }
        return false;
    }


    private static employee addEmployeeFunction(String namePAdd, String surnamePAdd, String positionPAdd, String seniorityPAdd, String solaryPAdd) {
            if (checkPosition(positionPAdd) && intCheck(seniorityPAdd) && intCheck(solaryPAdd)){
                if(employeePosition.checkSolary(positionPAdd,parseInt(solaryPAdd)))
                    return new employee(namePAdd, surnamePAdd, positionPAdd, parseInt(seniorityPAdd), parseInt(solaryPAdd));
                else
                    return null;
            }
            else return null;


    }

    private static Object[][] tableAllContent(Map MapEmployee) {
        employee tmp;
        Iterator<Map.Entry<Integer, employee>> iterator = MapEmployee.entrySet().iterator();
        String allEmployee="";
        Object[][] content = new Object[MapEmployee.size()][6];
        int i =0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, employee> entry = iterator.next();
            tmp = entry.getValue();
                allEmployee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                content[i]= new Object[]{entry.getKey(),tmp.getName(), tmp.getSurname(), tmp.getPosition(), Integer.toString(tmp.getSeniority()), Integer.toString(tmp.getSolary())};
                i++;
        }
        return content;
    }

    private static void editFunction(Map MapEmployee, JTextField idPracownikaPEdit, JTextField choiceValuePEdit, JTextField valuePEdit, JTextArea textAreaErrorEdit) {
        employee tmp;
        int idTmp;
        boolean check = true;
        if(checkID(MapEmployee,idPracownikaPEdit.getText())){
        tmp = (employee) MapEmployee.get(parseInt(idPracownikaPEdit.getText()));
        idTmp= parseInt(idPracownikaPEdit.getText());
        switch (choiceValuePEdit.getText()){
            case "name":{
                if(!valuePEdit.getText().equals("Podaj wartość")) tmp.setName(valuePEdit.getText());
                else check = false;
            }break;
            case "surname": {
                if(!valuePEdit.getText().equals("Podaj wartość")) tmp.setSurname(valuePEdit.getText());
                else check = false;
            }break;
            case "position": {
                if(!valuePEdit.getText().equals("Podaj wartość") && checkPosition(valuePEdit.getText())) tmp.setPosition(valuePEdit.getText());
                else check = false;
            }break;
            case "seniority": {
                if(intCheck(valuePEdit.getText())) tmp.setSeniority(parseInt(valuePEdit.getText()));
                else check = false;
            }break;
            case "solary": {
                if(intCheck(valuePEdit.getText())) {
                    if(employeePosition.checkSolary(tmp.getPosition(),parseInt(valuePEdit.getText())))
                        tmp.setSolary(parseInt(valuePEdit.getText()));
                    else
                        check = false;
                }
                else check = false;
            } default:
        }
            MapEmployee.replace(idTmp,tmp);
            tmp=null;
            idTmp=0;
        }else check = false;

        if(check)
            textAreaErrorEdit.setText("Zmieniono pomyślnie");
        else
            textAreaErrorEdit.setText("Wystąpił błąd sprawdź dane");

    }

    private static void findFunction(Map MapEmployee, JTextField value,JTextField choiceValue, JTextArea textAreaFind,String stringFindEmplyee) {
        employee tmp;
        Iterator<Map.Entry<Integer, employee>> iterator = MapEmployee.entrySet().iterator();
        switch (choiceValue.getText()) {
            case "name": {
                while (iterator.hasNext()) {
                    Map.Entry<Integer, employee> entry = iterator.next();
                    tmp = entry.getValue();
                    if (value.getText().equals(tmp.getName()))
                        stringFindEmplyee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                }
            }break;
            case "surname": {
                while (iterator.hasNext()) {
                    Map.Entry<Integer, employee> entry = iterator.next();
                    tmp = entry.getValue();
                    if (value.getText().equals(tmp.getSurname()))
                        stringFindEmplyee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                }
            }break;
            case "position": {
                while (iterator.hasNext()) {
                    Map.Entry<Integer, employee> entry = iterator.next();
                    tmp = entry.getValue();
                    if (value.getText().equals(tmp.getPosition()))
                        stringFindEmplyee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                }
            }break;
            case "seniority": {
                if(intCheck(value.getText())){
                while (iterator.hasNext()) {
                    Map.Entry<Integer, employee> entry = iterator.next();
                    tmp = entry.getValue();
                    if (parseInt(value.getText()) == tmp.getSeniority())
                        stringFindEmplyee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                }
                }
            }break;
            case "solary": {
                if(intCheck(value.getText())){
                while (iterator.hasNext()) {
                    Map.Entry<Integer, employee> entry = iterator.next();
                    tmp = entry.getValue();
                    if (parseInt(value.getText()) == tmp.getSolary())
                        stringFindEmplyee += String.valueOf(entry.getKey()) + " " + tmp.toString() + "\n";
                }
                }
            }break;
            default:

        }
        if(!stringFindEmplyee.isEmpty())
            textAreaFind.setText(stringFindEmplyee);
        else
            textAreaFind.setText("Brak wyników sprawdź dane");

        stringFindEmplyee=null;
    }

    private static void backButtonFunction(JFrame mainFrame, JPanel panel, JPanel[] tabPanel) {
        panelsOff(tabPanel);
        panel.setVisible(true);
        mainFrame.setContentPane(panel);
    }

    private static boolean checkPosition(String Position){
        for(employeePosition ep : employeePosition.values()){
            if(ep.name().equals(Position))
                return true;
        }
        return false;
    }

    private static boolean intCheck(String string){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) {
            if (parseInt(string) <= 65535)
                return true;
            else
                return false;
        } else
            return false;
    }

}
