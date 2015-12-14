package and_401.contactsselectionapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import and_401.contactsselectionapplication.Models.ContactModel;

public class ContactPageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtName, txtPhone, txtWebsite;
    private final int PHONE = 0;
    private final int WEBSITE = 1;
    private ContactModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtWebsite = (TextView) findViewById(R.id.txtWebsite);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        model = (ContactModel) getIntent().getSerializableExtra("contactObj");
        txtName.setText(model.getName());
        txtPhone.setText(model.getPhone());
        txtWebsite.setText(model.getWebsite());

        txtPhone.setOnClickListener(this);
        txtWebsite.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txtPhone:
                Intent intent = new Intent();
                intent.putExtra("value", model.getPhone());
                setResult(PHONE, intent);
                finish();
                break;
            case R.id.txtWebsite:
                intent = new Intent();
                intent.putExtra("value", model.getWebsite());
                setResult(WEBSITE, intent);
                finish();
                break;
        }
    }
}
