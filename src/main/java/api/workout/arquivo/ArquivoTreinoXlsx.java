package api.workout.arquivo;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Slf4j
public class ArquivoTreinoXlsx {

    public void executarArquivoTreino(final String enderecoArquivo, String nomeAluno, String nomeProfessor){

        try (FileInputStream arquivoDeEntrada = new FileInputStream(enderecoArquivo)){
            XSSFWorkbook pasta = new XSSFWorkbook(arquivoDeEntrada);
            XSSFSheet primeiraFolha = pasta.getSheetAt(0);

            log.info("iniciando leitura");

            Row linha = primeiraFolha.getRow(1);

            log.info("linha"+ linha.getCell(0).getStringCellValue());
            adicionarCelula(linha,0,nomeAluno);

            log.info("linha"+ linha.getCell(2).getStringCellValue());
            adicionarCelula(linha,2,nomeProfessor);

            log.info("linha"+ linha.getCell(4).getStringCellValue());
            adicionarCelula(linha,4,converterDataEmString(LocalDate.now()));

            FileOutputStream arquivoDeSaida = new FileOutputStream(enderecoArquivo);
            log.info("Arquivo alterado" + arquivoDeSaida);
            pasta.write(arquivoDeSaida);

            arquivoDeSaida.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void  adicionarCelula(Row linha, int coluna, String valor){
        Cell cell = linha.getCell(coluna);
        cell.setCellValue(valor);
    }
    private String converterDataEmString(LocalDate localDate) {

        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public String base64Exel(final String enderecoArquivo) {
        try {
            File excelFile = new File(enderecoArquivo);
            FileInputStream fileInputStream = new FileInputStream(excelFile);
            byte[] excelBytes = new byte[(int) excelFile.length()];
            fileInputStream.read(excelBytes);
            fileInputStream.close();

            byte[] base64Encoded = Base64.getEncoder().encode(excelBytes);

            return new String(base64Encoded);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
