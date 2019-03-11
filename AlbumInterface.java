import java.util.Comparator;
import java.util.List;
public interface AlbumInterface
{
    public Song get(int index);
    public List<Song> getSongs();
    public int indexOf(Song song);
    public int indexOf(String title);
    public int lastIndexOf(Song song);
    public int lastIndexOf(String title);
    public void printAlbum();
    public void printDescAlbum();
    public void printAscAlbum();
    public void sort(Comparator<Song> comparator);
    public boolean add(Song s);
    public boolean add(int index,Song s);
    public Song set(int index,Song s);
    public boolean remove(Song s);
    public boolean remove(String title);
    public Song remove(int index);
    public boolean removeAll(Album album);
    public boolean retainAll(Album album);
    public boolean addAll(Album album);
    public boolean addAll(int index,Album album);
    public boolean contains(Song s);
    public boolean contains(String title);
    public boolean containsAll(Album album);
    public boolean containsAll(String[] titles);
    public int size();
    public boolean isEmpty();
    public String[] getTitles();
    public int getTotalDuration();
}