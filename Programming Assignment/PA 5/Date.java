/**
 * Class to represent a date
 */
public class Date implements Comparable<Date>, Cloneable{
    // data members
    private int month, day, year;
    /**
     * Constructor to build the object from a string
     * @param d the date string
     * @throws FormatMismatchException if the the format, month, day, or year are not valid
     */
    public Date(String d) throws FormatMismatchException{
        if(!d.matches("\\d{1,2}/\\d{1,2}/\\d{4}")){
            throw new FormatMismatchException("Invalid Date Format (mm/dd/yyyyy): " + d);
        }
        String[] tokens = d.split("/");
        int m = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);
        int y = Integer.parseInt(tokens[2]);
        if(m < 1 || m > 12)
            throw new FormatMismatchException("Invalid Month (expected: 1 to 12): " + m);
        if(day < 1 || day > 31)
            throw new FormatMismatchException("Invalid Day (expected: 1 to 31): " + day);
        if(y < 2010 || y > 2030)
            throw new FormatMismatchException("Invalid Year (expected: 2010 to 2030): " + y);
        month = m;
        this.day = day;
        year = y;
    }
    /**
     * getter for the month
     * @return the value of the month
     */
    public int getMonth(){ return month;}
    /**
     * getter for the day
     * @return the value of the day
     */
    public int getDay(){ return day;}
    /**
     * getter for the year
     * @return the value of the year
     */
    public int getYear(){ return year;}
    /**
     * setter for the month
     * @param m the new value of the month
     */
    public void setMonth(int m){ month = m;}
    /**
     * setter for the day
     * @param d the new value of the day
     */
    public void setDay(int d){ day = d;}
    /**
     * setter for the year
     * @param y the new value of the year
     */
    public void setYear(int y){ year = y;}
    /**
     * setter for the date object info
     * @return a formatted string mm/dd/yyyy
     */
    public String toString(){
        return String.format("%02d/%02d/%04d", month, day, year);
    }
    /**
     * compareTo method to order two Date objects
     * @param d the date being compared to this date
     * @return 0 if the two dates are identical
     *           a positive value if this date is after d
     *           a negative value if this date is before d
     */
    public int compareTo(Date d){
        if(this.year == d.year){
            if(this.month == d.month){
                return this.day - d.day;
            }
            return this.month - d.month;
        }
        return this.year - d.year;
    }
    /**
     * equals method to compare two dates for equality only
     * @param o the date object being compared to this date
     * @return true is the two dates are identical, false otherwise
     */
    public boolean equals(Object o){
        if(o instanceof Date){
            Date d = (Date) o;
            return this.month == d.month &&
                   this.day == d.day &&
                   this.year == d.year;
        }
        return false;
    }
}