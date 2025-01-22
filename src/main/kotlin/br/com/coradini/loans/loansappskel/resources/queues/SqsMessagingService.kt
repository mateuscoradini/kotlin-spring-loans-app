package br.com.coradini.loans.loansappskel.resources.queues

// Implementação para SQS
class SqsMessagingService : MessagingService {
    override fun sendMessage(queueName: String, message: String) {
        // Lógica para enviar mensagem para SQS
        println("Enviando mensagem para SQS na fila $queueName: $message")
    }

    override fun receiveMessages(queueName: String): List<String> {
        // Lógica para receber mensagens do SQS
        println("Recebendo mensagens da fila SQS: $queueName")
        return listOf("Mensagem 1", "Mensagem 2") // Exemplo de mensagens recebidas
    }
}