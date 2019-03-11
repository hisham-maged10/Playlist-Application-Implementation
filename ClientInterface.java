public interface ClientInterface{
    public Song playSong(int index);
    public Song stop();
    public Song play();
    public Song skipForward();
    public Song skipBackward();
    public boolean enqueueSong(Song s);
    public void insertSong(Song s);
    public boolean dequeueSong();
    public void dequeueCurrent();
    public boolean dequeueSong(int index);
    public void printSongs();
    public String[] getSongs();
}