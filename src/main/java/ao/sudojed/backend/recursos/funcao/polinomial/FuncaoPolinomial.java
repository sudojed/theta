package ao.sudojed.backend.recursos.funcao.polinomial;

import java.util.ArrayList;
import java.util.HashMap;

import ao.sudojed.backend.recursos.funcao.Funcao;
import ao.sudojed.backend.recursos.funcao.Intervalo;
import ao.sudojed.backend.recursos.funcao.enums.SimetriaEnum;


public class FuncaoPolinomial implements Funcao{
    private Intervalo dominio;
    private Intervalo contradominio;
    private Intervalo conjuntoImagem;
    private SimetriaEnum simetria;
    private HashMap<Intervalo, String> monotonia;
    private ArrayList<Double> zeros;
    

    @Override
    public Double limitar(Double tendencia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Double derivar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String integrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}