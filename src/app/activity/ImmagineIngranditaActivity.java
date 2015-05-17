package app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import app.isole.R;

public class ImmagineIngranditaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dettaglio_immagini_marker);
		ImageView immagine = (ImageView) findViewById(R.id.imageViewDettaglio);
		String stringNomeCalaSelezionata = this.getIntent().getExtras().getString("nome marker");
		TextView textViewNomeCala = (TextView)findViewById(R.id.textViewNomeCala);
		textViewNomeCala.setText(stringNomeCalaSelezionata);
		if (stringNomeCalaSelezionata.contains("Elefante"))
			immagine.setImageResource(R.drawable.dettagliogrande_elefante);
		else if (stringNomeCalaSelezionata.equals("Cala Matano"))
			immagine.setImageResource(R.drawable.dettagliogrande_calamatano);
		else if (stringNomeCalaSelezionata.equals("Cala dello Spido"))
			immagine.setImageResource(R.drawable.dettagliogrande_calaspido);
		else if (stringNomeCalaSelezionata.equals("Cala del Sale"))
			immagine.setImageResource(R.drawable.dettagliogrande_caladelsale);
		else if (stringNomeCalaSelezionata.equals("Cala delle Roselle"))
			immagine.setImageResource(R.drawable.dettagliogrande_calaroselle);
		else if (stringNomeCalaSelezionata.equals("Grotta delle Viole"))
			immagine.setImageResource(R.drawable.dettagliogrande_grottadelleviole);
		else if (stringNomeCalaSelezionata.equals("Cala di Zio Cesare"))
			immagine.setImageResource(R.drawable.dettagliogrande_calaziocesare);
		else if (stringNomeCalaSelezionata.equals("Punta di Ponente"))
			immagine.setImageResource(R.drawable.dettagliogrande_puntaponente);
		else if (stringNomeCalaSelezionata.equals("Punta della Provvidenza"))
			immagine.setImageResource(R.drawable.dettagliogrande_puntadellaprovvidenza);
		else if (stringNomeCalaSelezionata.equals("Grotta del Bue Marino"))
			immagine.setImageResource(R.drawable.dettagliogrande_buemarino);
		else if (stringNomeCalaSelezionata.equals("Architiello di San Domino"))
			immagine.setImageResource(R.drawable.dettagliogrande_architiellosandomino);
		else if (stringNomeCalaSelezionata.equals("Cala delle Arene"))
			immagine.setImageResource(R.drawable.dettagliogrande_caladellearene);
		else if (stringNomeCalaSelezionata.equals("Punta Secca di San Domino"))
			immagine.setImageResource(R.drawable.dettagliogrande_puntaseccasandomino);
		else if (stringNomeCalaSelezionata.equals("Grotta delle Rondinelle"))
			immagine.setImageResource(R.drawable.dettagliogrande_grottadellerondinelle);
		else if (stringNomeCalaSelezionata.equals("Cala dei Benedettini"))
			immagine.setImageResource(R.drawable.dettagliogrande_caladeibenedettini);
		else if (stringNomeCalaSelezionata.equals("Cala Tramontana"))
			immagine.setImageResource(R.drawable.dettagliogrande_calatramontana);
		else if (stringNomeCalaSelezionata.equals("Cala Tonda"))
			immagine.setImageResource(R.drawable.dettagliogrande_calatonda);
		else if (stringNomeCalaSelezionata.equals("Cala degli Inglesi"))
			immagine.setImageResource(R.drawable.dettagliogrande_calainglesi);
		else if (stringNomeCalaSelezionata.equals("Cala Tamariello"))
			immagine.setImageResource(R.drawable.dettagliogrande_calatamariello);
		else if (stringNomeCalaSelezionata.equals("I Pagliai"))
			immagine.setImageResource(R.drawable.dettagliogrande_pagliai);
		else if (stringNomeCalaSelezionata.equals("Cala Pietra del Fucile"))
			immagine.setImageResource(R.drawable.dettagliogrande_pietrafucili);
		else if (stringNomeCalaSelezionata.equals("Cala dei Turchi"))
			immagine.setImageResource(R.drawable.dettagliogrande_caladeiturchi);
		else if (stringNomeCalaSelezionata.equals("Architiello di Capraia"))
			immagine.setImageResource(R.drawable.dettagliogrande_architiellocapraia);
		else if (stringNomeCalaSelezionata.equals("Statua di Padre Pio Sommersa"))
			immagine.setImageResource(R.drawable.dettagliogrande_padrepio);
	}
}