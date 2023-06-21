package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.Controllers.FXCombateController;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Design.*;

import java.io.Serializable;

/**
 * Classe do jogador, nela sao armazenados todos os atributos do jogador (que sao salvos no save) e os metodos do combate
 * tambem ficam aqui uma instancia do inventario contendo todas as suas habiliades e do MissionManager, que toma conta das missoes coletadas pelo jogador
 */
public class Player extends Personagem implements Serializable {
    private int experiencia; // mede o progresso ate subir de nivel
    private int nivel; //muda a forca e defesa base do jogador
    private Inventario inventario;
    private MissaoManager missoes = new MissaoManager();
    private Acao<String,Object> turno = new Acao<String,Object>();
    private Acao<String,Object> turnodefault = new Acao<String,Object>("DEFAULT","DEFAULT");

    private String efeitoNegativoPassivo = "DEFAULT";

    private boolean itemDisponivel;

    private boolean[] cursosDerrotados = new boolean[7];
    
    public Player(){
        super("Jogador",true,0,0,1,1, 1,1,1f);
        this.setNivel(1);
        this.inventario = new Inventario();
        this.setExperiencia(0);

        this.setAtaqueBase(7 + (getNivel()-1));
        this.setDefesaBase(3 + (getNivel()-1));
        this.setVidaMaxima(10 + 10*getNivel());
        this.setVidaAtual(10 + 10*getNivel());
    }

    /**
     * Metodo de ataque basico para o combate, chamada sempre que o botao "ataque" for pressionado na tela de combate (quando o turno é do jogador)
     * @return retorna a acao "ATAQUE" e o dano causado pelo jogador
     */
    public Acao<String,Object> jogadorAtacar(FXCombateController UI){
        turno.setT("ATAQUE");
        turno.setV((getAtaqueBase()*getMultiplicadorAtaque())*getDebuffDano());
        //UI.imprimirTexto(turno.getT());
        setDebuffDano(1f);
        return turno;
    }

    /**
     * Metodo de defesa basico para o combate, chamada sempre que o botao "defesa" for pressionado na tela de combate (quando o turno é do jogador)
     * @return retorna a acao "DEFESA" e quanto dano sera conservado do proximo ataque inimigo
     */
    public Acao<String,Object> jogadorDefender(FXCombateController UI){
        turno.setT("DEFESA");
        turno.setV(0.75f-(getNivel()/50));
        UI.imprimirTexto(turno.getT());
        setDebuffDano(1f);
        return turno;
    }

    /**
     * Metodo que chama a habilidade equipada pelo jogador, chamada quando o botao "Habilidade" e pressionado na tela de combate quando o turno e do jogador
     *
     * @return retorna a acao "HABILIDADE" e seu efeito pela funcao usarHabilidade
     */
    public Acao<String,Object> jogadorHabilidade(FXCombateController UI){
        if(getInventario().getHabilidadeEquipada().getEfeito().getT() == "PASSIVA") {
            UI.imprimirTexto("A habilidade equipada é passiva, não é necessário ativá-la.");

        }
        if(getInventario().getHabilidadeEquipada().checarTempoDeRecarga(UI)){
            turno.setV(usarHabilidade());
        }else{
        }
        turno.setT("HABILIDADE");
        UI.imprimirTexto(turno.getT() + ": " + getInventario().getHabilidadeEquipada().getNome());
        acaoPropria(turno,UI);
        setDebuffDano(1f);
        return turno;

    }

    /**
     * Metodo para utilizar item no combate, chamado quando o jogador pressiona o botao Item quando esta na sua vez do combate
     * @return retorna a acao Item e RU ou XEPA dependendo da duracao do combate, mas so uma vez por combate, se ja for usada retorna o turnoDefault
     */
    public Acao<String,Object> jogadorItem(FXCombateController UI){
        if(isItemDisponivel()) {
            turno.setT("ITEM");
            UI.imprimirTexto(turno.getT());
            if (getContadorTurnos() < 6) {
                turno.setV("RU");
                UI.imprimirTexto(turno.getV() + ": cura 20% da vida máxima");
            } else {
                turno.setV("XEPA");
                UI.imprimirTexto(turno.getV() + ": cura 50% da vida perdida");
            }
            acaoPropria(turno,UI);
            return turno;
        }else{
            UI.imprimirTexto("O item ja foi usado nesse combate!");
            return turnodefault;
        }

    }

    /**
     * funcao para testar o combate no terminal
     * @return retorna a acao descrita pelo jogador no terminal
     */
//    @Override
//    public Acao<String,Object> turnoNoCombate(){
//        System.out.println("Turno do Jogador, selecione 1 para atacar, 2 para defender, 3 para usar habilidade e 4 para usar item.");
//        String op = input.nextLine();
//
//        switch(op){
//            case "1":
//                return jogadorAtacar();
//
//            case "2":
//                return jogadorDefender();
//
//            case "3":
//                return jogadorHabilidade();
//
//            case "4":
//                return jogadorItem();
//
//        }
//
//    }

    /**
     * Chamada sempre que o jogador pode causar um efeito a si mesmo por meio de uma acao (habilidade ou item) e faz esse efeito
     * @param turno recebe a acao escolhida e age de acordo
     */
    private void acaoPropria(Acao<String,Object> turno, FXCombateController UI){

        switch(turno.getT()){
            case "HABILIDADE":
                Acao<String,Object> efeito = getInventario().getHabilidadeEquipada().getEfeito();
                switch((String)efeito.getT()){
                    case "VAMPIRISMO":
                        receberCura((float)efeito.getV(),UI);
                }
                break;
            case "ITEM":
                if((String)turno.getV() == "RU"){
                    receberCura((float)getVidaMaxima()/5,UI);
                }else if((String)turno.getV() == "XEPA"){
                    receberCura((float)(getVidaMaxima()-getVidaAtual())/2,UI);
                }
                setItemDisponivel(false);
                break;
        }

    }

//    public void iniciarCombate(){
//        setVidaAtual(getVidaMaxima());
//        setMultiplicadorAtaque(1);
//        setMultiplicadorDefesa(1);
//        setVivo(true);
//        ativarEfeitosPassivos();
//    }

    /**
     * Metodo chamado quando a habilidade e usada em combate pelo jogador, mandada para o adversario e o jogador regairem aos efeitos
     * @return retorna os efeitos da habilidade em forma da classe Acao, se o efeito for apenas proprio ela retorna nulo
     */
    private Acao<String,Object> usarHabilidade(){
        Acao<String,Object> efeito =  inventario.getHabilidadeEquipada().getEfeito();
        inventario.getHabilidadeEquipada().ativarRecarga();
        if(efeito.getT() == "DANO"){
            efeito.setV((float)getAtaqueBase()*getMultiplicadorAtaque()*2);
            return efeito;
        }
        if(efeito.getT() == "VAMPIRISMO") return efeito;
        //SE O EFEITO FOR PROPRIO, ESSA FUNCAO TEM Q RETORNAR NULO!!!
    
        return null;
    }

    /**
     * chama a habilidade passiva e o efeito negativo passivo todo turno
     */
    public void ativarEfeitosPassivos(FXCombateController UI){
        ativarHabilidadePassiva(UI);
        ativarEfeitoNegativoPassivo(UI);
    }

    /**
     * Manda o jogador reagir ao efeito passivo que esta sofrendo, quando nao sopfre nenhum efeito a funcao apenas retorna
     */
    private void ativarEfeitoNegativoPassivo(FXCombateController UI) {
        switch(getEfeitoNegativoPassivo()){
            case "DANO_ACIDO":
                UI.imprimirTexto("O ácido corrosivo penetra em sua pele!");
                reacaoJogador(new Acao<String,Object>("DANO_REAL",(getVidaMaxima()-getVidaAtual())/5),UI);
                break;
            default:
                return;
        }
    }

    /**
     * Metodo que ativa a habilidade passiva, é chamada todo turno e so faz alguma coisa se o jogador tiver equipado uma habilidade passiva
     */
    private void ativarHabilidadePassiva(FXCombateController UI){
        if(getInventario().getHabilidadeEquipada().getEfeito().getT() == "PASSIVA"){
            String efeito = (String)getInventario().getHabilidadeEquipada().getEfeito().getV();

            switch(efeito){
                case "CURA":
                    UI.imprimirTexto("Habilidade Passiva: Cura!");
                    receberCura(getVidaMaxima()/20,UI);
                    break;
                case "AUMENTAR_DANO":
                    UI.imprimirTexto("Habilidade Passiva: Aumentar Dano!");
                    setMultiplicadorAtaque(1.5f);
                    break;
                case "AUMENTAR_DEFESA":
                    UI.imprimirTexto("Habilidade Passiva: Aumentar Defesa!");
                    setMultiplicadorDefesa(1.5f);
                    break;
            }
        }
    }

    /**
     * Método que aumenta a variavel experiencia do jogador e chama a funcao subirDeNivel, é chamada toda vez que o jogador vence um combate
     * @param experienca experiencia recebida apos terminar um combate
     */
    public void receberExperiencia(int experienca, FXCombateController UI){
        UI.imprimirTexto("Jogador recebeu "+ experienca + " pontos de experiência!!!");
        experienca += getExperiencia();
        setExperiencia(experienca);
        subirDeNivel(UI);
    }

    /**
     * Metodo que checa se o jogador tem experiencia suficiente para subir de nivel, se sim, aumenta seus atributos base.
     */
    private void subirDeNivel(FXCombateController UI){
        if(experiencia >= nivel*10){
            this.setNivel(aumentarNivel());
            UI.imprimirTexto("Jogador subiu para o nivel " + getNivel() + "!!!");
            UI.imprimirTexto("Vida Maxima aumentada para: "+ SubirDeNivelVida());
            UI.imprimirTexto("Ataque aumentado para: " + SubirDeNivelAtaque());
            UI.imprimirTexto("Defesa aumentada para: "+ SubirDeNivelDefesa());
            if(experiencia >= nivel*10) subirDeNivel(UI);
        }else{
            UI.imprimirTexto("Faltam " + (10 - getExperiencia()%10) + " pontos de experiência para subir de nível!");
        }
    }

    /**
     * aumenta o ataque do jogador ao subir de nivel
     * @return retorna o ataque atulizado
     */
    private int SubirDeNivelAtaque(){
        int maisAtaque = this.getAtaqueBase();
        maisAtaque++;
        setAtaqueBase(maisAtaque);
        return maisAtaque;
    }

    /**
     * aumenta a defesa do jogador ao subir de nivel
     * @return retorna a defesa atulizado
     */
    private int SubirDeNivelDefesa(){
        int maisDefesa = this.getDefesaBase();
        maisDefesa++;
        setDefesaBase(maisDefesa);
        return maisDefesa;
    }

    /**
     * aumenta a vida do jogador ao subir de nivel
     * @return retorna a vida atulizado
     */
    private int SubirDeNivelVida(){
        int maisVida = this.getVidaMaxima();
        maisVida = 10 + 10*getNivel();
        setVidaMaxima(maisVida);
        return maisVida;
    }

    /**
     * aumenta o nivel do jogador ao subir de nivel
     * @return retorna o nivel atulizado
     */
    private int aumentarNivel(){
        return this.getNivel()+1;
    }

    /**
     * Metodo chamado todo turno do inimigo, ele recebe a acao do inimigo e reage a ela de maneira apropriada
     * @param acao acao do inimigo para o adversario
     */
    public void reacaoJogador(Acao<String,Object> acao, FXCombateController UI){
        switch(acao.getT()){
            case "ATAQUE":
                receberDano((Float)acao.getV(),UI);
                break;
            case "DEFESA":
                setDebuffDano((Float)acao.getV());
                break;
            case "DANO_REAL":
                setVidaAtual(getVidaAtual() - (int)acao.getV());
                //System.out.println("Dano ao jogador: " + acao.getV());
                break;
            case "ACIDO_CORROSIVO":
                setEfeitoNegativoPassivo((String)acao.getV());
        }
        
    }

    /**
     * @param dano recebe o dano dado pelo inimigo para fazer alterações com base na defesa
     * @return
     */
    @Override
    public int receberDano(float dano, FXCombateController UI){
        UI.imprimirTexto("Dano ao jogador: " + super.receberDano(dano,UI));
        return Math.round(dano);
    }

    /**
     * Adiciona uma nova missa ao catálogo de missoes no MissionManager
     * @param missao nova missao conquistada pelo jogador
     */
    public void receberMissao(Missao<? extends Habilidade> missao){
        missoes.adicionarMissao(missao);
    }

    /**
     * Método que atualiza e checa se o jogador ja derrotou pelo menos um representante dos outros cursos, é chamado toda vez que o jogador derrota um adversario
     * @param adversarioDerrotado adversario que o jogador derrotou em combate
     * @return retorna true se ja derrotou todos os cursos pelo menos uma vez, se nao retorna falso
     */
    public boolean checarProgresso(Inimigo adversarioDerrotado){
        if(adversarioDerrotado instanceof Traduteiro) cursosDerrotados[0] = true;
        if(adversarioDerrotado instanceof Biologo) cursosDerrotados[1] = true;
        if(adversarioDerrotado instanceof Fisico) cursosDerrotados[2] = true;
        if(adversarioDerrotado instanceof Quimico) cursosDerrotados[3] = true;
        if(adversarioDerrotado instanceof EngenheiroDeAlimentos) cursosDerrotados[4] = true;
        if(adversarioDerrotado instanceof Letreiro) cursosDerrotados[5] = true;
        if(adversarioDerrotado instanceof Matematico) cursosDerrotados[6] = true;

        for (boolean derrotado :cursosDerrotados) {
            if(!derrotado) return false;
        }
        return true;
    }

    /**
     * Metodo polimofisado do anterior, apenas checa se todos os outros cursos foram derrotados
     * @return retorna true se o jogador ja derrotou todos os outros cursos, false se nao
     */
    public boolean checarProgresso(){
       for (boolean derrotado :cursosDerrotados) {
           if(!derrotado) return false;
       }
       return true;
    }



   //getters e setters
    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public MissaoManager getMissoes() {
        return missoes;
    }

    public void setMissoes(MissaoManager missoes) {
        this.missoes = missoes;
    }

    public boolean isItemDisponivel() {
        return itemDisponivel;
    }

    public void setItemDisponivel(boolean itemDisponivel) {
        this.itemDisponivel = itemDisponivel;
    }
    public String getEfeitoNegativoPassivo() {
        return efeitoNegativoPassivo;
    }
    public void setEfeitoNegativoPassivo(String efeitoNegativoPassivo) {
        this.efeitoNegativoPassivo = efeitoNegativoPassivo;
    }
    public int getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}