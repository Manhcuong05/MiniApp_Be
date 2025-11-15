package com.adflex.fecredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.adflex.fecredit.entity.LoanApplication;
import com.adflex.fecredit.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication saveLoan(LoanApplication loan) {

        // 🌟 Đảm bảo createdAt luôn có giá trị
        if (loan.getCreatedAt() == null) {
            loan.setCreatedAt(java.time.LocalDateTime.now());
        }

        // 1️⃣ Lưu vào PostgreSQL
        LoanApplication saved = loanRepository.save(loan);

        // 2️⃣ Gửi webhook (tùy chọn)
        try {
            String webhookUrl = "https://webhook.site/4d05190e-cacb-41b2-93c0-fa50d80a5bb8";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<LoanApplication> request = new HttpEntity<>(saved, headers);
            restTemplate.postForEntity(webhookUrl, request, String.class);

            System.out.println("✅ Webhook sent successfully to: " + webhookUrl);

        } catch (Exception e) {
            System.err.println("⚠️ Webhook send failed: " + e.getMessage());
        }

        return saved;
    }
}
