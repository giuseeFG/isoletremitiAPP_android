package app.separators;

public class EntryItem implements Item{

	public final String title;
	public String subtitle;

	public EntryItem(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	public EntryItem(String title) {
		this.title = title;
	}
	
	@Override
	public boolean isSection() {
		return false;
	}

}
