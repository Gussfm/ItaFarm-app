package br.com.tech.itafarm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItaFarmAdapter extends BaseAdapter {
    private List<ItaFarmDTO> itafarmdtos;
    private Activity activity;

    public ItaFarmAdapter(Activity activity, List<ItaFarmDTO> itafarmdtos){
        this.activity = activity;
        this.itafarmdtos = itafarmdtos;
    }

    public int getCount(){
        return itafarmdtos.size();
    }

    public Object getItem(int i){
        return itafarmdtos.get(i);
    }
    public long getItemId(int i){
        return itafarmdtos.get(i).getId();
    }


    public View getView(int i, View view, ViewGroup viewGroup){
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nomeProduto = v.findViewById(R.id.listar_nomeProduto);
        TextView precoVenda = v.findViewById(R.id.listar_Preco);
        TextView quantidade =  v.findViewById(R.id.listar_Quantidade);

        ItaFarmDTO itaFarmDTO = itafarmdtos.get(i);
        nomeProduto.setText(itaFarmDTO.getNomeProduto());
        precoVenda.setText(itaFarmDTO.getPrecoVenda());
        quantidade.setText(itaFarmDTO.getQuantidade());

        return v;
    }
}
