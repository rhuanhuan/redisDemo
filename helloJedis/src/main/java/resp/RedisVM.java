package resp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisVM {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6378);

        Socket socket = serverSocket.accept();
        byte[] request = new byte[1024];

        InputStream inputStream = socket.getInputStream();

        inputStream.read(request);

        System.out.println(new String(request));
        socket.close();
        serverSocket.close();
    }
}
