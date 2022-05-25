package co.com.sofkau.entrenamiento_java_reactivo.operadores_errores;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operador_combinacion.Combinacion;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ErrorsOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorsOp.class);

    //Lanza la cantidad de veces que pongamos como parametro, en este caso 2, antes de un error
    public void retry() {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .retry(2)
                .doOnNext(person -> log.info(person.toString()))
                .subscribe();
    }

    //Sirve para controlar un error, en vez de mostrar en pantalla el nombre del error, podemos mostrar
    // lo que necesitamos, en esta caso se muestra un nuevo objeto persona
    public void errorReturn() {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorReturn(new Person(0, "Error", 99))
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para controlar un error, en vez de lanzar el error, podemos retornar un callback,
    // en este caso retornamos un nuevo flujo de datos o puedo lanzar la traza del error con un mensaje especifico
    public void errorResume(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorResume(e -> Mono.just(new Person(0, "Error", 99)))
                .onErrorResume(throwable -> (Publisher<? extends Person>) new Throwable(new IllegalArgumentException(throwable.getMessage())));
                /*.subscribe(person -> log.info(person.toString()));*/
    }

    //Sirve para lanzar la traza de un error con un mensaje especifico, en este caso nos muestra "Un Error"
    public void errorMap(){

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));
        Flux.fromIterable(persons)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorMap(e -> new IllegalArgumentException(e.getMessage()))
                .subscribe(person -> log.info(person.toString()));
    }
}
