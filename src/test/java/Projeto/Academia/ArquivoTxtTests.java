package Projeto.Academia;

import Projeto.Academia.arquivo.ArquivoTxt;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class ArquivoTxtTests {

    @Test
    void criarTxt(){
        var criarArquivoTxt = new ArquivoTxt();
        criarArquivoTxt.criarArquivoTxt("D:\\Edson\\manipulacao_de_arquivos\\texto-java\\test3.txt");
    }
    @Test
    void lerTxt(){
        var lerArquivoTxt = new ArquivoTxt();
        lerArquivoTxt.lerArquivoTxt("D:\\Edson\\manipulacao_de_arquivos\\texto-java\\test3.txt");
    }
    @Test
    void atualizarTxt(){
        var atualizarArquivoTxt = new ArquivoTxt();
        atualizarArquivoTxt.atualizarArquivoTxt("D:\\Edson\\manipulacao_de_arquivos\\texto-java\\test3.txt");
        System.out.println(atualizarArquivoTxt);
    }
    @Test
    void  deletarTxt() throws FileNotFoundException {
        var deletarTxt = new ArquivoTxt();
        deletarTxt.deletarArquivoTxt("D:\\Edson\\manipulacao_de_arquivos\\texto-java\\test2.txt");
    }
}
