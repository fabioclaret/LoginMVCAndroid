package com.example.loginmvc.datamodel;

public class UsuarioDataModel {
    // Modelo objeto Relacional

    // Toda Classe DataModel tem essa estrutura:
    // 1 - Atributo nome da tabela
    // 2 - Atributos um para um com os nomes dos campos
    // 3 - Query para criar a tabela no banco de dados
    // 4 - Metodo para gerar o script para criar a tabela.

    // 1
    public static final String TABELA = "usuarios";

    // 2
    public static final String ID   = "id";
    public static final String NOME  = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "email";

    // 3
    public static String queryCriarTabela = "";

    // 4
    public static String criarTabela(){
        // Concatenacao de Strings

        queryCriarTabela += "CREATE TABLE " + TABELA + "(";
        queryCriarTabela += ID    + " integer primary key autoincrement, ";
        queryCriarTabela += NOME  + " text, ";
        queryCriarTabela += EMAIL + " text ";
        queryCriarTabela += SENHA + " text ";
        queryCriarTabela += ")";

        return queryCriarTabela;
    }


}
