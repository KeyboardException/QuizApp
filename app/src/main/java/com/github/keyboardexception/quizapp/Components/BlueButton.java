package com.github.keyboardexception.quizapp.Components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.R;

public class BlueButton extends GreenButton {
	public BlueButton(@NonNull Context context) {
		super(context);
	}

	public BlueButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public BlueButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected int getEnabledBackground() {
		return R.drawable.button_blue_enabled;
	}

	@Override
	protected int getActiveBackground() {
		return R.drawable.button_blue_active;
	}
}
