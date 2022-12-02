// Credit: https://gist.github.com/pbloem/d29bf80e69d333415622

package sfx;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

/**
 * A little example showing how to play a tune in Java.
 * 
 * Inputs are not sanitized or checked, this is just to show how simple it is.
 * 
 * @author Peter
 */
public class Sound {
	
	private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private MidiChannel[] channels;
	private int INSTRUMENT = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private int VOLUME = 80; // between 0 et 127
	private Synthesizer synth;
	
	public Sound() {
		try {
			// * Open a synthesizer
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	public void resumeSynth() {
		try {
			this.synth.open();
		} catch (MidiUnavailableException e) {
			throw new RuntimeException(e);
		}
	}

	public void closeSynth() {
		this.synth.close();
	}
	
	/**
	 * Plays the given note for the given duration
	 */
	public void play(String note, int duration) throws InterruptedException
	{
			// * start playing a note
			channels[INSTRUMENT].noteOn(id(note), VOLUME );
			// * wait
			Thread.sleep( duration );
			// * stop playing a note
			channels[INSTRUMENT].noteOff(id(note));
	}

	/**
	 * Plays the given tone for the given duration
	 */
	public void play(int tone, int duration) throws InterruptedException
	{
			// * start playing a note
			channels[INSTRUMENT].noteOn(tone, VOLUME );
			// * wait
			Thread.sleep( duration );
			// * stop playing a note
			channels[INSTRUMENT].noteOff(tone);
	}
	
	/**
	 * Plays nothing for the given duration
	 */
	public void rest(int duration) throws InterruptedException
	{
		Thread.sleep(duration);
	}
	
	/**
	 * Returns the MIDI id for a given note: eg. 4C -> 60
	 * @return
	 */
	public int id(String note)
	{
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12;	
	}
}