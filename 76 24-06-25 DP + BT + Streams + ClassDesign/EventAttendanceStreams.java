// Given a list of EventAttendance (eventId, attendeeName, durationMinutes), 
// Your task is to consider only attendees who stayed ≥ 60 minutes.
// For each event, display the Event ID (ascending order), List of qualified 
// attendee names (alphabetically sorted) and Count of such attendees.

// Example 1
// ---------
// Sample Input:
// 4
// E101 John 90
// E101 Alice 55
// E101 Zara 75
// E102 Mark 120

// Sample Output:
// E101 [John, Zara] Count=2
// E102 [Mark] Count=1

// Example 2
// ---------
// Sample Input:
// 11
// E502 Carl 90
// E502 Dan 45
// E501 Ana 100
// E502 Evan 75
// E501 Beth 61
// E502 Fred 20
// E301 Ron 30
// E301 Tony 60
// E302 Lily 75
// E302 Kevin 50
// E301 Maya 90

// Sample Output:
// E301 [Maya, Tony] Count=2
// E302 [Lily] Count=1
// E501 [Ana, Beth] Count=2
// E502 [Carl, Evan] Count=2

import java.util.*;
import java.util.stream.*;

class EventAttendance{
    private String eventId;
    private String attendeeName;
    private int durationMinutes;
    public EventAttendance(String eventId, String attendeeName, int durationMinutes){
        this.eventId = eventId;
        this.attendeeName = attendeeName;
        this.durationMinutes = durationMinutes;
    }
    public String getEventId(){
        return this.eventId;
    }
    public String getAttendeeName(){
        return this.attendeeName;
    }
    public int getDurationMinutes(){
        return this.durationMinutes;
    }
}

public class EventAttendanceStreams{
    public static void display(List<EventAttendance> arr, int n){
        Map<String,List<String>> res = arr.stream()
                                .filter(c -> c.getDurationMinutes() >= 60)
                                .collect(Collectors.groupingBy(
                                        EventAttendance::getEventId, Collectors.mapping(EventAttendance::getAttendeeName, Collectors.toList())
                                    ));
                                
        // for(Map.Entry<String,List<String>> entry : res.entrySet()){
        //     System.out.println(entry.getKey() + " " + entry.getValue());
        // }
        
        // res.forEach((eventId, names)->{
        //     System.out.println(eventId + " " + names + " " + "Count=" + names.size());
        // });
        
        res.entrySet().stream()
            .sorted((e1,e2)-> e1.getKey().compareTo(e2.getKey()))
            .forEach(entry ->{
                List<String> names = entry.getValue();
                Collections.sort(names);
                System.out.println(entry.getKey() + " " + names + " " + "Count=" + names.size());
            }
        );
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<EventAttendance> arr = new ArrayList<>();
        for(int i = 0;i<n;i++){
            String[] input = sc.nextLine().split(" ");
            arr.add(new EventAttendance(input[0], input[1], Integer.parseInt(input[2])));
        }
        display(arr,n);
        sc.close();
    }
}