package ua.nure.kn.kornienko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.*;

import ua.nure.kn.kornienko.usermanagement.User;
import ua.nure.kn.kornienko.usermanagement.agent.exception.SearchException;
import ua.nure.kn.kornienko.usermanagement.util.TextManager;
import ua.nure.kn.kornienko.usermanagement.agent.SearchAgent;

public class SearchGui extends JFrame {

    private static final long serialVersionUID = 2324889757517534537L;

    private SearchAgent agent;

    private JPanel contentPanel;

    private JPanel tablePanel;

    private JTable table;

    public SearchGui(SearchAgent agent) {
        this.agent = agent;
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Searcher " + agent.getName());
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getSearchPanel(), BorderLayout.NORTH);
            contentPanel.add(new JScrollPane(getTablePanel()), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(getTable(), BorderLayout.CENTER);
        }
        return tablePanel;
    }

    private JTable getTable() {
        if (table == null) {
            table = new JTable(new UserTableModel(new LinkedList<>()));
        }
        return table;
    }

    private JPanel getSearchPanel() {
        return new SearchPanel(agent);
    }

    class SearchPanel extends JPanel implements ActionListener {
        SearchAgent agent;
        private JPanel fieldPanel;
        private JButton cancelButton;
        private JButton searchButton;
        private JTextField firstNameField;
        private JTextField lastNameField;

        public SearchPanel(SearchAgent agent) {
            this.agent = agent;
            initialize();
        }

        private void initialize() {
            this.setName("addPanel"); //$NON-NLS-1$
            this.setLayout(new BorderLayout());
            this.add(getFieldPanel(), BorderLayout.NORTH);

        }

        private JButton getCancelButton() {
            if (cancelButton == null) {
                cancelButton = new JButton();
                cancelButton.setText(TextManager.getString("addPanel.cancel")); //$NON-NLS-1$
                cancelButton.setName("cancelButton"); //$NON-NLS-1$
                cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
                cancelButton.addActionListener(this);
            }
            return cancelButton;
        }

        private JButton getSearchButton() {
            if (searchButton == null) {
                searchButton = new JButton();
                searchButton.setText(TextManager.getString("searchPanel.search")); //$NON-NLS-1$
                searchButton.setName("okButton"); //$NON-NLS-1$
                searchButton.setActionCommand("ok"); //$NON-NLS-1$
                searchButton.addActionListener(this);
            }
            return searchButton;
        }

        private JPanel getFieldPanel() {
            if (fieldPanel == null) {
                fieldPanel = new JPanel();
                fieldPanel.setLayout(new GridLayout(2, 3));
                addLabeledField(fieldPanel, "FirstName", getFirstNameField()); //$NON-NLS-1$
                fieldPanel.add(getSearchButton());
                addLabeledField(fieldPanel, "LastName", getLastNameField()); //$NON-NLS-1$
                fieldPanel.add(getCancelButton());
            }
            return fieldPanel;
        }

        protected JTextField getLastNameField() {
            if (lastNameField == null) {
                lastNameField = new JTextField();
                lastNameField.setName("lastNameField");
            }
            return lastNameField;
        }

        private void addLabeledField(JPanel panel, String labelText,
                                     JTextField textField) {
            JLabel label = new JLabel(labelText);
            label.setLabelFor(textField);
            panel.add(label);
            panel.add(textField);
        }

        protected JTextField getFirstNameField() {
            if (firstNameField == null) {
                firstNameField = new JTextField();
                firstNameField.setName("firstNameField");
            }
            return firstNameField;
        }

        protected void doAction(ActionEvent e) throws ParseException {
            if ("ok".equalsIgnoreCase(e.getActionCommand())) {
                String firstName = getFirstNameField().getText();
                String lastName = getLastNameField().getText();
                try {
                    clearUsers();
                    agent.search(firstName, lastName);
                } catch (SearchException e1) {
                    throw new RuntimeException(e1);
                }
            }
            clearFields();
        }

        public void actionPerformed(ActionEvent e) {
            try {
                doAction(e);
            } catch (ParseException e1) {

            }
        }

        private void clearFields() {
            getFirstNameField().setText("");
            getLastNameField().setText("");
        }
    }

    public void addUsers(Collection<User> users) {
        UserTableModel model = (UserTableModel) getTable().getModel();
        model.addUsers(users);
        this.repaint();
    }

    private void clearUsers() {
        UserTableModel model = (UserTableModel) getTable().getModel();
        model.clearUsers();
        this.repaint();
    }
}