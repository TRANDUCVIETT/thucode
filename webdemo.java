package testt;

import java.io.*;
import java.net.*;

public class webdemo {
    public static void main(String[] args) throws IOException {
        int port = 8888;

        // Tạo ServerSocket lắng nghe trên cổng 8888
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server running at http://localhost:" + port);

        while (true) {
            // Chờ kết nối từ client
            Socket socket = server.accept();

            // Đọc request từ client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();

            String request = in.readLine();
            if (request != null && request.startsWith("GET")) {
                // Gửi phản hồi HTTP đơn giản
                String response = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Type: text/html\r\n\r\n" +
                                  "<html><body><h1>Hello World from Java!</h1></body></html>";
                out.write(response.getBytes());
            }

            // Đóng kết nối
            out.close();
            socket.close();
        }
    }
}
