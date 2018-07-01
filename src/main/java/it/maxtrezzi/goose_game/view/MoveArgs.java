package it.maxtrezzi.goose_game.view;

import it.maxtrezzi.goose_game.core.InvalidInputException;
import it.maxtrezzi.goose_game.core.Messages;

import java.util.StringTokenizer;

/**
 * Data about a move with player's name and dices values.
 * A static method {@link #parseMoveArgs(String)} provide a tool to extract these values from a String.
 * @author maxtrezzi
 */
public class MoveArgs {
    private final int firstDice;
    private final int secondDice;
    private final String playerName;

    private MoveArgs(int firstDice, int secondDice, String playerName) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.playerName = playerName;
    }

    public boolean isComplete() {
        return (secondDice != 0);
    }

    public int getFirstDice() {
        return firstDice;
    }

    public int getSecondDice() {
        return secondDice;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Parses the string with arguments for the move.
     * The string must contain the player's name and, after a space or comma, can contain dices values.
     *
     * @param string String to parse.
     * @return A MoveArgs objects with values parsed.
     */
    public static MoveArgs parseMoveArgs(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, " ," , false);
        int pos = 0, firstDice=0, secondDice=0;
        String playerName="";
        int countTokens=tokenizer.countTokens();

        if ( countTokens == 0) {
            throw new InvalidInputException(Messages.MOVE_PLAYER_NAME_IS_REQUIRED);
        }
        if ( (countTokens != 1)  && (countTokens != 3) ) {
            throw new InvalidInputException(Messages.MOVE_PLAYER_INVALID_ARGS);
        }

        while (tokenizer.hasMoreTokens()) {
            int value;
            String token = tokenizer.nextToken().trim();
            if (pos == 0) {
                playerName = token;
            } else {
                try {
                    value = Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException(String.format(Messages.INVALID_DICE_ARG, token), e);
                }

                if ( (value < 1) || (value > 6) ) {
                    throw new InvalidInputException(String.format(Messages.INVALID_DICE_ARG, token));
                }

                switch (pos) {
                    case 1 : firstDice = value;
                        break;
                    case 2 : secondDice = value;
                        break;
                    default : assert false : "invalid pos ";
                }
            }
            ++pos;
        }

        return new MoveArgs(firstDice, secondDice, playerName);
    }
}
