package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


/**
 * Classe pai de todos os adversarios que o jogador enfrentará, tem todos o atributos e metodos exclusivos dos adversarios
 */
public class Inimigo extends Personagem{
    private int expRecompensa;
    private int contadorTurnos;
    private String tag;


    public Inimigo(){
        super("Inimigo",true,20,20,5,2, 1,1,1);
        this.expRecompensa = 10;
        this.contadorTurnos = 0;
    }

    /**
     * Além de receber o dano exibe uma mensagem do dano recebido
     * @param dano recebe o dano dado pelo inimigo para fazer alterações com base na defesa
     * @return
     */
    @Override
    public int receberDano(float dano){
        System.out.println("Dano ao adversario: " + super.receberDano(dano));
        return 0;
    }

    /**
     * Metodo base para realizar o ataque do inimigo no jogador
     * @return retorna a acao "ATAQUE" e o dano do ataque
     */
    public Acao<String,Object> inimigoAtacar(){
        Acao<String,Object> turno = new Acao<String,Object>();
        turno.setT("ATAQUE");
        turno.setV((getAtaqueBase()*getMultiplicadorAtaque())*getDebuffDano());
        setDebuffDano(1);
        System.out.println(turno.getT());
        return turno;
    }

    /**
     * Metodo base para realizar a defesa do inimigo contra o jogador
     * @return retorna a acao "DEFESA" e o dano do mitigado pela defesa
     */
    public Acao<String,Object> inimigoDefender(){
        Acao<String,Object> turno = new Acao<String,Object>();
        turno.setT("DEFESA");
        turno.setV(0.5f);
        setDebuffDano(1);
        System.out.println(turno.getT());
        return turno;
    }

    /**
     * Metodo que cuida das reacoes do inimigo às acoes do jogador, age de acordo com o tipo de acao
     * @param acao acao realizada pelo jogador
     */
    public void reacaoInimigo(Acao<String,Object> acao){
        switch(acao.getT()){
            case "ATAQUE": 
                receberDano((Float)acao.getV());
                break;
            case "DEFESA":
                setDebuffDano((Float)acao.getV());
                break;
            case "HABILIDADE":
                if(acao.getV() != null){//se for nulo, a hablidade foi acao propria do jogador
                    Acao<String,Object> efeitoHabilidade = (Acao<String,Object>)acao.getV();
                    switch(efeitoHabilidade.getT()){
                        case "REDUCAO_DE_DEFESA":
                            setMultiplicadorDefesa((Float)efeitoHabilidade.getV());
                            System.out.println("Defesa do adversario diminuida pela metade!!!");
                            break;
                        case "DANO":
                            receberDano((Float)efeitoHabilidade.getV());
                            break;
                        case "REDUCAO_DE_ATAQUE":
                            setMultiplicadorAtaque((Float)efeitoHabilidade.getV());
                            System.out.println("Ataque do adversario diminuida pela metade!!!");
                            break;
                        case "VAMPIRISMO":
                            receberDano((Float)efeitoHabilidade.getV());
                            break;
                    }
                }
            
                break;
            
        }
    }
    //getters e setters
    public int getExpRecompensa() {
        return expRecompensa;
    }

    public void setExpRecompensa(int expRecompensa) {
        this.expRecompensa = expRecompensa;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
