package ma.enset.ensetdemospringang.dtos;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.ensetdemospringang.entities.PaymentStatus;
import ma.enset.ensetdemospringang.entities.PaymentType;
import ma.enset.ensetdemospringang.entities.Student;

import java.time.LocalDate;


@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString@Builder
public class PaymentDTO {

    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private Student student;
}
