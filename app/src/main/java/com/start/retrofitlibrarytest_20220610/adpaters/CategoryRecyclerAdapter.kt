package com.start.retrofitlibrarytest_20220610.adpaters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoryRecyclerAdapter: RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHoler>() {

//    클래스 내부에 또다른 클래스(inner class)
    inner class CategoryViewHoler(view: View): RecyclerView.ViewHolder(view){

    }
}