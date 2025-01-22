package br.com.coradini.loans.loansappskel.domain.service

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import reactor.core.publisher.Flux

interface LoanService {
    fun simulateLoans(requests: List<LoanSimulationRequest>): Flux<LoanSimulationResponse>
}