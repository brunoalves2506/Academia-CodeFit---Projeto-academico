package Util;

import Model.Frequencia;

import java.time.LocalTime;
import java.util.ArrayList;

public interface Catraca {

    void passarCatracaEntrada(LocalTime horarioChegada, ArrayList<Frequencia> frequencias);

    void passarCatracaSaida(LocalTime horarioSaida);

}
