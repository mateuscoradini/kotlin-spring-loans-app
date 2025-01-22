package br.com.coradini.loans.loansappskel.domain.repositories

import br.com.coradini.loans.loansappskel.domain.entities.LoanSimulationEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanSimulationRepository : ReactiveCrudRepository<LoanSimulationEntity, Long> {

}