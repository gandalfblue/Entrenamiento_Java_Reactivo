package co.com.sofkau.entrenamiento_java_reactivo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Person {

    private Integer idPerson;
    private String name;
    private Integer edad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return idPerson.equals(person.idPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson);
    }
}
