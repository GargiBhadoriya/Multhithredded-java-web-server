package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetHandler implements HttpHandler{
        @Override
        public void handle(HttpRequest request, HttpResponse response) {

            if ("/form".equals(request.getPath())) {
                String html = """
        <!DOCTYPE html>
        <html>
        <head><title>Submit Name</title></head>
        <body>
          <form action="/submit" method="post">
            Name: <input type="text" name="name"/><br/><br/>
            <button type="submit">Send</button>
          </form>
        </body>
        </html>
        """;
                response.sendHtml(200, "OK", html);
                System.out.println("[FormHandler] Handling " + request.getMethod() + " at " + request.getPath());
                return;
            }
            if ("/portfolio".equals(request.getPath())) {
                try {
                    String html = Files.readString(Paths.get("src/portfolio/index.html"));
                    response.sendHtml(200, "OK", html);
                } catch (IOException e) {
                    // Send a 500 Internal Server Error if reading fails
                    response.sendResponse(500, "Internal Server Error", "Error loading portfolio page.");
                    e.printStackTrace(); // Optional: log the error
                }
                return;
            }



            String responseBody = "Welcome to Runner Web Server!";
            response.sendResponse(200, "OK", responseBody);
        }
    }

