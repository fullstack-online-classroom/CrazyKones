import javax.management.StringValueExp;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    private ServerSocket serverSocket;

    private Socket clientSocket;

    private BufferedReader inputBufferReader;

    //private OutputStreamWriter outputBufferWriter;

    private PrintWriter out;


    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("waiting for connection in port " + port);
            clientSocket = serverSocket.accept();
            System.out.println("Client connected " + clientSocket);
            setUpSocketStreams();
//            out.println("Server : You're Connected");
            label:
            while (true){
                String clientMessage = inputBufferReader.readLine().toUpperCase();

                switch (clientMessage) {
                    case "QUIT":
                    case "BYE":
                    case "DISCONNECT":
                        System.out.println("connection terminated");
                        out.println("connection terminated");
                        break label;
                    case "HELP":
                        //out.println("Commands: QUIT, LS, PUT, GET, MKDIR");
                        help();
                        break;
                    case "LS":
                        out.println(lsCommand());
                        break;
                    case "GET":
                        out.println("Command not available!");
                        break;
                    case "PUT":
                        out.println("Command not available!");
                        break;
                    case "MKDIR":
                        String folder;
                        while(true){
                            out.println("Give a name to folder:");
                            out.println("exit");
                            folder = inputBufferReader.readLine();
                            while(folder == ""){
                                out.println("That not a good name!!Give a better name: ");
                                out.println("exit");
                                folder = inputBufferReader.readLine();

                            }
                            if(folder != null){
                                break;
                            }
                        }
                        makeDir(folder);
                        out.println("We created a folder!");
                        break;
                    default:
                        out.println("Command not available");
                        break;
                }
                System.out.println(clientMessage);
                out.println("exit");
                //out.println(clientMessage + " server receive this message");
                //out.flush();

            }
            out.close();
            inputBufferReader.close();
        }
        catch (IOException e){
            throw new RuntimeException("");
        }
    }

    public void help (){

        out.println("bye or disconnect or quit" +  " - terminate connection ");
        out.println("ls" + " - list files available on the server ");
        out.println("put" + " - upload a file from the server ");
        out.println("get" + " - get a file from the server ");
        out.println("mkdir" + " - create a directory on the server \n");

    }
    
    public String lsCommand(){
        //what the directory
        File file = new File("serverRoot/");
        //get all files
        String [] allFiles = file.list();

        String files = "";
        for(int i = 0; i < allFiles.length; i++){

            if(i == (allFiles.length -1)){
                files += allFiles[i];
                break;
            }
            files += allFiles[i] + ", ";
        }
        
        return files;
    }

    public void makeDir(String folder){
        File file = new File("serverRoot/" + folder);
        file.mkdir();
    }


    private void setUpSocketStreams() throws IOException {
        inputBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //outputBufferWriter = new OutputStreamWriter(clientSocket.getOutputStream());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public static void main(String[] args){
        new Server(9000);
    }
}

