package conference.constants;

/**
 * @author ajatshatru singh
 * @created on: 15 March 2020
 *
 */

public class Config{

    public static final int MORNING_TIME_MINUTES = 180;
    public static final int AFTERNOON_TIME_MINUTES = 240;
    public static final Double TOTAL_CONFERENCE_TALKS_TRACK_MINUTES = 420.0;
    public static final String INPUT_FILE = "Input.txt";
    public static final String TIME_LIGHTINING;

    static {
        TIME_LIGHTINING = "lightning";
    }
}