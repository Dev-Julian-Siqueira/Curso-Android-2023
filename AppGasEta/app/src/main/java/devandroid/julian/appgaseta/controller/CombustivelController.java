package devandroid.julian.appgaseta.controller;

import android.content.SharedPreferences;

import devandroid.julian.appgaseta.model.Combustivel;
import devandroid.julian.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;
    SharedPreferences.Editor editorCombustivel;

    public static final String NOME_PREFERENCES = "pref_combustivel";

    public CombustivelController(GasEtaActivity combustivel){
        preferences = combustivel.getSharedPreferences(NOME_PREFERENCES, 0);
        editorCombustivel = preferences.edit();
    }

    public void salvar(Combustivel combustivel){
        editorCombustivel.putString("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        editorCombustivel.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        editorCombustivel.putString("recomendacao", combustivel.getRecomendacao());
        editorCombustivel.apply();
    }

    public void limpar(){
        editorCombustivel.clear();
        editorCombustivel.apply();
    }
}
