import javax.swing.*;
import java.io.*;
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
public class SearchClient {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    ObjectOutputStream oos = null;
    JFrame f;
    SearchClient() throws IOException {
        boolean flag = false;
        boolean flag1 = false;
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Hello, Welcome.");
        String name = JOptionPane.showInputDialog(f, "Enter Name");
        int port = Integer.parseInt(JOptionPane.showInputDialog(f, "Enter Port Number"));
        try {
            socket = new Socket(name, port);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Connection Unsuccessfully", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        //SearchClient client = new SearchClient(name, port);
        JOptionPane.showMessageDialog(f, "Connection successfully");
        OutputStream outputStream = socket.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        do {
            String text = JOptionPane.showInputDialog(f, "Enter a search text");
            //Server server = new Server(text);
            //oos = new ObjectOutputStream(socket.getOutputStream());
            //oos.("" + text);
            dataOutputStream.writeUTF(text);
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            // read the message from the socket
            String message = dataInputStream.readUTF();
            //System.out.println(message);
            int x = Integer.parseInt(message);
            ArrayList<Object> options = new ArrayList<>();
            //System.out.println(x);
            for (int i = 0; i < x; i++) {
                message = dataInputStream.readUTF();
                options.add(message);
                //System.out.println(options);
            }
            //System.out.println("1");
            //System.out.println(options.size());
            Object[] result = new Object[options.size()];
            for (int i = 0; i < options.size(); i++) {
                //System.out.println("1");
                result[i] = options.get(i);
                //System.out.println(result[i]);
            }
            if (result.length > 0) {
                //...and passing `frame` instead of `null` as first parameter
                //System.out.println("1");
                Object selectionObject = JOptionPane.showInputDialog(f, "Choose", "Menu",
                        JOptionPane.PLAIN_MESSAGE, null, result, result[0]);
                dataOutputStream.writeUTF(String.valueOf(selectionObject));
                String last = dataInputStream.readUTF();
                JOptionPane.showMessageDialog(f, last);
            } else {
                JOptionPane.showMessageDialog(f, "Error there is nothing found");
                dataOutputStream.writeUTF("secret");
                //ystem.out.println("working");
                //JOptionPane.showMessageDialog(f, "Error there is nothing found", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
            //String title = dataInputStream.readUTF();
            //String description = dataInputStream.readUTF();
            //this.socket = new Socket(serverIP, serverPort);
            String again = JOptionPane.showInputDialog(f, "Would you like to search again?");
            if (again.equals("Yes") || again.equals("yes")) {
                flag = true;
            } else {
                JOptionPane.showMessageDialog(f, "GoodBye Thank you");
                //f.dispose();
                flag = false;
            }
        } while (flag);

    }

    public static void main(String[] args) {
        try {
            new SearchClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
