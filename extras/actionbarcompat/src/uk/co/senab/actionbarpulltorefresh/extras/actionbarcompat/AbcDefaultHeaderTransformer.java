/*
 * Copyright 2013 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;

import uk.co.senab.actionbarpulltorefresh.library.DefaultHeaderTransformer;

public class AbcDefaultHeaderTransformer extends DefaultHeaderTransformer {

    @Override
    protected Drawable getActionBarBackground(Context context) {
        // Super handles ICS+ anyway...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return super.getActionBarBackground(context);
        }

        // Need to get resource id of style pointed to from actionBarStyle
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarStyle, outValue, true);
        // Now get action bar style values...
        TypedArray abStyle = context.getTheme().obtainStyledAttributes(outValue.resourceId,
                R.styleable.ActionBar);
        try {
            return abStyle.getDrawable(R.styleable.ActionBar_background);
        } finally {
            abStyle.recycle();
        }
    }

    @Override
    protected int getActionBarSize(Context context) {
        // Super handles ICS+ anyway...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return super.getActionBarSize(context);
        }

        int[] attrs = { R.attr.actionBarSize };
        TypedArray values = context.getTheme().obtainStyledAttributes(attrs);
        try {
            return values.getDimensionPixelSize(0, 0);
        } finally {
            values.recycle();
        }
    }

    @Override
    protected int getMinimumApiLevel() {
        return Build.VERSION_CODES.ECLAIR_MR1;
    }
}
