package com.mycompany.trabajodeconsulta;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrabajoDeConsulta {

public static void main(String[] args) {
        List<String> Frutas = Arrays.asList("Pera", "Sandia", "Manzana", "Naranja");
        List<String> Vegetales = Arrays.asList("Lechuga", "Zanahoria", "Espinaca", "Cebolla");

        // Obs 1
        Observable.fromIterable(Frutas)
                .concatMap(item -> Observable.just(item).delay(5, TimeUnit.SECONDS))
                .subscribe(System.out::println);


        
        // Obs 2
        Observable.fromIterable(Vegetales)
                .concatMap(item -> Observable.just(item).delay(5, TimeUnit.SECONDS))
                .subscribe(System.out::println);
        
        // Obs 3 (Combinacion)
        
        Observable<String> ObsFrutas = Observable.fromIterable(Frutas)
                .concatMap(item -> Observable.just(item).delay(5, TimeUnit.SECONDS));

        Observable<String> ObsVegetales = Observable.fromIterable(Vegetales)
                .concatMap(item -> Observable.just(item).delay(5, TimeUnit.SECONDS));

        Observable.zip(ObsFrutas, ObsVegetales, (fruta, verdura) ->
                "Recibí una " + fruta + " y una " + verdura)
                .subscribe(System.out::println);

        


// Esperar a que los flujos se completen (esto es solo para este ejemplo)
        try {
            Thread.sleep(50000); // Asegurar que hay suficiente tiempo para que los flujos se ejecuten
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
