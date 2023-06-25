package devandroid.julian.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import devandroid.julian.appgaseta.database.GasEtaDB;
import devandroid.julian.appgaseta.model.Combustivel;
import devandroid.julian.appgaseta.view.GasEtaActivity;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor editorCombustivel;

    public static final String NOME_PREFERENCES = "pref_combustivel";

    public CombustivelController(GasEtaActivity combustivel){

        super(combustivel);

        preferences = combustivel.getSharedPreferences(NOME_PREFERENCES, 0);
        editorCombustivel = preferences.edit();
    }

    public void salvar(Combustivel combustivel){

        ContentValues dados = new ContentValues();

        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());


        editorCombustivel.putString("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        editorCombustivel.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        editorCombustivel.putString("recomendacao", combustivel.getRecomendacao());
        editorCombustivel.apply();

        salvarObjeto("Combustivel", dados);
    }

    public void alterar(Combustivel combustivel){
        ContentValues dados = new ContentValues();

        dados.put("id", combustivel.getId());
        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        alterarObjeto("Combustivel", dados);
    }

    public List<Combustivel> getListaDeDados(){
        return listarDados();
    }

    public void limpar(){
        editorCombustivel.clear();
        editorCombustivel.apply();
    }
}
