package app.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import app.isole.R;

public class InfoAppActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infodeveloper);
		TextView infoApp = (TextView)findViewById(R.id.textViewInfoApp);
		Typeface eurof35 = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
		infoApp.setTypeface(eurof35);
		String testoApp = "\tIsole Tremiti app è la prima applicazione per Android esistente riguardante le Isole Tremiti.\n\tNasce con " +
				"l'intento di promuovere lo splendore delle Isole in tutti i suoi aspetti, far conoscere" +
				" e far vivere quanto offerto dalle \"Isole Diomedee\" a 360°.\n\tTra le diverse categorie è possibile trovare " +
				"alberghi, ristoranti, bar e sapere quali principali punti di interesse ti circondano.\n" +
				"\tL'app è in grado di mettere a diretto contatto l'utente con tutte le strutture ricettive dell'isola, con l'opzione di " +
				"inviare un'email e di collegarsi automaticamente ai vari siti internet degli alberghi/affittacamere senza prendersi la briga" +
				" di cercarli online.\n\t" +
				"Spero che questa app riesca a valorizzare lo splendore delle uniche isole dell'Adriatico.";
		infoApp.setText(testoApp);
	}
}