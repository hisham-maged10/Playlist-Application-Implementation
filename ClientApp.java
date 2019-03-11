public class ClientApp implements ClientInterface
{
    private Playlist playlist;
    public ClientApp(Playlist playlist)
    {
        this.playlist=playlist;
    }
    @Override
    public Song playSong(int index) {
        return playlist.playSong(index);
    }

    @Override
    public Song stop() {
        return playlist.stop();
    }


    @Override
    public Song play() {
        return playlist.play();
    }

    @Override
    public Song skipForward() {
        return playlist.next();
    }

    @Override
    public Song skipBackward() {
        return playlist.previous();
    }

    @Override
    public boolean enqueueSong(Song s) {
        return playlist.enqueue(s);
    }

    @Override
    public void insertSong(Song s) {
         playlist.addNext(s);
    }

    @Override
    public boolean dequeueSong() {
        return playlist.dequeue()!=null;
    }

    @Override
    public void dequeueCurrent() {
         playlist.removeCurrent();
    }

    @Override
    public boolean dequeueSong(int index) {
        return playlist.remove(index)!=null;
    }

    @Override
    public void printSongs()
    {
        playlist.printSongs();
    }

    @Override
    public String[] getSongs()
    {
        return playlist.getSongs();
    }
    public boolean isEmpty()
    {
        return playlist.isEmpty();
    }
   
}