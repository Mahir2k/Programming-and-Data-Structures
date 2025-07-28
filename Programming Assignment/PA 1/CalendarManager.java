public class CalendarManager {
    public static void main(String[] args){
        Calendar calendar = new Calendar();
        System.out.println("\nTest case 1: adding events to the calendar (successful)");
        calendar.add(new Meeting("CSE017 class", "RB-184", "01/20/2025", "9:20", "Houria Oudghiri", 114));
        calendar.add(new Appointment("Doctor", "Lehigh Valley Clinic", "01/22/2025", "11:10", "Kathy Brown"));
        calendar.add(new Meeting("Programming Club", "RB-184", "01/23/2025", "10:45", "Jack Buster", 15));
        calendar.add(new Appointment("Bank", "Wells Fargo", "05/12/2025", "10:30", "Sarah Clark"));
        calendar.add(new Meeting("Tutoring", "RB-184", "03/22/2025", "16:30", "Chris Rock", 10));
        calendar.add(new Appointment("Dentist", "Dental Center", "02/24/2025", "13:15", "Kelly Sullivan"));
        calendar.add(new Appointment("Advising", "RB-184", "01/23/2024", "12:20", "Shelly Roberts"));
        calendar.add(new Appointment("SPA", "Beauty & Peace", "12/20/2024", "18:00", "Lisa Mealy"));
        calendar.add(new Meeting("Science Fair", "Stadium", "05/12/2025", "10:15", "Stacy Bouchard", 100));
        calendar.add(new Meeting("Research Seminar", "BC-307", "01/23/2025", "17:30", "Robin Atwood", 35));
        System.out.println(calendar.toString());

        System.out.println("\nTest case 2: adding events to the calendar (fail)");
        boolean added = calendar.add(new Meeting("Office Hours", "Zoom", "01/20/2025", "14:15", "Emily Stanford", 50));
        if(!added){
            System.out.println("Cannot add new events. The calendar is full.");
        }

        System.out.println("\nTest case 3: find an event (successful)");
        Event e = calendar.find("Tutoring");
        if(e == null){
            System.out.println("Event \"Tutoring\" not found");
        }
        else{
            System.out.println("Event \"Tutoring\" found: \n" + e.toString());
        }

        System.out.println("\nTest case 4: find an event (fail)");
        e = calendar.find("Department meeting");
        if(e == null){
            System.out.println("Event \"Department Meeting\" not found");
        }
        else{
            System.out.println("Event \"Department Meeting\" found: \n" + e.toString());
        }

        System.out.println("\nTest case 5: remove an event (successful)");
        boolean found = calendar.remove("Tutoring");
        if(found){
            System.out.println("Event \"Tutoring\" found and removed successfully");
            System.out.println(calendar.toString());
        }
        else{
            System.out.println("Event \"Tutoring\" not found");
        }

        System.out.println("\nTest case 6: remove an event (fail)");
        found = calendar.remove("Department meeting");
        if(found){
            System.out.println("Event \"Department meeting\" found and removed successfully");
        }
        else{
            System.out.println("Event \"Department meeting\" not found");
        }

        System.out.println("\nTest case 7: find events by date (no events)");
        Event[] events = calendar.findByDate("01/01/0000");
        if(events == null){
            System.out.println("0 events found with the date \"01/01/0000\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/01/0000\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 8: find events by date (one event)");
        events = calendar.findByDate("01/20/2025");
        if(events == null){
            System.out.println("0 events found with the date \"01/20/2025\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/20/2025\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 9: find events by date (more than one event)");
        events = calendar.findByDate("01/23/2025");
        if(events == null){
            System.out.println("0 events found with the date \"01/23/2025\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/23/2025\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 10: find events by location (no events)");
        events = calendar.findByLocation("PA-112");
        if(events == null){
            System.out.println("0 events found with the location \"PA-112\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"PA-112\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 11: find events by location (one event)");
        events = calendar.findByLocation("BC-307");
        if(events == null){
            System.out.println("0 events found with the location \"BC-307\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"BC-307\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 12: find events by location (more than one event)");
        events = calendar.findByLocation("RB-184");
        if(events == null){
            System.out.println("0 events found with the location \"RB-184\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"RB-184\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 13: sorting the events by location");
        calendar.sortByLocation();
        System.out.println(calendar.toString());
    }
}
