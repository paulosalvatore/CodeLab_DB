package paulosalvatore.com.br.codelab_db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo on 24/02/2018.
 */
public class DatabaseManager {
	protected SQLiteDatabase db;
	protected DatabaseHelper helper;
	private static DatabaseManager instance;
	private static DatabaseHelper mDatabaseHelper;
	private int mOpenCounter;

	public static synchronized void initializeInstance(DatabaseHelper helper) {
		if (instance == null) {
			instance = new DatabaseManager();
			mDatabaseHelper = helper;
		}
	}

	public static synchronized DatabaseManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Conexão com o banco de dados não iniciada, execute initializeInstance(..) primeiro.");
		}
		return instance;
	}

	public void openDB() {
		mOpenCounter++;
		if (mOpenCounter == 1) {
			// Abrindo uma nova conexão caso não esteja aberta ainda
			db = mDatabaseHelper.getWritableDatabase();
		}
	}

	public void closeDB() {
		mOpenCounter--;
		if (mOpenCounter == 0) {
			// Fechando a conexão caso não haja mais conexões sendo utilizadas
			db.close();
		}
	}

	protected void Close(Cursor cursor) {
		if (cursor != null) {
			cursor.close();
		}
	}

	public List<Posicao> ObterPosicoesUsuario() {
		List<Posicao> res = new ArrayList<Posicao>();
		openDB();
		String selectQuery = "SELECT CHAVE_POSICAO, LATITUDE, LONGITUDE, DATA_HORA FROM TBL_POSICAO";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Posicao posicao = new Posicao();
				posicao.setId(c.getInt(c.getColumnIndex("CHAVE_POSICAO")));
				posicao.setLatitude(c.getLong(c.getColumnIndex("LATITUDE")));
				posicao.setLongitude(c.getLong(c.getColumnIndex("LONGITUDE")));
				posicao.setDataHora(c.getString(c.getColumnIndex("DATA_HORA")));
				res.add(posicao);
			} while (c.moveToNext());
		}
		Close(c);
		closeDB();
		return res;
	}

	public void InserirPosicao(Posicao posicao) {
		openDB();
		ContentValues values = new ContentValues();
		values.put("LATITUDE", posicao.getLatitude());
		values.put("LONGITUDE", posicao.getLongitude());
		values.put("DATA_HORA", posicao.getDataHora());
		// Insere a posição na tabela
		db.insert("TBL_POSICAO", null, values);
		closeDB();
	}

	public void AtualizarPosicao(Posicao posicao) {
		String strSQL = "UPDATE TBL_POSICAO SET LATITUDE = " + posicao.getLatitude()
				+ ", LONGITUDE = " + posicao.getLongitude()
				+ ", DATA_HORA = '" + posicao.getDataHora()
				+ "' WHERE CHAVE_POSICAO = " + posicao.getId();
		db.execSQL(strSQL);
		closeDB();
	}

	public void RemoverPosicao(Posicao posicao) {
		openDB();
		String strSQL = "DELETE FROM TBL_POSICAO WHERE CHAVE_POSICAO = " + posicao.getId();
		db.execSQL(strSQL);
		closeDB();
	}
}
