package app.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import app.isole.R;

public class InfoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.info);
		TextView testoInfoIsole1 = (TextView)findViewById(R.id.testoInfoIsole1);
		TextView testoInfoIsole2 = (TextView)findViewById(R.id.testoInfoIsole2);
		TextView testoInfoIsole3 = (TextView)findViewById(R.id.testoInfoIsole3);
		TextView testoInfoIsole4 = (TextView)findViewById(R.id.testoInfoIsole4);
		TextView testoInfoIsole5 = (TextView)findViewById(R.id.testoInfoIsole5);
		Typeface eurof35 = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
		testoInfoIsole1.setTypeface(eurof35);
		testoInfoIsole2.setTypeface(eurof35);
		testoInfoIsole3.setTypeface(eurof35);
		testoInfoIsole4.setTypeface(eurof35);
		testoInfoIsole5.setTypeface(eurof35);
		testoInfoIsole1.setText("\tL'arcipelago delle Isole Tremiti si trova a circa 12 miglia dalla costa garganica. " +
				"Il piccolo arcipelago è composto da cinque isole: S. Nicola, S. Domino, Caprara, Cretaccio e, " +
				"a 11 miglia da queste, Pianosa. Le acque cristalline delle Tremiti attirano ogni anno migliaia di turisti.");
		testoInfoIsole2.setText("\tÈ un arcipelago di origine sedimentaria marina. Le isole presentano diversi paesaggi, " +
				"si passa da spiagge di sabbia chiara a falesie imponenti.\n\tL'arcipelago è Area Marina Protetta dal 1989, " +
				"e fa parte del parco Nazionale del Gargano. L'importanza naturalistica di queste isole è eccezionale, " +
				"sia per quanto riguarda la fauna e flora marina, sia per quella terrestre: l'arcipelago infatti riveste " +
				"particolare interesse sotto il profilo della vegetazione e della flora poiché vi si riscontra una elevata " +
				"biodiversità floristica se rapportata alle dimensiono del luogo.\n\tTroviamo una imponente pineta di " +
				"pini d'Aleppo, la macchia mediterranea, orchidee selvatiche endemiche, il bosco misto a leccio ed " +
				"una infinità di formazioni algali. Si riscontrano anche diverse praterie di Posidonia.");
		testoInfoIsole3.setText("\tPer quanto riguarda la fauna l'attenzione si sposta quasi tutta in mare dove " +
				"troviamo dai piccoli e coloratissimi nudibranchi, alle imponenti cernie, dalle miriadi di spugne " +
				"di diversa foggia e colore alle splendide gorgonie screziate! Non per nulla sono considerate " +
				"le perle dell'adriatico, meta indiscussa dei subacquei di tutta Italia.");
		testoInfoIsole4.setText("\tPer quanto riguarda la fauna terrestre bisogna sottolineare la presenza della berta maggiore " +
				"(Calonectris diomedea) conosciuta come Diomedea, che da secoli raggiunge le scogliere delle Tremiti per nidificare.");
		testoInfoIsole5.setText("\tÈ per gli appassionati di immersioni che le Tremiti sono un vero e proprio paradiso. " +
				"In questa Riserva marina si possono avvistare decine di specie ittiche. I fondali sono ricchi di relitti, " +
				"tesori nascosti di una certa rilevanza storica, datati fino al I secolo a.C.\n" +
				"\tPiù recente è l'enorme statua di Padre Pio, collocata nei pressi dell'isola di Capraia nel 1998, " +
				"che rappresenta la più grande statua sottomarina del mondo, creata dallo scultore foggiano Domenico Norcia e posta a 10m" +
				" di profondità. La statua è davvero imponente: 3 metri di altezza, per un peso di 12,25 quintali più 110 quintali di basamento.");
	}
}
