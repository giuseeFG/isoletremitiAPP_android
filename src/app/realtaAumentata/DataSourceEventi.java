package app.realtaAumentata;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import app.entity.Evento;

public class DataSourceEventi {
	/**	
	 * dalla stringa dell'url viene creata una lista di PoiBean
	 * @param url
	 */


	public List<Evento> getEventiByURL(InputStream stream) throws Exception {
		//		InputStream stream = getInputStreamByUrl(url);
		if (stream.equals(null)){
			return null;
		}

		String string = getHttpInputString(stream);
		if (string==null) 
			return null;
		JSONObject json = null;
		json = new JSONObject(string);
		List<Evento> pois = new ArrayList<Evento>();
		if (json!=null) 
			pois = this.getEventiByJSONObject(json);
		return pois;
	}

	//	public List<PoiBean> getPoisByURL(String url) throws Exception {			ONLINE!!!
	//		InputStream stream = getInputStreamByUrl(url);
	//		if (stream.equals(null)){
	//			return null;
	//		}
	//
	//		String string = getHttpInputString(stream);
	//		if (string==null) 
	//			return null;
	//		JSONObject json = null;
	//		json = new JSONObject(string);
	//		List<PoiBean> pois = new ArrayList<PoiBean>();
	//		if (json!=null) 
	//			pois = this.getPoisByJSONObject(json);
	//		return pois;
	//	}

	/**
	 * da stringa a InputStream
	 * @param urlString
	 */
	protected static InputStream getInputStreamByUrl(String urlString) throws IOException {
		InputStream is = null;
		URLConnection conn = null;  
		try {
			if (urlString.startsWith("file://"))
				return new FileInputStream(urlString.replace("file://", ""));
			URL url = new URL(urlString);
			conn =  url.openConnection();
			//			conn.setReadTimeout(10000);
			//			conn.setConnectTimeout(10000);

			is = conn.getInputStream();

			return is;
		} 
		catch (Exception e1) {
			is.close();
		}
		try {
			if(conn instanceof HttpURLConnection)
				((HttpURLConnection)conn).disconnect();
		} catch (Exception e) {
		}	      
		return null;
	}

	protected String getHttpInputString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8 * 1024);
		StringBuilder sb = new StringBuilder();

		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * @param latitude, longitude
	 */

	public List<Evento> getEventiByJSONObject(JSONObject root) throws JSONException {
		JSONObject jo = null;
		JSONArray dataArray = null;
		List<Evento> pois = new ArrayList<Evento>();

		if(root.has("eventi")) 
			dataArray = root.getJSONArray("eventi");

		if (dataArray == null) 
			return pois;

		for (int i = 0; i < dataArray.length(); i++) {          
			jo = dataArray.getJSONObject(i);
			Evento poi = processJSONObject(jo);
			if(poi!=null) 
				pois.add(poi);
		}
		return pois;
	}

	public Evento processJSONObject(JSONObject jo) throws JSONException { 
		Evento evento = null;
		String id = jo.getString("id");
		String nome = jo.getString("name");
		String data = jo.getString("data");
		evento = new Evento(id, nome, data);
		return evento;
	}
}
