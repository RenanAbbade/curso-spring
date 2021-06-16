package Com.Renan.Spring.domain.Enums;

public enum EstadoPagamento {
  
    PENDENTE(1, "Pendente"), 
    QUITADO(2, "Quitado"),
    CANELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
    
      this.cod = cod;

      this.descricao = descricao;
    }

    
  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }
//Devolve um tipo cliente atraves de um cod
  public static EstadoPagamento toEnum(Integer cod){
    //Precisarei percorrer todos os valores possiveis do meu enum

    if(cod == null)
      return null;

    for(EstadoPagamento x : EstadoPagamento.values()){
      if (cod == x.getCod())
        return x;
    }
    //Se o for acabar e ninguém for achado, devolvemos uma Exception
    throw new IllegalArgumentException("Id invalido: "+ cod);
  }
  
  
}