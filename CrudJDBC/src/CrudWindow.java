import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class CrudWindow extends JFrame implements ActionListener {
    private String[] colNames;
    private String[][] data;
    private JTable table;
    private JScrollPane scrollPane;

    private JLabel cLabel;
    private JTextField cNameT;
    private JTextField cSurnameT;
    private JTextField cYearT;
    private JTextField cMonthT;
    private JTextField cDayT;
    private JButton addButton;
    private JPanel cPanel;


    private JTextField uIdT;
    private JTextField uNameT;
    private JTextField uSurnameT;
    private JTextField uYearT;
    private JTextField uMonthT;
    private JTextField uDayT;
    private JPanel uPanel;
    private JButton updateButton;
    private JButton getIdButton;

    private JTextField dIdT;
    private JButton deleteButton;
    private JPanel dPanel;

    private JLabel uLabel;
    private JComboBox aYearList;
    private JComboBox aMonthList;
    private JComboBox aDayList;

    private JComboBox uYearList;
    private JComboBox uMonthList;
    private JComboBox uDayList;


    private AuthorDAO authorDAO;

    public CrudWindow() throws SQLException {
        setLayout(new FlowLayout());
        authorDAO = new AuthorDAO();
        authorDAO.fillList();

        colNames = new String[]{"Author ID", "Name", "Surname", "Birthday"};
        data = new String[authorDAO.getAuthorList().size()][colNames.length];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = String.valueOf(authorDAO.getAuthorList().get(i).getAutId());
            data[i][1] = authorDAO.getAuthorList().get(i).getName();
            data[i][2] = authorDAO.getAuthorList().get(i).getSurname();
            data[i][3] = String.valueOf(authorDAO.getAuthorList().get(i).getBirthday());
        }

        table = new JTable(data, colNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane);


        cPanel = new JPanel();
        cNameT = new JTextField(10);
        cSurnameT = new JTextField(10);
        cPanel.add(new JLabel("Name"));
        cPanel.add(new JLabel("Surname"));
        cPanel.add(new JLabel("Year"));
        cPanel.add(new JLabel("Month"));
        cPanel.add(new JLabel("Day"));
        cPanel.add(new JLabel("     "));
        cPanel.add(new JLabel("     "));

        String[] year = new String[50];
        for (int i = 0; i < year.length; i++) {
            year[i] = String.valueOf(1950 + i);
        }
        aYearList = new JComboBox(year);
        String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        aMonthList = new JComboBox(month);
        String[] day = new String[31];
        for (int i = 0; i < day.length; i++) {
            day[i] = String.valueOf(i + 1);
        }
        aDayList = new JComboBox(day);
        addButton = new JButton("add");
        cPanel.add(cNameT);
        cPanel.add(cSurnameT);
        cPanel.add(aYearList);
        cPanel.add(aMonthList);
        cPanel.add(aDayList);
        cPanel.add(addButton);
        addButton.addActionListener(this);
        GridLayout aGridLayout = new GridLayout(2, 7);
        cPanel.setLayout(aGridLayout);
        cPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.BLACK), "Add row"));
        add(cPanel);
        add(new JLabel("                    "));
//----------------------------------------------------------------------
        uPanel = new JPanel();
        uIdT = new JTextField(2);
        uNameT = new JTextField(10);
        updateButton = new JButton("update");
        getIdButton = new JButton("get ID");
        updateButton.addActionListener(this);
        uSurnameT = new JTextField(10);
        uYearT = new JTextField(5);
        uMonthT = new JTextField(2);
        uDayT = new JTextField(2);

        uYearList = new JComboBox(year);
        uMonthList = new JComboBox(month);
        uDayList = new JComboBox(day);

        uPanel.add(new JLabel("Name"));
        uPanel.add(new JLabel("Surname"));
        uPanel.add(new JLabel("Year"));
        uPanel.add(new JLabel("Month"));
        uPanel.add(new JLabel("Day"));
        uPanel.add(new JLabel(" "));

        add(new JLabel("ID"));
        add(uIdT);
        add(getIdButton);
        getIdButton.addActionListener(this);
        add(new JLabel("insert id then click 'get id', after which update the fields"));

        uPanel.add(uNameT);
        uPanel.add(uSurnameT);
        uPanel.add(uYearList);
        uPanel.add(uMonthList);
        uPanel.add(uDayList);
        uPanel.add(updateButton);

        GridLayout uGridLayout = new GridLayout(2, 7);
        uPanel.setLayout(uGridLayout);
        uPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.BLACK), "Update row"));
        add(uPanel);
        add(new JLabel("                    "));
//--------------------------------------------------------
        dIdT = new JTextField(2);
        dPanel = new JPanel();
        dPanel.add(new JLabel("ID"));
        dPanel.add(dIdT);
        deleteButton = new JButton("delete");
        dPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.BLACK), "Delete row"));
        dPanel.add(deleteButton);
        deleteButton.addActionListener(this);

        add(dPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 900);
        setVisible(true);
    }

    public String getRow(String id) {
        String[][] rowData = new String[1][4];
        String row = null;
        for (int i = 0; i < rowData.length; i++) {
            if (id.equals(rowData[i][0]))
                row = rowData[i][0] + " " + rowData[i][1] + " " + rowData[i][2] + " " + rowData[i][3];
        }
        return row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Author author = new Author(0, cNameT.getText(), cSurnameT.getText(),
                        new Date(Integer.parseInt(Objects.requireNonNull(aYearList.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(aMonthList.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(aDayList.getSelectedItem()).toString())));
                authorDAO.insert(author);
                setVisible(false);
                new CrudWindow();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == deleteButton) {
            try {
                authorDAO.delete(Integer.parseInt(dIdT.getText()));
                setVisible(false);
                new CrudWindow();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == getIdButton) {
            try {
                Author obj = authorDAO.getRow(Integer.parseInt(uIdT.getText()));
                uNameT.setText(obj.getName());
                uSurnameT.setText(obj.getSurname());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == updateButton) {
            int id = Integer.parseInt(uIdT.getText());
            String name = uNameT.getText();
            String surname = uSurnameT.getText();
            try {
                Author author = new Author(id, name, surname,
                        new Date(Integer.parseInt(Objects.requireNonNull(aYearList.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(aMonthList.getSelectedItem()).toString()),
                                Integer.parseInt(Objects.requireNonNull(aDayList.getSelectedItem()).toString())));
                System.out.println(author.toString() + " ***update");
                authorDAO.update(author, id);
                setVisible(false);
                new CrudWindow();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
