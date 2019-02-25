package codesquad.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    public void update(User updatedUser) {
        this.setName(updatedUser.getName());
        this.setEmail(updatedUser.getEmail());
        this.setPassword(updatedUser.getPassword());
    }

    public boolean isSamePassword(User userToUpdate) {
        if (!password.equals(userToUpdate.getPassword())) {
            return false;
        }
        return true;
    }

    public boolean isSameUser(User comparedUser) {
        if (id.equals(comparedUser.getId()))
            return true;
        else
            return false;
    }
}
