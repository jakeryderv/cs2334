import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;

	// added constant declarations
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;

	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}

	// added constructor
	public Song(String info) {
		String[] parts = info.split(INFO_DELIMITER);
		this.title = parts[0];
		this.artist = parts[1];
		String[] timeStringParts = parts[2].split(TIME_DELIMITER);
		this.time = new int[timeStringParts.length];
	
		for (int i = timeStringParts.length - 1, j = 0; i >= 0; i--, j++) {
			this.time[j] = Integer.parseInt(timeStringParts[i]);
		}
	}
	
	
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}


	// added toString method
	@Override
	public String toString() {
		String result = "";

		for (int i = (time.length-1); i >= 0; --i) {
			if (i != (time.length-1)) {
				result += ":";
			}
			if (time[i] < 10 && i != (time.length-1)) {
				result += "0";
			}
			result += time[i];
		}
		return title + "; " + artist + "; " + result;
	}

	


}
