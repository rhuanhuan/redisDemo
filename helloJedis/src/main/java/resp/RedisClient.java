package resp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 从控制台输入简单的 Redis 命令，程序根据命令构造 RESP 协议，发送给 Redis 服务，
 * 根据分析响应结果并显示。
 */
public class RedisClient {

    private static Socket socket;
    private static OutputStream write;
    private static InputStream read;

    public static void main(String[] args) throws IOException {
        socket = new Socket("127.0.0.1",6379);
        write = socket.getOutputStream();
        read = socket.getInputStream();

        Scanner scan = new Scanner(System.in);

        // 判断是否还有输入
        while (scan.hasNextLine()) {
            String str = scan.nextLine();

            // 构造协议
            String commannd = buildCommand(str);

            System.out.println("发送命令为：\r\n" + commannd);

            String result = sendCommand(commannd);

            System.out.println("响应命令为：" + result);
        }

        scan.close();
    }

    private static String sendCommand(String commannd) throws IOException {
        write.write(commannd.toString().getBytes());
        byte[] bytes = new byte[1024];
        read.read(bytes);

        return new String(bytes,"UTF-8");
    }

    private static String buildCommand(String str) {
        if (str != null && !"".equals(str)) {
            String[] strs = str.split(" ");

            StringBuilder builder = new StringBuilder();
            builder.append("*").append(strs.length).append("\r\n");
            for (String str1 : strs) {
                builder.append("$").append(str1.length()).append("\r\n");
                builder.append(str1).append("\r\n");
            }

            return builder.toString();
        }

        return null;
    }

}