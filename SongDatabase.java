import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
public class SongDatabase{
    private static List<Song> songs;
    private static List<Album> albums;
    static{
        songs=new ArrayList<>(Arrays.asList(SongDatabase.load("./data/Songs.txt")));
        albums=new ArrayList<>(Arrays.asList(SongDatabase.shuffleSongs(5,5)));
    }
    public static Song get(int index)
    {
        return songs.get(index);
    }
    public static String[] getSongTitles()
    {
        String[] songs=new String[SongDatabase.songs.size()];
        for(int i=0;i<songs.length;i++)
            songs[i]=SongDatabase.songs.get(i).getTitle();
        return songs;
    }
    public static void printTable()
    {
        String leftAlignFormat = "| %-15s |     %-16s |%n";

        System.out.format("+-----------------+----------------------+%n");
        System.out.format("| Album           | Song                 |%n");
        System.out.format("+-----------------+----------------------+%n");
        for (int i = 0,n=albums.size(),z=0; i < n; ++i) {
                System.out.format(leftAlignFormat, albums.get(i).getName(),"");
                 for(int j=0,m=albums.get(i).size();j<m;++j)
                    System.out.format(leftAlignFormat, z++,albums.get(i).get(j).getTitle());
        }
        System.out.format("+-----------------+----------------------+%n");
    }
    public static Song[] load(String filepath)
    {
        try(BufferedReader reader=new BufferedReader(new FileReader(filepath));)
        {
            List<Song> loadedSongs=new LinkedList<>();
            String[] items;
            String line=null;
            for(;(line=reader.readLine())!=null;)
            {
                items=line.split(",");
                loadedSongs.add(new Song(items[0],items[1]));
            }
            return loadedSongs.toArray(new Song[0]);
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public static Album[] shuffleSongs(int n,int m)
    {
        Album[] loadedAlbums=new Album[n];
        Set<Song> songs;
        for(int i=0,size=SongDatabase.songs.size();i<n;++i)
        {
            songs=new HashSet<>();
            for(;songs.size()<m;)
            {
                Song s=SongDatabase.songs.get((int)(Math.random()*size-1));
                while(!Album.songsInAlbum.contains(s) && songs.add(s));
            }
            loadedAlbums[i]=new Album("Album"+i,songs.toArray(new Song[0]));
        }
        return loadedAlbums;
    }
}