package com.ghanem.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;


@Data // Lombok helps us to do getters, setters, toString.....
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "contact_id")
    private int contactId;
    @NotBlank(message = "Name must not be Blank")
    @Size(min=3, message ="Name must be at least 3 characters long")
    private String name;
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provid a vaild email address")
    private String email;
    @NotBlank(message = "Subject must not be blank")
    @Size(min=5, message = "Subject must be at least 5 characters long")
    private String subject;
    @NotBlank(message = "Message must not be black")
    @Size(min=10, message = "Message must be at least 10 characters long")
    private String message;

    private String status;
}
