package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class Fisico extends Inimigo {
    private int buffProximoDano;

    private Acao<String,Object> AcaoEReacao(){
        if(buffProximoDano != 0 ) System.out.println("Ataque Poderoso!");
        return inimigoAtacar(buffProximoDano);
    }

    private Acao<String,Object> CarregarAcaoEReacao(){
        System.out.println("Fisico não reagiu");
        return new Acao<String,Object>("DEFAULT","DEFAULT");
    }


    public Fisico(){
        super();
        setTag("MATEMATICO");
        setNome("Matematico");
        setDefesaBase(2);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        if(getContadorTurnos()%4 == 0){
            incrementarContadorTurnos();
            return inimigoAtacar();
        }else if(getContadorTurnos()%4 == 1){
            incrementarContadorTurnos();
            return inimigoDefender();
        }else if(getContadorTurnos()%4 == 2){
            incrementarContadorTurnos();
            return CarregarAcaoEReacao();
        }else if(getContadorTurnos()%4 == 3){
            incrementarContadorTurnos();
            return AcaoEReacao();
        }
        return null;
    }

    public Acao<String,Object> inimigoAtacar(int buff){
        Acao<String,Object> turno = new Acao<String,Object>();
        turno.setT("ATAQUE");
        turno.setV(((getAtaqueBase()*getMultiplicadorAtaque())*getDebuffDano()) + buff);
        setDebuffDano(1);
        setBuffProximoDano(0);
        System.out.println(turno.getT());
        return turno;
    }

    @Override
    public int receberDano(float danoPuro){
        setBuffProximoDano(Math.round(danoPuro)*2);
        return super.receberDano(danoPuro);
    }

    public int getBuffProximoDano() {
        return buffProximoDano;
    }

    public void setBuffProximoDano(int buffProximoDano) {
        this.buffProximoDano = buffProximoDano;
    }
}
