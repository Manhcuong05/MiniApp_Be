package com.adflex.fecredit.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String cccd;

    @Column(name = "cmnd_old")
    private String cmndOld;

    private Long amount;          // Số tiền vay
    private Integer term;         // Kỳ hạn vay
    private Double rate;          // Lãi suất
    private Long monthlyPayment;  // Tiền trả hàng tháng
    private Boolean insurance;    // Bảo hiểm khoản vay

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 🔥 Auto-set createdAt trước khi INSERT
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
