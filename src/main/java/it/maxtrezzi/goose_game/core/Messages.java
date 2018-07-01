package it.maxtrezzi.goose_game.core;


/**
 * Class with strings for messages
 * @author maxtrezzi
 */
public class Messages {
    public  static final String APP_MENU =
            "\n" +
            "==Goose Game App Commands==\n" +
            " add player <player-name>\n"+
            " play\n" +
            " exit\n" +
            "Please input your command";

    public  static final String GAME_MENU =
            "\n" +
            "============ Game Commands ============\n" +
            " move <player-name> [<dice1>,<dice2>]\n"+
            " exit\n" +
            "Please input your command";

    public static final String ALREADY_EXISTING_PLAYER = "%s: already existing player";
    public static final String PLAYERS = "players: %s";
    public static final String UNKNOWN_COMMAND = "Unknown command";
    public static final String UNKNOWN_PLAYER = "Unknown player %s";
    public static final String PLAYER_NAME_IS_REQUIRED = "Player's name is required";
    public static final String BYE = "Bye Bye";
    public static final String GAME_QUITTED = "Game quitted";
    public static final String NO_PLAYERS = "No players for the game";
    public static final String INVALID_DICE_ARG = "Invalid dice argument: %s";
    public static final String START = "Start";
    public static final String PLAYER_MOVES_FROM_TO = "%s moves from %s to %d";
    public static final String PLAYER_MOVES_AGAIN_TO = ". %s moves again and goes to %d";
    public static final String PLAYER_MOVES_TO_THE_BRIDGE = "%s moves from %s to The Bridge";
    public static final String PLAYER_JUMPS_TO = ". %s jumps to %d";
    public static final String PLAYER_BOUNCE_TO = ". %1$s bounces! %1$s returns to %2$d";
    public static final String PLAYER_ROLLS = "%s rolls %d, %d. ";
    public static final String PLAYER_WINS = ". %s Wins!";
    public static final String MOVE_PLAYER_NAME_IS_REQUIRED = "Command Move: Player's name is required";
    public static final String MOVE_PLAYER_INVALID_ARGS = "Command Move: Invalid arguments";
    public static final String PRANK = ". On %d there is %s, who returns to %s";
    public static final String THE_GOOSE = ", The Goose";
}
