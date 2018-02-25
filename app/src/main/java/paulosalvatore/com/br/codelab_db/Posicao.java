package paulosalvatore.com.br.codelab_db;

/**
 * Created by paulo on 24/02/2018.
 */

class Posicao {
	private int id;
	private double latitude;
	private double longitude;
	private String dataHora;

	public Posicao(int id, double latitude, double longitude, String dataHora) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataHora = dataHora;
	}

	public Posicao(double latitude, double longitude, String dataHora) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataHora = dataHora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
}
