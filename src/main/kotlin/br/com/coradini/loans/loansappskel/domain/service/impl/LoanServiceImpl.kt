package br.com.coradini.loans.loansappskel.domain.service.impl

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.domain.service.LoanService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Period

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

    private fun simulateLoan(loanAmount: BigDecimal, birthDate: String, paymentTermMonths: Int): Mono<LoanSimulationResponse> {
        val annualInterestRate = calculateInterestRate(birthDate)
        val monthlyInterestRate = annualInterestRate.divide(BigDecimal(100)).divide(BigDecimal(12), 2, RoundingMode.HALF_UP)
        val monthlyInstallment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, paymentTermMonths)
        val totalAmountToBePaid = monthlyInstallment.multiply(BigDecimal(paymentTermMonths))
        val totalInterestPaid = totalAmountToBePaid.subtract(loanAmount)

        return Mono.just(
            LoanSimulationResponse(
                loanAmount,
                birthDate,
                paymentTermMonths,
                annualInterestRate,
                totalAmountToBePaid,
                monthlyInstallment,
                totalInterestPaid
            )
        )
    }

    private fun calculateInterestRate(birthDate: String): BigDecimal {
        val birthDateParsed = LocalDate.parse(birthDate)
        val age = Period.between(birthDateParsed, LocalDate.now()).years

        return when {
            age <= 25 -> BigDecimal(5.0)
            age in 26..40 -> BigDecimal(3.0)
            age in 41..60 -> BigDecimal(2.0)
            else -> BigDecimal(4.0)
        }
    }

    private fun calculateMonthlyPayment(PV: BigDecimal, r: BigDecimal, n: Int): BigDecimal {
        val numerator = PV.multiply(r)
        val denominator = BigDecimal.ONE.subtract(BigDecimal.ONE.add(r).pow(-n))
        return numerator.divide(denominator, 2, RoundingMode.HALF_UP)
    }
}