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

        //1- Fazer conexão http e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        //1.1- executando o request e pegando o body:
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //pegar só os dados que nos interessam(parsear dados) [titulo, poster, classificação...]
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
       

        //exibir e manipular dados
        var geradora = new GeradoraDeStickers();
        for (int i = 0; i < 10; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");


            String titulo = filme.get("title").replace(":", " -");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
