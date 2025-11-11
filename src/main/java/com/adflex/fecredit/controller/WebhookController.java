package com.adflex.fecredit.controller;

import org.springframework.web.bind.annotation.*;
import com.adflex.fecredit.entity.LoanApplication;
import com.adflex.fecredit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/webhook")
@CrossOrigin(origins = "*") // 👈 Cho phép tất cả domain truy cập
public class WebhookController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/fecredit")
    public LoanApplication receiveLoanData(@RequestBody LoanApplication loan) {
        System.out.println("📩 Dữ liệu nhận được từ FE: " + loan);
        return loanService.saveLoan(loan);
    }
}
