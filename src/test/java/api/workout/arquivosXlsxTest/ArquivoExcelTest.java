package api.workout.arquivosXlsxTest;

import api.workout.arquivo.ArquivoXlsx;
import api.workout.domains.entitys.planilha.InscricaoAluno;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArquivoExcelTest {

    private final List<InscricaoAluno> inscricaoAlunos = new ArrayList<>();

    @Test
    void arquivoExcel(){

        inscricaoAlunos.add(new InscricaoAluno( "2023-08-22","MachFit_Setubal","edson","Pedro"));
        inscricaoAlunos.add(new InscricaoAluno("2023-07-22","MachFit_Setubal","Luiz","Pedro"));
        inscricaoAlunos.add(new InscricaoAluno( "2023-08-22","MachFit_Setubal","Sergio","Pedro"));
        inscricaoAlunos.add(new InscricaoAluno("2023-07-22","MachFit_Setubal","Lucas","Edson"));

        var criarArquivoExcel = new ArquivoXlsx();
        criarArquivoExcel.criarArquivoXlsx("D:\\Edson\\manipulacao_de_arquivos\\excel-java\\test4.xlsx",inscricaoAlunos);
    }
    @Test
    void lerAquivoXlsxTests(){
        var lerArquivo = new ArquivoXlsx();
        lerArquivo.lerArquivoXlsx("D:\\Edson\\manipulacao_de_arquivos\\excel-java\\test4.xlsx");
    }
    @Test
    void atualizarXlsxTests(){
        var atualizarArquiovo = new ArquivoXlsx();
        atualizarArquiovo.atualizarArquivoXlsx("D:\\Edson\\manipulacao_de_arquivos\\excel-java\\test4.xlsx");
        System.out.println();
    }
    @Test
    void deletarXlsxTests(){
        var deletarArquiovo = new ArquivoXlsx();
        deletarArquiovo.deletarArquivoXlsx("D:\\Edson\\manipulacao_de_arquivos\\excel-java\\test3.xlsx");
        System.out.println("arquivo deletado com sucesso!");
    }
}

