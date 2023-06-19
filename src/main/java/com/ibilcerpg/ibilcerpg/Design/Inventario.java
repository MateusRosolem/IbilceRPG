package com.ibilcerpg.ibilcerpg.Design;

import java.util.ArrayList;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;
public class Inventario {

    private ArrayList<Habilidade> habilidades = new ArrayList<Habilidade>();
    private Habilidade habilidadeEquipada;
    private HabilidadeDefault habilidadeDefault = new HabilidadeDefault();

    public void adicionarHabilidade(Habilidade habilidade){
        if(habilidade != null){
            habilidades.add(habilidade);
        }
    }

    public void printInventario(){
        int i = 1;
        for (Habilidade habilidade: habilidades) {
            System.out.println(i + ": "+ habilidade.getNome());
            i++;
        }
    }

    public void equiparHabilidade(int i){
        if(habilidades != null && i > 0 && i <= habilidades.size()) {
            setHabilidadeEquipada(habilidades.get(i - 1));
        }
    }
    public Habilidade getHabilidadeEquipada() {
        if(habilidadeEquipada == null){
            return habilidadeDefault;
        }
        return habilidadeEquipada;
    }

    public void setHabilidadeEquipada(Habilidade habilidadeEquipada) {
        this.habilidadeEquipada = habilidadeEquipada;
    }

    public Inventario(){
        habilidadeEquipada = null;
    }

}
