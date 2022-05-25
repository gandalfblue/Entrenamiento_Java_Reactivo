package co.com.sofkau.entrenamiento_java_reactivo.operador_transformacion;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operador_creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    //Sirve para transformar los elementos de la lista de flujos.
    public void map(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .map(person -> {
                        person.setEdad(person.getEdad() + 10);
                        return person;
                })
                .subscribe(person -> log.info(person.toString()));

        Flux<Integer> flux = Flux.range(0, 10);
        flux.subscribe(dato -> log.info("dato1: " +dato));
        Flux<Integer> flux2 = flux.map(dato -> dato + 10);
        flux2.subscribe(dato -> log.info("dato2: " +dato));
    }

    //Similar al map solo que se debe retornar no el flujo modificado, sino otro flujo
    public void flatMap(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .flatMap(person -> {
                    person.setEdad(person.getEdad() + 10);
                    return Mono.just(person);
                })
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para agrupar por las coinciddencias que se puso de logica, en este caso por el idPerson
    public void groupBy(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .groupBy(Person::getIdPerson)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(person -> log.info(person.toString()));
    }
}
