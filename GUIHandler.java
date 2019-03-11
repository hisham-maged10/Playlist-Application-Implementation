import java.util.Scanner;
import java.util.InputMismatchException;
public class GUIHandler{
    private GUIHandler(ClientApp app){this.app=app;}
    private static GUIHandler instance=null;
    private ClientApp app;
    public static GUIHandler getInstance()
    {
        if(instance==null)
            return instance=new GUIHandler(new ClientApp(new Playlist()));
        else
            return instance;
    }
    private static Scanner input;
    static{
        input=new Scanner(System.in);
    }
    public void start()
    {
        createPlaylistGUI();
    }
    public void mainGui()
    {
        app.printSongs();
        GUI.doGUI("Playlist Application","Options",true,"Play","Pause","Play a certain song in playlist","Next","Previous","Enqueue a song","Insert a song at current position","Dequeue top song","Dequeue current song","Dequeue a certain song","Exit");
        getInteraction();
    }
    public void createPlaylistGUI()
    {
        SongDatabase.printTable();
        GUI.doGUI("Playlist Creation","Options",true,"Add Songs","Exit");
        try{
            switch(input.nextInt())
            {
                case 1:
                    System.out.println("choose songs separated by commas (2 songs atleast)");
                    input.nextLine();
                    String choices=input.nextLine();
                    if(choices.matches("^(\\d+,\\d+(,\\d){0,})+$"))
                    {
                        String[] songs=choices.split(",");
                        for(String e:songs)
                            app.enqueueSong(SongDatabase.get(Integer.parseInt(e)));
                        break;

                    }else{
                        System.err.println("Wrong format please input in (x,y) format");
                        createPlaylistGUI();
                        return;
                    }

                case 2:System.exit(0);break;
            }
        mainGui();
        return;
        }catch(IndexOutOfBoundsException ex)
        {
            System.err.println("Wrong indexing, retry");
            createPlaylistGUI();
            return;
        }catch(InputMismatchException ex)
        {
            System.err.println("Wrong format, please enter digits only");
            createPlaylistGUI();
            return;
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        catch(Throwable th)
        {
            th.printStackTrace();
        }
    }
    public void getInteraction()
    {
        int choice=input.nextInt();
        if(app.isEmpty() && (choice!=6 || choice !=7))
        {
            System.out.println("Playlist is empty, you can only Enqueue or insert a song");
            return;
        }
        try{
            switch(choice)
            {
                case 1:GUI.doInteraction(GUISelector.PLAY,app);return;
                case 2:GUI.doInteraction(GUISelector.PAUSE,app);return;
                case 3:GUI.doInteraction(GUISelector.PLAY_SONG,app);return;
                case 4:GUI.doInteraction(GUISelector.NEXT,app);return;
                case 5:GUI.doInteraction(GUISelector.PREVIOUS,app);return;
                case 6:GUI.doInteraction(GUISelector.ENQUEUE,app);return;
                case 7:GUI.doInteraction(GUISelector.INSERT,app);return;
                case 8:GUI.doInteraction(GUISelector.DEQUEUE,app);return;
                case 9:GUI.doInteraction(GUISelector.DEQUEUE_CURRENT,app);return;
                case 10:GUI.doInteraction(GUISelector.DEQUEUE_SONG,app);return;
                case 11:GUI.doInteraction(GUISelector.EXIT,app);return;
                default:System.out.println("Wong input, try again!");getInteraction();return;
            }
        }catch(InputMismatchException ex)
        {
            System.out.println("Please only enter digits");
            getInteraction();
            return;
        } 
    }
}