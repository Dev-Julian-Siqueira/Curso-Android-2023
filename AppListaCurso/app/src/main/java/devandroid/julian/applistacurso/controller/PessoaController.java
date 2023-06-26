package devandroid.julian.applistacurso.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import devandroid.julian.applistacurso.database.ListaVipDB;
import devandroid.julian.applistacurso.model.Pessoa;
import devandroid.julian.applistacurso.view.MainActivity;
import devandroid.julian.applistacurso.view.SplashActivity;

public class PessoaController extends ListaVipDB {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listavip";


    public PessoaController(MainActivity mainActivity) {

        super(mainActivity);

        preferences =
                mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();
    }


    @NonNull
    @Override
    public String toString() {

        Log.d("MVC_Controller", "Controller iniciada...");

        return super.toString();
    }

    public void salvar(Pessoa pessoa) {

        ContentValues dados = new ContentValues();

        dados.put("primeiroNome",pessoa.getPrimeiroNome());
        dados.put("sobreNome",pessoa.getSobreNome());
        dados.put("cursoDesejado",pessoa.getCursoDesejado());
        dados.put("telefoneContato",pessoa.getTelefoneContato());

        salvarObjeto("Pessoa", dados);


        Log.i("MVC_Controller", "Salvo: " + pessoa.toString());

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoa.getSobreNome());
        listaVip.putString("nomeCurso", pessoa.getCursoDesejado());
        listaVip.putString("telefoneContato", pessoa.getTelefoneContato());
        listaVip.apply();

    }

    public Pessoa buscar(Pessoa pessoa) {
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", "NA"));
        pessoa.setSobreNome(preferences.getString("sobreNome", "NA"));
        pessoa.setCursoDesejado(preferences.getString("nomeCurso", "NA"));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", "NA"));

        return pessoa;

    }

    public void alterar(Pessoa pessoa){

        ContentValues dados = new ContentValues();

        dados.put("id",pessoa.getId());
        dados.put("primeiroNome",pessoa.getPrimeiroNome());
        dados.put("sobreNome",pessoa.getSobreNome());
        dados.put("cursoDesejado",pessoa.getCursoDesejado());
        dados.put("telefoneContato",pessoa.getTelefoneContato());

        salvarObjeto("Pessoa", dados);
    }

    public void deletar(int id){
        deletarObjeto("Pessoa", id );
    }

    public List<Pessoa> getListaDeDados(){
        return listarDados();
    }

    public void limpar() {

        listaVip.clear();
        listaVip.apply();

    }
}
