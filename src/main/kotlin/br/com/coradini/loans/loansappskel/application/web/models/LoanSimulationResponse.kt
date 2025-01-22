package br.com.coradini.loans.loansappskel.application.web.models

import java.math.BigDecimal

data class LoanSimulationResponse(
    val loanAmount: BigDecimal,
    val birthDate: String,
    val paymentTermMonths: Int,
    val annualInterestRate: BigDecimal,
    val totalAmountToBePaid: BigDecimal,
    val monthlyInstallment: BigDecimal,
    val totalInterestPaid: BigDecimal
)
