import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GeradoraDeStickers {
    
    public void criar() throws Exception{
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filmeQualidadeMaior.jpg"));

        //cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //copiar imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //add uma frase para a nova imagem
        graphics.drawString("Top 250", 0, novaAltura - 100);

        //escrever a nova imagem em um arquivos
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
    }
    public static void main(String[] args) throws Exception {
        //chamando método para gerar o sticker
        var geradora = new GeradoraDeStickers();
        geradora.criar();
    }

}
