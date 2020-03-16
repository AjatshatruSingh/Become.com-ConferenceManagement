This project is an attempt to solve the below Problem for Become.com

*PROBLEM STATEMENT*

You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.
- The conference has multiple tracks each of which has a morning and afternoon session.
- Each session contains multiple talks.
- Morning sessions begin at 9am and must finish by 12 noon, for lunch.
- Afternoon sessions begin at 1pm and must finish in time for the networking event.
- The networking event can start no earlier than 4:00 and no later than 5:00.
- No talk title has numbers in it.
- All talk lengths are either in minutes (not hours) or lightning (5 minutes).
- Presenters will be very punctual; there needs to be no gap between sessions.
Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don't need to exactly duplicate the sample output given here.


Test input:

Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min
 
Test output:

Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event
 

Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event

*SOLUTION APPROACH*

The Problem statement here is on the lines of that of a 2D Bin Packing Problem
Based on the rules mentioned in the problem statements, the below assumptions have been made

Assumptions:

    - Since there are no details mentioned regarding the networking event other than its start time has to be between 
    4:00pm to 5:00pm, we are assuming, that this event will always be the last event in the tracks
    - Assuming that the timelines are fixed, for this solution, we have fixed the available talk time for Pre-Lunch session
    at 180 mins and the post-Lunch session at 240 mins
    
Solution:

The solution is based on approximation and based on the logic used, can create different sets of results for the same
set of inputs. The solution used here is based on Hueristic approach. The workflow is as follows

    - STEP 1: It reads all the talks from the input files and gathers all required information like, the title, time etc
    - STEP 2: It sorts all the talks in descending order of time taken 
    - STEP 3: It inserts the talks from the list from step 2 in a Tracks morning and evening session without considering 
    for the time remaining in each session.
    - STEP 4: It should take the tracks from step 3 and applys the given rules to re-evaluate the talks in the track. 
    for instance, if Track1 has 20 mins remaining and track2 has 15 mins remaining and we have a talk with the duration 
    of 35 mins, it will move a 15 mins talk from track 1 to track 2 (all subject to availability) and add the 35 min talk
    to track 1
    
Potential Drawback:

Since this algorithm is based on re-evaluating the talks and applying rules in the later stage, it will be more precise,
if we have a variety of data in our input file


*HOW TO RUN*

This is a simple Java Program requiring jdk1.8 and above. 
Checkout the program, open it in any IDE and run the application from the main class.
In order to test with various data entries, please update the Input.txt file


*SCOPES OF IMPROVEMENTS*

Can be more modularised
Can be turned into an executable build using maven or ant
Can have more than one data input sources
Can have better logging and reporting segments
Should have unit test cases in order to test the code logic
To write the logic of re-evaluation based on rules once the Tracks are created

-- Ajatshatru Singh 
 