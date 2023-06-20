package devandroid.julian.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.julian.appgaseta.R;
import devandroid.julian.appgaseta.apoio.UtilGasEta;
import devandroid.julian.appgaseta.controller.CombustivelController;
import devandroid.julian.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {

    CombustivelController controller;
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new CombustivelController(GasEtaActivity.this);
        setContentView(R.layout.activity_gaseta);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOk = true;

                if(TextUtils.isEmpty(editGasolina.getText())){
                    editGasolina.setError("* Required");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if(TextUtils.isEmpty(editEtanol.getText())){
                    editEtanol.setError("* Required");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }

                if(isDadosOk){

                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);


                }else{
                    Toast.makeText(GasEtaActivity.this,
                            "Please enter the required data...",
                            Toast.LENGTH_LONG).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelEtanol);

                Toast.makeText(GasEtaActivity.this, "Saved successfully...",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editEtanol.setText("");
                editGasolina.setText("");
                txtResultado.setText("RESULTADO");

                btnSalvar.setEnabled(false);
                controller.limpar();

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GasEtaActivity.this, "App GasEta Closed...",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
