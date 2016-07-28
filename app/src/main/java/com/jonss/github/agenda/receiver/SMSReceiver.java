package com.jonss.github.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.jonss.github.agenda.R;
import com.jonss.github.agenda.dao.AlunoDao;

import java.io.Serializable;

/**
 * Created by joao on 28/07/16.
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String format = (String) intent.getSerializableExtra("format");

        SmsMessage smsMessage = SmsMessage.createFromPdu(pdu, format);

        String telefone = smsMessage.getDisplayOriginatingAddress();

        AlunoDao alunoDao = new AlunoDao(context);

        if(alunoDao.ehAluno(telefone)) {
            Toast.makeText(context, "SMS recebido", Toast.LENGTH_SHORT ).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
            mediaPlayer.start();
        }
        alunoDao.close();
    }
}
