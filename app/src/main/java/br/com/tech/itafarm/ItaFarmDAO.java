package br.com.tech.itafarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItaFarmDAO {
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    ItaFarmDAO(Context context) {
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(ItaFarmDTO itaFarmDTO){
        ContentValues values = new ContentValues();
        values.put("nomeProduto", itaFarmDTO.getNomeProduto());
        values.put("nomeMarca", itaFarmDTO.getNomeMarca());
        values.put("quantidade", itaFarmDTO.getQuantidade());
        values.put("descricaoCategoria", itaFarmDTO.getDescricaoCategoria());
        values.put("codigoBarras", itaFarmDTO.getCodigoBarras());
        values.put("peso", itaFarmDTO.getPeso());
        values.put("precoVenda", itaFarmDTO.getPrecoVenda());
        values.put("lote", itaFarmDTO.getLote());
        values.put("dataValidade", itaFarmDTO.getDataValidade());

        return banco.insert("itaFarm", null, values);
    }

    public List<ItaFarmDTO> obterTodos(){
        List<ItaFarmDTO> itaFarmDTOS =  new ArrayList<>();
        Cursor cursor = banco.query("itaFarm", new String[]{"id", "nomeProduto", "nomeMarca", "quantidade", "descricaoCategoria", "codigoBarras", "peso", "precoVenda", "lote", "dataValidade"}, null, null, null, null, "nomeProduto");

        while(cursor.moveToNext()){
            ItaFarmDTO itafarmdto = new ItaFarmDTO();
            itafarmdto.setId(cursor.getInt(0));
            itafarmdto.setNomeProduto(cursor.getString(1));
            itafarmdto.setNomeMarca(cursor.getString(2));
            itafarmdto.setQuantidade(cursor.getString(3));
            itafarmdto.setDescricaoCategoria(cursor.getString(4));
            itafarmdto.setCodigoBarras(cursor.getString(5));
            itafarmdto.setPeso(cursor.getString(6));
            itafarmdto.setPrecoVenda(cursor.getString(7));
            itafarmdto.setLote(cursor.getString(8));
            itafarmdto.setDataValidade(cursor.getString(9));
            itaFarmDTOS.add(itafarmdto);
        }

        return itaFarmDTOS;
    }

    public void excluirItaFarm (ItaFarmDTO itafarmdto) {
        banco.delete("itaFarm", "id = ?", new String[] {itafarmdto.getId().toString()});
    }

    public void atualizarItaFarm(ItaFarmDTO itafarmdtos) {
        ContentValues values = new ContentValues();
        values.put("nomeProduto", itafarmdtos.getNomeProduto());
        values.put("nomeMarca", itafarmdtos.getNomeMarca());
        values.put("quantidade", itafarmdtos.getQuantidade());
        values.put("descricaoCategoria", itafarmdtos.getDescricaoCategoria());
        values.put("codigoBarras", itafarmdtos.getCodigoBarras());
        values.put("peso", itafarmdtos.getPeso());
        values.put("precoVenda", itafarmdtos.getPrecoVenda());
        values.put("lote", itafarmdtos.getLote());
        values.put("dataValidade", itafarmdtos.getDataValidade());

        //ATUALIZAR NO BANCO
        banco.update("itaFarm", values, "id = ?", new String[]{itafarmdtos.getId().toString()});

    }

}
