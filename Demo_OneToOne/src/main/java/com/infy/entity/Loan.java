package com.infy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;
    private Double amount;
    @Column(name = "issue_date")
    private LocalDate loanIssueDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    private String status;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getLoanIssueDate() {
        return loanIssueDate;
    }

    public void setLoanIssueDate(LocalDate loanIssueDate) {
        this.loanIssueDate = loanIssueDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan loan)) return false;
        return Objects.equals(loanId, loan.loanId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(loanId);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" + "loanId=" + loanId + ", amount=" + amount + ", loanIssueDate=" + loanIssueDate + ", customer=" + customer + ", status='" + status + '\'' + '}';
    }
}
