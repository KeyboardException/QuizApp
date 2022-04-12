package com.github.keyboardexception.quizapp;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.util.Log;

import java.util.Random;

public class Sounds {
	public static int[] CORRECT = new int[] {
		R.raw.correct1,
		R.raw.correct2,
		R.raw.correct3,
		R.raw.correct4,
		R.raw.correct5,
		R.raw.correct6
	};

	public static int[] WRONG = new int[] {
		R.raw.wrong1,
		R.raw.wrong2,
		R.raw.wrong3,
		R.raw.wrong4,
		R.raw.wrong5,
		R.raw.wrong6,
		R.raw.wrong7
	};

	protected static MediaPlayer player = null;
	protected static MediaPlayer background = null;

	public static void startBGM() {
		if (background != null && background.isPlaying())
			stopBGM();

		background = MediaPlayer.create(Main.context, R.raw.bgm);
		background.start();
	}

	public static void startBGMOut() {
		if (background != null && background.isPlaying())
			stopBGM();

		background = MediaPlayer.create(Main.context, R.raw.bgmout);
		background.start();
	}

	public static void stopBGM() {
		if (background != null)
			background.stop();

		background = null;
	}

	public static void correct() {
		play(getRandom(CORRECT));
	}

	public static void wrong() {
		play(getRandom(WRONG));
	}

	public static int getRandom(int[] sounds) {
		return sounds[new Random().nextInt(sounds.length)];
	}

	public static void play(int id) {
		if (player != null && player.isPlaying())
			stop();

		player = MediaPlayer.create(Main.context, id);

		if (player == null) {
			Log.e("sounds", String.format("Failed initializing sound id %d for playing", id));
			return;
		}

		player.start();
	}

	public static void stop() {
		if (player != null)
			player.stop();

		player = null;
	}
}
