package br.com.tech.itafarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


public class InfoItaFarm extends AppCompatActivity {
    private EditText nomeProduto, precoVenda, quantidade;

    private EditText nomeMarca, descricaoCategoria, codigoBarras;

    private EditText peso, dataValidade, lote;

    private ItaFarmDAO dao;
    private ItaFarmDTO itaFarmDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info_ita_farm);

        dao = new ItaFarmDAO(this);


        nomeProduto = findViewById(R.id.editTextNome);
        nomeMarca = findViewById(R.id.editTextMarca);
        quantidade = findViewById(R.id.editTextQuantidade);
        descricaoCategoria = findViewById(R.id.editTextDescricao);
        codigoBarras = findViewById(R.id.editTextCodigo_de_barras);
        peso = findViewById(R.id.editTextPeso);
        precoVenda = findViewById(R.id.editTextValor);
        lote = findViewById(R.id.editTextLote);
        dataValidade = findViewById(R.id.editTextValidade);

        Intent it = getIntent();
        if(it.hasExtra("itaFarm")){
            itaFarmDTO = (ItaFarmDTO) it.getSerializableExtra("itaFarm");
            nomeProduto.setText(itaFarmDTO.getNomeProduto());
            nomeMarca.setText(itaFarmDTO.getNomeMarca());
            quantidade.setText(itaFarmDTO.getQuantidade());
            descricaoCategoria.setText(itaFarmDTO.getDescricaoCategoria());
            codigoBarras.setText(itaFarmDTO.getCodigoBarras());
            peso.setText(itaFarmDTO.getPeso());
            precoVenda.setText(itaFarmDTO.getPrecoVenda());
            lote.setText(itaFarmDTO.getLote());
            dataValidade.setText(itaFarmDTO.getDataValidade());
        }
    }
}