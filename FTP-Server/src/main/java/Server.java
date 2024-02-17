import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;


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
                System.out.println("Client message: " + clientMessage);

                switch (clientMessage) {
                    case "QUIT":
                    case "BYE":
                    case "DISCONNECT":
                        System.out.println("Connection terminated");
                        out.println("Connection terminated");
                        break label;
                    case "HELP":
                        help();
                        exit();
                        break;
                    case "LS":
                        out.println(lsCommand());
                        exit();
                        break;
                    case "GET":
                        String fileName;
                        out.println("What file do you want?");
                        exit();
                        fileName = inputBufferReader.readLine();
                        System.out.println(fileName);
                        out.println("files");
                        getCommand(fileName);
                        break;
                    case "PUT":
                        out.println("Command not available!");
                        exit();
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
                        exit();
                        break;
                    default:
                        out.println("Thats not a command");
                        exit();
                        break;
                }
                //System.out.println("Client message: " + clientMessage);
                //exit();

            }
            out.close();
            inputBufferReader.close();
        }
        catch (IOException e){
            throw new RuntimeException("");
        }
    }

    private void help (){

        out.println("bye or disconnect or quit - terminate connection ");
        out.println("ls - list files available on the server ");
        out.println("put - upload a file from the server ");
        out.println("get - get a file from the server ");
        out.println("mkdir - create a directory on the server"); // \n

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

            //gets the file
            File teste = new File("serverRoot/" + fileName);
            FileInputStream file = new FileInputStream("serverRoot/" + fileName);

            //get the bytes that we need to give to client
            byte buffer[] = new byte[(int) teste.length()];
            //converts into bytes
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file);
            bufferedInputStream.read(buffer, 0, buffer.length);

            //Send file to the client
            OutputStream downloadFile = clientSocket.getOutputStream();
            downloadFile.write(buffer,0, buffer.length);
            downloadFile.flush();

            //Close streams tht we dont need anymore
            file.close();
            bufferedInputStream.close();
            //out.println("Copy complete!");
            //exit();


        }catch (FileNotFoundException e){
            System.out.println("Error - File not found");
        }catch (Exception e){
            System.out.println("Error - There was a problem");
        }
    }
    private void putCommand(String fileName){

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

