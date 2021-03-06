package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.ReviewData

class MainRecylerAdapter(val mContext:Context, val mList: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    2가지 ViewHolder가 필요함. => 0번칸: 상단(Header)부 xml / 나머지(Item)칸 : 리뷰 모양 xml

    inner class HeaderViewHolder(row: View): RecyclerView.ViewHolder(row){

        val imgCategory1 = row.findViewById<ImageView>(R.id.imgCategory1)

        fun bind(){

            imgCategory1.setOnClickListener {
                Toast.makeText(mContext, "1번 카테고리 눌림", Toast.LENGTH_SHORT).show()
            }
        }

    }

    inner class ItemViewHolder(row: View): RecyclerView.ViewHolder(row){

        val txtReviewProductName = row.findViewById<TextView>(R.id.txtReviewProductName)
        val txtReviewerName = row.findViewById<TextView>(R.id.txtReviewerName)
        val imgProductImg = row.findViewById<ImageView>(R.id.imgProductImg)

        fun bind(data: ReviewData){

            txtReviewProductName.text = data.product.name
            txtReviewerName.text = data.user.nickname

        }

    }

//    position별로 어떤 모양이 나가야 하는지(viewType이 어떻게 되는지) 알려줄 함수

    val HEADER_VIEW_TYPE = 1000
    val REVIEW_ITEM_TYPE = 1001
    override fun getItemViewType(position: Int): Int {

//        position 0: 맨 윗 칸 => 상단 뷰
//        position 그 외: 목록 표시. => 리뷰 아이템

        return when(position){

            0 -> HEADER_VIEW_TYPE
            else -> REVIEW_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {

            HEADER_VIEW_TYPE ->{
                val row = LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item_top_view, parent, false)
                HeaderViewHolder(row)
            }
            else -> {
//                리뷰 아이템

                val row = LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item_review_item, parent, false)
                ItemViewHolder(row)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when( holder ){
            is HeaderViewHolder -> {

                holder.bind()

            }
            is ItemViewHolder -> {
//                리뷰아이템 바인딩
                holder.bind(mList[position-1])
            }
        }

    }

    override fun getItemCount() = mList.size + 1
}