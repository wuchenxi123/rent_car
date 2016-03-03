package com.atayun.hgs.wuliu.utils;

import java.text.DecimalFormat;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class FloatEditor extends PropertiesEditor {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals("")) {
			text = "0";
		} else {
			DecimalFormat fnum = new DecimalFormat("##0.0");
			text = fnum.format(Float.valueOf(text));
		}
		setValue(Float.parseFloat(text));
	}

	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
