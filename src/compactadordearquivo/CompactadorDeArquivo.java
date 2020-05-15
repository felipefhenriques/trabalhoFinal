package compactadordearquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Luciana Alves
 */
public class CompactadorDeArquivo {

    private static void escreveCompactado(String saida) throws IOException {
        FileWriter escreve = new FileWriter("saidaCompactado.txt", false);
        escreve.write(saida);
        escreve.close();
        System.out.println(saida);
    }
    
    private static void escreveDescompactado(String saida) throws IOException {
        FileWriter escreve = new FileWriter("saidaDescompactado.txt", false);
        escreve.write(saida);
        escreve.close();
        System.out.println(saida);
    }

    private static String[] dividirEmPalavras(String texto) {
        String separadoraDePalavras = "special" + System.currentTimeMillis() + "divider";
        String resultado = "";
        String palavraAtual = "";
        for (int i = 0; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            boolean ehLetraOuNumero = Character.isLetter(caracterAtual) || Character.isDigit(caracterAtual);
            if (ehLetraOuNumero) {
                palavraAtual += caracterAtual;
            } else {
                if (palavraAtual.length() > 0) {
                    resultado += palavraAtual;
                    resultado += separadoraDePalavras;
                    palavraAtual = "";
                }

                resultado += caracterAtual;
                if (i < texto.length() - 1) {
                    resultado += separadoraDePalavras;
                }
            }
        }

        return resultado.split(separadoraDePalavras);
    }
    

    public static String compactarTexto(String texto) {
        String textoCompactado = "";
        String palavras[] = dividirEmPalavras(texto);
        int i;
        ListaEncadeada lista = new ListaEncadeada();
        for (i = 0; i < palavras.length; i++) {
            String palavraAtual = palavras[i];

            if (!Character.isLetter(palavraAtual.charAt(0))) {
                textoCompactado = textoCompactado + palavraAtual;

            } else {
                int posicao = lista.buscarElemento(palavraAtual);
                if (posicao == -1) {
                    lista.insereInicio(palavraAtual);
                    textoCompactado = textoCompactado + palavraAtual;
                } else {
                    textoCompactado = textoCompactado + (1 + posicao);

                    lista.removerElemento(palavraAtual);
                    lista.insereInicio(palavraAtual);
                }
            }

        }
        return textoCompactado;
    }

    public static String descompactarTexto(String texto) {
        String textoDescompactado = "";
        String palavras[] = dividirEmPalavras(texto);
        int i;
        ListaEncadeada lista = new ListaEncadeada();
        for (i = 0; i < palavras.length; i++) {
            String palavraAtual = palavras[i];

            boolean ePalavra = Character.isLetter(palavraAtual.charAt(0));
            boolean eNumero = Character.isDigit(palavraAtual.charAt(0));

            if (! ePalavra && ! eNumero) {
                textoDescompactado = textoDescompactado + palavraAtual;

            } else {
                if (eNumero) {
                    int posicao = Integer.parseInt(palavraAtual);
                    String palavra = lista.buscarElementoPorPosicao(posicao-1);
                    textoDescompactado = textoDescompactado + palavra;
                    lista.removerElemento(palavra);
                    lista.insereInicio(palavra);

                } else {
                    lista.insereInicio(palavraAtual);
                    textoDescompactado = textoDescompactado + palavraAtual;
                }
            }

        }
        return textoDescompactado;
    }
    
    public static String leArquivo() throws FileNotFoundException, IOException{
            
                FileReader arquivo = new FileReader("texto.txt");
                BufferedReader leBufferizado = new BufferedReader(arquivo); 
                String linha = leBufferizado.readLine(); 
                        
                return linha;
}
    

    public static void main(String[] args) throws IOException {
        String entrada = leArquivo();
        String compactado = compactarTexto(entrada);
        String descompactado = descompactarTexto(compactado);
        System.out.println("----> T E X T O   C O M P A C T A D O <----");
        escreveCompactado(compactado);

        System.out.println("----> T E X T O   D E S C O M P A C T A D O <----");
        escreveDescompactado(descompactado);

    }

}
