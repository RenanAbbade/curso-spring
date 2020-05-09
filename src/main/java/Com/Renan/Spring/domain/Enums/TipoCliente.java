package Com.Renan.Spring.domain.Enums;

public enum TipoCliente {

  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Juridica");

  private int cod;
  private String descricao;

  private TipoCliente(int cod, String descricao){
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
  public static TipoCliente toEnum(Integer cod){
    //Precisarei percorrer todos os valores possiveis do meu enum

    if(cod == null)
      return null;

    for(TipoCliente x : TipoCliente.values()){
      if (cod == x.getCod())
        return x;
    }
    //Se o for acabar e ninguém for achado, devolvemos uma Exception
    throw new IllegalArgumentException("Id invalido: "+ cod);
  }
  
  
}

//Criação personalizada dos enums, por conta de manutenibilidade do banco de dados, de modo que
//na alteração deste classe, não corrompa o BD, associando cada enums a valores de código unicos.
//Essa classe só possui Getters porque enums não podem ser alterados