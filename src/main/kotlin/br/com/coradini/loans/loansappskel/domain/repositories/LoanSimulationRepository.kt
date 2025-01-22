package br.com.coradini.loans.loansappskel.domain.repositories

import br.com.coradini.loans.loansappskel.domain.entities.LoanSimulationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanSimulationRepository : JpaRepository<LoanSimulationEntity, String> {

}