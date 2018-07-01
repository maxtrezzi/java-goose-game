package it.maxtrezzi.goose_game.core;

import static it.maxtrezzi.goose_game.core.SpaceType.*;

/**
 * A single player {@link Player} in a single {@link Game} with a position in the {@link Board}
 * @author maxtrezzi
 */
public class GamePlayer {
    private final Player player;
    private final Game game;
    private int position=0;

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    private GamePlayer forceMove(int newPos) {
        position = newPos;
        return this;
    }

    private String getPositionAsString(int position) {
        return (position > 0) ? Integer.toString(position) : Messages.START;
    }

    private String processPrank(int oldPosition, int newPosition) {
        if (newPosition != position) {
            GamePlayer colliding = game.findPlayerOnSpace(newPosition, this);
            if (colliding != null) {
                colliding.forceMove(oldPosition);
                return String.format(Messages.PRANK, newPosition,
                        colliding.getPlayer().getName(), getPositionAsString(oldPosition));
            }
        }

        return "";
    }

    private String getTextForTargetSpace(int newPosition, boolean again) {
        Board board = game.getBoard();
        int index = Math.min(board.getLastIndex(), newPosition);
        SpaceType spaceType = board.get(index);
        String ret;
        if (spaceType == BRIDGE) {
            ret = String.format(Messages.PLAYER_MOVES_TO_THE_BRIDGE, player.getName(), getPositionAsString(position));
        } else if (again) {
            ret = String.format(Messages.PLAYER_MOVES_AGAIN_TO, player.getName(), index);
        } else {
            ret = String.format(Messages.PLAYER_MOVES_FROM_TO, player.getName(), getPositionAsString(position), index);
        }

        return ret;
    }

    public String moveBy(int firstDice, int secondDice) {
        assert ( (firstDice > 0) && (firstDice <= 6) ) : "Invalid firstDice value";
        assert ( (secondDice > 0) && (secondDice <= 6) ) : "Invalid secondDice value";
        int newPosition = position + firstDice + secondDice;
        Board board = game.getBoard();

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(String.format(Messages.PLAYER_ROLLS, player.getName(), firstDice, secondDice));
        messageBuilder.append(getTextForTargetSpace(newPosition, false));

        while ((!board.isNormalPosition(newPosition) && (!game.hasWinner()))) {
            if (board.getLastIndex() >= newPosition) {
                SpaceType spaceType = board.get(newPosition);

                if (spaceType == GOOSE) {
                    newPosition += firstDice + secondDice;
                    messageBuilder
                            .append(Messages.THE_GOOSE)
                            .append(getTextForTargetSpace(newPosition, true));
                } else if (spaceType == BRIDGE) {
                    newPosition += Consts.BRIDGE_SPACES_TO_ADVANCE;
                    messageBuilder.append(String.format(Messages.PLAYER_JUMPS_TO, player.getName(), newPosition));
                } else {
                    game.setWinner(this);
                    messageBuilder.append(String.format(Messages.PLAYER_WINS, player.getName()));
                }
            } else {
                newPosition = board.getLastIndex() - (newPosition - board.getLastIndex());
                messageBuilder.append(String.format(Messages.PLAYER_BOUNCE_TO, player.getName(), newPosition));
            }
        }

        messageBuilder.append(processPrank(position, newPosition));
        position = newPosition;
        return messageBuilder.toString();
    }

    public int getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }
}
