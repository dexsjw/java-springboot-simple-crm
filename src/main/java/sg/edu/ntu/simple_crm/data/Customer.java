package sg.edu.ntu.simple_crm.data;

import java.util.List;

import jakarta.persistence.CascadeType;

// import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Tells Hibernate to make a table out of this class
@Table(name = "customer") // Tell Hibernate to name the table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Digits(fraction = 0, integer = 8, message = "contact number must be digits")
    @Size(min = 8, max = 8, message = "contact number must be 8 digits")
    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "job_title")
    private String jobTitle;

    @Min(value = 1940, message = "Year of Birth must be later than 1940")
    @Max(value = 2005, message = "Year of Birth must be earlier than 2005")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) 
    private List<Interaction> interactions;

    public Customer(String firstName, String lastName, int yearOfBirth) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    // public String getId() {
    //     return id;
    // }
    // public void setId(String id) {
    //     this.id = id;
    // }
    // public String getFirstName() {
    //     return firstName;
    // }
    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }
    // public String getLastName() {
    //     return lastName;
    // }
    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }
    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    // public String getContactNo() {
    //     return contactNo;
    // }
    // public void setContactNo(String contactNo) {
    //     this.contactNo = contactNo;
    // }
    // public String getJobTitle() {
    //     return jobTitle;
    // }
    // public void setJobTitle(String jobTitle) {
    //     this.jobTitle = jobTitle;
    // }
    // public int getYearOfBirth() {
    //     return yearOfBirth;
    // }
    // public void setYearOfBirth(int yearOfBirth) {
    //     this.yearOfBirth = yearOfBirth;
    // }
}
