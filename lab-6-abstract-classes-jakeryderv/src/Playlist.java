import java.io.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Playlist {
    private ArrayList<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public Playlist(String filename) {
        this();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                songs.add(new Song(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumSongs() {
        return songs.size();
    }

    public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}

    public Song[] getSongs() {
        return songs.toArray(new Song[0]);
    }

    public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}

    public boolean addSong(int index, Song song) {
        if (index < 0 || index > songs.size() || song == null) {
            return false;
        }
        songs.add(index, song);
        return true;
    }

    public int addSongs(Playlist playlist) {
        if (playlist == null) {
            return 0;
        }
        int count = 0;
        for (Song song : playlist.getSongs()) {
            if (addSong(song)) {
                count++;
            }
        }
        return count;
    }

    public int addSongs(String filename) {
        Playlist playlist = new Playlist(filename);
        return addSongs(playlist);
    }

    public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}

    public Song removeSong(int index) {
        if (index >= 0 && index < songs.size()) {
            return songs.remove(index);
        }
        return null;
    }

    public void saveSongs(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Song song : songs) {
                writer.println(song.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Song song : songs) {
            joiner.add(song.toString());
        }
        return joiner.toString();
    }

    public int[] getTotalTime() {
        int totalSeconds = 0;
        for (Song song : songs) {
            int[] time = song.getTime();
            int seconds = time.length > 0 ? time[0] : 0;
            int minutes = time.length > 1 ? time[1] : 0;
            int hours = time.length > 2 ? time[2] : 0;
            
            totalSeconds += seconds + minutes * 60 + hours * 3600;
        }
    
        if (totalSeconds >= 3600) {
            return new int[] {totalSeconds % 60, (totalSeconds / 60) % 60, totalSeconds / 3600};
        } else if (totalSeconds >= 60) {
            return new int[] {totalSeconds % 60, totalSeconds / 60};
        } else {
            return new int[] {totalSeconds};
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
}
