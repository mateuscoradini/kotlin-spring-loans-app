package br.com.coradini.loans.loansappskel

import br.com.coradini.loans.loansappskel.application.Main
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Main::class]) // Especifique a classe principal
class LoansappskelApplicationTests {

	@Test
	fun contextLoads() {
	}

}
