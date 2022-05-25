package co.com.sofkau.entrenamiento_java_reactivo.operador_combinacion;

import co.com.sofkau.entrenamiento_java_reactivo.model.Person;
import co.com.sofkau.entrenamiento_java_reactivo.model.Venta;
import co.com.sofkau.entrenamiento_java_reactivo.operador_filtrado.Filtrado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    //Sirve para combinar varios flujos de datos, sin importar que tipos de objetos son
    public void merge() {

        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person(1, "Andres", 33));
        persons1.add(new Person(2, "Juan", 18));
        persons1.add(new Person(3, "Silvia", 25));

        List<Person> persons2 = new ArrayList<>();
        persons2.add(new Person(4, "Lorena", 22));
        persons2.add(new Person(5, "Tomas", 27));
        persons2.add(new Person(6, "Yenni", 38));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(persons1);
        Flux<Person> flux2 = Flux.fromIterable(persons2);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        Flux.merge(flux1, flux2, flux3)
                .subscribe(person -> log.info(person.toString()));
    }

    //Sirve para concatenar varios flujos de datos, sin importar que tipos de objetos son
    public void zip() {

        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person(1, "Andres", 33));
        persons1.add(new Person(2, "Juan", 18));
        persons1.add(new Person(3, "Silvia", 25));

        List<Person> persons2 = new ArrayList<>();
        persons2.add(new Person(4, "Lorena", 22));
        persons2.add(new Person(5, "Tomas", 27));
        persons2.add(new Person(6, "Yenni", 38));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(persons1);
        Flux<Person> flux2 = Flux.fromIterable(persons2);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        Flux.zip(flux1, flux2, (p1, p2) -> String.format("Flux1: %s , Flux2: %s", p1, p2))
                .subscribe(person -> log.info(person.toString()));

        Flux.zip(flux1, flux2, flux3)
                .subscribe(person -> log.info(person.toString()));
    }

    //Similar al zip, solo que la concatenacion inicia desde un flujo de datos
    public void zipWith() {

        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person(1, "Andres", 33));
        persons1.add(new Person(2, "Juan", 18));
        persons1.add(new Person(3, "Silvia", 25));

        List<Person> persons2 = new ArrayList<>();
        persons2.add(new Person(4, "Lorena", 22));
        persons2.add(new Person(5, "Tomas", 27));
        persons2.add(new Person(6, "Yenni", 38));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Person> flux1 = Flux.fromIterable(persons1);
        Flux<Person> flux2 = Flux.fromIterable(persons2);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        flux1.zipWith(flux2, (p1, p2) -> String.format("Flux1: %s , Flux2: %s", p1, p2))
                .subscribe(person -> log.info(person.toString()));
    }

}
