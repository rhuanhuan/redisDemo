package resp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedisProxy {
    private static List<String> servers = new ArrayList<>();

    static {
        servers.add("127.0.0.1:6379");
//        servers.add("127.0.0.1:6380");
//        servers.add("127.0.0.1:6381");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6378);
        Socket socket;

        while (true) {
            try {
                // 阻塞，放弃cpu
                socket = serverSocket.accept();

                System.out.println("------------------\nConnection built...");
                InputStream inputStream = socket.getInputStream();

                // 读取input信息
                byte[] request = new byte[1024];
                inputStream.read(request);

                String req = new String(request);
                System.out.println("Request get");
                System.out.println(req);

                // 计算hash，应该分配到哪个redis server
                String property = System.getProperty("line.separator");
                String[] params = req.split(property);

                int keyHash = params[4].hashCode();
                int mod = keyHash % servers.size();
                System.out.println("Proxy server info: " + servers.get(mod));

                // 给对应的redis server发请求
                String[] serverInfo = servers.get(mod).split(":");
                Socket client = new Socket(serverInfo[0], Integer.parseInt(serverInfo[1]));
                client.getOutputStream().write(request);

                byte[] response = new byte[1024];

                client.getInputStream().read(response);
                System.out.println("response: " + Arrays.toString(response));
                client.close();
                socket.getOutputStream().write(response);

                System.out.println("Req finished");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
