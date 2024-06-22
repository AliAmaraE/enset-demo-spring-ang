package ma.enset.ensetdemospringang;

import ma.enset.ensetdemospringang.entities.Payment;
import ma.enset.ensetdemospringang.entities.PaymentStatus;
import ma.enset.ensetdemospringang.entities.PaymentType;
import ma.enset.ensetdemospringang.entities.Student;
import ma.enset.ensetdemospringang.repository.PaymentRepository;
import ma.enset.ensetdemospringang.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EnsetDemoSpringAngApplication {

    public static void main(String[] args) {

        SpringApplication.run(EnsetDemoSpringAngApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("Mohamed").code("112233").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("Iman").code("112244").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("Yasmine").code("112255").programId("GLSID")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("NAjat").code("112266").programId("BSCC")
                    .build());

            PaymentType[] paymentTypes=PaymentType.values();
            Random random=new Random();
            studentRepository.findAll().forEach(st->{
                for(int i = 0; i<10;i++){
                    int index= random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int)(Math.random()+20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });

        };

    }
}
