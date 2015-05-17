package app.helper;

public class HelperDateFormat {

	public HelperDateFormat () {

	}
	public boolean checkDateFormat(int giornoArrivo, int meseArrivo, int annoArrivo, int giornoRitorno, int meserRitorno, int annoRitorno) {
		//		if (annoPartenza >= annoArrivo && mesePartenza >= meseArrivo && giornoPartenza > giornoArrivo)
		//			return true;
		//		else
		//			return false;

		if (annoArrivo < annoRitorno)
			return true;
		else {
			if (annoArrivo > annoRitorno)
				return false;
			if (meseArrivo < meserRitorno)
				return true;
			if (meseArrivo == meserRitorno){
				if (giornoArrivo < giornoRitorno) 
					return true;
				else
					return false;
			}
		}
		return false;
	}
}