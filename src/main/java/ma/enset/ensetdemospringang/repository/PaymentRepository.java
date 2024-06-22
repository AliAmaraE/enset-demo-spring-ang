package ma.enset.ensetdemospringang.repository;

import ma.enset.ensetdemospringang.entities.Payment;
import ma.enset.ensetdemospringang.entities.PaymentStatus;
import ma.enset.ensetdemospringang.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);

}
