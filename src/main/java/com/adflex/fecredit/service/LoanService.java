package com.adflex.fecredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adflex.fecredit.entity.LoanApplication;
import com.adflex.fecredit.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication saveLoan(LoanApplication loan) {
        return loanRepository.save(loan);
    }
}
