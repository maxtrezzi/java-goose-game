package it.maxtrezzi.goose_game.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Board with all spaces defined.
 * @author maxtrezzi
 */
@SuppressWarnings("SameParameterValue")
public class Board {
    private final List<SpaceType> spaces;

    public Board(int size, int[] bridges, int[] gooses) {
        this.spaces = new ArrayList<>(size);

        for (int i=0; i != size; ++i) {
            this.spaces.add(SpaceType.NORMAL);
        }

        for (int index: bridges) {
            if ( (index < size) && (index >= 0)) {
                this.spaces.set(index, SpaceType.BRIDGE);
            } else {
                throw new IllegalArgumentException("invalid bridge parameter");
            }
        }

        for (int index: gooses) {
            if ( (index < size) && (index >= 0)) {
                this.spaces.set(index, SpaceType.GOOSE);
            } else {
                throw new IllegalArgumentException("invalid goose parameter");
            }
        }

        this.spaces.set(getLastIndex(), SpaceType.FINISH);
    }

    public SpaceType get(int position) {
        assert ( (position >= 0) && (position <= this.spaces.size()) );

        return this.spaces.get(position);
    }

    public int getLastIndex() {
        return this.spaces.size()-1;
    }

    public boolean isNormalPosition(int position) {
        assert (position >= 0) ;
        if (position > getLastIndex()) {
            return false;
        } else {
            return (get(position) == SpaceType.NORMAL);
        }
    }
}
