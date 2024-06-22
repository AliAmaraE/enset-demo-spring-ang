package ma.enset.ensetdemospringang.services;

import jakarta.transaction.Transactional;
import ma.enset.ensetdemospringang.entities.Payment;
import ma.enset.ensetdemospringang.entities.PaymentStatus;
import ma.enset.ensetdemospringang.entities.PaymentType;
import ma.enset.ensetdemospringang.entities.Student;
import ma.enset.ensetdemospringang.repository.PaymentRepository;
import ma.enset.ensetdemospringang.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(MultipartFile file, LocalDate date, double amount
            , PaymentType type, String studentCode) throws IOException {
        Path forldePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
        if (!Files.exists(forldePath)) {
            Files.createDirectories(forldePath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName + ".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date).type(type).student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);


    }
    public Payment updatePaymentStatus( PaymentStatus status, Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile( Long paymentTd)throws IOException{
        Payment payment = paymentRepository.findById(paymentTd).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
}