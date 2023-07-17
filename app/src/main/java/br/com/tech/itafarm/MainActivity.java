package br.com.tech.itafarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void Cadastrar (View view){
        Intent cad = new Intent(this,cadastroItaFarm.class);
        startActivity(cad);
    }

    public void Listar (View view){
        Intent list = new Intent(this,ListarItaFarm.class);
        startActivity(list);
   }


}