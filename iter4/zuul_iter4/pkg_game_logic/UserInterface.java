package pkg_game_logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

import pkg_command.CommandWord;
import pkg_command.CommandWords;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 *
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener {
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    private JButton lookBtn;
    private String language;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     *
     * @param gameEngine  The GameEngine object implementing the game logic.
     * @param language The language "en" or "fr".
     */
    public UserInterface(GameEngine gameEngine, String language)
    {
        engine = gameEngine;
        this.language = language;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            int sizeX = icon.getIconWidth();
            int sizeY = icon.getIconHeight();
            float big;
            float newX = sizeX;
            float newY = sizeY;
            if (sizeX > sizeY){
              big = sizeX;
            }
            else{
              big = sizeY;
            }
            if (big > 800){
              big = big / (big - (big - 800));
              newX = newX / big;
              newY = newY / big;
            }
            image.setIcon(new ImageIcon(icon.getImage().getScaledInstance(Math.round(newX), Math.round(newY), Image.SCALE_DEFAULT)));
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Aventhür");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        JPanel btn_panel = new JPanel();
        image = new JLabel();
        lookBtn = new JButton("Look around");
        CommandWords cmdWord = new CommandWords(language);


        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        btn_panel.add(lookBtn, BorderLayout.CENTER);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);
        myFrame.getContentPane().add(btn_panel, BorderLayout.SOUTH);


        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        lookBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                processCommand(cmdWord.commandWordToString(CommandWord.LOOK));
            }
        });

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e)
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        processCommand(null);
    }

    /**
     * A command has been entered. Read the command and do whatever is
     * necessary to process it.
     */
    private void processCommand(String str)
    {
        String input;
        boolean finished = false;
        if(str != null){
            input = str;
        } else {
            input = entryField.getText();
            entryField.setText("");
        }
        engine.interpretCommand(input);
    }
}
