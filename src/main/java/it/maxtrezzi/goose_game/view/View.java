package it.maxtrezzi.goose_game.view;

/**
 * Base class for all the Views
 * @author maxtrezzi
 */
public abstract class View {
    protected View println (String line) {
        System.out.println(line);
        return this;
    }

    public abstract View show();
}
