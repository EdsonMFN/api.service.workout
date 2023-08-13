package Projeto.Academia.arquivo;

import Projeto.Academia.entitys.planilha.InscricaoAluno;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ArquivoXlsx {

    public void criarArquivoXlsx(final String nomeArquivo, final List<InscricaoAluno> inscricaoAlunos) {

        try (var workbook = new XSSFWorkbook();
             var outputStream = new FileOutputStream(nomeArquivo)) {
            var planilha = workbook.createSheet("Lista de inscrições");
            int numeroDeLinha= 0;

            adicionarCabecalho(planilha,numeroDeLinha++);

            for (InscricaoAluno inscricaoAluno : inscricaoAlunos){
                var linha = planilha.createRow(numeroDeLinha++);

                adicionarCelula(linha,0,inscricaoAluno.getDataDaInscricao());
                adicionarCelula(linha,1,inscricaoAluno.getAcademia());
                adicionarCelula(linha,2,inscricaoAluno.getAluno());
                adicionarCelula(linha,3,inscricaoAluno.getProfessor());
            }
            workbook.write(outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void adicionarCabecalho(XSSFSheet planilha,int numeroDeLinha){
        var linha = planilha.createRow(numeroDeLinha);
        adicionarCelula(linha,0,"Data");
        adicionarCelula(linha,1,"Academia");
        adicionarCelula(linha,2,"Aluno");
        adicionarCelula(linha,3,"Professor");
    }
    private void adicionarCelula(Row linha, int coluna, String valor){
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
    private void adicionarCelula(Row linha, int coluna, int valor){
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }

    public List<InscricaoAluno> lerArquivoXlsx(final String nomeArquivo){
            List<InscricaoAluno> inscricaoAlunos = new ArrayList<>();
        try(FileInputStream arquivoDeEntrada = new FileInputStream(nomeArquivo)) {
            XSSFWorkbook pasta = new XSSFWorkbook(arquivoDeEntrada);
            XSSFSheet primeiraFolha = pasta.getSheetAt(0);
            int contadorDeLinha = 0;
            log.info("iniciando leitura");
            for (Row linha: primeiraFolha) {
                log.info("linha" + linha.getCell(contadorDeLinha));
                if (++contadorDeLinha == 1) continue;

                InscricaoAluno inscricaoAluno = InscricaoAluno.builder()
                        .dataDaInscricao(linha.getCell(0).getStringCellValue())
                        .academia(linha.getCell(1).getStringCellValue())
                        .aluno(linha.getCell(2).getStringCellValue())
                        .professor(linha.getCell(3).getStringCellValue())
                        .build();
                inscricaoAlunos.add(inscricaoAluno);

            }
            log.info("Arquivo lido");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inscricaoAlunos;
    }

    public void atualizarArquivoXlsx(final String nomeArquivo){

        try (FileInputStream arquivoDeEntrada = new FileInputStream(nomeArquivo);
             XSSFWorkbook pasta = new XSSFWorkbook(arquivoDeEntrada);
        ){
            XSSFSheet primeiraFolha = pasta.getSheetAt(0);

            Row modificarLinha = primeiraFolha.getRow(1);
            Cell modificarCelula = modificarLinha.getCell(0);
            modificarCelula.setCellValue("05-03-2022");

            FileOutputStream arquivoDeSaida = new FileOutputStream(nomeArquivo);
            pasta.write(arquivoDeSaida);

            arquivoDeSaida.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletarArquivoXlsx(final String nomeArquivo){
        try {
            Files.delete(Path.of(nomeArquivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
