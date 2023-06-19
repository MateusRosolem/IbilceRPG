package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.io.Serializable;
import java.util.Scanner;

public class Player extends Personagem implements Serializable {
    private int experiencia; // mede o progresso ate subir de nivel
    private int nivel; //muda a forca e defesa base do jogador
    private Inventario inventario;
    private Scanner input = new Scanner(System.in);
    private MissaoManager missoes = new MissaoManager();
    private Acao<String,Object> turno = new Acao<String,Object>();
    private Acao<String,Object> turnodefault = new Acao<String,Object>("DEFAULT","DEFAULT");

    private boolean itemDisponivel;
    
    public Player(){
        super("Jogador",true,0,0,1,1,
                10, 1,1,1f);
        this.setNivel(1);
        this.inventario = new Inventario();
        this.setExperiencia(0);

        this.setAtaqueBase(7 + (getNivel()-1));
        this.setDefesaBase(3 + (getNivel()-1));
        this.setVidaMaxima(10 + 10*getNivel());
        this.setVidaAtual(10 + 10*getNivel());
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

    public Acao<String,Object> jogadorAtacar(){
        turno.setT("ATAQUE");
        turno.setV((getAtaqueBase()*getMultiplicadorAtaque())*getDebuffDano());
        System.out.println(turno.getT());
        setDebuffDano(1f);
        return turno;
    }

    public Acao<String,Object> jogadorDefender(){
        turno.setT("DEFESA");
        turno.setV(0.75f-(getNivel()/50));
        System.out.println(turno.getT());
        setDebuffDano(1f);
        return turno;
    }

    public Acao<String,Object> jogadorHabilidade(){
        if(getInventario().getHabilidadeEquipada().getEfeito().getT() == "PASSIVA") {
            System.out.println("A habilidade equipada é passiva, não é necessário ativá-la.");
            return turnoNoCombate();
        }
        if(getInventario().getHabilidadeEquipada().checarTempoDeRecarga()){
            turno.setV(usarHabilidade());
        }else{
            return turnoNoCombate();
        }
        turno.setT("HABILIDADE");
        System.out.println(turno.getT() + ": " + getInventario().getHabilidadeEquipada().getNome());
        acaoPropria(turno);
        setDebuffDano(1f);
        return turno;

    }
    public Acao<String,Object> jogadorItem(){
        if(isItemDisponivel()) {
            turno.setT("ITEM");
            System.out.println(turno.getT());
            if (getContadorTurnos() < 6) {
                turno.setV("RU");
                System.out.println(turno.getV() + ": cura 20% da vida máxima");
            } else {
                turno.setV("XEPA");
                System.out.println(turno.getV() + ": cura 50% da vida perdida");
            }
            acaoPropria(turno);
            return turno;
        }else{
            System.out.println("O item ja foi usado nesse combate!");
            return turnodefault;
        }

    }


    @Override
    public Acao<String,Object> turnoNoCombate(){


        System.out.println("Turno do Jogador, selecione 1 para atacar, 2 para defender, 3 para usar habilidade e 4 para usar item.");
        String op = input.nextLine();


        switch(op){
            case "1":
                return jogadorAtacar();

            case "2":
                return jogadorDefender();

            case "3":
                return jogadorHabilidade();

            case "4":
                return jogadorItem();

            default:
                return turnodefault;
        }

//
    }

    private void acaoPropria(Acao<String,Object> turno){

        switch(turno.getT()){
            case "HABILIDADE":
                Acao<String,Object> efeito = getInventario().getHabilidadeEquipada().getEfeito();
                switch((String)efeito.getT()){
                    case "VAMPIRISMO":
                        receberCura((float)efeito.getV());
                }
                break;
            case "ITEM":
                if((String)turno.getV() == "RU"){
                    receberCura((float)getVidaMaxima()/5);
                }else if((String)turno.getV() == "XEPA"){
                    receberCura((float)(getVidaMaxima()-getVidaAtual())/2);
                }
                setItemDisponivel(false);
                break;
        }

    }

    public void iniciarCombate(){
        setVidaAtual(getVidaMaxima());
        setMultiplicadorAtaque(1);
        setMultiplicadorDefesa(1);
        setVivo(true);
        ativarHabilidadePassiva();
    }

    private Acao<String,Object> usarHabilidade(){
        Acao<String,Object> efeito =  inventario.getHabilidadeEquipada().getEfeito();
        inventario.getHabilidadeEquipada().ativarRecarga();
        if(efeito.getT() == "DANO") return efeito;
        if(efeito.getT() == "VAMPIRISMO") return efeito;
        //SE O EFEITO FOR PROPRIO, ESSA FUNCAO TEM Q RETORNAR NULO!!!
    
        return null;
    }

    public void ativarHabilidadePassiva(){

        if(getInventario().getHabilidadeEquipada().getEfeito().getT() == "PASSIVA"){
            String efeito = (String)getInventario().getHabilidadeEquipada().getEfeito().getV();

            switch(efeito){
                case "CURA":
                    System.out.println("Habilidade Passiva: Cura");
                    receberCura(getVidaMaxima()/20);
                    System.out.println("--------------------------------------------------------------------------------------");
                    break;
            }
        }
        
    }

    public void receberExperiencia(int experienca){
        System.out.println("Jogador recebeu "+ experienca + " pontos de experiência!!!");
        experienca += getExperiencia();
        setExperiencia(experienca);
        subirDeNivel();
    }

    private void subirDeNivel(){
        if(experiencia >= nivel*10){
            this.setNivel(aumentarNivel());
            System.out.println("Jogador subiu para o nivel " + getNivel() + "!!!");
            System.out.println("Vida Maxima aumentada para: "+ SubirDeNivelVida());
            System.out.println("Ataque aumentado para: " + SubirDeNivelAtaque());
            System.out.println("Defesa aumentada para: "+ SubirDeNivelDefesa());
            if(experiencia >= nivel*10) subirDeNivel();
        }else{
            System.out.println("Faltam " + (10 - getExperiencia()%10) + " pontos de experiência para subir de nível!");
        }
    }

    private int SubirDeNivelAtaque(){
        int maisAtaque = this.getAtaqueBase();
        maisAtaque++;
        setAtaqueBase(maisAtaque);
        return maisAtaque;
    }
    private int SubirDeNivelDefesa(){
        int maisDefesa = this.getDefesaBase();
        maisDefesa++;
        setDefesaBase(maisDefesa);
        return maisDefesa;
    }
    private int SubirDeNivelVida(){
        int maisVida = this.getVidaMaxima();
        maisVida = 10 + 10*getNivel();
        setVidaMaxima(maisVida);
        return maisVida;
    }
    private int aumentarNivel(){
        return this.getNivel()+1;
    }

    public void reacaoJogador(Acao<String,Object> acao){
        switch(acao.getT()){
            case "ATAQUE": 
                receberDano((Float)acao.getV());
                break;
            case "DEFESA":
                setDebuffDano((Float)acao.getV());
                break;
            case "DANO_REAL":
                setVidaAtual(getVidaAtual() - (int)acao.getV());
                System.out.println("Dano ao jogador: " + acao.getV());
                break;
        }
        
    }
    @Override
    public int receberDano(float dano){
        System.out.println("Dano ao jogador: " + super.receberDano(dano));
        return Math.round(dano);
    }

    public void receberMissao(Missao<? extends Habilidade> missao){
        missoes.adicionarMissao(missao);
    }
   
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
}
