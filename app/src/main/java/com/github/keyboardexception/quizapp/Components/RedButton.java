package com.github.keyboardexception.quizapp.Components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.keyboardexception.quizapp.R;

public class RedButton extends GreenButton {
	public RedButton(@NonNull Context context) {
		super(context);
	}

	public RedButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public RedButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected int getEnabledBackground() {
		return R.drawable.button_red_enabled;
	}

	@Override
	protected int getActiveBackground() {
		return R.drawable.button_red_active;
	}
}
