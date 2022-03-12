// socket programming open a web page given a URI and shows the text
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
// import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	public static void main(String[] args) {
		String host = "monta.if.its.ac.id";
		int port = 80;
		
		try {
            Socket socket = new Socket(host, port);
            String reqMethod = "GET / HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
            System.out.println(reqMethod);
 
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            // DataInputStream dis = new DataInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
 
            bos.write(reqMethod.getBytes());
            bos.flush();
 
            int bufferSize = 100;
            byte[] bResp = new byte[bufferSize];
            int c = bis.read(bResp);
            String resp = "";
            // String resp = dis.readLine();
			// String first_line = resp.split(" ")[1];
			while (c != -1) {
				resp += new String(bResp, 0, c);
				c = bis.read(bResp);
			}

            // while(c != -1) {
            //     resp += (new String(bResp));
            //     bResp = new byte[bufferSize];
            //     c = bis.read(bResp);
 
            // }
            
			// System.out.println("Status code: " + first_line);
			System.out.println(resp);
 
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}