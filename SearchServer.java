import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A program that reads names from a text file, sorts them, then writes them to another text file.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Purdue CS
 * @version June 13, 2022
 */
public class SearchServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Waiting for the client to connect...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        // read the message from the socket
        do {
            String message = dataInputStream.readUTF();
            ArrayList<String> result = new ArrayList<String>();
            OutputStream outputStream = socket.getOutputStream();
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            int count = 0;
            try {
                FileReader file = new FileReader("searchDatabase.txt");
                BufferedReader bfr = new BufferedReader(file);
                String clientMessage;
                while ((clientMessage = bfr.readLine()) != null) {
                    //stringArray = clientMessage.split(";");
                    //for(int i = 0; i < stringArray.length; i++){
                    result.add(clientMessage);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).contains(message)) {
                    count++;
                }
            }
            dataOutputStream.writeUTF(String.valueOf(count));
            for (int i = 0; i < result.size(); i++) {

                if (result.get(i).contains(message)) {
                    String[] stringArray = result.get(i).split(";");
                    //System.out.println(stringArray[0] + " " + stringArray[1]);
                    String reply = stringArray[0] + stringArray[1];
                    dataOutputStream.writeUTF(reply);
                }
            }
            //if (count >0) {
            //System.out.println("printing");
            String index = dataInputStream.readUTF();
            //System.out.println(index);
            if (!index.equals("secret")) {

                index = index.substring(0, 1);
                //System.out.println(index);
                int place = 0;
                //int place = stringArray.indexOf(index);
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).contains(index)) {
                        place = i;
                    }
                }
                String[] finalPrint = result.get(place).split(";");
                String finalReturn = finalPrint[1] + " " + finalPrint[2];
                //System.out.println(finalPrint[1] + " " + finalPrint[2]);
                dataOutputStream.writeUTF(finalReturn);
            }
        } while (true);

    }
}