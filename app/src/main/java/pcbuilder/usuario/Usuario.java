package pcbuilder.usuario;

import pcbuilder.computador.*;
import pcbuilder.components.*;

/**
 * Classe que representa um usuário da loja.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha; // emai e senha podem ser uma interface login se quiserem
    private ComputadorBuilder builder;
    private Computador computador;
    private Role role; 

    public Usuario() {
        this.builder = new ComputadorBuilder();
    }

    /**
     * Construtor da classe Usuário
     * @param username Username do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     */
    public Usuario(String username, String email, String senha, Role role) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.builder = new ComputadorBuilder();
    }

    // Métodos para a construção do computador

    /**
     * Define uma Placa-Mãe para o computador por meio do Builder
     * @param placaMae Placa-Mãe a ser definida
     */
    public void adicionarPlacaMae(PlacaMae placaMae){
        builder.adicionarPlacaMae(placaMae);
    }

    /**
     * Define um Processador para o computador por meio do Builder
     * @param Processador Processador a ser definido
     */
    public void adicionarProcessador(Processador processador){
        builder.adicionarProcessador(processador);
    }

    /**
     * Adiciona uma Memória RAM ao computador por meio do Builder
     * @param memoria Memória RAM a ser adicionada
     */
    public void adicionarMemoriaRAM(MemoriaRAM memoria){
        builder.adicionarMemoriaRAM(memoria);
    }

    /**
     * Define uma Placa de Vídeo para o computador por meio do Builder
     * @param placaDeVideo Placa de Vídeo a ser definido
     */
    public void adicionarPlacaDeVideo(PlacaDeVideo placaDeVideo){
        builder.adicionarPlacaDeVideo(placaDeVideo);
    }

    /**
     * Define uma Fonte para o computador por meio do Builder
     * @param fonte Fonte a ser definida
     */
    public void adicionarFonte(Fonte fonte){
        builder.adicionarFonte(fonte);
    }

    /**
     * Adiciona um Cooelr ao computador por meio do Builder
     * @param cooler Cooler a ser adicionado
     */
    public void adicionarCooler(Cooler cooler){
        builder.adicionarCooler(cooler);
    }

    /**
     * Define um Gabinete para o computador por meio do Builder
     * @param gabinete Gabinete a ser definido
     */
    public void adicionarGabinete(Gabinete gabinete){
        builder.adicionarGabinete(gabinete);
    }

    /**
     * Constroi o Computador após um minímo de peças serem selecionadas
     */
    public void construirComputador(){ // podemos fazer essa parte throw-ar uma exceção
        computador = builder.build();
    }

    // Métodos getters para acessar atributos do Usuário

    /**
     * Retorna o username do usuário.
     * @return Username do usuário.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Retorna o email do usuário.
     * @return Email do usuário.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Retorna a senha do usuário.
     * @return Senha do usuário.
     */
    public String getSenha(){ // nao seria uma boa pratica faezr isso num projeto real, mas para fins academicos tudo bem
        return senha;
    }

    /**
     * Retorna o computador criado pelo usuário.
     * @return Computador criado pelo usuário.
     */
    public Computador getComputador(){
        return computador;
    }

    public void setUsername(String username) {
    this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

     public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}