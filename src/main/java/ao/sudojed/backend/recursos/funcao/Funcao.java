package main.java.ao.sudojed.backend.recursos.funcao;

import java.util.HashMap;

public class Funcao {
    private String leiFormacao;
    private String dominio;
    private String zeros;
    private String paridade;
    private String variacaoSinal;
    private HashMap<String, String> monotonia;
    private HashMap<String, String> concavidade;

    public void limitar(int tendencia){

    }
    
    public void derivar(){

    }

    public void integrar(){
        
    }


    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    public String getZeros() {
        return zeros;
    }
    public void setZeros(String zeros) {
        this.zeros = zeros;
    }
    public String getParidade() {
        return paridade;
    }
    public void setParidade(String paridade) {
        this.paridade = paridade;
    }
    public String getVariacaoSinal() {
        return variacaoSinal;
    }
    public void setVariacaoSinal(String variacaoSinal) {
        this.variacaoSinal = variacaoSinal;
    }
    public HashMap<String, String> getMonotonia() {
        return monotonia;
    }
    public void setMonotonia(HashMap<String, String> monotonia) {
        this.monotonia = monotonia;
    }
    public HashMap<String, String> getConcavidade() {
        return concavidade;
    }
    public void setConcavidade(HashMap<String, String> concavidade) {
        this.concavidade = concavidade;
    }
     
}
