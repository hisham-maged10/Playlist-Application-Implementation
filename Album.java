import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.InputMismatchException;
public class Album implements AlbumInterface {

    private List<Song> songs;
    {
        this.songs = new ArrayList<>();
    }
    public static Set<Song> songsInAlbum=new HashSet<>();
    private int totalMinutes;
    private String[] titles;
    private String name;
    public Album(String name)
    {
        this(name,null);
    }
    public Album(String name,Song[] songs)
    {
        if(name==null)
            throw new NullPointerException("Name of album can't be null");
        if(name.matches("^[^A-Za-z\\d]+$"))
            throw new InputMismatchException("Name of the album can't have symbols");
        this.initialize(songs);
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    private void initialize(Song[] songs)
    {
        if(songs==null)
            return;
        else
        {
            this.songs=new ArrayList<>(Arrays.asList(songs));
            Album.songsInAlbum.addAll(Arrays.asList(songs));
            
        }
        return;
    }


    @Override
    public int indexOf(Song song) {
        return this.songs.indexOf(song);
    }

    @Override
    public int lastIndexOf(Song song) {
        return this.songs.lastIndexOf(song);
    }

    @Override
    public void printAlbum() {
        int i=1;
        for(Song s:this.songs)
            System.out.println(i+++"."+s);
    }

    @Override
    public void printDescAlbum() {
        this.sort(new DescComparator());
        this.printAlbum();
    }
    public void printAscAlbum(){
        this.sort(null);
        this.printAlbum();
    }

    @Override
    public void sort(Comparator<Song> comparator) {
        this.songs.sort(comparator);
        return;
    }

    @Override
    public boolean add(Song s) {
        return Album.songsInAlbum.add(s) && this.songs.add(s);
    }

    @Override
    public boolean add(int index, Song s) {
        if(Album.songsInAlbum.add(s))
        {
            this.songs.add(index,s);
            return true;
        }
        return false;
    }

    @Override
    public Song set(int index, Song s) {

        Song ret=this.songs.set(index,s);
        Album.songsInAlbum.add(s);
        Album.songsInAlbum.remove(ret);
        return ret;
    }

    @Override
    public boolean remove(Song s) {
        return this.songs.remove(s) && Album.songsInAlbum.remove(s);
    }

    @Override
    public Song remove(int index) {
        Song ret=this.songs.remove(index);
        Album.songsInAlbum.remove(ret);
        return ret;
    }

    @Override
    public boolean removeAll(Album album) {
        return this.songs.removeAll(album.getSongs()) && Album.songsInAlbum.removeAll(album.getSongs());
    }

    @Override
    public boolean retainAll(Album album) {
        return this.songs.retainAll(album.getSongs()) && Album.songsInAlbum.retainAll(album.getSongs());
    }

    @Override
    public boolean addAll(Album album) {
        return Album.songsInAlbum.addAll(album.getSongs())&& this.songs.addAll(album.getSongs());
    }

    @Override
    public boolean addAll(int index, Album album) {
        return Album.songsInAlbum.addAll(album.getSongs()) && this.songs.addAll(index,album.getSongs());
    }

    @Override
    public boolean contains(Song s) {
        return this.songs.contains(s);
    }

    @Override
    public boolean containsAll(Album album) {
        return this.songs.containsAll(album.getSongs());
    }

    @Override
    public int size() {
        return this.songs.size();
    }

    @Override
    public boolean isEmpty() {
        return this.songs.isEmpty();
    }

    @Override
    public String[] getTitles() {
        if(this.titles!=null)return this.titles;
        List<String> titles=new ArrayList<>(this.songs.size());
        for(Song s:this.songs)
            titles.add(s.getTitle());
        return (this.titles=titles.toArray(new String[0]));
    }

    @Override
    public int getTotalDuration() {
        if(this.totalMinutes!=0)return this.totalMinutes;
        int totalDuration=0;
        for(Song s:this.songs)
        {
            totalDuration+=s.getDurationInstance().getSeconds()/60;
        }
        return this.totalMinutes=totalDuration;
    }

    @Override
    public int indexOf(String title) {
        for(ListIterator<Song> it=this.songs.listIterator();it.hasNext();)
        {
            if(it.next().getTitle().equalsIgnoreCase(title))
                return it.previousIndex();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String title) {
        for(ListIterator<Song> it=this.songs.listIterator(this.songs.size());it.hasPrevious();)
        {
            if(it.previous().getTitle().equalsIgnoreCase(title))
                return it.nextIndex();
        }
        return -1;
    }

    @Override
    public boolean remove(String title) {
        int index=this.indexOf(title);
        return index==-1?false:Album.songsInAlbum.remove(this.remove(index));
    }

    @Override
    public boolean contains(String title) {
        int index=this.indexOf(title);
        return index==-1?false:true;
    }

    @Override
    public boolean containsAll(String[] titles) {
        return false;
    }

    @Override
    public Song get(int index) {
        return this.songs.get(index);
    }

    @Override
    public List<Song> getSongs() {
        return this.songs;
    }
    
    @Override
    public String toString()
    {
        return this.name+this.size();
    }
    

}