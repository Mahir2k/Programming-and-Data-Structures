import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
/**
 * Class Show to model a show entity with various attributes.
 * <p>
 * This class encapsulates a show with attributes such as id, type, title, director,
 * country, year, rating, and duration. It provides constructors, accessors, mutators,
 * and a formatted toString method.
 * <p>
 * Time Complexity:
 *  - All accessor and mutator methods run in O(1) time.
 * 
 * @version 1.0
 */
public class Show {
    private String id;
    private String type;
    private String title;
    private String director;
    private String country;
    private int year;
    private String rating;
    private int duration;
    private ArrayList<String> cast;
    private boolean countryQuoted;  // We'll keep the name "countryQuoted"

    /**
     * Constructor with parameters.
     * @param id        ID of the show
     * @param type      "Movie" or "TV Show"
     * @param title     Title of the show
     * @param director  Director name
     * @param country   Country string from CSV
     * @param year      Release year
     * @param rating    Content rating
     * @param duration  Duration in minutes or # of seasons
     */
    public Show(String id, String type, String title, String director, String country, int year, String rating, int duration){
        this.id = id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.country = country;
        this.year = year;
        this.rating = rating;
        this.duration = duration;
        cast = new ArrayList<>();
        countryQuoted = false;
    }

    public String getId(){ 
        return id; 
    }
    public String getType() { 
        return type; 
    }
    public String getTitle() { 
        return title; 
    }
    public String getDirector() { 
        return director; 
    }
    public String getCountry() { 
        return country; 
    }
    public int getYear() { 
        return year; 
    }
    public String getRating() { 
        return rating; 
    }
    public int getDuration() { 
        return duration; 
    }

    /**
     * Returns an iterator to the cast list.
     */
    public Iterator<String> getCastList(){
        return cast.listIterator();
    }
    
    /**
     * Adds a cast member to the show.
     * @param c cast member's name
     */
    public void addCast(String c){
        cast.add(c);
    }
    
    /**
     * Sets whether the country's CSV field was quoted.
     */
    public void setCountryQuoted(boolean flag) {
        this.countryQuoted = flag;
    }
    
    /**
     * Checks if the country's CSV field was quoted.
     */
    public boolean wasCountryQuoted() {
        return countryQuoted;
    }
    
    /**
     * Returns a formatted string representing the show
     * with field widths matching your friend's code.
     */
    public String toString(){
        return String.format("%-8s%-16s%-60s%-32s%-30s%-8d%-8s%-4d", 
            id, type, title, director, country, year, rating, duration);
    }
}
