package br.com.coradini.loans.loansappskel.application.web.models

import java.math.BigDecimal

data class LoanSimulationRequest(
    val loanAmount: BigDecimal,
    val birthDate: String,
    val paymentTermMonths: Int
)
