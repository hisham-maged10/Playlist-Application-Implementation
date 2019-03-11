import java.util.Scanner;
import java.util.InputMismatchException;
public class GUI
{
    private static Scanner input;
    private static final int SAMEPLACE=0,INDEX=1,END=2;
    static{
        input=new Scanner(System.in);
    }
    public static void doGUI(String header,String subheader,boolean choice,String... items)
    {
        if(header!=null)
            System.out.println("\t\t\t "+header+" Menu\t \t \t");
        if(subheader!=null)
            System.out.println("\n  "+subheader);
        for(int i=0;i<items.length;i++)
            System.out.println("\t "+(i+1)+"."+items[i]);
        if(choice)
            System.out.print("  Enter your choice: ");
    }
    public static void doInteraction(GUISelector selector,ClientApp app)
    {
        interaction(selector,app);
        System.out.println("Operation Done successfully, returning to main menu");
        GUIHandler.getInstance().mainGui();
    }
    public static void interaction(GUISelector selector,ClientApp app)
    {
        switch(selector)
        {
           case PLAY:System.out.println("Now Playing: "+app.play());break;
           case PAUSE:System.out.println("Paused: "+app.stop());break;
           case PLAY_SONG:GUI.changeSongInteraction(selector,"Choose a song to play in the playlist",app.getSongs(),true,app);break;
           case NEXT:System.out.println("Now Playing: "+app.skipForward());break;
           case PREVIOUS:System.out.println("Now Playing: "+app.skipBackward());break;
           case ENQUEUE:GUI.changeSongInteraction(selector,"Choose a song to enqueue in the playlist",SongDatabase.getSongTitles(),true,app);break;
           case INSERT:GUI.changeSongInteraction(selector,"Choose a song to insert in the playlist",SongDatabase.getSongTitles(),true,app);break;
           case DEQUEUE:GUI.changeSongInteraction(selector,null,null,false,app);break;
           case DEQUEUE_CURRENT:GUI.changeSongInteraction(selector,null,null,false,app);break;
           case DEQUEUE_SONG:GUI.changeSongInteraction(selector,"Choose a song to dequeue from playlist",app.getSongs(),true,app);break;
           case EXIT:System.out.println("Exiting..");
                System.exit(0);
            default:return;
        }
    }
    public static void playSongInteraction(ClientApp app)
    {
        
        System.out.println("Now Playing: "+app.playSong(input.nextInt()-1));
        
    }
    
    public static void enqueueInteraction(ClientApp app)
    {
        Song s=SongDatabase.get(input.nextInt()-1);
            if(Album.songsInAlbum.contains(s))
                System.out.println(app.enqueueSong(s)?"Successfully Enqueued "+s:"Failed to Enqueue"+s);
            else
                System.out.println("failed to enqueue "+s+" as it doesn't exist in an album");
    }

    public static void insertInteraction(ClientApp app)
    {
        Song s=SongDatabase.get(input.nextInt());
            if(Album.songsInAlbum.contains(s))
            {
                app.insertSong(s);
                System.out.println("Successfully added "+s);
            }
            else
                System.out.println("Failed to add, Not in an album"+s);
        
    }
    public static void dequeueInteraction(ClientApp app,int type){
        switch(type)
        {
            case END:System.out.println(app.dequeueSong()?"Successfully dequeued":"Failed to dequeue");return;
            case SAMEPLACE:app.dequeueCurrent();System.out.println("Successfully dequeued");return;
            case INDEX:
                GUI.doGUI("choose a song to remove from playlist","options",true,app.getSongs());
                System.out.println(app.dequeueSong(input.nextInt()-1)?"Song Dequeued successfully":"song failed to dequeue");
                return;
        }
    }
    public static void changeSongInteraction(GUISelector selector,String header,String[] songTitles,boolean choice,ClientApp app)
    {
        if(songTitles!=null)
            GUI.doGUI(header,"Options",choice,songTitles);
        else
            GUI.doGUI(header,"Options",choice);
        try{
            switch(selector)
            {
                case ENQUEUE:enqueueInteraction(app);return;
                case PLAY_SONG:playSongInteraction(app);return;
                case INSERT:insertInteraction(app);return;
                case DEQUEUE:dequeueInteraction(app,END);return;
                case DEQUEUE_CURRENT:dequeueInteraction(app,SAMEPLACE);return;
                case DEQUEUE_SONG:dequeueInteraction(app,INDEX);return;
                default:return;
            }
            
        }catch(IndexOutOfBoundsException ex)
        {
            System.out.println("bad indexing");
            GUI.playSongInteraction(app);
        }
        catch(InputMismatchException ex)
        {
            System.out.println("Please enter a number only");
            GUI.playSongInteraction(app);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        catch(Throwable th)
        {
            th.printStackTrace();
        }
    }


}