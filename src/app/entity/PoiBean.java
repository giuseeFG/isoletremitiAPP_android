package app.entity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiBean implements Parcelable{
	private int id;
	private String name;
	private String description;
	private String sitoWeb;
	private String telefono;
	private String email;
	private String type;
	private String descrizione;
	private double lat, lng;
	
	public PoiBean(Parcel source){
		readFromParcel(source);
	}
	public void readFromParcel(Parcel source){
		id = source.readInt();
		name = source.readString();
		description = source.readString();
		sitoWeb = source.readString();
		telefono = source.readString();
		email = source.readString();
		type = source.readString();
		lat = source.readDouble();
		lng = source.readDouble();
		descrizione = source.readString();
		
	}
	public PoiBean(int id, String name, String desc, String type,  double lat, double lng, String sitoWeb, String telefono, String email, String descrizione) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.type = type;
		this.sitoWeb = sitoWeb;
		this.setTelefono(telefono);
		this.setEmail(email);
		this.lat = lat;
		this.lng = lng;
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}

	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", this.id);
		object.put("name", this.name);
		object.put("description", this.description);
		object.put("type", this.type);
		object.put("web", this.sitoWeb);
		object.put("tel", this.telefono);
		object.put("email", this.email);
		object.put("lat", this.lat);
		object.put("lng", this.lng);
		object.put("descrizione", this.descrizione);
		return object;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public static final Parcelable.Creator<PoiBean> CREATOR = new Parcelable.Creator<PoiBean>(){

		@Override
		public PoiBean createFromParcel(Parcel source) {
			return new PoiBean(source);
		}

		@Override
		public PoiBean[] newArray(int size) {
			return new PoiBean[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(sitoWeb);
		dest.writeString(telefono);
		dest.writeString(email);
		dest.writeString(type);
		dest.writeDouble(lat);
		dest.writeDouble(lng);
		dest.writeString(descrizione);
	}
}