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
        // 1️⃣ Lưu dữ liệu vào PostgreSQL
        LoanApplication saved = loanRepository.save(loan);

        // 2️⃣ Gửi dữ liệu đã lưu sang webhook bên ngoài
        try {
            String webhookUrl = "https://webhook.site/4d05190e-cacb-41b2-93c0-fa50d80a5bb8"; // 🔁 Thay bằng link thật (Hookdeck, Zapier, Webhook.site,...)
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<LoanApplication> request = new HttpEntity<>(saved, headers);
            restTemplate.postForEntity(webhookUrl, request, String.class);

            System.out.println("✅ Webhook sent successfully to: " + webhookUrl);
        } catch (Exception e) {
            System.err.println("⚠️ Webhook send failed: " + e.getMessage());
        }

        // 3️⃣ Trả về object đã lưu
        return saved;
    }
}
