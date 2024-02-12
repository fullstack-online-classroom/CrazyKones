import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
            while (true){
                String clientMessage = inputBufferReader.readLine();
                if(clientMessage.equals("QUIT") || clientMessage.equals("BYE") || clientMessage.equals("DISCONNECT")){
                    System.out.println("connection terminated");
                    out.println("connection terminated");
                    break;
                } else if (clientMessage.equals("HELP"))
                {
                    help();
                }
                System.out.println(clientMessage);
                out.println(clientMessage + " this is message from client");

            }
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


    private void setUpSocketStreams() throws IOException {
        inputBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //outputBufferWriter = new OutputStreamWriter(clientSocket.getOutputStream());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public static void main(String[] args){
        new Server(9000);
    }
}

