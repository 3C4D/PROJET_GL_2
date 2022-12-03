package projet.graphic_engine.drawable;

import java.awt.*;
import java.io.Serializable;

/**
 * Interface définissant les méthodes à implémenter pour dessiner un objet
 */
public interface PIDrawable extends Serializable {
    public void paint(Graphics g);
    public void setPosition(int x, int y);
}
