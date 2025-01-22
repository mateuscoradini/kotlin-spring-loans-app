package br.com.coradini.loans.loansappskel.domain.service

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.domain.service.impl.LoanServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.math.RoundingMode

class LoanServiceImplTest {

	private val loanService = LoanServiceImpl()

	@Test
	fun `simulateLoans should return correct responses for valid requests`() {
		// Arrange
		val requests = listOf(
			LoanSimulationRequest(BigDecimal("10000"), "1990-01-01", 24), // 3% interest
			LoanSimulationRequest(BigDecimal("5000"), "1985-06-15", 12)   // 3% interest
		)

		// Act
		val result: Flux<LoanSimulationResponse> = loanService.simulateLoans(requests)

		// Assert
		StepVerifier.create(result)
			.expectNextMatches { response ->
				response.loanAmount == BigDecimal("10000").setScale(2, RoundingMode.HALF_UP) &&
						response.birthDate == "1990-01-01" &&
						response.paymentTermMonths == 24 &&
						response.annualInterestRate == BigDecimal("3.00") &&
						response.totalAmountToBePaid == BigDecimal("10315.44") &&
						response.monthlyInstallment == BigDecimal("429.81") &&
						response.totalInterestPaid == BigDecimal("315.44")
			}
			.expectNextMatches { response ->
				response.loanAmount == BigDecimal("5000").setScale(2, RoundingMode.HALF_UP) &&
						response.birthDate == "1985-06-15" &&
						response.paymentTermMonths == 12 &&
						response.annualInterestRate == BigDecimal("3.00") &&
						response.totalAmountToBePaid == BigDecimal("5081.64") &&
						response.monthlyInstallment == BigDecimal("423.47") &&
						response.totalInterestPaid == BigDecimal("81.64")
			}
			.verifyComplete()
	}

	@Test
	fun `calculateInterestRate should return correct interest rates based on age`() {
		// Arrange
		val service = LoanServiceImpl()

		// Act & Assert
		assertEquals(BigDecimal("5.00"), service.calculateInterestRate("2000-01-01")) // 25 years old
		assertEquals(BigDecimal("3.00"), service.calculateInterestRate("1985-01-01")) // 39 years old
		assertEquals(BigDecimal("2.00"), service.calculateInterestRate("1965-01-01")) // 59 years old
		assertEquals(BigDecimal("4.00"), service.calculateInterestRate("1950-01-01")) // Older than 60
	}

	@Test
	fun `calculateMonthlyPayment should return correct monthly payment`() {
		// Arrange
		val service = LoanServiceImpl()
		val loanAmount = BigDecimal("10000")
		val interestRate = BigDecimal("3.00").divide(BigDecimal(100), 10, RoundingMode.HALF_UP).divide(BigDecimal(12), 10, RoundingMode.HALF_UP) // 3% annual
		val months = 24

		// Act
		val monthlyPayment = service.calculateMonthlyPayment(loanAmount, interestRate, months)

		// Assert
		assertEquals(BigDecimal("429.81"), monthlyPayment.setScale(2, RoundingMode.HALF_UP))
	}
}
