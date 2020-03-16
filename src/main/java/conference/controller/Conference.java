package conference.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import conference.constants.*;

/**
 * @author ajatshatrusingh
 * @created on: 15 March 2020
 */
public class Conference {
    List<Talks> trackTalks;

    int totalTrackMinutes;
    int countTrack;
    int countTalks;
    // getter and setters
    public int getTotalTrackMinutes() {
        return totalTrackMinutes;
    }

    public void setTotalTrackMinutes(int totalTrackMinutes) {
        this.totalTrackMinutes = totalTrackMinutes;
    }

    public int getCountTrack() {
        return countTrack;
    }

    public void setCountTrack(int countTrack) {
        this.countTrack = countTrack;
    }

    public int getCountTalks() {
        return countTalks;
    }

    public void setCountTalks(int countTalks) {
        this.countTalks = countTalks;
    }

    public List<Talks> getTrackTalks() {
        return trackTalks;
    }
    public void setTrackTalks(List<Talks> trackTalks) {
        this.trackTalks = trackTalks;
    }


    /**
     * Set the Morning session 180- minutes, Lunch 60 minutes,Afternoon Session-240 minutes, networking event starts from 5:00 PM.
     *
     * Step-1 : It picks all the talks in a track, in descending order based on the Talk-Time,
     *
     * Step-2 : It process it track by track e.g. if track count is 2 then first it process for track-1 and process the N conference.controller.Talks,
     * and then on next round it process (totalTalks - N talks) for next track until all talks get processed.
     *
     * Step-3: It picks the talks and start putting to talk until morning session don't get filled, so either morning session get filled
     * or it left by some minutes but it doesn't go beyond 180 minutes. or if this is afternoon session then it won't go beyond 240 minutes
     *
     * Step-4: During the processing it sets the information in each talks, e.g. Its 'required title' with required information,
     * Its Track number, its minutes, if after the talk it supposed to be Lunch or Networking then set appropriate flag and put the Lunch or
     * Networking Title.
     *
     * Step-5: At last it return the no. of conference.controller.Talks processed for this Track, and leftover talks will be processed for next track.
     *
     * */


    public int ScheduleTalksIntoTracks(int trackCountIndex, List<Talks> trackTalks, int trackCount, int startTalkIndex , int totalTalkCount){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        int firstSessionTime = Config.MORNING_TIME_MINUTES;
        int secondReferenceTime = Config.AFTERNOON_TIME_MINUTES;

        int talkIndex;

        String sessionTime;
        String SessionTitle;

        for(talkIndex=startTalkIndex; talkIndex< totalTalkCount;talkIndex++) {


            // Get the combination of 180 and fill it
            if (firstSessionTime >= trackTalks.get(talkIndex).getMinutes()) {
                firstSessionTime = firstSessionTime - trackTalks.get(talkIndex).getMinutes();
                sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(talkIndex).getTitle() + " " + trackTalks.get(talkIndex).getMinutes() + "min";
                trackTalks.get(talkIndex).setTitle(sessionTime);
                cal.add(Calendar.MINUTE, trackTalks.get(talkIndex).getMinutes());
                SessionTitle = "Track" + " " + (trackCountIndex + 1);
                trackTalks.get(talkIndex).setTrackTitle(SessionTitle);
            }
            if (firstSessionTime < trackTalks.get(talkIndex).getMinutes())
                break;

            if (firstSessionTime > 0)
                continue;

            if (firstSessionTime <= 0)
                break;
        }

        trackTalks.get(talkIndex).setLunchFlag(true);
        sessionTime = "12:00 PM" + " " + "Lunch";
        trackTalks.get(talkIndex).setLunchTitle(sessionTime);
        cal.add(Calendar.MINUTE, 60);

        talkIndex++;

        for(;talkIndex< totalTalkCount;talkIndex++) {
            // Get the combination of 180 and fill it
            if (secondReferenceTime >= trackTalks.get(talkIndex).getMinutes()) {
                secondReferenceTime = secondReferenceTime - trackTalks.get(talkIndex).getMinutes();
                sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(talkIndex).getTitle() + " " + trackTalks.get(talkIndex).getMinutes() + "min";
                trackTalks.get(talkIndex).setTitle(sessionTime);
                cal.add(Calendar.MINUTE, trackTalks.get(talkIndex).getMinutes());
                SessionTitle = "Track" + " " + (trackCountIndex + 1);
                trackTalks.get(talkIndex).setTrackTitle(SessionTitle);
            }
            if (secondReferenceTime < trackTalks.get(talkIndex).getMinutes())
                break;

            if (secondReferenceTime > 0)
                continue;

            if (secondReferenceTime <= 0)
                break;
        }

        if(totalTalkCount == (talkIndex))
            --talkIndex;
        trackTalks.get(talkIndex).setNetworkingFlag(true);
        sessionTime = "5:00 PM" + " " + "Networking Event";
        trackTalks.get(talkIndex).setNetworkingTitle(sessionTime);

        talkIndex++;
        return talkIndex;

    }


    /**
     * Get the conference.controller.Talks from the List of trackTalks, and processed it one by one. every-time its extract the prepared trackTitle and
     * print it as the output of talks into tracks.
     * */
    public void OutputOfTalksIntoTracks(List<Talks> trackTalks){

        System.out.println("Test Output :\n");
        String TrackTitle = "dummyValue";

        // Output the talks into tracks based on the totalTalks and the count of conference.controller.Talks.
        for(int trackCountIndex=0;trackCountIndex<trackTalks.size();trackCountIndex++)
        {

            // Print the Track Title
            if(!TrackTitle.equals(trackTalks.get(trackCountIndex).getTrackTitle()))
            {
                System.out.println(trackTalks.get(trackCountIndex).getTrackTitle() + ":\n");
                TrackTitle = trackTalks.get(trackCountIndex).getTrackTitle();
            }

            // Print the prepared talk's title for this Track
            System.out.println(trackTalks.get(trackCountIndex).getTitle());

            // if lunch flag set then output the Lunch Title
            if(trackTalks.get(trackCountIndex).isLunchFlag())
            {
                System.out.println(trackTalks.get(trackCountIndex).getLunchTitle());
            }

            // if networking flag set then output the Networking Title
            if(trackTalks.get(trackCountIndex).isNetworkingFlag())
            {
                System.out.println(trackTalks.get(trackCountIndex).getNetworkingTitle()+"\n\n");
                // simple convention to display extra lines.
            }

        }
    }

    // constructor
    public Conference(){
        this.trackTalks = new ArrayList();
    }
}