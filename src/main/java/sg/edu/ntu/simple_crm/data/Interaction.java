package sg.edu.ntu.simple_crm.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interaction")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 30, message = "remarks must be between 3 and 30 characters")
    @Column(name = "remarks")
    private String remarks;

    @PastOrPresent(message = "interaction date cannot be in the future")
    @Column(name = "interaction_date")
    private LocalDate interactionDate;

    // MANY interactions are associated with ONE customer
    @ManyToOne(optional = false)
    // JoinColumn tells Hibernate to use the customer_id column in the interaction table 
    // to store the foreign key and it should reference the id column in the customer table
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
}
