package com.github.keyboardexception.quizapp.Components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.R;

public class GreenButton extends BaseButton {
	public GreenButton(@NonNull Context context) {
		super(context);
	}

	public GreenButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public GreenButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected int getEnabledBackground() {
		return R.drawable.button_green_enabled;
	}

	@Override
	protected int getActiveBackground() {
		return R.drawable.button_green_active;
	}
}
