package comb.pallefire.b_34notificationexam;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    EditText et1,et2,et3,et4;
    Button button,button2;
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        et1= (EditText) v.findViewById(R.id.et1);
        et2 = (EditText) v.findViewById(R.id.et2);
        et3 = (EditText) v.findViewById(R.id.et3);
        et4 = (EditText) v.findViewById(R.id.et4);
        button= (Button) v.findViewById(R.id.bt1);
        button2 = (Button) v.findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we upadte the notification
                builder.setContentTitle(et2.getText().toString());
                notificationManager.notify(1,builder.build());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step1:actual code for displaying notification
                notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                builder=new NotificationCompat.Builder(getActivity());
                builder.setSmallIcon(R.mipmap.ic_launcher);
                //here below we can pass hello or any string in setTicker
                builder.setTicker(et1.getText().toString());
                //here we set the large Icon
                //here we convert the jpg/png in bitmapImage
                BitmapDrawable bitmapDrawable= (BitmapDrawable) getResources().getDrawable(R.drawable.degree);
                //here we set the large icon
                builder.setLargeIcon(bitmapDrawable.getBitmap());
                //set the title
                builder.setContentTitle(et2.getText().toString());
                builder.setContentText(et3.getText().toString());
                builder.setContentInfo(et4.getText().toString());
                //we will write the code for starting our screen or Application when user click on the notification message
                Intent intent =new Intent(getActivity(),MainActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(getActivity(),0,intent,0);
                builder.setContentIntent(pendingIntent);
                //when user click the notification got cancelled after reading we will use setAutoCancel(true);
                builder.setAutoCancel(true);
                //here above pending intent will take 4 parameters
                //how to delete particulars notificatons or all the notification
//                notificationManager.cancel(1);
//                notificationManager.cancelAll();

                //now let us push the notification to top bar
                //here one is unique notification number..later we will use for deleting or for changes to use this ID 1
                notificationManager.notify(1,builder.build());
            }
        });
        return v;
    }

}
