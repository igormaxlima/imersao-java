import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP(protocolo para se comunicar com a web) e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);


        // extrair só os dados que interessam (titulo, imagem , rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados 
        var gerador = new GeradoraDeFigurinhas();
        
        for (Map<String,String> filme : listaDeFilmes) {

            String titulo = filme.get("title");
            String urlImagem = filme.get("image");
            String rating = filme.get("imDbRating");

            InputStream inputStream = new URL(urlImagem).openStream();
            
            String nomeArquivo = titulo + ".png";
            String arquivoDestino = "saida/" + nomeArquivo;
            

            gerador.cria(inputStream, nomeArquivo, arquivoDestino);
            
            System.out.println(titulo);
            System.out.println(rating);
            System.out.println();
        }
    }

}
