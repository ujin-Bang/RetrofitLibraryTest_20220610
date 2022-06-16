package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.ReviewData

class ReviewRecyclerAdapter(
    val mContext: Context,
    val mList: List<ReviewData>): RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view){

         val txtReviewTitle = view.findViewById<TextView>(R.id.txtReviewTitle)
         val txtReviewContent = view.findViewById<TextView>(R.id.txtReviewContent)
         val txtScore = view.findViewById<TextView>(R.id.txtScore)
         val txtUserNickname = view.findViewById<TextView>(R.id.txtUserNickname)
         val txtProductName = view.findViewById<TextView>(R.id.txtProductName)

        fun bind( data: ReviewData ){
            txtReviewTitle.text = "리뷰 제목 :${data.title}"
            txtReviewContent.text = "리뷰 내용 : ${data.content}"

            txtScore.text = "리뷰 평가 : ${data.score}"
            txtUserNickname.text = "작성자 닉네임 : ${data.user.nickname}"
            txtProductName.text = "상품 이름 : ${data.product.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.review_list_item,parent, false)
        return ReviewViewHolder(row)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

        holder.bind( mList[position] )

    }

    override fun getItemCount() = mList.size
}