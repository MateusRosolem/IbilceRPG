package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

import java.util.Scanner;

public class Player extends Personagem{   
    private int experiencia; // mede o progresso ate subir de nivel
    private int nivel; //muda a forca e defesa base do jogador
    private Inventario inventario;
    private Scanner input = new Scanner(System.in);
    private MissaoManager missoes = new MissaoManager();
    
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
    @Override
    public Acao<String,Object> turnoNoCombate(){
        Acao<String,Object> turno = new Acao<String,Object>();
        System.out.println("Turno do Jogador, selecione 1 para atacar, 2 para defender e 3 para usar habilidade");
        String op = input.nextLine();
        switch(op){
            case "1":
                turno.setT("ATAQUE");
                turno.setV((getAtaqueBase()*getMultiplicadorAtaque())*getDebuffDano());
                System.out.println(turno.getT());
                setDebuffDano(1f);
                break;
            case "2":
                turno.setT("DEFESA");
                turno.setV(0.75f-(getNivel()/50));
                System.out.println(turno.getT());
                setDebuffDano(1f);
                break;
            case "3":
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
                    break;
                }
            case "4":
                turno.setT("ITEM");
                turno.setV("EFEITO");//EFEITO DO ITEM

                acaoPropria(turno);
        }

            



            
        
        // turno.setT("ITEM");
        // turno.setV("EFEITO");//EFEITO DO ITEM

        // acaoPropria(turno);


        return turno;
    }

    private void acaoPropria(Acao<String,Object> turno){//n ta pronto

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

                }else if((String)turno.getV() == "Xepa"){

                }
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

    public void desativarHabilidadePassiva() {
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

    
}
