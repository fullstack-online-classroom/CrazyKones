import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader inputBufferReader;
    private BufferedWriter outputBufferWriter;
    private PrintWriter out;
    BufferedReader in;
    public Client(){
        try {
            clientSocket = new Socket("127.0.0.1", 9000);
            setUpSocketStreams();
        }
        catch (IOException e){
            throw new RuntimeException("client cannot connect to server");
        }
        String line = "";

        try {
            while  (!line.equals("QUIT")){

                //System.out.println(in.readLine());
                line = inputBufferReader.readLine();
                outputBufferWriter.write(line);
                outputBufferWriter.newLine();
                outputBufferWriter.flush();

                //reading message from server
                String serverEcho;

                //exit
                while ((serverEcho = in.readLine()) != null && !serverEcho.equals("exit")){
                    if(serverEcho.equals("exit")){
                        break;
                    }
                    System.out.println(serverEcho);

                }


            }
            outputBufferWriter.close();
        }
        catch (IOException e){
            throw new RuntimeException("");
        }
    }

    private void setUpSocketStreams() throws IOException {
        inputBufferReader = new BufferedReader(new InputStreamReader(System.in));
        outputBufferWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public static void main(String[] args) {
        new Client();
    }
}
