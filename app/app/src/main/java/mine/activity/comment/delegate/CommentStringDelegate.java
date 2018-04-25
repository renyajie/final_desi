package mine.activity.comment.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/10.
 */

public class CommentStringDelegate extends SuperDelegate{

    private Context context;
    private LayoutInflater layoutInflater;
    private ChangeCommentString changeCommentString;
    private SubmitClick submitClick;
    private CommentStringViewHolder commentStringViewHolder;

    public CommentStringDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface ChangeCommentString {
        void changeCommentString(String comment);
    }

    public interface SubmitClick {
        void submitComment();
    }

    public void setSubmitClick(SubmitClick submitClick) {
        this.submitClick = submitClick;
    }

    public void setChangeCommentString(ChangeCommentString changeCommentString) {
        this.changeCommentString = changeCommentString;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.COMMENT_STRING;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CommentStringViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_comment_order_string, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {

        Log.d("recycler", "bind RatingDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        commentStringViewHolder = ((CommentStringViewHolder)viewHolder);
        commentStringViewHolder.commentString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeCommentString.changeCommentString(
                        s.toString());
            }
        });
        commentStringViewHolder.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitClick.submitComment();
            }
        });
    }

    public static class CommentStringViewHolder extends RecyclerView.ViewHolder {

        EditText commentString;
        Button submitButton;

        public CommentStringViewHolder(View itemView) {
            super(itemView);
            commentString = itemView.findViewById(R.id.comment_string);
            submitButton = itemView.findViewById(R.id.submit_button);
        }
    }
}
