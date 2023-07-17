package br.com.tech.itafarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class cadastroItaFarm extends AppCompatActivity {
    private EditText nomeProduto, precoVenda, quantidade;

    private EditText nomeMarca, descricaoCategoria, codigoBarras;

    private EditText peso, dataValidade, lote;

    private ItaFarmDAO dao;
    private ItaFarmDTO itaFarmDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro_ita_farm);

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

    public void salvar(View view) {
        if  (itaFarmDTO == null) {
            ItaFarmDTO itaFarmDTO = new ItaFarmDTO();

            if(nomeProduto.getText().toString().equals("") || (nomeMarca.getText().toString().equals("") || (quantidade.getText().toString().equals("") || (descricaoCategoria.getText().toString().equals("") || (codigoBarras.getText().toString().equals("") || (peso.getText().toString().equals("") || (precoVenda.getText().toString().equals("") || (lote.getText().toString().equals("") || (dataValidade.getText().toString().equals("")|| (precoVenda.getText().toString().equals(""))))))))))){
                Toast.makeText(this, "TODOS OS CAMPOS SÃO OBRIGATÓRIOS!", Toast.LENGTH_SHORT).show();

            }else{
                itaFarmDTO.setNomeProduto(nomeProduto.getText().toString());
                itaFarmDTO.setNomeMarca(nomeMarca.getText().toString());
                itaFarmDTO.setQuantidade(quantidade.getText().toString());
                itaFarmDTO.setDescricaoCategoria(descricaoCategoria.getText().toString());
                itaFarmDTO.setCodigoBarras(codigoBarras.getText().toString());
                itaFarmDTO.setPeso(peso.getText().toString());
                itaFarmDTO.setPrecoVenda(precoVenda.getText().toString());
                itaFarmDTO.setLote(lote.getText().toString());
                itaFarmDTO.setDataValidade(dataValidade.getText().toString());


                long id = dao.inserir(itaFarmDTO);
                Toast.makeText(this, "Pessoa Inserida com sucesso:" + id, Toast.LENGTH_SHORT).show();
            }




        } else {




            //Atualizar Dados
            itaFarmDTO.setNomeProduto(nomeProduto.getText().toString());
            itaFarmDTO.setNomeMarca(nomeMarca.getText().toString());
            itaFarmDTO.setQuantidade(quantidade.getText().toString());
            itaFarmDTO.setDescricaoCategoria(descricaoCategoria.getText().toString());
            itaFarmDTO.setCodigoBarras(codigoBarras.getText().toString());
            itaFarmDTO.setPeso(peso.getText().toString());
            itaFarmDTO.setPrecoVenda(precoVenda.getText().toString());
            itaFarmDTO.setLote(lote.getText().toString());
            itaFarmDTO.setDataValidade(dataValidade.getText().toString());

            dao.atualizarItaFarm(itaFarmDTO);
            Toast.makeText(this, "Pessoa Atualizada com sucesso:", Toast.LENGTH_SHORT).show();

        }
    }
}





