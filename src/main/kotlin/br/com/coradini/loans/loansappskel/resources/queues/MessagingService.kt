package br.com.coradini.loans.loansappskel.resources.queues

interface MessagingService {
    fun sendMessage(queueName: String, message: String)
    fun receiveMessages(queueName: String): List<String>
}