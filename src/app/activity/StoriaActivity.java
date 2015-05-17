package app.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import app.isole.R;

public class StoriaActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.storia);
		Typeface eurof35 = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
		TextView testoStoria1 = (TextView)findViewById(R.id.testoStoria1);
		TextView testoStoria2 = (TextView)findViewById(R.id.testoStoria2);
		TextView testoStoriaAcheo = (TextView)findViewById(R.id.testoStoriaAcheo);
		TextView testoStoria3 = (TextView)findViewById(R.id.testoStoria3);
		TextView testoStoria4 = (TextView)findViewById(R.id.testoStoria4);
		TextView testoStoria5 = (TextView)findViewById(R.id.testoStoria5);
		TextView testoStoria6 = (TextView)findViewById(R.id.testoStoria6);
		TextView testoStoria7 = (TextView)findViewById(R.id.testoStoria7);
		testoStoria1.setTypeface(eurof35);
		testoStoria2.setTypeface(eurof35);
		testoStoria3.setTypeface(eurof35);
		testoStoria4.setTypeface(eurof35);
		testoStoria5.setTypeface(eurof35);
		testoStoria6.setTypeface(eurof35);
		testoStoria7.setTypeface(eurof35);
		
		testoStoria1.setText("\tLe Isole Tremiti nonostante debbano, a ragione, la propria fama alla loro bellezza " +
				"paesaggistico-naturalistica, rivestono un ruolo molto importante anche da un punto di vista storico-culturale, " +
				"tanto da poter essere considerate un \"museo a cielo aperto\".");
		testoStoria2.setText("\tIl loro antico nome era \"Insulae Diomedeae\", in onore dell'eroe greco Diomede: " +
				"la leggenda racconta che egli approdò fortunosamente nell'arcipelago dopo la guerra di Troia " +
				"e vi si stabilì rapito dalla bellezza del luogo. Alla sua morte, Venere trasformò i suoi compagni in uccelli " +
				"(le diomedee), ancora oggi presenti sulle isole. Le Diomedee appunto, sono uccelli dalla grande " +
				"apertura alare, che ogni primavera lasciano l\'Africa Orientale verso l\'Adriatico per nidificare" +
				" sulle pareti a strapiombo tipiche della costa di queste isole. \n\tIl loro canto che si sente in particolare " +
				"nelle ore notturne, altro non è che il lamento dei compagni di Diomede che piangono la perdita del loro eroe.");
		testoStoriaAcheo.setText("In figura: l\'Acheo guardiano delle isole Tremiti - statua in bronzo donata da Lucio Dalla.");
		testoStoria3.setText("\tLe isole erano abitate già  dal IV-III secolo a. C., per molti secoli furono un luogo di confino. " +
				"Abitate fin dal Neolitico trovarono il loro massimo splendore verso il XI secolo quando i Benedettini " +
				"di Montecassino fondarono una chiesa con annesso monastero sull\'isola di S. Nicola. " +
				"Ai Benedettini si susseguirono altri due ordini religiosi, i Cistercensi, a partire dal 1237, " +
				"ed i Canonici regolari lateranensi. Questi ultimi trasformarono l\'arcipelago in una base di appoggio della Serenissima.");
		testoStoria4.setText("\tVi fu poi un periodo di decadenza. Con l'avvento dei Borboni le isole divennero colonia penale" +
				" e successivamente con il fascismo luogo di confino.");
		testoStoria5.setText("\tL\'abbazia di S. Maria a Mare custodisce diversi capolavori: " +
				"possiamo sottolineare la presenza di un polittico ligneo di scuola veneziana risalente al 400, " +
				"una pavimentazione a mosaico del XI-XII secolo ed una croce tremitese greco-bizantina (raffigurata in foto).");
		testoStoria6.setText("\tIl nome \"Tremitis\", da cui l\'odierno Tremiti, compare per la prima volta nei manoscritti " +
				"di età  medioevale e dovrebbe richiamare l\'antica attività  sismica che avrebbe portato le attuali isole a " +
				"distaccarsi dal gargano.");
		testoStoria7.setText("\tQuesto e tanto altro riservano \"Le perle dell\'Adriatico\", da vivere intensamente nella " +
				"conoscenza ed esplorazione dei luoghi più suggestivi e mistici che la natura potesse regalare, dove " +
				"il tempo non ha mai fine.");
	}
}