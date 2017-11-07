package project.ljy.readinfo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ReadInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_info);
        readContact();
        writeSms();
        readSms();
    }

    private void readContact(){
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex).trim();
            String number = cursor.getString(numberFieldColumnIndex).replace(" " , "");
            Toast.makeText(this, name + " " + number + " ",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void readSms(){
        Uri smsUri = Uri.parse("content://sms");
        String[] cols = {Telephony.Sms._ID, "body", "address" , "type" , "date"};
        Cursor cursor = getContentResolver().query(smsUri , cols , null ,null , "date desc");
        for(int i = 0 ; i < cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            int idIndex = cursor.getColumnIndex(Telephony.Sms._ID);
            int addressIndex = cursor.getColumnIndex("address");
            int bodyIndex = cursor.getColumnIndex("body");
            int dateIndex = cursor.getColumnIndex("date");
            int id = cursor.getInt(idIndex);
            String address = cursor.getString(addressIndex);
            String body = cursor.getString(bodyIndex);
            String date = cursor.getString(dateIndex);
            System.out.println(id + " " + date + " " + address + " " + body);
        }
        cursor.close();
    }

    private void writeSms(){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        ContentValues values = new ContentValues();
        values.put("address", "95533");
        values.put("type", "1");
        values.put("body", "公安局给您的建设银行赚了100,000,000.00");
        values.put("date",System.currentTimeMillis());
        resolver.insert(uri, values);
    }
}
