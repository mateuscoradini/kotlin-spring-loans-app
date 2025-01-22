package br.com.coradini.loans.loansappskel.application

import br.com.coradini.loans.loansappskel.domain.repositories.LoanSimulationRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("br.com.coradini.loans")
@EntityScan("br.com.coradini.loans.loansappskel.domain.entities.*")
@EnableJpaRepositories
class Main

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
