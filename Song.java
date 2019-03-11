import java.time.Duration;
import java.util.InputMismatchException;
public class Song implements Comparable<Song>{

    private String title;
    private Duration duration;
    private String strDuration;
    public Song(String title,String duration)
    {
        this.validate(title,duration);
        this.title=title;
        this.duration=Duration.ofSeconds(this.computeSeconds(duration));
        this.strDuration=duration;
    }
    public String getDuration()
    {
        return this.duration.toString();
    }
    public Duration getDurationInstance()
    {
        return this.duration;
    }
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public String toString()
    {
        return this.getTitle()+" - "+this.strDuration;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o==null)
            return false;
        if(o==this)
            return true;
        if(!(o instanceof Song))
            return false;
        return ((Song)o).getTitle().equalsIgnoreCase(this.title) && ((Song)o).getDurationInstance().equals(this.duration);
    }
    
    @Override
    public int hashCode()
    {
        return this.title.hashCode()+this.duration.hashCode();
    }

    @Override
    public int compareTo(Song anotherSong)
    {
        return this.title.compareTo(anotherSong.getTitle());
    }

    private int computeSeconds(String duration)
    {
        String[] inputs=duration.trim().split(":");
        return Integer.parseInt(inputs[0])*60+Integer.parseInt(inputs[1]);
    }
    private boolean validateTitle(String title)
    {
        return title.matches("^[A-Za-z\\d\\s']+$");
    }
    private boolean validateDuration(String duration)
    {
        return duration.trim().matches("^\\d+:\\d+$");
    }
    private void validate(String title,String duration)
    {
        if(title==null || duration==null)
            throw new NullPointerException("Title/duration can't be null\nTitle: "+title+"\nDuration: "+duration);
        if(!this.validateTitle(title) || !this.validateDuration(duration))
            throw new InputMismatchException("Title should only contain letters or digits\nDuration should only contain positive numbers separated by a colon in the format (XX:XX)\nTitle: "+title+"\nDuration: "+duration);
    }

}