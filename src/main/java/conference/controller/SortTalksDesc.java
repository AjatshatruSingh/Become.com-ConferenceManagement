package conference.controller;

import conference.controller.Talks;

import java.util.Comparator;

/**
 * @author ajatshatrusingh
 * @created on: 15 March 2020
 * This class implements the comparator to sort the talks in descending order based on their duration.
 */
public class SortTalksDesc implements Comparator<Talks>{

    @Override
    public int compare(Talks t1, Talks t2) {
        if(t1.getMinutes() < t2.getMinutes()){
            return 1;
        } else {
            return -1;
        }
    }
}