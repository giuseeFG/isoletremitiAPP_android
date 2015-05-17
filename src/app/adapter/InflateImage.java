package app.adapter;

import android.widget.ImageView;
import app.isole.R;

public class InflateImage {

	public void inflateImageRistorante(String s, ImageView icon) {
		if (s.contains("Bel Mare"))
			icon.setImageResource(R.drawable.alberghi_belmare);
		else if (s.contains("Matana"))
			icon.setImageResource(R.drawable.bar_lunamatana);
		else if (s.contains("Rondinelle"))
			icon.setImageResource(R.drawable.ristorante_lerondinelle);
		else if (s.contains("Pio"))
			icon.setImageResource(R.drawable.ristorante_dapio);
		else if (s.contains("Conchiglia"))
			icon.setImageResource(R.drawable.ristorante_laconchiglia);
		else if (s.contains("L'Architiello"))
			icon.setImageResource(R.drawable.ristorante_larhitiello);
		else if (s.contains("Il Gambero"))
			icon.setImageResource(R.drawable.ristorante_ilgambero);
		else if (s.contains("Belvedere"))
			icon.setImageResource(R.drawable.alberghi_belvedere);
		else if (s.contains("Fenice"))
			icon.setImageResource(R.drawable.ristorante_lafenice);
		else if (s.contains("L'altro Faro"))
			icon.setImageResource(R.drawable.alberghi_laltrofaro);
		else if (s.contains("Tramontana"))
			icon.setImageResource(R.drawable.alberghi_tramontana);
		else if (s.contains("Oasi"))
			icon.setImageResource(R.drawable.alberghi_oasi);
		else if (s.contains("Domino"))
			icon.setImageResource(R.drawable.alberghi_sandomino);
		else if (s.contains("Furmicula"))
			icon.setImageResource(R.drawable.bar_furmicula);
		else if (s.contains("Livornese"))
			icon.setImageResource(R.drawable.bar_lalivornese);
		else if (s.contains("Relais"))
			icon.setImageResource(R.drawable.ristorante_relaisalfaro);
		else if (s.contains("Rossana"))
			icon.setImageResource(R.drawable.alberghi_rossana);
		else if (s.contains("Nassa"))
			icon.setImageResource(R.drawable.ristorante_lanassa);
		else if (s.contains("Pirata"))
			icon.setImageResource(R.drawable.bar_ilpirata);
		else
			icon.setImageResource(R.drawable.non_disponibile);
	}
	public void inflateImageAttivita(String s, ImageView icon) {
		if (s.contains("Noleggio") && s.contains("Benedettini"))
			icon.setImageResource(R.drawable.attivita_benedettini);
		else if (s.contains("Tullio"))
			icon.setImageResource(R.drawable.attivita_datullio);
		else if (s.contains("Tutto in"))
			icon.setImageResource(R.drawable.attivita_tuttointasca);
		else if (s.contains("PRO LOCO"))
			icon.setImageResource(R.drawable.attivita_proloco);
		else if (s.contains("Blu Tremiti"))
			icon.setImageResource(R.drawable.attivita_blutremiti);
		else if (s.contains("Cala Tamariello"))
			icon.setImageResource(R.drawable.attivita_calatamariello);
		else if (s.contains("Saraceni"))
			icon.setImageResource(R.drawable.attivita_saraceni);
		else if (s.contains("Salvagente"))
			icon.setImageResource(R.drawable.attivita_ilsalvagente);
		else if (s.contains("Motoscafo Sparviero"))
			icon.setImageResource(R.drawable.attivita_motiscafosparviero);
		else if (s.contains("Gente di Mare"))
			icon.setImageResource(R.drawable.attivita_gentedimare);
		else if (s.contains("Scrigno"))
			icon.setImageResource(R.drawable.attivita_loscrigno);
		else if (s.contains("Mejofauna"))
			icon.setImageResource(R.drawable.attivita_lamejofauna);
		else if (s.contains("Furmicula"))
			icon.setImageResource(R.drawable.bar_furmicula);
		else if (s.contains("Ernesto"))
			icon.setImageResource(R.drawable.attivita_ernesto);
		else if (s.contains("Umberto"))
			icon.setImageResource(R.drawable.attivita_umbertoboat);
		else if (s.contains("Scoglitti"))
			icon.setImageResource(R.drawable.attivita_gliscoglitti);
		else if (s.contains("Greco"))
			icon.setImageResource(R.drawable.attivita_ilgreco);
		else if (s.contains("benessere"))
			icon.setImageResource(R.drawable.attivita_isoladelbenessere);
		else if (s.contains("Neverland"))
			icon.setImageResource(R.drawable.attivita_neverland);
		else if (s.contains("Aquolina"))
			icon.setImageResource(R.drawable.attivita_aquolinadivingcenter);
		else if (s.contains("Jimmy"))
			icon.setImageResource(R.drawable.attivita_jimmybike);
		else if (s.contains("Marlin"))
			icon.setImageResource(R.drawable.attivita_marlindivingcenter);
		else if (s.equals("Diving Center Tremiti"))
			icon.setImageResource(R.drawable.attivita_tremitidivingcenter);
		else if (s.contains("Skizzo"))
			icon.setImageResource(R.drawable.attivita_skizzo);
		else if (s.contains("88"))
			icon.setImageResource(R.drawable.attivita_shopottantotto);
		else if (s.contains("Polo Nord"))
			icon.setImageResource(R.drawable.attivita_bagnipolonord);
		else if (s.contains("Lido") & s.contains("Benedettini"))
			icon.setImageResource(R.drawable.attivita_caladeibenedettini);
		else if (s.contains("Hanif"))
			icon.setImageResource(R.drawable.attivita_hanif);
		else if (s.contains("Franca"))
			icon.setImageResource(R.drawable.attivita_francasport);
		else if (s.contains("Fentini"))
			icon.setImageResource(R.drawable.attivita_minimarketfentini);
		else if (s.contains("Addosso"))
			icon.setImageResource(R.drawable.attivita_ilmareaddosso);
		else if (s.contains("benzina"))
			icon.setImageResource(R.drawable.bar_arthurclub);
		else if (s.equals("La bottega del mare"))
			icon.setImageResource(R.drawable.attivita_labottegadelmare);
		else
			icon.setImageResource(R.drawable.non_disponibile);
	}
	public void inflateImageHotel(String s, ImageView icon) {
		if (s.contains("Baely"))
			icon.setImageResource(R.drawable.alberghi_baelyresort);
		else if (s.contains("Bel Mare"))
			icon.setImageResource(R.drawable.alberghi_belmare);
		else if (s.contains("Angela"))
			icon.setImageResource(R.drawable.alberghi_appartamentiangela);
		else if (s.contains("Junior"))
			icon.setImageResource(R.drawable.alberghi_junior);
		else if (s.contains("da Teresa"))
			icon.setImageResource(R.drawable.alberghi_teresa);
		else if (s.contains("Panorama"))
			icon.setImageResource(R.drawable.alberghi_panorama);
		else if (s.contains("Gambero"))
			icon.setImageResource(R.drawable.alberghi_ilgambero);
		else if (s.contains("Girolamo"))
			icon.setImageResource(R.drawable.alberghi_degirolamo);
		else if (s.contains("Belvedere"))
			icon.setImageResource(R.drawable.alberghi_belvedere);
		else if (s.contains("Eden"))
			icon.setImageResource(R.drawable.alberghi_eden);
		else if (s.contains("Gabbiano"))
			icon.setImageResource(R.drawable.alberghi_gabbiano);
		else if (s.contains("Lo Russo"))
			icon.setImageResource(R.drawable.alberghi_lorusso);
		else if (s.contains("Capannone"))
			icon.setImageResource(R.drawable.alberghi_ilcapannone);
		else if (s.contains("Tullia"))
			icon.setImageResource(R.drawable.alberghi_tullia);
		else if (s.contains("Rondinelle"))
			icon.setImageResource(R.drawable.ristorante_lerondinelle);
		else if (s.contains("Diamante"))
			icon.setImageResource(R.drawable.alberghi_puntadeldiamante);
		else if (s.contains("Scrigno"))
			icon.setImageResource(R.drawable.alberghi_loscrigno);
		else if (s.contains("L'altro Faro"))
			icon.setImageResource(R.drawable.alberghi_laltrofaro);
		else if (s.contains("Pineta"))
			icon.setImageResource(R.drawable.alberghi_lapineta);
		else if (s.contains("Tramontana"))
			icon.setImageResource(R.drawable.alberghi_tramontana);
		else if (s.contains("Vela"))
			icon.setImageResource(R.drawable.alberghi_lavela);
		else if (s.contains("Viole"))
			icon.setImageResource(R.drawable.alberghi_leviole);
		else if (s.contains("Oasi"))
			icon.setImageResource(R.drawable.alberghi_oasi);
		else if (s.contains("Paradiso"))
			icon.setImageResource(R.drawable.alberghi_villaparadiso);
		else if (s.contains("Domino"))
			icon.setImageResource(R.drawable.alberghi_sandomino);
		else if (s.contains("Touring"))
			icon.setImageResource(R.drawable.alberghi_tci);
		else if (s.contains("Elefante"))
			icon.setImageResource(R.drawable.alberghi_villaelefante);
		else if (s.contains("Kirie"))
			icon.setImageResource(R.drawable.alberghi_kirie);
		else if (s.contains("Waikiki"))
			icon.setImageResource(R.drawable.alberghi_waikiki);
		else if (s.contains("Skandenberg"))
			icon.setImageResource(R.drawable.alberghi_villaskandenberg);
		else if (s.contains("Relais"))
			icon.setImageResource(R.drawable.ristorante_relaisalfaro);
		else if (s.contains("Isola Verde"))
			icon.setImageResource(R.drawable.alberghi_isolaverde);
		else if (s.contains("Bussola"))
			icon.setImageResource(R.drawable.alberghi_labussola);
		else if (s.contains("Mare Blu"))
			icon.setImageResource(R.drawable.alberghi_mareblu);
		else if (s.contains("Rossana"))
			icon.setImageResource(R.drawable.alberghi_rossana);
		else if (s.contains("Nassa"))
			icon.setImageResource(R.drawable.ristorante_lanassa);
		else
			icon.setImageResource(R.drawable.non_disponibile);
	}
	public void inflateImageBar(String s, ImageView icon) {
		if (s.contains("Arthur Club"))
			icon.setImageResource(R.drawable.bar_arthurclub);
		else if (s.contains("L'Architiello"))
			icon.setImageResource(R.drawable.ristorante_larhitiello);
		else if (s.contains("L'Approdo"))
			icon.setImageResource(R.drawable.bar_approdo);
		else if (s.contains("Capannina"))
			icon.setImageResource(R.drawable.bar_lacapannina);
		else if (s.contains("Stella"))
			icon.setImageResource(R.drawable.bar_stelladimare);
		else if (s.contains("Yogurtoteka"))
			icon.setImageResource(R.drawable.bar_layogurtoteka);
		else if (s.contains("Bel Mare"))
			icon.setImageResource(R.drawable.alberghi_belmare);
		else if (s.contains("Belvedere"))
			icon.setImageResource(R.drawable.alberghi_belvedere);
		else if (s.contains("Matana"))
			icon.setImageResource(R.drawable.bar_lunamatana);
		else if (s.contains("Rossana"))
			icon.setImageResource(R.drawable.alberghi_rossana);
		else if (s.contains("Fenice"))
			icon.setImageResource(R.drawable.ristorante_lafenice);
		else if (s.contains("Pirata"))
			icon.setImageResource(R.drawable.bar_ilpirata);
		else if (s.contains("Diomede"))
			icon.setImageResource(R.drawable.bar_diomede);
		else if (s.contains("Livornese"))
			icon.setImageResource(R.drawable.bar_lalivornese);
		else if (s.contains("Furmicula"))
			icon.setImageResource(R.drawable.bar_furmicula);
		else if (s.equals("Era Ora"))
			icon.setImageResource(R.drawable.bar_eraora);
		else if (s.contains("Braceria"))
			icon.setImageResource(R.drawable.bar_areapicnic);
		else
			icon.setImageResource(R.drawable.non_disponibile);
	}
}

