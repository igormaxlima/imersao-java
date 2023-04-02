import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class filmesPopulares {
    public static void main(String[] args) throws Exception {
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);


        var parser = new JsonParser();
        List<Map<String, String>> listaDeMelhoresFilmes = parser.parse(body);

        for (Map<String,String> filmes : listaDeMelhoresFilmes) {
            System.out.println( "\u001b[30m \u001b[43m \u001b[1m Titulo: " + "\u001b[3m " + filmes.get("title") + "\u001b[m");
            System.out.println("\u001b[30m \u001b[43m \u001b[1m Link da Imagem: " + "\u001b[3m" + filmes.get("image") + "\u001b[m");
            System.out.println("\u001b[30m \u001b[43m \u001b[1m Avaliaçoes: " + "\u001b[3m" + filmes.get("imDbRating") + "\u001b[m");
            System.out.println();
        }

    }
}
