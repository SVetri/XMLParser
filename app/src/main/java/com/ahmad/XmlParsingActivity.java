package com.ahmad;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlParsingActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView listView;
    private Button btn;
    ParsingClass pc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.listview);
		bindDataToListing();

        btn = (Button) findViewById(R.id.additembtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(XmlParsingActivity.this);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Enter new student details");

                final EditText n = (EditText) dialog.findViewById(R.id.name);
                final EditText a = (EditText) dialog.findViewById(R.id.address);
                final EditText q = (EditText) dialog.findViewById(R.id.qua);

                Button dialogButton = (Button) dialog.findViewById(R.id.okbutton);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name1 = n.getText().toString();
                        String address1 = a.getText().toString();
                        String qua1 = q.getText().toString();

                        pc.name.add(name1);
                        pc.address.add(address1);
                        pc.qua.add(qua1);
                        BindingData bindingData1= new BindingData(XmlParsingActivity.this, pc.name,
                                pc.address, pc.qua);
                        listView.setAdapter(bindingData1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
	}

	private void bindDataToListing() {
		try {
			SAXParserFactory saxparser = SAXParserFactory.newInstance();
			SAXParser parser = saxparser.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			pc = new ParsingClass();
			xmlReader.setContentHandler(pc);
			InputStream is = getAssets().open("xmldocument.xml");
			xmlReader.parse(new InputSource(is));
			BindingData bindingData = new BindingData(this, pc.name,
					pc.address, pc.qua);
			listView.setAdapter(bindingData);

		} catch (Exception e) {
			e.getMessage();
		}
	}
}