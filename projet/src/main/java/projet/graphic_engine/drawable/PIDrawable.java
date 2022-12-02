package projet.graphic_engine.drawable;

import java.awt.*;

/**
 * Interface définissant les méthodes à implémenter pour dessiner un objet
 */
public interface PIDrawable {
    public void paint(Graphics g);
    public void setPosition(int x, int y);
}
