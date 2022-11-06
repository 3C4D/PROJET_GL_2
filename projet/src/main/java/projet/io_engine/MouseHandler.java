package projet.io_engine;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public abstract class MouseHandler implements MouseListener,
                                              MouseMotionListener{
  @Override
  public void mouseClicked(MouseEvent e){
    actionMouseClicked(
      e.getButton(),
      e.getXOnScreen(),
      e.getYOnScreen(),
      e.getX(),
      e.getY()
    );
  }

  @Override
  public void mousePressed(MouseEvent e){
    actionMouseClicked(
      e.getButton(),
      e.getXOnScreen(),
      e.getYOnScreen(),
      e.getX(),
      e.getY()
    );
  }

  @Override
  public void mouseReleased(MouseEvent e){
    actionMouseClicked(
      e.getButton(),
      e.getXOnScreen(),
      e.getYOnScreen(),
      e.getX(),
      e.getY()
    );
  }

  @Override
  public void mouseEntered(MouseEvent e){
    actionMouseEntered(e.getXOnScreen(), e.getYOnScreen(), e.getX(), e.getY()
    );
  }

  @Override
  public void mouseExited(MouseEvent e){
    actionMouseExited(e.getXOnScreen(), e.getYOnScreen(), e.getX(), e.getY()
    );
  }

  @Override
  public void mouseMoved(MouseEvent e){
    actionMouseMoved(
      e.getXOnScreen(),
      e.getYOnScreen(),
      e.getX(),
      e.getY()
    );
  }

  @Override
  public void mouseDragged(MouseEvent e){
    actionMouseDragged(
      e.getXOnScreen(),
      e.getYOnScreen(),
      e.getX(),
      e.getY()
    );
  }

  /**
    * Fonction d'écoute d'un clic de souris
    * @param button Bouton cliqué
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseClicked(
    int button,
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'un bouton est pressé
    * @param button Bouton pressé
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMousePressed(
    int button,
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'un bouton est relaché
    * @param button Bouton relaché
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseReleased(
    int button,
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'elle entre dans l'objet courant
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseEntered(
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'elle sort de l'objet courant
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseExited(
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'elle bouge sans bouton pressé
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseMoved(
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );

  /**
    * Fonction d'écoute d'un de la souris lorsqu'elle bouge avec bouton pressé
    * @param absoluteX abscisse de la souris par rapport à l'écran
    * @param absoluteY ordonnée de la souris par rapport à l'écran
    * @param relativeX abscisse de la souris par rapport à l'objet courant
    * @param relativeY ordonnée de la souris par rapport à l'objet courant
  */
  public abstract void actionMouseDragged(
    int absoluteX,
    int absoluteY,
    int relativeX,
    int relativeY
  );
}
