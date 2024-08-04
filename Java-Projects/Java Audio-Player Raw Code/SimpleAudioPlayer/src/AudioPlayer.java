import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Scanner;

public class AudioPlayer {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			File file = new File("Past-Life-JVNA.wav");

			try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					Clip clip = AudioSystem.getClip()) {

				clip.open(audioStream);

				System.out.println("P = play, S = stop, R = reset, Q = quit");
				String response = "";

				while (!response.equals("Q")) {
					System.out.print("Enter your choice: ");
					response = sc.next().toUpperCase();

					switch (response) {
					case "P":
						clip.start();
						break;
					case "S":
						clip.stop();
						break;
					case "R":
						clip.setMicrosecondPosition(0);
						break;
					case "Q":
						clip.close();
						break;
					default:
						System.out.println("Invalid input");
					}
				}

				System.out.println("Playback ended.");
				
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
}