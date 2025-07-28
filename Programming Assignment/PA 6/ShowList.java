import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class ShowList to manage a list of shows with
 * aggregator queries, no for/foreach except readShows/cast, showGrowth/years.
 */
public class ShowList {
    private LinkedList<Show> showsLinked; // renamed from "shows"

    /**
     * Constructor: initializes an empty linked list and reads from CSV.
     */
    public ShowList(String filename){
        showsLinked = new LinkedList<>();
        readShows(filename);
    }
    
    /**
     * readShows method splits each CSV line on commas not in quotes,
     * then uses a for loop to parse cast members (allowed by instructions).
     */
    private void readShows(String filename){
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            if(fileScanner.hasNextLine()){
                fileScanner.nextLine(); // skip header
            }
            while(fileScanner.hasNextLine()){
                String currentLine = fileScanner.nextLine().trim();
                if(currentLine.isEmpty()) continue;

                // Splitting on commas not in quotes
                String[] tokensRenamed = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if(tokensRenamed.length < 10) continue;
                
                String id = removeOuterQuotes(tokensRenamed[0]);
                String type = removeOuterQuotes(tokensRenamed[1]);
                String title = removeOuterQuotes(tokensRenamed[2]);
                String director = removeOuterQuotes(tokensRenamed[3]);
                String castStr = removeOuterQuotes(tokensRenamed[4]);
                String rawCountry = tokensRenamed[5].trim();
                boolean isQuoted = rawCountry.startsWith("\"") && rawCountry.endsWith("\"");
                String country = removeOuterQuotes(rawCountry);
                // tokens[6] => date added => ignoring
                String yearStr = removeOuterQuotes(tokensRenamed[7]);
                String rating = removeOuterQuotes(tokensRenamed[8]);
                String durationStr = removeOuterQuotes(tokensRenamed[9]);
                
                int yearVal = 0;
                try {
                    yearVal = Integer.parseInt(yearStr);
                } catch(NumberFormatException e) {}

                int durationVal = 0;
                try {
                    String[] durSplit = durationStr.split(" ");
                    durationVal = Integer.parseInt(durSplit[0]);
                } catch(Exception e) {}

                Show newShow = new Show(id, type, title, director, country, yearVal, rating, durationVal);
                newShow.setCountryQuoted(isQuoted);

                // parse cast members if not "N/A" or ""
                if(!castStr.equals("") && !castStr.equals("N/A")){
                    String[] castMembersArr = castStr.split(",");
                    for(int i=0; i<castMembersArr.length; i++){
                        String aMember = castMembersArr[i].trim();
                        if(!aMember.isEmpty()){
                            newShow.addCast(aMember);
                        }
                    }
                }
                showsLinked.add(newShow);
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found " + filename);
        }
    }
    
    /**
     * Helper to remove leading/trailing quotes from a string.
     */
    private String removeOuterQuotes(String rawToken){
        String trimmedValue = rawToken.trim();
        if(trimmedValue.startsWith("\"") && trimmedValue.endsWith("\"") && trimmedValue.length() > 1){
            return trimmedValue.substring(1, trimmedValue.length()-1).trim();
        }
        return trimmedValue;
    }

    /**
     * Returns the total number of shows in the list.
     */
    public int size(){
        return showsLinked.size();
    }

    /**
     * Finds a show by the given title.
     */
    public Show findShow(String title){
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show candidateShow = showIterator.next();
            if(candidateShow.getTitle().equals(title)){
                return candidateShow;
            }
        }
        return null;
    }

    /**
     * Returns an iterator over shows directed by 'director', or null if none found.
     */
    public Iterator<Show> findDirector(String director){
        ArrayList<Show> resultsList = new ArrayList<>();
        boolean anyFound = false;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show candidate = showIterator.next();
            if(candidate.getDirector().equals(director)){
                resultsList.add(candidate);
                anyFound = true;
            }
        }
        if(!anyFound) return null;
        return resultsList.iterator();
    }

    /**
     * Returns an iterator over shows that feature 'actor' in their cast,
     * or null if none found.
     */
    public Iterator<Show> findCastMember(String actor){
        ArrayList<Show> localResults = new ArrayList<>();
        boolean foundOne = false;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show sCandidate = showIterator.next();
            Iterator<String> castIterator = sCandidate.getCastList();
            while(castIterator.hasNext()){
                String castMember = castIterator.next();
                if(castMember.equals(actor)){
                    localResults.add(sCandidate);
                    foundOne = true;
                    break;
                }
            }
        }
        if(!foundOne) return null;
        return localResults.iterator();
    }

    /**
     * Returns how many shows are of the given 'type' (Movie or TV Show).
     */
    public int shows(String type){
        int countShows = 0;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show sCandidate = showIterator.next();
            if(sCandidate.getType().equals(type)){
                countShows++;
            }
        }
        return countShows;
    }

    /**
     * Returns how many shows are from the given 'year'.
     */
    public int showsByYear(int year){
        int countYear = 0;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show sCandidate = showIterator.next();
            if(sCandidate.getYear() == year){
                countYear++;
            }
        }
        return countYear;
    }

    /**
     * Returns how many shows are from the given 'year' and 'type'.
     */
    public int showsByYearAndType(int year, String type){
        int countYearType = 0;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show sCandidate = showIterator.next();
            if(sCandidate.getYear() == year && sCandidate.getType().equals(type)){
                countYearType++;
            }
        }
        return countYearType;
    }

    /**
     * Returns how many shows have 'rating' for a given 'type'.
     */
    public int ratings(String rating, String type){
        int ratingCount = 0;
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show sCandidate = showIterator.next();
            if(sCandidate.getType().equals(type) && sCandidate.getRating().equals(rating)){
                ratingCount++;
            }
        }
        return ratingCount;
    }

    /**
     * showGrowth prints # of shows from start..end plus growth% with 1 decimal place.
     */
    public void showGrowth(int start, int end){
        System.out.println("Year    # Shows Growth");
        if(start > end) return;
        int prevYearCount = showsByYear(start);
        System.out.printf("%d    %d\n", start, prevYearCount);
        double totalGrowth = 0.0;
        int yearsSpan = 0;
        for(int y = start+1; y <= end; y++){
            int currentCount = showsByYear(y);
            double growth = 0.0;
            if(prevYearCount != 0){
                growth = ((double)(currentCount - prevYearCount)) * 100.0 / prevYearCount;
            }
            totalGrowth += growth;
            yearsSpan++;
            System.out.printf("%d    %d    %.1f%%\n", y, currentCount, growth);
            prevYearCount = currentCount;
        }
        if(yearsSpan > 0){
            double averageGrowth = totalGrowth / yearsSpan;
            System.out.printf("Average         %.1f%%\n", averageGrowth);
        }
    }

    /**
     * Private aggregator: lumps or splits countries.
     */
    private ArrayList<Triple<String,Integer,Integer>> showsByCountry(){
        HashMap<String, Triple<String,Integer,Integer>> aggregatorMap = new HashMap<>();
        Iterator<Show> showIterator = showsLinked.iterator();
        while(showIterator.hasNext()){
            Show showItem = showIterator.next();
            String countryField = showItem.getCountry();
            if(countryField == null || countryField.isEmpty()){
                countryField = "(none)";
            }
            // If there's a comma, we forcibly split, ignoring wasCountryQuoted logic.
            // This ensures multi-country combos get separate counts.
            if(countryField.contains(",")){
                String[] splitted = countryField.split(",");
                for(int i=0; i<splitted.length; i++){
                    String subC = splitted[i].trim();
                    if(!subC.isEmpty()){
                        incrementCountryCount(aggregatorMap, subC, showItem.getType());
                    }
                }
            } else {
                incrementCountryCount(aggregatorMap, countryField, showItem.getType());
            }
        }
        return new ArrayList<>(aggregatorMap.values());
    }

    /**
     * Helper to increment aggregator map for a single country c.
     */
    private void incrementCountryCount(HashMap<String, Triple<String,Integer,Integer>> aggregatorMap,
                                       String c, String showType){
        if(!aggregatorMap.containsKey(c)){
            aggregatorMap.put(c, new Triple<>(c, 0, 0));
        }
        Triple<String,Integer,Integer> countTrip = aggregatorMap.get(c);
        if(showType.equals("Movie")){
            countTrip.setSecond(countTrip.getSecond() + 1);
        } else if(showType.equals("TV Show")){
            countTrip.setThird(countTrip.getThird() + 1);
        }
    }

    /**
     * Sort by # of movies descending, returns a ListIterator at the end.
     */
    public ListIterator<Triple<String,Integer,Integer>> moviesByCountry(){
        ArrayList<Triple<String,Integer,Integer>> dataRef = showsByCountry();
        Collections.sort(dataRef, new Comparator<Triple<String,Integer,Integer>>() {
            public int compare(Triple<String,Integer,Integer> a, Triple<String,Integer,Integer> b){
                // descending by second
                return Integer.compare(b.getSecond(), a.getSecond());
            }
        });
        System.out.println("Number of countries: " + dataRef.size());
        return dataRef.listIterator(dataRef.size());
    }

    /**
     * Sort by # of TV shows descending, returns a ListIterator at the end.
     */
    public ListIterator<Triple<String,Integer,Integer>> tvShowsByCountry(){
        ArrayList<Triple<String,Integer,Integer>> dataRef = showsByCountry();
        Collections.sort(dataRef, new Comparator<Triple<String,Integer,Integer>>() {
            public int compare(Triple<String,Integer,Integer> a, Triple<String,Integer,Integer> b){
                // descending by third
                return Integer.compare(b.getThird(), a.getThird());
            }
        });
        System.out.println("Number of countries: " + dataRef.size());
        return dataRef.listIterator(dataRef.size());
    }

    public int uniqueCountriesCount(){
        ArrayList<Triple<String,Integer,Integer>> arr = showsByCountry();
        return arr.size();
    }
}
