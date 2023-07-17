package br.com.tech.itafarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionFactory  extends SQLiteOpenHelper {

    private static final String NAMEDB = "banco.db_farma";
    private static final int VERSION = 1;

    public ConnectionFactory(Context context) { super(context, NAMEDB, null, VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE itaFarm (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeProduto VARHAR(200), nomeMarca VARCHAR(200), quantidade VARCHAR(200),descricaoCategoria VARCHAR(200), codigoBarras VARCHAR(200), peso VARCHAR(20), precoVenda VARCHAR(20), lote VARCHAR(200), dataValidade VARCHAR(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}