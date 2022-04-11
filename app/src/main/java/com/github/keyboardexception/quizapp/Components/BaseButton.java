package com.github.keyboardexception.quizapp.Components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.github.keyboardexception.quizapp.R;

public class BaseButton extends AppCompatButton {
	public BaseButton(@NonNull Context context) {
		super(context, null, R.style.Button);
		init();
	}

	public BaseButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs, R.style.Button);
		init();
	}

	public BaseButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	protected int getEnabledBackground() {
		return R.drawable.button_base_enabled;
	}

	protected int getActiveBackground() {
		return R.drawable.button_base_active;
	}

	protected void init() {
		setTextSize(18);
		setTextColor(ContextCompat.getColor(getContext(), R.color.white));
		updateColor();
	}

	protected void updateColor() {
		setBackgroundResource(isPressed()
			? getActiveBackground()
			: getEnabledBackground());
	}

	@Override
	public void setPressed(boolean pressed) {
		if (pressed != isPressed()) {
			super.setPressed(pressed);
			updateColor();
		}
	}
}
