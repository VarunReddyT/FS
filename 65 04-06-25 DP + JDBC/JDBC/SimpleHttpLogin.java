import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpLogin {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new LoginHandler());
        server.setExecutor(null);
        System.out.println("Server started on port 8000");
        server.start();
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, 0);
                OutputStream os = exchange.getResponseBody();
                os.write("Only POST method is supported".getBytes());
                os.close();
                return;
            }

            // Read form data
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buf.append(line);
            }
            Map<String, String> params = parseFormData(buf.toString());
            String username = params.get("username");
            String password = params.get("password");
            boolean valid = false;
            if (username != null && password != null) {
                valid = checkCredentials(username, password);
            }

            String response;
            int statusCode;
            if (valid) {
                response = "Login successful";
                statusCode = 200;
            } else {
                response = "Invalid credentials";
                statusCode = 401;
            }
            exchange.sendResponseHeaders(statusCode, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
            Map<String, String> map = new HashMap<>();
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                String[] parts = pair.split("=", 2);
                if (parts.length == 2) {
                    String key = URLDecoder.decode(parts[0], "UTF-8");
                    String value = URLDecoder.decode(parts[1], "UTF-8");
                    map.put(key, value);
                }
            }
            return map;
        }

        private boolean checkCredentials(String username, String password) {
            Dotenv dotenv = new Dotenv(".env");
            String url = "jdbc:mysql://localhost:3306/restdb";
            String dbUser = "root"; 
            String dbPass = dotenv.get("MYSQL_PASSWORD");   
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                System.out.println("Wrong creds");
                e.printStackTrace();
                return false;
            }
        }
    }
}
