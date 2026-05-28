import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class Transaction {
    String Name;
    double ammount;
    boolean income;
    String description
}

Transaction CreateTransaction() {

}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Damn Girl ur Thicc");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Click Me");
        button.addActionListener(e ->{
            String userInput = JOptionPane.showInputDialog("Enter your name");

            if (userInput != null) {
                JOptionPane.showMessageDialog(frame,userInput);
        }
        });


        frame.add(button);
        frame.setVisible(true);
    }
}

JOptionPane.showMessageDialog(
        frame,
                        "Created Transaction: \n" +
                                "Name: " + t.name + "\n" +
                "Amount: " + t.ammount + "\n" +
                "Income: " + t.income + "\n" +
                "Description: " + t.description
);