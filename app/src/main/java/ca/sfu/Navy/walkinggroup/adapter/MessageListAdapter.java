package ca.sfu.Navy.walkinggroup.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.sfu.Navy.walkinggroup.GroupSendMsgActivity;
import ca.sfu.Navy.walkinggroup.R;
import ca.sfu.Navy.walkinggroup.model.Group;
import ca.sfu.Navy.walkinggroup.model.HandleMsgStatusListener;
import ca.sfu.Navy.walkinggroup.model.Message;
import ca.sfu.Navy.walkinggroup.model.SavedSharedPreference;

public class MessageListAdapter extends BaseAdapter {
    private List<Message> messages;
    private LayoutInflater mInflater;
    private Context context;
    private HandleMsgStatusListener listener;

    public void setListener(HandleMsgStatusListener listener) {
        this.listener = listener;
    }

    public MessageListAdapter(Context context) {
        this.messages = new ArrayList<>();
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void updateData(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_message_item, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.mUserIdText = convertView.findViewById(R.id.user_text);
            viewHolder.mMessageText = convertView.findViewById(R.id.message_text);
            viewHolder.mTimeText = convertView.findViewById(R.id.time_text);
            viewHolder.mReadBtn = convertView.findViewById(R.id.msg_read);
            viewHolder.mUnreadBtn = convertView.findViewById(R.id.msg_unread);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Message message = messages.get(position);
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        viewHolder.mTimeText.setText(sdf.format(message.getTimestamp()));
        viewHolder.mMessageText.setText(message.getText());
        viewHolder.mUserIdText.setText(message.getFromUser().getId() + "");
        viewHolder.mReadBtn.setOnClickListener(v -> {
            if (listener != null)
                listener.read(message.getId(), SavedSharedPreference.getPreUserId(context), true);
        });
        viewHolder.mUnreadBtn.setOnClickListener(v -> {
            if (listener != null)
                listener.read(message.getId(), SavedSharedPreference.getPreUserId(context), false);
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mUserIdText;
        private TextView mTimeText;
        private TextView mMessageText;
        private Button mReadBtn;
        private Button mUnreadBtn;
    }
}
