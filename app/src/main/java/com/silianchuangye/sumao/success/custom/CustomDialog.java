package com.silianchuangye.sumao.success.custom;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;


/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        private Context context;
        private String title;
        private  String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setContentView(View v)
        {
            this.contentView = v;
            return this;
        }
        public Builder setPositiveButton(String positiveButtonText ,
                                         OnClickListener listener)
        {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
        public Builder setNegativeButton(String negativeButtonText,OnClickListener listener)
        {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create()
        {
            LayoutInflater inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.custom_dialog,null);
            dialog.addContentView(layout,new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.title_tv)).setText(title);
            if(positiveButtonText!=null)
            {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener!=null)
                {
                    ((Button) layout.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }else {
                    layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
                }
            if (negativeButtonText!=null)
                {
                    ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                    if (negativeButtonClickListener!=null)
                    {
                        ((Button) layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                negativeButtonClickListener.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
                    }
                }else {
                    layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                }
        dialog.setContentView(layout);

        return dialog;
        }
    }
}
