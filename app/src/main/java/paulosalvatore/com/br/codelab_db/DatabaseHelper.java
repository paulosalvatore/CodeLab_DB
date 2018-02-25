package paulosalvatore.com.br.codelab_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paulo on 24/02/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private Context context;

	public DatabaseHelper(Context context) {
		/* Primeiro argumento: contexto da aplicacao.
		 * Segundo argumento: nome do banco de dados.
		 * Terceiro argumento: ponteiro para manipulação de dados, não precisaremos dele.
		 * Quarto argumento: versão do banco de dados */
		super(context, "codelab_posicoes", null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL("CREATE TABLE posicoes (id integer primary key, latitude double, longitude double, data_hora varchar(255));");
		} catch (Exception ex) {
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			// Caso seja necessário atualizar a estrutura da base de dados em uma nova versão do app
		} catch (Exception ex) {
		}
	}

}
