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
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        //1.1- executando o request e pegando o body:
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //pegar só os dados que nos interessam(parsear dados) [titulo, poster, classificação...]
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(body);
        
       

        //exibir e manipular dados
        var geradora = new GeradoraDeStickers();
        for (int i = 0; i < 3; i++) {
            Map<String,String> conteudo = listaDeConteudos.get(i);

            String urlImagem = 
                //conteudo.get("image")
                conteudo.get("url")
                .replaceAll("(@+)(.*).jpg$", "$1.jpg");


            String titulo = conteudo.get("title").replace(":", " -");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
