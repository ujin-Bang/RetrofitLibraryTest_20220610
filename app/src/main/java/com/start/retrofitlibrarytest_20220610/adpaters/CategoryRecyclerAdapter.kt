package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.SmallCategoryData

class CategoryRecyclerAdapter(
    val mContext: Context,
    val mList: List<SmallCategoryData>
): RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHoler>() {

//    클래스 내부에 또다른 클래스(inner class)
    inner class CategoryViewHoler(view: View): RecyclerView.ViewHolder(view){

//        이 내부의 용도 : xml -> UI찾아내서 -> 데이터 반영기능.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHoler {

        val row = LayoutInflater.from(mContext).inflate(R.layout.category_list_item,parent, false)
        return CategoryViewHoler(row)
    }

    override fun onBindViewHolder(holder: CategoryViewHoler, position: Int) {


    }

    override fun getItemCount() = mList.size
}