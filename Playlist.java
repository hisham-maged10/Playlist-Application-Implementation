import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
public class Playlist implements PlaylistInterface
{
    private List<Song> playlist;
    private boolean isForward;
    private boolean status;

    public ListIterator<Song> it;
    {
        this.playlist=new LinkedList<>();
        this.it=this.playlist.listIterator();
    }

    public Playlist()
    {
        this(null);
    }
    public Playlist(Song[] songs)
    {
        initialize(songs);
    }


    private void initialize(Song[] songs)
    {
        if(songs==null)
            return;
        for(Song s: songs)
        {
            if(Album.songsInAlbum.contains(s))
                this.playlist.add(s);
        }
    }

    @Override
    public Song next() {
        if(it.hasNext())
        {
            if(isForward)
                return it.next();
            else
            {
                it.next();
                this.isForward=true;
                return it.next();
            }
        }
        else
            return null;
    }

    @Override
    public Song previous() {
        if(it.hasPrevious())
        {
         
            if(isForward)
            {
                it.previous();
                return it.previous();
            }
            else
                return it.previous();
        }
        else
            return null;
    }

    @Override
    public String atSongName() {
        Song s=null;
        if(isForward)
        {
            s=it.previous();
            it.next();
        }
        else
        {
            s=it.next();
            it.previous();
        }
        return s.getTitle();
    }

    @Override
    public int atSongIndex() {
        return isForward?it.previousIndex():it.nextIndex();
    }

    @Override
    public Song play() {
        this.status=true;
        Song s=null;
        if(isForward)
        {
            if(it.hasPrevious())
            {
                s=it.previous();
                it.next();
            }
            else
                s=it.next();
        }else
        {
            if(it.hasNext())
            {
                s=it.next();
                it.previous();
            }
            else
                s=it.previous();
        }
        return s;
    }

    @Override
    public boolean status() {
        return this.status;
    }

    @Override
    public Song stop() {
       Song s=null;
        if(isForward)
        {
            if(it.hasPrevious())
            {
                s=it.previous();
                it.next();
            }
            else
                s=it.next();
        }else
        {
            if(it.hasNext())
            {
                s=it.next();
                it.previous();
            }
            else
                s=it.previous();
        }
        this.status=false;
        return s;
    }

    @Override
    public Song replay() {
        if(it.hasPrevious())
        {
            Song nw=it.previous();
            it.next();
            return nw;
        }
        else
            return null;
    }

    @Override
    public boolean enqueue(Song s) {
        if(this.playlist.add(s))
        {
            it=playlist.listIterator(it.nextIndex());
            return true;
        }
        else
            return false;
    }

    @Override
    public void printSongs()
    {
        int i=1;
        for(Song s:this.playlist)
        {
            System.out.println(i+++". "+s);
        }
    }

    @Override
    public void add(int index, Song s) {
        this.playlist.add(index,s);    
        it=playlist.listIterator(it.nextIndex());
    
    }

    @Override
    public void addNext(Song s)
    {
        it.add(s);
    }
    @Override
    public Song dequeue() {
        Song s=((LinkedList<Song>)playlist).poll();
        it=playlist.listIterator(it.nextIndex());
        return s;
    }

    @Override
    public Song remove(int index) {
        Song s=((LinkedList<Song>)playlist).remove(index);
        it=playlist.listIterator(it.nextIndex());
        return s;
    }

    @Override
    public void removeCurrent() {
        it.remove();
    }

    @Override
    public Song playSong(int index)
    {
        if(index<0 || index>=playlist.size())
            throw new IndexOutOfBoundsException("index is out of range index: "+index);
        if(index>=it.nextIndex())
            for(;it.hasNext();it.next())
            {
                if(it.nextIndex()==index)
                    return it.next();
             }   
            
        else
            for(;it.hasPrevious();it.previous())
                if(it.previousIndex()==index)
                    return it.previous();
     System.out.println("done");
        return null;
    }

    @Override
    public String[] getSongs()
    {
        String[] songs=new String[this.playlist.size()];
        ListIterator<Song> iterator=this.playlist.listIterator();
        for(int i=0;i<songs.length && iterator.hasNext();i++)
            songs[i]=iterator.next().toString();
        return songs;
    }

    public boolean isEmpty()
    {
        return playlist.isEmpty();
    }
   
}