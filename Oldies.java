import java.util.*;

public class Oldies{
	public static void main(String args[]){
		new Solver(new Scanner(System.in));
	}
}

class Solver{
	private Scanner in;
	int linesToScan;
	int totalReqs;
	
	public Solver(Scanner input){
		in = input;
		linesToScan = in.nextInt();
		in.nextLine();
		LinkedList<Song> jukebox = new LinkedList<Song>();
		//read in the current jukebox state
		for(int i = 0; i<linesToScan; i++){
			jukebox.add(new Song(in.nextLine().trim()));
			//System.out.println("initial: "+ jukebox.getLast());//debug
		}
		
		//simulate all the song requests in the file
		String currentSong;
		Song s;
		while(in.hasNext()){
			currentSong = in.nextLine().trim();
			Iterator<Song> it = jukebox.iterator();
			//advance the iterator to the song in the jukebox that matches the current request
			while(!(s = it.next()).title.equals(currentSong)){}; 
			it.remove();
			s.play();
			totalReqs++;
			jukebox.addFirst(s);
			//System.out.println("request: "+ song);//debug
		}
		
		//print each song
		Song song_i;
		int l = jukebox.size();
		for(int i = 0;i<l;i++){
			song_i = jukebox.removeFirst();
			System.out.println(outputLine(song_i.title, song_i.plays));
		}
		char[] dashes = new char[55];
		Arrays.fill(dashes, '-');
		String dashedLine = new String(dashes);
		System.out.println(dashedLine);
		System.out.println(outputLine("TOTAL",totalReqs));		
	}
	
	//format an output entry given a song and a number of plays.
	public static String outputLine(String s, int i){
		char[] spaces = new char[55-s.length()-Integer.toString(i).length()];
		Arrays.fill(spaces, ' ');
		return s + new String(spaces) + i;
	}
	
	
}

//making a song class allows us to keep the number of plays associated with the song
class Song{
	public String title;
	public int plays;
	
	public Song(String title){
		this.title = title;
		this.plays = 0;
	}
	
	public void play(){
	  plays++;	
	}
	
	public boolean equals(Song other){
		return this.title.equals(other.title);
	}
}