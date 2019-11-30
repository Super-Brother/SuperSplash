package com.wenchao.supersplash;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ParallaxLayoutInflater extends LayoutInflater {

    private ParallaxFragment fragment;

    public ParallaxLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    public ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext, fragment);
    }

    class ParallaxFactory implements Factory2 {

        private final String[] sClassPrefix = {
                "android.widget.",
                "android.view."
        };
        int[] attrIds = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out};

        private LayoutInflater inflater;

        public ParallaxFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Nullable
        @Override
        public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context,
                                 @NonNull AttributeSet attrs) {
            View view = createMyView(name, context, attrs);
            if (view != null) {
                TypedArray a = context.obtainStyledAttributes(attrs, attrIds);
                if (a.length() > 0) {
                    //获取自定义属性的值
                    ParallaxViewTag tag = new ParallaxViewTag();
                    tag.alphaIn = a.getFloat(0, 0f);
                    tag.alphaOut = a.getFloat(1, 0f);
                    tag.xIn = a.getFloat(2, 0f);
                    tag.xOut = a.getFloat(3, 0f);
                    tag.yIn = a.getFloat(4, 0f);
                    tag.yOut = a.getFloat(5, 0f);
                    view.setTag(R.id.parallax_view_tag, tag);
                }
                fragment.getParallaxViews().add(view);
                a.recycle();
            }
            return view;
        }

        /**
         * @param name    系统控件/自定义控件
         * @param context
         * @param attrs
         * @return
         */
        private View createMyView(String name, Context context, AttributeSet attrs) {
            if (name.contains(".")) {
                return reflectView(name, null, context, attrs);
            } else {
                for (String prefix : sClassPrefix) {
                    View view = reflectView(name, prefix, context, attrs);
                    if (view != null) {
                        return view;
                    }
                }
            }
            return null;
        }

        private View reflectView(String name, String prefix, Context context, AttributeSet attrs) {
            try {
                return inflater.createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            return null;
        }
    }

}
