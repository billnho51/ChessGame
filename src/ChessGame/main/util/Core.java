package ChessGame.main.util;


//testing launch frame
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Core extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JPanel panel = new JPanel();

    //image setup
    private BufferedImage boardImage;


    public static void Launch(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Core frame = new Core();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Core() {
        //set settings for JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        panel.setBounds(0, 0, 434, 261);
        contentPane.add(panel);
        panel.setLayout(null);

        //initialized image path
        try {
            boardImage = ImageIO.read(new File("pictures/Chessboard.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JLabel Chessboard = new JLabel(new ImageIcon(boardImage));

        Chessboard.setBounds(0, 0, 434, 261);
        panel.add(Chessboard);
    }
}
