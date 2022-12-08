package projet.graphic_engine.GUI;

import java.awt.*;
import javax.swing.*;

public class PPanel extends JPanel {

    /**
     * Permet de créer un panel utilable pour une GUI
     */
    public PPanel() {
        super();
        this.setOpaque(true);
        this.setBackground(new Color(0,0,0,0));
    }

    /**
     * permet d'ajouter un bouton à la GUI
     * @param button
     */
    public void add(PButton button) {
        super.add(button);
    }

    /**
     * permet d'ajouter un label à la GUI
     * @param label
     */
    public void add(PLabel label) {
        super.add(label);
    }

}
