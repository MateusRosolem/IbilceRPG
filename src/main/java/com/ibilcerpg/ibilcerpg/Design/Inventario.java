package com.ibilcerpg.ibilcerpg.Design;

import java.io.Serializable;
import java.util.ArrayList;

import com.ibilcerpg.ibilcerpg.Controllers.FXInventarioController;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;

/**
 * Classe que cuida das Habilidades do joador, armazenando-as e podendo equipá-las
 */
public class Inventario implements Serializable {

    private ArrayList<Habilidade> habilidades = new ArrayList<Habilidade>();
    private Habilidade habilidadeEquipada;
    private HabilidadeDefault habilidadeDefault = new HabilidadeDefault();
    public Inventario(){
        habilidadeEquipada = null;
    }

    /**
     * Metodo que adiciona uma nova habilidade ao inventario
     * @param habilidade nova habilidade a ser adicionada
     */
    public void adicionarHabilidade(Habilidade habilidade){
        if(habilidade != null){
            habilidades.add(habilidade);
        }
    }

    /**
     * Printa o inventario inteiro no terminal
     */
    public void printInventario(FXInventarioController UI){
        int i = 1;
        for (Habilidade habilidade: habilidades) {
            UI.descricaoItem.setText(i + ": "+ habilidade.getNome());
            i++;
        }
    }

    /**
     * equipa a habilidade que esta na posicao i da lista de habilidades
     * @param i posicao da habilidade desejada
     */
    public void equiparHabilidade(int i){
        if(habilidades != null && i > 0 && i <= habilidades.size()) {
            setHabilidadeEquipada(habilidades.get(i - 1));
        }
    }

    /**
     * @return retorna a habilidade equipada, se nao houver, retorna uma habilidade neutra default
     */
    public Habilidade getHabilidadeEquipada() {
        if(habilidadeEquipada == null){
            return habilidadeDefault;
        }
        return habilidadeEquipada;
    }


    //getters e setters
    public void setHabilidadeEquipada(Habilidade habilidadeEquipada) {
        this.habilidadeEquipada = habilidadeEquipada;
    }
    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
    }

}
