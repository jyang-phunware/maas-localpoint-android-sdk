package com.example.android.actionbarcompat;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digby.localpoint.sdk.core.impl.LPAttributeValue;
import com.digby.localpoint.sdk.core.impl.LPLocalpointService;
import com.digby.localpoint.sdk.core.profile.ILPAttributeManager;
import com.digby.localpoint.sdk.core.profile.ILPAttributeValue;

public class LPAttributeActivity extends Activity {
	private EditText attributesEditText;

	private void updateAttributes() {
		String attributes = attributesEditText.getText().toString().trim();
		ILPAttributeManager manager = LPLocalpointService
				.getInstance(getApplicationContext()).getUser()
				.getAttributeManager();
		Map<String, ILPAttributeValue<?>> attrMap = new HashMap<String, ILPAttributeValue<?>>();
		for (String item : attributes.split(";")) {
			String[] pair = item.split("=");
			if (null != pair && pair.length == 2) {
				String key = pair[0].trim();
				String value = pair[1].trim();
				attrMap.put(key, new LPAttributeValue<Object>(value));
			}
		}
		if (attrMap.size() > 0) {
			manager.updateProfileAttributes(attrMap);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lpattribute);
		attributesEditText = (EditText) findViewById(R.id.attributes_value);
		final Button updateButton = (Button) findViewById(R.id.button_send_attributes);
		updateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				updateAttributes();
			}
		});
	}



}
