import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas  {
    

    public void cria(InputStream inputStream, String nomeArquivo, String arquivoDestino) throws Exception {
 
        // // leitura da imagem (por arquivo)
        BufferedImage imagemOriginal = ImageIO.read(inputStream);


        // criar nova imagem em memória com traansparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        // copiar a imagem original pra novo imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);


        // configurar fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(font);

        // escrever uma frase na nova imagem 
        graphics.drawString("BONZINHO, VAI!", 0, novaAltura - 100);  
        graphics.drawString("SELO IGOR APROVAÇÃO", 0, novaAltura - 100);

        
        // escrever a nova imagem em um arquivo 
        ImageIO.write(novaImagem, "png", new File(arquivoDestino));


    }

}
