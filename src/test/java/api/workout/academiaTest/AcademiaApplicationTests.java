package api.workout.academiaTest;

import api.workout.domains.entitys.Endereco;
import api.workout.service.AcademiaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AcademiaApplicationTests {

	private Endereco idEndereco;
	private AcademiaService academiaService;

	@BeforeEach
	public void setUp(){
		idEndereco = new Endereco();
		academiaService = new AcademiaService();
	}

//	@Test
//	@DisplayName("Se a academia receber um endereco real")
//	void end_real_academia() {
//		Assertions.assertTrue(academiaService.criarAcademia(idEndereco));
//	}

}
