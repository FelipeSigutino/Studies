import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class Main {

    class TransactionCatergory {
        String name;
        ArrayList<Transaction> transactions;

        TransactionCatergory(String name) {
            this.name = name;
            this.transactions = new ArrayList<>();
        }

    }

    class Transaction {
        TransactionCatergory category;
        String name;
        double ammount;
        boolean income;
        LocalDate date;
        String description;

    }

    ArrayList<TransactionCatergory> TransactionCategories = new ArrayList<TransactionCatergory>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    TransactionCatergory getOrCreateTransactionCategory(String name) {
        name = name.toUpperCase().trim();
        for (TransactionCatergory tc : TransactionCategories) {
            if (tc.name.equals(name)) {
                return tc;
            }
        }

        TransactionCatergory tc = new TransactionCatergory(name);
        TransactionCategories.add(tc);
        JOptionPane.showMessageDialog(null, "Transaction Category " + name + " created");
        return tc;
    }

    Transaction getTransactionFromUser(JFrame parent) {
        String Category = JOptionPane.showInputDialog(parent, "Enter Category:");
        if (Category == null) return null;
        TransactionCatergory category = getOrCreateTransactionCategory(Category);


        String name = JOptionPane.showInputDialog(parent, "Enter the name of the transaction");
        if (name == null) return null;

        String amountString = JOptionPane.showInputDialog(parent, "Enter the amount of the transaction");
        if (amountString == null) return null;

        double  ammount;
        try {
            ammount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter a number");
            return null;
        }

        int type = JOptionPane.showConfirmDialog(
                parent,

                "Is this income?",
                "Transaction type",
                JOptionPane.YES_NO_OPTION
        );
        boolean income = (type == JOptionPane.YES_OPTION);

        String dateString = JOptionPane.showInputDialog(parent, "Enter the date of the transaction (YYYY-MM-DD)");
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Invalid date using todays date");
            date = LocalDate.now();
        }


        String description = JOptionPane.showInputDialog(parent, "Enter the description of the transaction");
        if (description == null) description = "-";

        return CreateTransaction(category, name,ammount, income,date,description );
    }

    Transaction CreateTransaction(TransactionCatergory category, String name, double ammount, boolean income,LocalDate date, String description) {
        Transaction transaction = new Transaction();
        transaction.category = category;
        transaction.name = name;
        transaction.ammount = ammount;
        transaction.income = income;
        transaction.date = date;
        transaction.description = description;
        return transaction;
    }



    JFrame frame = new JFrame("Your Hot Girl Your Hot");
    void createFrame() {
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(AddTransaction);
        frame.add(ShowTransactions);
        frame.add(CalculateWholeIncome);
        frame.add(CalculateWholeExpense);
        frame.add(CalculateTotal);
        frame.add(LoadTransactions);
        frame.add(DeleteTransaction);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveTransactionsToFile("transactions.txt");
            }
        });
        frame.setVisible(true);
    }

    JButton AddTransaction = new JButton("Add Transaction");
    void listenForTransaction() {
        AddTransaction.addActionListener(e -> {
            Transaction t = getTransactionFromUser(frame);
            if (t != null) {
                transactions.add(t);
                t.category.transactions.add(t);

                JOptionPane.showMessageDialog(
                        frame,
                        "Transaction has been created! \n"
                        );
            }
        });
    }

    JButton ShowTransactions = new JButton("Show Transactions");
    void showTransactions() {
        ShowTransactions.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no transactions yet");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (TransactionCatergory tc : TransactionCategories) {
                sb.append(tc.name).append(":\n");

                if (tc.transactions.isEmpty()) {
                    sb.append("  (no transactions)\n");
                } else {
                    for (int i = 0; i < tc.transactions.size(); i++) {
                        Transaction t = tc.transactions.get(i);
                        sb.append("  ")
                                .append(i + 1).append(". ")
                                .append(t.name).append(" | ")
                                .append(t.ammount).append(" | ")
                                .append(t.income ? "Income" : "Expense")
                                .append(" | ")
                                .append(t.date)
                                .append(" | ")
                                .append(t.description)
                                .append("\n");
                    }
                }
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(frame,sb.toString());
        });
    }

    JButton CalculateWholeIncome = new JButton("Calculate Whole Income");
    void calculateWholeIncome() {
        CalculateWholeIncome.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no transactions yet");
                return;
            }

            double sum = 0;
            for(int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).income) {
                    sum += transactions.get(i).ammount;
                }
            }
            JOptionPane.showMessageDialog(frame,
                    "Whole Income: " + sum
            );
        });
    }

    JButton CalculateWholeExpense = new JButton("Calculate Whole Expense");
    void calculateWholeExpense() {
        CalculateWholeExpense.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no transactions yet");
                return;
            }

            double sum = 0;
            for(int i = 0; i < transactions.size(); i++) {
                if (!transactions.get(i).income) {
                    sum += transactions.get(i).ammount;
                }
            }
            JOptionPane.showMessageDialog(frame,
                    "Whole Expense: " + sum
            );
        });
    }

    JButton CalculateTotal = new JButton("Calculate Total");
    void calculateTotal() {
        CalculateTotal.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no transactions yet");
                return;
            }

            double Total = 0;
            for(int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).income) {
                    Total += transactions.get(i).ammount;
                } else{
                    Total -= transactions.get(i).ammount;
                }
            }
            JOptionPane.showMessageDialog(frame,
                    "Total: " + Total
            );
        });
    }

    //automatic on exit
    void saveTransactionsToFile(String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            for (Transaction t : transactions) {
                writer.write(
                        t.category.name +
                            "," + t.name +
                                "," + t.ammount +
                                "," + t.income +
                                "," + t.date +
                                "," + t.description.replace(",",";") +  "\n"
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JButton LoadTransactions = new JButton("Load Transactions");
    void loadTransactionsFromFile(String fileName) {
        LoadTransactions.addActionListener(e -> {
            transactions.clear();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int i=0;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    String Category = parts[0];
                    TransactionCatergory category = getOrCreateTransactionCategory(Category);
                    String name = parts[1];
                    double ammount = Double.parseDouble(parts[2]);
                    boolean income = Boolean.parseBoolean(parts[3]);
                    LocalDate  date = LocalDate.parse(parts[4]);
                    String description = parts.length >= 6 ? parts[5] : "-";

                    Transaction t = CreateTransaction(category, name, ammount, income,date, description);

                    transactions.add(t);
                    category.transactions.add(t);
                    i ++;
                }

            } catch (IOException a) {
                a.printStackTrace();
            }
        });
    }

    JButton DeleteTransaction = new JButton("Delete Transaction");
    void deleteTransaction() {
        DeleteTransaction.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no transactions yet");
            }
            String toDeleteName = JOptionPane.showInputDialog(frame, "Enter name");
            Transaction TransactionToDelete = null;
            for(Transaction t : transactions) {
                if(t.name.equals(toDeleteName)) {
                    TransactionToDelete = t;
                }
            }
            TransactionCatergory transactionFromCategoryToDelete = TransactionToDelete.category;

            transactions.remove(TransactionToDelete);
            transactionFromCategoryToDelete.transactions.remove(TransactionToDelete);

            JOptionPane.showMessageDialog(frame, "Transaction Deleted!");
        });
    }

    void main() {
        createFrame();
        listenForTransaction();
        showTransactions();
        calculateWholeIncome();
        calculateWholeExpense();
        calculateTotal();
        deleteTransaction();
        loadTransactionsFromFile("transactions.txt");
    }
}