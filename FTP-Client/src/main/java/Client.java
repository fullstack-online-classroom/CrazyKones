import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader inputBufferReader;
    private BufferedWriter outputBufferWriter;
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

                line = inputBufferReader.readLine();
                outputBufferWriter.write(line);
                outputBufferWriter.newLine();
                outputBufferWriter.flush();

                //reading message from server
                String serverEcho;

                //when server sends exit it means that we are not waiting for a message that the servers sends
                while ((serverEcho = in.readLine()) != null && !serverEcho.equals("exit")){
                    String type;
                    type = serverEcho;

                    if(type.equals("files")){

                        try {
                            if (serverEcho.equals("exit")) {
                                break;
                            }
                            FileOutputStream download = new FileOutputStream("clientRoot/" + line);
                            serverEcho = in.readLine();
                            System.out.println(serverEcho);
                            download.write(serverEcho.getBytes());

                        }catch (FileNotFoundException e){
                            System.out.println("error teste");
                        }

                    }

                            System.out.println(serverEcho);

                }

            }
            outputBufferWriter.close();
        }
        catch (IOException e){
            throw new RuntimeException("");
        }catch (Exception e){
            System.out.println("we got an error");
        }
    }

    private void setUpSocketStreams() throws IOException {
        inputBufferReader = new BufferedReader(new InputStreamReader(System.in));
        outputBufferWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public static void main(String[] args) {
        new Client();
    }
}
