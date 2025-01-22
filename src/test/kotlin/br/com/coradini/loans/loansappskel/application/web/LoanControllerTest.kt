package br.com.coradini.loans.loansappskel.application.web

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.domain.service.LoanService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import java.math.BigDecimal

@WebFluxTest(LoanController::class)
class LoanControllerTest {

	@MockBean
	private lateinit var loanService: LoanService

	private val webTestClient: WebTestClient = WebTestClient.bindToController(LoanController(mockk())).build()

	@Test
	fun `simulateLoan should return simulated loan response`() {
		// Arrange
		val request = LoanSimulationRequest(
			loanAmount = BigDecimal("10000"),
			birthDate = "1990-01-01",
			paymentTermMonths = 24
		)
		val response = LoanSimulationResponse(
			loanAmount = BigDecimal("10000"),
			birthDate = "1990-01-01",
			paymentTermMonths = 24,
			annualInterestRate = BigDecimal("3.00"),
			totalAmountToBePaid = BigDecimal("10315.49"),
			monthlyInstallment = BigDecimal("429.81"),
			totalInterestPaid = BigDecimal("315.49")
		)
		every { loanService.simulateLoans(listOf(request)) } returns Flux.just(response)

		// Act & Assert
		webTestClient.post()
			.uri("/api/simulation")
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk
			.expectBody()
			.jsonPath("$.loanAmount").isEqualTo(10000)
			.jsonPath("$.annualInterestRate").isEqualTo(3.00)
			.jsonPath("$.totalAmountToBePaid").isEqualTo(10315.49)
			.jsonPath("$.monthlyInstallment").isEqualTo(429.81)
	}

	@Test
	fun `simulateLoans should return simulated loan responses for multiple requests`() {
		// Arrange
		val requests = listOf(
			LoanSimulationRequest(BigDecimal("10000"), "1990-01-01", 24),
			LoanSimulationRequest(BigDecimal("5000"), "1985-06-15", 12)
		)
		val responses = listOf(
			LoanSimulationResponse(
				loanAmount = BigDecimal("10000"),
				birthDate = "1990-01-01",
				paymentTermMonths = 24,
				annualInterestRate = BigDecimal("3.00"),
				totalAmountToBePaid = BigDecimal("10315.49"),
				monthlyInstallment = BigDecimal("429.81"),
				totalInterestPaid = BigDecimal("315.49")
			),
			LoanSimulationResponse(
				loanAmount = BigDecimal("5000"),
				birthDate = "1985-06-15",
				paymentTermMonths = 12,
				annualInterestRate = BigDecimal("3.00"),
				totalAmountToBePaid = BigDecimal("5158.12"),
				monthlyInstallment = BigDecimal("429.84"),
				totalInterestPaid = BigDecimal("158.12")
			)
		)
		every { loanService.simulateLoans(requests) } returns Flux.fromIterable(responses)

		// Act & Assert
		webTestClient.post()
			.uri("/api/simulation/multiple")
			.bodyValue(requests)
			.exchange()
			.expectStatus().isOk
			.expectBodyList(LoanSimulationResponse::class.java)
			.hasSize(2)
			.contains(*responses.toTypedArray())
	}

	@Test
	fun `testEndpoint should return a success message`() {
		// Act & Assert
		webTestClient.get()
			.uri("/api/simulation/test")
			.exchange()
			.expectStatus().isOk
			.expectBody()
			.jsonPath("$.body").isEqualTo("Test endpoint is working!")
	}
}
