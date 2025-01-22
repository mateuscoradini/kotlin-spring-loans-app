-- V1__Create_LoanSimulationEntity.sql
CREATE TABLE loan_simulation_entity (
    id UUID PRIMARY KEY,
    user_token VARCHAR(255) NOT NULL,
    loan_amount DOUBLE PRECISION NOT NULL,
    birth_date DATE NOT NULL,
    payment_term_months INT NOT NULL,
    annual_interest_rate DOUBLE PRECISION NOT NULL,
    total_amount_to_be_paid DOUBLE PRECISION NOT NULL,
    monthly_installment DOUBLE PRECISION NOT NULL,
    total_interest_paid DOUBLE PRECISION NOT NULL
);

CREATE INDEX idx_user_token ON loan_simulation_entity (user_token);