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

	protected void close(Cursor cursor) {
		if (cursor != null) {
			cursor.close();
		}
	}

	public List<Posicao> obterPosicoesUsuario() {
		List<Posicao> res = new ArrayList<Posicao>();

		openDB();

		String selectQuery = "SELECT * FROM posicoes";
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Posicao posicao =
						new Posicao(
								c.getInt(c.getColumnIndex("id")),
								c.getDouble(c.getColumnIndex("latitude")),
								c.getDouble(c.getColumnIndex("longitude")),
								c.getString(c.getColumnIndex("data_hora"))
						);

				res.add(posicao);
			} while (c.moveToNext());
		}

		close(c);
		closeDB();

		return res;
	}

	public void inserirPosicao(Posicao posicao) {
		openDB();

		ContentValues values = new ContentValues();
		values.put("latitude", posicao.getLatitude());
		values.put("longitude", posicao.getLongitude());
		values.put("data_hora", posicao.getDataHora());

		// Insere a posição na tabela
		db.insert("posicoes", null, values);

		closeDB();
	}

	public void atualizarPosicao(Posicao posicao) {
		String strSQL = "UPDATE posicoes SET latitude = " + posicao.getLatitude()
				+ ", longitude = " + posicao.getLongitude()
				+ ", data_hora = '" + posicao.getDataHora()
				+ "' WHERE id = " + posicao.getId();
		db.execSQL(strSQL);

		closeDB();
	}

	public void removerPosicao(Posicao posicao) {
		openDB();

		String strSQL = "DELETE FROM posicoes WHERE id = " + posicao.getId();
		db.execSQL(strSQL);

		closeDB();
	}
}
