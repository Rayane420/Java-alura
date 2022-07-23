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
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
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
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
