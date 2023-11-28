package com.github.keyboardexception.quizapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.keyboardexception.quizapp.Objects.Comment;
import com.github.keyboardexception.quizapp.R;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

	private final Context context;
	private final List<Comment> commentList;

	public CommentAdapter(Context context, List<Comment> commentList) {
		super(context, R.layout.comment_item, commentList);
		this.context = context;
		this.commentList = commentList;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View rowView = inflater.inflate(R.layout.comment_item, parent, false);

		TextView commentAuthor = rowView.findViewById(R.id.comment_author);
		TextView commentContent = rowView.findViewById(R.id.comment_content);

		Comment comment = commentList.get(position);

		commentAuthor.setText(comment.author.name);
		commentContent.setText(comment.content);

		return rowView;
	}
}