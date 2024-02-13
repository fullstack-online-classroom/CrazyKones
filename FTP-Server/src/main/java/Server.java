import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


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
            //out.println("Server : You're Connected");
            //exit();
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
                        help();
                        break;
                    case "LS":
                        out.println(lsCommand());
                        break;
                    case "GET":
                        String fileName;
                        out.println("What file do you want?");
                        exit();
                        fileName = inputBufferReader.readLine();
                        getCommand(fileName);
                        out.println("Copy complete!");
                        break;
                    case "PUT":
                        out.println("Command not available!");
                        break;
                    case "MKDIR":
                        String folder;
                        while(true){
                            out.println("Give a name to folder:");
                            exit();
                            folder = inputBufferReader.readLine();
                            while(folder == ""){
                                out.println("That not a good name!!Give a better name: ");
                                exit();
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
                        out.println("Thats not a command");
                        break;
                }
                System.out.println(clientMessage);
                exit();

            }
            out.close();
            inputBufferReader.close();
        }
        catch (IOException e){
            throw new RuntimeException("");
        }
    }

    public void help (){

        out.println("bye or disconnect or quit - terminate connection ");
        out.println("ls - list files available on the server ");
        out.println("put - upload a file from the server ");
        out.println("get - get a file from the server ");
        out.println("mkdir - create a directory on the server \n");

    }
    
    private String lsCommand(){
        //what's the root
        File file = new File("serverRoot/");
        //get all files
        String [] allFiles = file.list();
        //if thenes no file in the directory
        if(allFiles.length == 0){
            return "Theres no Files";
        }
        //getting the files and folders available into an array
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

    private void makeDir(String folder){
        File file = new File("serverRoot/" + folder);
        file.mkdir();
    }

    private void getCommand(String fileName){
        try {
            out.println("files");
            //File getFile = new File("serverRoot/" + fileName);
            FileInputStream file = new FileInputStream("serverRoot/"+ fileName);
            byte buffer[] = new byte[1024];
            int bytesRead = file.read(buffer);
            //sending the file
            while(bytesRead != -1){
                //String read = String.valueOf(bytesRead);
                //out.println("copiar");
                String s = new String(buffer, StandardCharsets.UTF_8);
                out.println(s);
                //System.out.println(bytesRead);
                bytesRead = file.read(buffer);
            }
            file.close();

        }catch (FileNotFoundException e){
            System.out.println("Error - File not found");
        }catch (Exception e){
            System.out.println("Error - There was a problem");
        }
    }


    private void setUpSocketStreams() throws IOException {
        inputBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //outputBufferWriter = new OutputStreamWriter(clientSocket.getOutputStream());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    private void exit(){
        out.println("exit");
    }

    public static void main(String[] args){
        new Server(9000);
    }
}

