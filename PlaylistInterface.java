public interface PlaylistInterface
{
    public Song next();
    public Song previous();
    public Song playSong(int index);
    public String atSongName();
    public int atSongIndex();
    public Song play();
    public boolean enqueue(Song s);
    public void add(int index,Song s);
    public void addNext(Song s);
    public Song dequeue();
    public Song remove(int index);
    public void removeCurrent();
    public boolean status();
    public Song stop();
    public Song replay();
    public void printSongs();
    public String[] getSongs();

}