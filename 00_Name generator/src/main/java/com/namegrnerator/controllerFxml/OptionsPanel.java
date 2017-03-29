package com.namegrnerator.controllerFxml;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrzej on 2017-03-29.
 */
public class OptionsPanel {

    public OptionsPanel(){
        JFrame frame = new JFrame("Option");
        frame.setSize(200,200);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        JPanel panel = new JPanel();
        
        ButtonGroup fileoption = new ButtonGroup();
        JRadioButton option1 = new JRadioButton("Option1");
        JRadioButton option2 = new JRadioButton("Option2");

        fileoption.add(option1);
        fileoption.add(option2);
        panel.add(option1);
        panel.add(option2);
        frame.add(panel);
        frame.setVisible(true);
    }


}
