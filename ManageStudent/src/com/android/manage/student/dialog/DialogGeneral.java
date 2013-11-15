package com.android.manage.student.dialog;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;

public class DialogGeneral extends Dialog {

	private TextView dialog_general_textmsg;
	private Button dialog_general_btnok;

	public DialogGeneral(SlidingSherlockFragmentActivity shl, String msg) {
		super(shl);
		this.setCancelable(true);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_general);

		dialog_general_textmsg = (TextView) findViewById(R.id.dialog_general_textmsg);
		dialog_general_textmsg.setText(msg);
		dialog_general_btnok = (Button) findViewById(R.id.dialog_general_btnok);
		dialog_general_btnok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
}
