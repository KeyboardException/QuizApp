package com.github.keyboardexception.quizapp.Components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.keyboardexception.quizapp.R;

public class DynamicButton extends BaseButton {
	public DynamicButton(@NonNull Context context) {
		super(context);
	}

	public DynamicButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public DynamicButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected String color;

	public void setColor(String color) {
		this.color = color;
		updateColor();
	}

	@Override
	protected int getEnabledBackground() {
		if (color != null) {
			switch (color) {
				case "green":
					return R.drawable.button_green_enabled;

				case "blue":
					return R.drawable.button_blue_enabled;

				case "red":
					return R.drawable.button_red_enabled;
			}
		}

		return R.drawable.button_base_enabled;
	}

	@Override
	protected int getActiveBackground() {
		switch (color) {
			case "green":
				return R.drawable.button_green_active;

			case "blue":
				return R.drawable.button_blue_active;

			case "red":
				return R.drawable.button_red_active;
		}

		return R.drawable.button_base_active;
	}
}
