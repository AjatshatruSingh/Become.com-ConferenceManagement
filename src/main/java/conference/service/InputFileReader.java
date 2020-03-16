package conference.service;

import conference.constants.Config;
import conference.controller.Conference;
import conference.controller.SortTalksDesc;
import conference.controller.Talks;

import java.io.*;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class InputFileReader {

/**
 * @author: ajatshatru singh
 * @created on: 15 March 2020
 * Read the  input from file, extract the title, total track count, prepare a trackTalk list to include all
 * talks object, and at the end sort all the talks based on the Talk's time.
 **/
    public Conference parseInputFile(String fileName) throws ParseException {

        Conference newConference = new Conference();

        int id =0;
        int trackCount = 0;

        FileInputStream fstream = null;

        // Open the TestInputFile (assuming the location to be in base repo)
        try {
            fstream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine = "";

        int intMinutes;
        int totalMinutes = 0;

        System.out.println("Test Input : \n");

        //Read Input File Line By Line
        try {
            while ((strLine = br.readLine()) != null) {
                // if (lines start from // then ignore it) else get it. In order to bypass comments and empty lines
                if(strLine.contains("//")|| (strLine.isEmpty()))
                    continue;

                id = id +1 ;

                System.out.println(strLine);

                // Process the received line, extract title, minutes and if it has lightning instead of minutes value
                // the convert into 5 mins

                String talkTitle = strLine.substring(0, strLine.lastIndexOf(" "));
                String minutesString = strLine.substring(strLine.lastIndexOf(" ") + 1);
                String minutes = strLine.replaceAll("\\D+", "");
                if(Config.TIME_LIGHTINING.equals(minutesString))
                {
                    intMinutes = 5;

                    totalMinutes = totalMinutes + intMinutes;
                }else
                {
                    intMinutes = Integer.parseInt(minutes);
                    totalMinutes = totalMinutes + intMinutes;
                }

                // Create a Talk Object, Fill all the input values
                Talks singleTalk = new Talks(intMinutes,talkTitle,id);

                // Add this Talk Object to the List of Track conference.controller.Talks
                newConference.getTrackTalks().add(singleTalk);

            }
        } catch (IOException e){
            e.printStackTrace();
        }

        // Set the total no. of count talks.
        newConference.setCountTalks(id);

        // set total no. of minutes of talks.
        newConference.setTotalTrackMinutes(totalMinutes);

        // Calculate the no. of tracks
        Double totalMinutesInDouble =  totalMinutes*1.0;

        Double numberOfTracks =  totalMinutesInDouble/ Config.TOTAL_CONFERENCE_TALKS_TRACK_MINUTES;

        double fractionalPart = numberOfTracks % 1;
        double integralPart = numberOfTracks - fractionalPart;

        int leftMinutes = totalMinutes - (int)integralPart* Config.TOTAL_CONFERENCE_TALKS_TRACK_MINUTES.intValue();

        // if it comes 1.5 or 1.4 or 1.8 - it will give the value of 2 Tracks
        if (leftMinutes > 0) {
            trackCount = (int) integralPart + 1;
        }else
        {
            trackCount = (int) integralPart;
        }

        newConference.setCountTrack(trackCount);

        // Sort all talks based on the talks-time in descending order.
        Collections.sort(newConference.getTrackTalks(), new SortTalksDesc());

        //Close the input stream
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Just to show the empty lines to display the test-input
        System.out.println("\n\n");

        return newConference;
    }

}
