package co.com.sofkau.entrenamiento_java_reactivo.operador_matematico;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operadores_errores.ErrorsOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {

    private static final Logger log = LoggerFactory.getLogger(ErrorsOp.class);

    //Sirve para calcular el promedio, en este caso de la edad del flujo de datos
    public void average(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .collect(Collectors.averagingInt(Person::getEdad))
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para contar cuantos elementos hay en el flujo de datos
    public void count(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .count()
                .subscribe(cantidad -> log.info("Cantidad: " +cantidad));
    }

    //Sirva para encontrar el valor minimo a traves de un flujo de datos
    public void min(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .collect(Collectors.minBy(Comparator.comparing(Person::getEdad)))
                .subscribe(person -> log.info(person.get().toString()));
    }

    //Sirva para sumar, en este caso la edad del flujo de datos
    public void suma(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .collect(Collectors.summingInt(Person::getEdad))
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para entregarme un resumen de las operaciones aritmeticas mas comunes
    public void summarizing(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .collect(Collectors.summarizingInt(Person::getEdad))
                .subscribe(resumen -> log.info("Resumen genral: " +resumen));
    }
}
