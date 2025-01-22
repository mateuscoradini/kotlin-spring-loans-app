package br.com.coradini.loans.loansappskel.domain.service

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

interface LoanService {
    fun simulateLoans(requests: List<LoanSimulationRequest>): Flux<LoanSimulationResponse>
    fun simulateLoan(loanAmount: BigDecimal, birthDate: String, paymentTermMonths: Int): Mono<LoanSimulationResponse>
}