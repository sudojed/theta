package ao.sudojed.backend.recursos.funcao;

public interface Funcao{
    // function behaviors

    public abstract Double limitar(Double tendencia);
    public abstract Double derivar();
    public abstract Double integrar();
    
}

