import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeStickers {

    public void criar(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem

        
        //InputStream inputStream = new FileInputStream(new File("entrada/filmeQualidadeMaior.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@.jpg").openStream();
       
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //copiar imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //Configurações da Fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor( new Color( 57, 255, 20 ) );
        graphics.setFont(fonte);

        //add uma frase para a nova imagem
        graphics.drawString("Top 250 IMDB", 160, novaAltura - 80);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
