package si.fri.rso.samples.customers.models.entities;

import org.eclipse.persistence.annotations.UuidGenerator;
import si.fri.rso.samples.customers.models.dtos.Order;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Entity(name = "app_user")
@NamedQueries(value =
        {
                @NamedQuery(name = "User.getAll", query = "SELECT c FROM app_user c")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;


    @Column(name="status")
    private String status;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;




    @Column(name="message_group_id")
    private String messageGroupId;

    @Column(name = "last_logged_in")
    private Instant lastLoggedIn;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String status) {
        this.email = email;
    }

    public String getMessageGroupId() {
        return messageGroupId;
    }

    public void setMessageGroupId(String messageGroupId) {
        this.messageGroupId= messageGroupId;
    }


    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Instant getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Instant lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

}