package com.adflex.fecredit.repository;

import com.adflex.fecredit.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanApplication, Long> {
}
