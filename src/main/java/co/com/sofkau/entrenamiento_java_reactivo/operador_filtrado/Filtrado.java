package co.com.sofkau.entrenamiento_java_reactivo.operador_filtrado;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operador_transformacion.Transformacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {

    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);

    //Sirve para filtrar los datos, de acuerdo a la logica que se le pone, en este caso
    // filtra los datos que sean mayores de edad que 28.
    public void filter(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .filter(person -> person.getEdad() >= 28)
                .subscribe(person -> log.info(person.toString()));
    }

    //Los datos que son repetidos en este caso por el id, solo muestra el primer dato que encuentre,
    // Para datos primitivos realiza el filtrado.
    // Para objetos, debemos implementar el metodo equals_and_hashcode, para que realice el filtro.
    public void distinct(){

        Flux.fromIterable(List.of(1,2,3,1,2,4))
                .distinct()
                .subscribe(person -> log.info(person.toString()));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .distinct()
                .subscribe(person -> log.info(person.toString()));
    }

    //Toma los datos de flujo a partir del valor ingresado, es decir, si metes 2, toma los dos primeros objetos
    public void take(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .take(2)
                .subscribe(person -> log.info(person.toString()));
    }

    // Similar al take, solo que toma los datos iniciando desde el final.
    public void takeLast(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .takeLast(2)
                .subscribe(person -> log.info(person.toString()));
    }

    //Evita tomar los datos de flujo a partir del valor ingresado, es decir,
    // si metes 2, no va mostrar los dos primeros objetos
    public void skip(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .skip(2)
                .subscribe(person -> log.info(person.toString()));
    }

    // Similar al skip, solo que evita tomar los datos iniciando desde el final.
    public void skipLast(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        persons.add(new Person(1, "Lorena", 22));
        persons.add(new Person(2, "Tomas", 27));
        persons.add(new Person(4, "Yenni", 38));
        Flux.fromIterable(persons)
                .skipLast(2)
                .subscribe(person -> log.info(person.toString()));
    }
}
