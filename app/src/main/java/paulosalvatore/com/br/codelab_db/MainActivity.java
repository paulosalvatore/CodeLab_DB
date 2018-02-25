package paulosalvatore.com.br.codelab_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DatabaseHelper helper = new DatabaseHelper(this.getApplicationContext());
		DatabaseManager.initializeInstance(helper);
		DatabaseManager baseDados = DatabaseManager.getInstance();

		/*
		baseDados.InserirPosicao(new Posicao());
		List<Posicao> listaPosicoes = baseDados.ObterPosicoesUsuario();

		for (Posicao posicao :
				listaPosicoes) {
			Log.d("Latitude", Long.valueOf(posicao.getLatitude()).toString());
		}

		baseDados.AtualizarPosicao(listaPosicoes.get(0));
		baseDados.RemoverPosicao(listaPosicoes.get(0));
		*/
	}
}
