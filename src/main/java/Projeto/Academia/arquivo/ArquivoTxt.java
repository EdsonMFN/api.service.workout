package Projeto.Academia.arquivo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ArquivoTxt {

    public void criarArquivoTxt(final String nomeArquivo) {
        try (FileOutputStream arquivoDeSaida =
                     new FileOutputStream(nomeArquivo)) {
            OutputStreamWriter conversor =
                    new OutputStreamWriter(arquivoDeSaida);
            BufferedWriter dados =
                    new BufferedWriter(conversor);

            dados.write("primeiro arquivo txt criado");
            dados.newLine();
            dados.write("mais uma linha, para teste");
            dados.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lerArquivoTxt(final String nomeArquivo) {

        try (FileInputStream arquivoDeSaida = new FileInputStream(nomeArquivo)) {

            InputStreamReader conversor = new InputStreamReader(arquivoDeSaida);

            BufferedReader dados = new BufferedReader(conversor);
            String s;
            do {
                s = dados.readLine();
                if (s == null) {
                    break;
                }
            } while (true);
            dados.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarArquivoTxt(final String nomeArquivo) {

        try (FileInputStream arquivoDeEntrada =
                     new FileInputStream(nomeArquivo)) {
            InputStreamReader conversorEntrada =
                    new InputStreamReader(arquivoDeEntrada);
            BufferedReader dadosEntrada =
                    new BufferedReader(conversorEntrada);
            String s;
            do {
                s = dadosEntrada.readLine();
                if (s == null) {
                    break;
                }
            } while (true);

            FileOutputStream arquivoDeSaida = new FileOutputStream(nomeArquivo);
            OutputStreamWriter conversorSaida =
                    new OutputStreamWriter(arquivoDeSaida);
            BufferedWriter dadosSaida =
                    new BufferedWriter(conversorSaida);

            dadosSaida.write("primeiro arquivo txt criado para teste");
            dadosSaida.newLine();
            dadosSaida.write("mais uma linha");
            dadosSaida.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarArquivoTxt(final String nomeArquivo) {
        try  {
            Files.delete(Path.of(nomeArquivo));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
