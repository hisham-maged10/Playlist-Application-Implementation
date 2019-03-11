import java.util.Comparator;
public class DescComparator implements Comparator<Song>
{
    public int compare(Song s1,Song s2)
    {
        return s2.compareTo(s1);
    }    
}