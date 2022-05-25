package co.com.sofkau.entrenamiento_java_reactivo.operador_creacion;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {

    private static final Logger log = LoggerFactory.getLogger(Creacion.class);

    //Sirven para crear un flujo de datos, ya sea un solo elemento o varios
    // Con RxJava, es indistinto si es uno a varios elementos en el flujo de datos
    public void just_From(){

        Observable.just(new Person(1, "Andres", 33)); //Con RxJava

        Mono.just(new Person(1, "Andres Lozada", 33));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons).subscribe(person -> log.info(person.toString()));
    }

    //Sirve para manejar flujos de datos vacios
    public void empty(){

        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    //Sirve para crear un flujo de datos a partir de un rango, se ingresan dos parametros, el primero
    //indica la posicion inicial (es inclusivo) y el ultimo la pocision final (es exclusivo).
    public void range(){
        Flux.range(0, 3)
                .doOnNext(posicion -> log.info("posicion: " +posicion))
                .subscribe();
    }

    //Sirve para repetir el flujo de datos
    public void repeat(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));

        Flux.fromIterable(persons)
                .repeat(3)
                .subscribe(person -> log.info(person.toString()));

        Mono.just(new Person(1, "Andres Lozada", 33))
                .repeat(3)
                .subscribe(person -> log.info(person.toString()));
    }



}
