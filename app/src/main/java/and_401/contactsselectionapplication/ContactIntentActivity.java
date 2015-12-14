package and_401.contactsselectionapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import and_401.contactsselectionapplication.Models.ContactModel;

public class ContactIntentActivity extends AppCompatActivity {

    private final int PHONE = 0;
    private final int WEBSITE = 1;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<ContactModel> contactList;
    private ListView lstViewContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_intent);
        lstViewContacts = (ListView) findViewById(R.id.lstViewContacts);
        //Creamos datos estaticos para el ejemplo
        contactList = new ArrayList<>();
        contactList.add(new ContactModel("Rodrigo", "6141913053", "www.intelectix.com"));
        contactList.add(new ContactModel("Charly", "6141568995", "www.intelectix.com"));
        contactList.add(new ContactModel("Fatboy", "6145157360", "www.intelectix.com"));
        contactList.add(new ContactModel("Alfredo", "6141235684", "www.intelectix.com"));
        contactList.add(new ContactModel("Isaac", "6141568978", "www.intelectix.com"));

        //Recorremos los datos
        List<String> nameList = new ArrayList<>();
        for (ContactModel model : contactList
                ) {
            nameList.add(model.getName());
        }
        //llenamos el adapter y lo seteamos al listView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        lstViewContacts.setAdapter(arrayAdapter);

        //Creamos el intent Explicito y llamamos a la subActivity
        lstViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactModel contactModel = contactList.get(position);
                if (contactModel == null) {
                    return;
                }
                Intent intent = new Intent(ContactIntentActivity.this, ContactPageActivity.class);
                intent.putExtra("contactObj", contactModel);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        Bundle resultData = data.getExtras();
        String value = resultData.getString("value");

        switch (resultCode) {

            case PHONE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value)));
                break;
            case WEBSITE:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + value)));
                break;
        }

    }
}
