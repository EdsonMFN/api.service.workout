package Projeto.Academia;

import Projeto.Academia.arquivo.ArquivoXlsx;
import Projeto.Academia.entitys.planilha.InscricaoAluno;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArquivoExcelTest {

    private final List<InscricaoAluno> inscricaoAlunos = new ArrayList<>();

//    @BeforeEach
//    public void setup(){
//        inscricaoAlunos.add(new InscricaoAluno( "2023-08-22","MachFit_Setubal","edson","Pedro"));
//        inscricaoAlunos.add(new InscricaoAluno("2023-07-22","MachFit_Setubal","Luiz","Pedro"));
//        inscricaoAlunos.add(new InscricaoAluno( "2023-08-22","MachFit_Setubal","Sergio","Pedro"));
//        inscricaoAlunos.add(new InscricaoAluno("2023-07-22","MachFit_Setubal","Lucas","Edson"));
//    }
//    @Test
//    void arquivoExcel(){
//        var criarArquivoExcel = new ArquivoXlsx();
//        criarArquivoExcel.criarPlanilha("D:\\Edson\\test3.xlsx",inscricaoAlunos);
//    }
//    @Test
//    void lerAquivoXlsxTests(){
//        var lerArquivoXlsx = new ArquivoXlsx();
//        var inscricaoAluno = lerArquivoXlsx.lerArquivo("D:\\Edson\\test3.xlsx");
//    }
    @Test
    void atualizarXlsxTests(){
        var atualizarArquiovo = new ArquivoXlsx();
        atualizarArquiovo.atualizarArquivo("D:\\Edson\\test3.xlsx");
    }
}

