package br.com.coradini.loans.loansappskel.application.web

import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationResponse
import br.com.coradini.loans.loansappskel.application.web.models.LoanSimulationRequest
import br.com.coradini.loans.loansappskel.domain.service.LoanService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/simulation")
class LoanController(private val loanService: LoanService) {


    @Operation(summary = "Simulate a single loan")
    @PostMapping
    fun simulateLoan(@RequestBody request: LoanSimulationRequest): Mono<ResponseEntity<LoanSimulationResponse>> {
        return loanService.simulateLoans(listOf(request))
            .next() // Get the first result as Mono
            .map { result -> ResponseEntity.ok(result) }
    }

    @Operation(summary = "Simulate multiple loans")
    @PostMapping("/multiple")
    fun simulateLoans(@RequestBody requests: List<LoanSimulationRequest>): Flux<ResponseEntity<LoanSimulationResponse>> {
        return loanService.simulateLoans(requests)
            .map { result -> ResponseEntity.ok(result) }
    }

    @GetMapping("/test")
    fun testEndpoint(): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.ok("Test endpoint is working!"))
    }
}