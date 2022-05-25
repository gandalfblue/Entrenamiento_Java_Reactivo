package co.com.sofkau.entrenamiento_java_reactivo.operadores_condicion;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.operadores_errores.ErrorsOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CondicionOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorsOp.class);

    /*Solo se ejecuta para cuando nos llega una respuesta vacia, en vez de devolver un dato vacio, podemos
    devolver cualquier cosa, en este caso devolvemos el mismo objeto que creemos que nos devolveria,
     si en el flujo de datos hay contenido, no se ejecuta el defaultIfEmpty       */
    public void defaultIfEmpty() {

        Mono.empty()
                .defaultIfEmpty(new Person(0, "Objeto vacio mono", 99))
                .subscribe(person -> log.info(person.toString()));

        Flux.empty()
                .defaultIfEmpty(new Person(0, "Objeto vacio flux", 99))
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para solo tomar datos de flujos hasta que se cumpla la condicion, es decir, tomara los datos
    // hasta que encuentre el primer dato de flujo que cumpla la condicion, ese dato es inclusivo
    public void takeUntil() {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                .takeUntil(person -> person.getEdad() > 25)
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para lanzar una excepcion, si la lectura se demora mas de lo que puedo esperar
    public void timeOut() throws InterruptedException {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Andres", 18));
        persons.add(new Person(2, "Juan", 20));
        persons.add(new Person(3, "Silvia", 22));
        Flux.fromIterable(persons)
                //Sirve para indicarle cada cuento quiero que se demore la lectura de la emision de cada dato
                .delayElements(Duration.ofSeconds(3))
                //Solo se lanza si el delayElements tien un mayor timepo que el timeout
                .timeout(Duration.ofSeconds(2))
                .subscribe(person -> log.info(person.toString()));

        Thread.sleep(10000);//Sirve para dormir el hilo en un tiempo mientras realizo lo que necesito
    }
}
