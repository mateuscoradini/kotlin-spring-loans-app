package br.com.coradini.loans.loansappskel.domain.entities

import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(
    name = "loan_simulation_entity",
    indexes = [Index(name = "idx_user_token", columnList = "user_token")]
)
data class LoanSimulationEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "user_token", nullable = false)
    val userToken: String,

    @Column(name = "loan_amount", nullable = false)
    val loanAmount: Double,

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "payment_term_months", nullable = false)
    val paymentTermMonths: Int,

    @Column(name = "annual_interest_rate", nullable = false)
    val annualInterestRate: Double,

    @Column(name = "total_amount_to_be_paid", nullable = false)
    val totalAmountToBePaid: Double,

    @Column(name = "monthly_installment", nullable = false)
    val monthlyInstallment: Double,

    @Column(name = "total_interest_paid", nullable = false)
    val totalInterestPaid: Double
)