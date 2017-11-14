# Challenge

Assumptions:  
+ If an event has no tickets then it is ignored in the search  
+ Error messages are desired  
+ Being able to exit is desired using 'exit'  

Required to build:  
+ gradle   
+ Java 8  

To build:  
+ Locate ***gradle.build*** in the project root directory  
+ Within this directory from command line enter: ***gradle jar***  
+ Runnable jar file, ***challenge-1.0.jar***, should now be located in ***build/libs/*** directory
+ Run using: ***java -jar challenge-1.0.jar***

Changes for multiple events at the same location:  
+ Instead of a map of events at the coordinate locations, a object would be used that would contain a list of events and the number of events at that location.  
+ Move the distance calculation to the coordinates class.  

Changes for larger world map:  
+ Break the map up into quadrants so only the nearest quadrant(s) are searched initially until the target number is reached.  