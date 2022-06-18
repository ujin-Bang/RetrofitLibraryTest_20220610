package com.start.retrofitlibrarytest_20220610.adpaters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainRecylerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    2가지 ViewHolder가 필요함. => 0번칸: 상단(Header)부 xml / 나머지(Item)칸 : 리뷰 모양 xml

    inner class HeaderViewHolder(row: View): RecyclerView.ViewHolder(row){

    }

    inner class ItemViewHolder(row: View): RecyclerView.ViewHolder(row){

    }
}