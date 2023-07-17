package br.com.tech.itafarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarItaFarm extends AppCompatActivity {

    private ListView listView;
    private ItaFarmDAO dao;
    private List<ItaFarmDTO> itafarmdtos;
    private List<ItaFarmDTO> itafarmFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ita_farm);

        listView = findViewById(R.id.lista_itaFarm);
        dao = new ItaFarmDAO(this);

        itafarmdtos = dao.obterTodos();
        itafarmFiltrados.addAll(itafarmdtos);
        //ArrayAdapter<ItaFarmDTO> adaptador = new ArrayAdapter<ItaFarmDTO>(this, android.R.layout.simple_list_item_1, itafarmFiltrados);
        ItaFarmAdapter adaptador = new ItaFarmAdapter(this, itafarmFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarItaFarm(s); return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i =getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final ItaFarmDTO excluirDados = itafarmFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção").setMessage("Realmente deseja excluir?").setNegativeButton("Não", null).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itafarmFiltrados.remove(excluirDados);
                itafarmdtos.remove(excluirDados);
                dao.excluirItaFarm(excluirDados);
                listView.invalidateViews();
            }
        }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        final ItaFarmDTO atualizarDados = itafarmFiltrados.get(menuInfo.position);

        Intent it =  new Intent(this, cadastroItaFarm.class);
        it.putExtra("itaFarm", atualizarDados);
        startActivity(it);



    }

    public void maisInfo(MenuItem item){
        AdapterView.AdapterContextMenuInfo  menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        final ItaFarmDTO infosDados = itafarmFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, InfoItaFarm.class);
        it.putExtra("itaFarm", infosDados);
        startActivity(it);

    }



    public void procurarItaFarm(String nomeProduto){
        itafarmFiltrados.clear();
        for(ItaFarmDTO a: itafarmdtos){
            if(a.getNomeProduto().toLowerCase().contains(nomeProduto.toLowerCase())){
                itafarmFiltrados.add(a);
            }
            listView.invalidateViews();
        }
    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, cadastroItaFarm.class);
        startActivity(it);
    }

    public void onResume(){
        super.onResume();
        itafarmdtos = dao.obterTodos();
        itafarmFiltrados.clear();
        itafarmFiltrados.addAll(itafarmdtos);
        listView.invalidateViews();
    }
}