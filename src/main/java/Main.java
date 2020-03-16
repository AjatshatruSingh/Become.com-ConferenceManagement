import conference.constants.Config;
import conference.controller.Conference;
import conference.service.InputFileReader;

import java.text.ParseException;

/**
 * @Owner: Ajatshatru Singh
 * @Date: 15 March 2020
 *
 * @Purpose: The Main function is the starting point. It calls the Passes the Sample file to the conference.controller.Conference function for creating talks list
 * and then prints the talks list created
 *
 */

public class Main {

    public static void main(String[] args) throws ParseException {
        // Process the received input of conference.controller.Talks's Title and their time.
        InputFileReader fileReader = new InputFileReader();

        // Setting the conference object returned from the input file for next steps
        Conference newConference = fileReader.parseInputFile(Config.INPUT_FILE);

        // Get the no. of Tracks required to schedule all of these talks.
        int numberOfTracks = newConference.getCountTrack();


        int startTalkIndex = 0;

        // Schedule the conference.controller.Talks into Tracks.
        for(int trackCount = 0; trackCount <numberOfTracks; trackCount++)
        {
            startTalkIndex = newConference.ScheduleTalksIntoTracks(trackCount, newConference.getTrackTalks(), newConference.getCountTrack(), startTalkIndex, newConference.getCountTalks());
        }

        // Output the schedule of conference.controller.Talks into tracks.
        newConference.OutputOfTalksIntoTracks(newConference.getTrackTalks());

    }
}