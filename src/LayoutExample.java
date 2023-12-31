import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LayoutExample extends JFrame
{
    private static final String INITIAL_TEXT = "Nothing Pressed";
    private static final String ADDED_TEXT = " was Pressed";
    private JLabel positionLabel;
    private JButton resetButton;
    private static int gridSize = 4;

    public LayoutExample()
    {
        super("Layout Example");
    }

    private void createAndDisplayGUI()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel labelPanel = new JPanel();
        positionLabel = new JLabel(INITIAL_TEXT, JLabel.CENTER);
        JPanel buttonLeftPanel = new JPanel();
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                positionLabel.setText(INITIAL_TEXT);
            }
        });
        labelPanel.add(positionLabel);
        buttonLeftPanel.add(resetButton);
        leftPanel.add(labelPanel);
        leftPanel.add(buttonLeftPanel);

        contentPane.add(leftPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize));
        JButton button = new JButton("hello");
        buttonPanel.add(button);
        JButton button2 = new JButton("hello");
        buttonPanel.add(button2);
        JButton button3 = new JButton("hello");
        buttonPanel.add(button3);
        JButton button4 = new JButton("hello");
        buttonPanel.add(button4);
        JButton button5 = new JButton("hello");
        buttonPanel.add(button5);


        contentPane.add(buttonPanel);

        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            gridSize = Integer.parseInt(args[0]);
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new LayoutExample().createAndDisplayGUI();
            }
        });
    }
}