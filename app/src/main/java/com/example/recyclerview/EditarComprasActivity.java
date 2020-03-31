package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditarComprasActivity extends AppCompatActivity {

    EditText nomeEditText;
    EditText marcaEditText;
    EditText quantidadeEditText;
    Compras compras;
    int position;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_compras);

        nomeEditText = (EditText)findViewById(R.id.nomeEditText);
        marcaEditText = (EditText)findViewById(R.id.marcaEditText);
        quantidadeEditText = (EditText)findViewById(R.id.quantidadeEditText);

        Bundle bundle = getIntent().getExtras();
        requestCode = bundle != null ? bundle.getInt("request_code") : 0;

        if (requestCode == MainActivity.REQUEST_EDITAR_COMPRAS) {
            compras = (Compras) bundle.getSerializable("compras");
            position = bundle.getInt("position");

            nomeEditText.setText(compras.getNome());
            marcaEditText.setText(compras.getMarca());
            quantidadeEditText.setText(String.valueOf(compras.getQuantidade()));
        } else
            compras = new Compras();
    }

    public void concluir(View view){
        Bundle bundle = new Bundle();

        if (requestCode == MainActivity.REQUEST_EDITAR_COMPRAS)
            bundle.putInt("position", position);

        compras.setNome(nomeEditText.getText().toString());
        compras.setMarca(marcaEditText.getText().toString());
        compras.setQuantidade(Integer.parseInt(quantidadeEditText.getText().toString()));

        bundle.putSerializable("compras", compras);
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }
}