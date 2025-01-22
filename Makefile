# Nome do arquivo: Makefile

# Vari�veis
DB_URL=r2dbc:postgresql://myuser:secret@localhost/mydatabase
DB_USER=myuser
DB_PASSWORD=secret

# Caminho para o Flyway CLI (se estiver instalado localmente)
FLYWAY_CLI=flyway

# Tarefas
.PHONY: clean-database migrate-database

# Tarefa para limpar o banco de dados
clean-database:
	@echo "Limpando o banco de dados..."
	$(FLYWAY_CLI) -url=$(DB_URL) -user=$(DB_USER) -password=$(DB_PASSWORD) clean

# Tarefa para aplicar as migra��es no banco de dados
migrate-database:
	@echo "Aplicando migra��es no banco de dados..."
	$(FLYWAY_CLI) -url=$(DB_URL) -user=$(DB_USER) -password=$(DB_PASSWORD) migrate

# Tarefa para limpar e aplicar as migra��es novamente
clean-and-migrate: clean-database migrate-database
	@echo "Banco de dados limpo e migra��es aplicadas."