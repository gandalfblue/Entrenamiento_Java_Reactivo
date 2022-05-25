package co.com.sofkau.entrenamiento_java_reactivo;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operador_combinacion.Combinacion;
import co.com.sofkau.entrenamiento_java_reactivo.operador_creacion.Creacion;
import co.com.sofkau.entrenamiento_java_reactivo.operador_filtrado.Filtrado;
import co.com.sofkau.entrenamiento_java_reactivo.operador_matematico.Matematico;
import co.com.sofkau.entrenamiento_java_reactivo.operador_transformacion.Transformacion;
import co.com.sofkau.entrenamiento_java_reactivo.operadores_condicion.CondicionOp;
import co.com.sofkau.entrenamiento_java_reactivo.operadores_errores.ErrorsOp;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EntrenamientoJavaReactivoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EntrenamientoJavaReactivoApplication.class);

    public void reactor() {
        //Representa un flujo de un solo dato de tipo asincrono
        Mono.just(new Person(1, "Andres", 33))
                .doOnNext(p -> log.info("[RxJava2] Persona: " + p))
                //Sirve para suscribirse al flujo de datos para enterarse del contenido del flujo de datos
                .subscribe(p -> log.info("[Reactor] Persona: " + p));

    }

    public void rxjava3() {
        Observable.just(new Person(1, "Andres", 33))
                .doOnNext(person -> log.info("[RxJava2] Persona: " + person))
                .subscribe(person -> log.info("[RxJava2] Persona: " + person));
    }

    public void mono() {
        Mono.just(new Person(1, "Andres Lozada", 33))
                .subscribe(person -> log.info(person.toString()));
    }

    public void flux() {
        //Representa un flujo de varios datos de tipo asincrono de manera independiente, es decir, vaiors elementos.
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));

        Flux.fromIterable(persons).subscribe(person -> log.info(person.toString()));
    }

    public void fluxAMono() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 33));
        persons.add(new Person(2, "Juan", 18));
        persons.add(new Person(3, "Silvia", 25));

        Flux fx = Flux.fromIterable(persons);
        //Transforma los elementos del flujo de datos a una sola lista, es decir, como si fuera un solo elemento
        fx.collectList().subscribe(lista -> log.info(lista.toString()));
    }

    public static void main(String[] args) {

        SpringApplication.run(EntrenamientoJavaReactivoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //reactor();
        //rxjava3();
        /*mono();
        flux();
        fluxAMono();*/

        /*Creacion app = new Creacion();
        app.range();
        app.repeat();*/

        /*Transformacion app = new Transformacion();
        app.map();
        app.flatMap();
        app.groupBy();*/

        /*Filtrado app = new Filtrado();
        app.filter();
        app.distinct();*/

        /*Combinacion app = new Combinacion();
        app.merge();
        app.zipWith();*/

        /*ErrorsOp app= new ErrorsOp();
        app.errorResume();
        app.errorMap();*/

        /*CondicionOp app = new CondicionOp();
        app.defaultIfEmpty();
        app.takeUntil();*/

        Matematico app = new Matematico();
        app.average();
        app.count();
        app.min();
        app.suma();
        app.summarizing();
    }
}
