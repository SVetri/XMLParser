package com.ahmad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BindingData extends BaseAdapter {
	ArrayList<String> name;
	ArrayList<String> address;
	ArrayList<String> qua;
	LayoutInflater inflater;

	public BindingData() {

	}

	public BindingData(Activity act, ArrayList<String> name,
			ArrayList<String> add, ArrayList<String> qua) {
		this.name = name;
		this.address = add;
		this.qua = qua;
		inflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return name.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.listrow, null);
			holder.txtName = (TextView) convertView.findViewById(R.id.name);
			holder.txtAddress = (TextView) convertView
					.findViewById(R.id.address);
			holder.txtQua = (TextView) convertView.findViewById(R.id.quali);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.txtName.setText(Html.fromHtml("" + name.get(position)));
		holder.txtAddress.setText(Html.fromHtml("<b>Address : </b>"
				+ address.get(position)));
		holder.txtQua.setText(Html.fromHtml("<b>Qualification : </b>"
				+ qua.get(position)));

		return convertView;
	}

	private class Holder {
		TextView txtName, txtAddress, txtQua;
	}
}
