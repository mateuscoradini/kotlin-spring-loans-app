package br.com.coradini.loans.loansappskel.domain.service.impl

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.domain.service.LoanService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Period

private const val DECIMAL_AFTER_VALUE = 2

@Service
class LoanServiceImpl : LoanService {

    override fun simulateLoans(requests: List<LoanSimulationRequest>): Flux<LoanSimulationResponse> {
        return Flux.fromIterable(requests)
            .flatMap { request ->
                simulateLoan(
                    request.loanAmount,
                    request.birthDate,
                    request.paymentTermMonths
                )
            }
    }

    override fun simulateLoan(loanAmount: BigDecimal, birthDate: String, paymentTermMonths: Int): Mono<LoanSimulationResponse> {
        val annualInterestRate = calculateInterestRate(birthDate)
        val monthlyInterestRate = calculateMonthlyInterestRate(annualInterestRate)
        val monthlyInstallment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentTermMonths)
        val totalAmountToBePaid = monthlyInstallment.multiply(BigDecimal(paymentTermMonths)).setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
        val totalInterestPaid = totalAmountToBePaid.subtract(loanAmount)

        return Mono.just(
            LoanSimulationResponse(
                loanAmount = loanAmount.setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP),
                birthDate = birthDate,
                paymentTermMonths = paymentTermMonths,
                annualInterestRate = annualInterestRate,
                totalAmountToBePaid = totalAmountToBePaid,
                monthlyInstallment = monthlyInstallment,
                totalInterestPaid = totalInterestPaid.setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
            )
        )
    }

    fun calculateInterestRate(birthDate: String): BigDecimal {
        val birthDateParsed = LocalDate.parse(birthDate)
        val age = Period.between(birthDateParsed, LocalDate.now()).years

        return when {
            age <= 25 -> BigDecimal(5).setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
            age in 26..40 -> BigDecimal(3).setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
            age in 41..60 -> BigDecimal(2).setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
            else -> BigDecimal(4).setScale(DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
        }
    }

    private fun calculateMonthlyInterestRate(annualInterestRate: BigDecimal): BigDecimal {
        return annualInterestRate.divide(BigDecimal(12 * 100), 10, RoundingMode.HALF_UP)
    }

    fun calculateMonthlyPayment(loanAmount: BigDecimal, monthlyInterestRate: BigDecimal, paymentTermMonths: Int): BigDecimal {
        if (monthlyInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            // Caso raro: empréstimo sem juros
            return loanAmount.divide(BigDecimal(paymentTermMonths), DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
        }

        val onePlusRate = BigDecimal.ONE.add(monthlyInterestRate)
        val numerator = loanAmount.multiply(monthlyInterestRate).multiply(onePlusRate.pow(paymentTermMonths, MathContext.DECIMAL128))
        val denominator = onePlusRate.pow(paymentTermMonths, MathContext.DECIMAL128).subtract(BigDecimal.ONE)

        return numerator.divide(denominator, DECIMAL_AFTER_VALUE, RoundingMode.HALF_UP)
    }
}
