package ca.sfu.Navy.walkinggroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.sfu.Navy.walkinggroup.adapter.MessageListAdapter;
import ca.sfu.Navy.walkinggroup.model.Message;
import ca.sfu.Navy.walkinggroup.model.SavedSharedPreference;
import ca.sfu.Navy.walkinggroup.model.SendMessage;
import ca.sfu.Navy.walkinggroup.model.ServerProxy;
import ca.sfu.Navy.walkinggroup.model.ServerProxyBuilder;
import retrofit2.Call;

public class UserSendMsgActivity extends AppCompatActivity {

    private EditText mInputText;
    private Button mSendBtn;
    private ServerProxy mProxy;
    private boolean isEmergency;
    private CheckBox emergencyBtn;
    private ListView mMessageList;
    private MessageListAdapter mMessageListAdapter;
    private long user_id;
    private long mId;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_layout);
        init();
    }

    private void init() {
        mId = SavedSharedPreference.getPrefUserId(this);
        user_id = getIntent().getLongExtra("user_id", -1);
        String token = SavedSharedPreference.getPrefUserToken(UserSendMsgActivity.this);
        mProxy = ServerProxyBuilder.getProxy(getString(R.string.apikey), token);

        mInputText = findViewById(R.id.input_text);
        mSendBtn = findViewById(R.id.send_btn);
        emergencyBtn = findViewById(R.id.emergency_btn);
        mMessageList = findViewById(R.id.message_list);
        mMessageListAdapter = new MessageListAdapter(this);
        mMessageList.setAdapter(mMessageListAdapter);
        emergencyBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isEmergency = isChecked;
                getUserList();
            }
        });
        getUserList();
    }

    public void sendMsg(View view) {
        String message = mInputText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(this, "The message should not be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user_id != -1) {
            // Make call
            SendMessage msg = new SendMessage();
            msg.setText(message);
            msg.setEmergency(isEmergency);
            Call<Message> caller = mProxy.messageToParents(user_id, msg);
            ServerProxyBuilder.callProxy(UserSendMsgActivity.this, caller, this::response);
        }
    }

    private void response(Message message) {
        mInputText.setText("");
        getUserList();
    }

    public void getUserList() {
        Log.e("test", isEmergency + "");
        Call<List<Message>> caller = mProxy.listUserMessage(mId, isEmergency);
        ServerProxyBuilder.callProxy(UserSendMsgActivity.this, caller, this::showMessage);
    }

    private void showMessage(List<Message> messages) {
        this.messages = new ArrayList<>();
        for (Message message:messages){
            if (message.getFromUser().getId()==user_id){
                this.messages.add(message);
            }
        }
        mMessageListAdapter.updateData(this.messages);
    }

    public void refresh(View view) {
        getUserList();
    }
}
