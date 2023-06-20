package com.ibilcerpg.ibilcerpg.Personagens;

import com.ibilcerpg.ibilcerpg.SuperClasses.Acao;

public class Fisico extends Inimigo {
    private float buffProximoDano;

    private boolean Carregando;

    private Acao<String,Object> AcaoEReacao(){
        if(buffProximoDano != 0 ) System.out.println("Ataque Poderoso!");
        setCarregando(false);
        return inimigoAtacar(buffProximoDano);
    }

    private Acao<String,Object> CarregarAcaoEReacao(){
        System.out.println("Fisico não reagiu");
        setCarregando(true);
        return new Acao<String,Object>("DEFAULT","DEFAULT");
    }


    public Fisico(){
        super();
        setTag("FISICO");
        setNome("Físico");
        setDefesaBase(2);
    }

    @Override
    public Acao<String,Object> turnoNoCombate(){
        if(getContadorTurnos()%4 == 0 || getContadorTurnos()%4 == 1){
            incrementarContadorTurnos();
            return inimigoAtacar();
        }else if(getContadorTurnos()%4 == 2){
            incrementarContadorTurnos();
            return CarregarAcaoEReacao();
        }else if(getContadorTurnos()%4 == 3){
            incrementarContadorTurnos();
            return AcaoEReacao();
        }
        return null;
    }

    public Acao<String,Object> inimigoAtacar(float buff){
        Acao<String,Object> turno = new Acao<String,Object>();
        turno.setT("ATAQUE");
        turno.setV(buff);
        setDebuffDano(1);
        setBuffProximoDano(0);
        System.out.println(turno.getT());
        return turno;
    }

    @Override
    public int receberDano(float danoPuro){
        if(isCarregando()) setBuffProximoDano(Math.round(danoPuro)*2);
        return super.receberDano(danoPuro);
    }

    public float getBuffProximoDano() {
        return buffProximoDano;
    }

    public void setBuffProximoDano(float buffProximoDano) {
        this.buffProximoDano = buffProximoDano;
    }

    public boolean isCarregando() {
        return Carregando;
    }

    public void setCarregando(boolean carregando) {
        Carregando = carregando;
    }
}
