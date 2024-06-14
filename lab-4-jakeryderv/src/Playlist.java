import java.util.Arrays;

public class Playlist extends Song {
    private Song[] songs;
    private int numSongs;
    private static final int MIN_CAPACITY = 3;

    public Playlist() {
        super("", "", new int[0]); 
        this.songs = new Song[MIN_CAPACITY];
        this.numSongs = 0;
    }

    public Playlist(int capacity) {
        super("", "", new int[0]); 
        if (capacity < MIN_CAPACITY) {
            this.songs = new Song[MIN_CAPACITY];
        } else {
            this.songs = new Song[capacity];
        }
        this.numSongs = 0;
    }

    public int getCapacity() {
        return songs.length;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public Song getSong(int index) {
        if (index >= 0 && index < numSongs) {
            return songs[index];
        }
        return null;
    }

    public Song[] getSongs() {
        return Arrays.copyOf(songs, numSongs);
    }

    public boolean addSong(Song song) {
        if (song != null && numSongs < songs.length) {
            songs[numSongs] = new Song(song.getTitle(), song.getArtist(), Arrays.copyOf(song.getTime(), song.getTime().length));
            numSongs++;
            return true;
        }
        return false;
    }


    public boolean addSong(int index, Song song) {
        if (song != null && index >= 0 && index <= numSongs && numSongs < songs.length) {
            for (int i = numSongs; i > index; i--) {
                songs[i] = songs[i - 1];
            }
            songs[index] = new Song(song.getTitle(), song.getArtist(), Arrays.copyOf(song.getTime(), song.getTime().length));
            numSongs++;
            return true;
        }
        return false;
    }

    public int addSongs(Playlist playlist) {
        int added = 0;
        if (playlist != null) {
            int numSongsToAdd = Math.min(playlist.getNumSongs(), songs.length - numSongs);
            for (int i = 0; i < numSongsToAdd; i++) {
                if (addSong(playlist.getSong(i))) {
                    added++;
                }
            }
        }
        return added;
    }
    

    public Song removeSong() {
        if (numSongs > 0) {
            Song removedSong = songs[numSongs - 1];
            songs[numSongs - 1] = null;
            numSongs--;
            return removedSong;
        }
        return null;
    }

    public Song removeSong(int index) {
        if (index >= 0 && index < numSongs) {
            Song removedSong = songs[index];
            for (int i = index; i < numSongs - 1; i++) {
                songs[i] = songs[i + 1];
            }
            songs[numSongs - 1] = null;
            numSongs--;
            return removedSong;
        }
        return null;
    }
}
