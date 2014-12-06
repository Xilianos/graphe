package view;

import controller.GraphBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author cedric
 *         .
 */
public class JGraphFrame extends GraphView implements WindowListener {

    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;

    public JGraphFrame(GraphBuilder controller) {
        super(controller);

        this.setUpPanel();
        this.setUpFrame();

        this.controller.registerView(this);
    }

    private void setUpFrame() {
        this.frame = new JFrame("Graphe");
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.panel.setBackground(Color.ORANGE);

        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.scrollPane, BorderLayout.CENTER);
        this.frame.add(this.panel);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(300, 300));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.addWindowListener(this);
    }

    private void setUpPanel() {
        this.scrollPanel = new JPanel();
        this.scrollPane = new JScrollPane(this.scrollPanel);
    }

    @Override
    public String openFile() {
        String chosenFile = null;
        int retValue;

        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Graph files", "graph"));
        retValue = jfc.showOpenDialog(null);

        if(retValue == JFileChooser.APPROVE_OPTION) {
            chosenFile = jfc.getSelectedFile().getAbsolutePath();
            System.out.println(chosenFile);
        }
        else {
            System.out.println("No file chosen...");
        }

        return chosenFile;
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        this.controller.unregisterView(this);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
