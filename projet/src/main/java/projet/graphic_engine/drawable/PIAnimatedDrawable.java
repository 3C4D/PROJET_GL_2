package projet.graphic_engine.drawable;


/**
 * Interface définissant les méthodes à implémenter pour dessiner un objet animé.
 */
public interface PIAnimatedDrawable extends PIDrawable {
    public void next(float dt);

}
