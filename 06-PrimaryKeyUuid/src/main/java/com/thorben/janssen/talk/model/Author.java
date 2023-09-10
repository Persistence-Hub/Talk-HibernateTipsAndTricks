package com.thorben.janssen.talk.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Author {

    @Id
    @GeneratedValue
    // @GeneratedValue(generator = "UUID")
	// @GenericGenerator(
	//         name = "UUID",
	//         strategy = "org.hibernate.id.UUIDGenerator",
	//         parameters = {
	//             @Parameter(
	//                 name = "uuid_gen_strategy_class",
	//                 value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
	//             )
	//         }
	//     )
    private UUID id;

    @Version
    private int version;

    private String firstName;

    private String lastName;

    public UUID getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Author)) {
            return false;
        }
        Author other = (Author) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (firstName != null && !firstName.trim().isEmpty()) {
            result += "firstName: " + firstName;
        }
        if (lastName != null && !lastName.trim().isEmpty()) {
            result += ", lastName: " + lastName;
        }
        return result;
    }
}
