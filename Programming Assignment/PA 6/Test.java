
import java.util.Iterator;
import java.util.ListIterator;

public class Test{
    
    public static void main(String[] args){
       ShowList netflix = new ShowList("netflix_titles.csv");
       System.out.println("\nTest case 1: Reading the CSV file of Netflix shows");
       System.out.println(netflix.size() + " shows loaded from the file");

       
       System.out.println("\nTest case 2: finding the number of movies");
       System.out.println("Number of movies in the list: " + netflix.shows("Movie"));
      
      
       System.out.println("\nTest case 3: finding the number of TV shows");
       System.out.println("Number of TV shows in the list: " + netflix.shows("TV Show"));

        
        System.out.println("\nTest case 4: finding a show by title (successful)");
        Show s = netflix.findShow("Maniac");
        if(s == null){
            System.out.println("No show found with the title \"Maniac\"");
        }
        else{
            System.out.println("Show found:\n" + s);
        }
        

        System.out.println("\nTest case 5: finding a show by title (fail)");
        s = netflix.findShow("This Is Us");
        if(s == null){
            System.out.println("No show found with the title \"This Is Us\"");
        }
        else{
            System.out.println("Show found:\n" + s);
        }
       

        System.out.println("\nTest case 6: finding the list of shows for a given director (successful)");
        Iterator<Show> showIterator = netflix.findDirector("Steven Spielberg");
        if(showIterator == null){
            System.out.println("No show found for the director \"Steven Spielberg\"");
        }
        else{
            System.out.println(printForward(showIterator) + " shows found for the director \"Steven Spielberg\"");
        }
        

        System.out.println("\nTest case 7: finding the list of shows for a given director (fail)");
        showIterator = netflix.findDirector("James Cameron");
        if(showIterator == null){
            System.out.println("No show found for the director \"James Cameron\"");
        }
        else{
            System.out.println(printForward(showIterator) + " shows found for the director \"James Cameron\"");
        }
       

        System.out.println("\nTest case 8: finding the list of shows for a given actor (successful)");
        showIterator = netflix.findCastMember("Emma Stone");
        if(showIterator == null){
            System.out.println("No show found for the actress \"Emma Stone\"");
        }
        else{
            System.out.println(printForward(showIterator) + " shows found for the actress \"Emma Stone\"");
        }
        
        
        System.out.println("\nTest case 9: finding the list of shows for a given actor (fail)");
        showIterator = netflix.findCastMember("Pierre Richard");
        if(showIterator == null){
            System.out.println("No show found for the actor \"Pierre Richard\"");
        }
        else{
            System.out.println(printForward(showIterator) + " shows found for the actor \"Pierre Richard\"");
        }
        
       
        System.out.println("\nTest case 10: finding the list of shows for a given year");
        System.out.println("Number of shows released in 2015: " + netflix.showsByYear(2015));
        System.out.println("Number of shows released in 2020: " + netflix.showsByYear(2020));

        
        System.out.println("\nTest case 11: finding the list of movies for a given year");
        System.out.println("Number of movies released in 2015: " + netflix.showsByYearAndType(2015, "Movie"));
        System.out.println("Number of movies released in 2020: " + netflix.showsByYearAndType(2020, "Movie"));


        System.out.println("\nTest case 12: finding the list of TV shows for a given year");
        System.out.println("Number of TV shows released in 2015: " + netflix.showsByYearAndType(2015, "TV Show"));
        System.out.println("Number of TV shows released in 2020: " + netflix.showsByYearAndType(2020, "TV Show"));


        System.out.println("\nTest case 13: viewing the growth of shows over a range of years");
        System.out.println("Growth of the number of shows from 2015 to 2021");
        netflix.showGrowth(2015, 2021);


        System.out.println("\nTest case 14: finding the list of shows for a given rating (TV-MA)");
        System.out.println("Number of TV shows with rating \"TV-MA\": " + netflix.ratings("TV-MA", "TV Show"));
        System.out.println("Number of movies with rating \"TV-MA\": " + netflix.ratings("TV-MA", "Movie"));


        System.out.println("\nTest case 15: finding the list of shows for a given rating (PG-13)");
        System.out.println("Number of TV shows with rating \"PG-13\": " + netflix.ratings("PG-13", "TV Show"));
        System.out.println("Number of movies with rating \"PG-13\": " + netflix.ratings("PG-13", "Movie"));


        System.out.println("\nTest case 16: viewing the top 20 countries for movie production");
        ListIterator<Triple<String,Integer,Integer>> iterator = netflix.moviesByCountry();
        System.out.printf("%-50s\t%-10s\n", "Country", "Number of movies");
        printBackward(iterator, 0);


        System.out.println("\nTest case 17: viewing the top 20 countries for TV show production");
        iterator = netflix.tvShowsByCountry();
        System.out.printf("%-50s\t%-10s\n", "Country", "Number of shows");
        printBackward(iterator, 1);
    }
    /**
     * print a list using its iterator
     * @param iterator to the list
     * @return the number of elements visited by the iterator
     */
    public static <E> int printForward(Iterator<E> iterator){
        int counter = 0;
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            counter++;
        }
        return counter;
    }
    /**
     * prints the last 20 elements of a list using its listIterator
     * @param iterator to the end of the list
     * @param member which member of the triple should be printed: 0 for the second and 1 for the third
     */
    public static void printBackward(ListIterator<Triple<String,Integer,Integer>> iterator, int member){
        int counter = 20;
        while(iterator.hasPrevious() && counter > 0){
            Triple<String,Integer,Integer> triple = iterator.previous();
            int number = (member == 0) ? triple.getSecond() : triple.getThird();
            System.out.printf("%-50s\t%-10d\n", triple.getFirst(), number);
            counter--;
        }
    }
}