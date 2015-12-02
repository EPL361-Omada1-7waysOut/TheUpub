package com.example.georg.theupub;
/*
*Copyright (c) <2015> <7WaysOut>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:



The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.



THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
* */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Monkey D Luffy on 19/11/2015.
 */
public class MyAdapter extends ArrayAdapter<String>
{
    //setting adapter to have the components
    //of the view
    Context context;
    int    []images;
    String []titles;
    String []descr;

    MyAdapter(Context c,String []titles,int []images,String []Descr){
        super(c, R.layout.single_row,R.id.textView,titles);
        this.context=c;
        this.images=images;
        this.descr=Descr;
        this.titles=titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        //to be optimal
        //when an object  from is destroid should be return
        //to be the next to put into the data
        if(row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            //row is taking the relative view of singlerow
        }
        //these object are set to take id in the view
        ImageView MyImage=(ImageView) row.findViewById(R.id.imageView);
        TextView MyTitle=(TextView) row.findViewById(R.id.textView);
        TextView MyDescription=(TextView) row.findViewById(R.id.textView2);

        //these object are setting the relative details of their position
        MyImage.setImageResource(images[position]);
        MyTitle.setText(titles[position]);
        MyDescription.setText(descr[position]);

        return row;
    }
}