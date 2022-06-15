package com.start.retrofitlibrarytest_20220610.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.start.retrofitlibrarytest_20220610.R
import com.start.retrofitlibrarytest_20220610.datas.ProductData
import java.text.NumberFormat
import java.util.*

class ProductRecyclerAdapter(
    val mContext: Context,
    val mList: List<ProductData>
) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imgProductImg = view.findViewById<ImageView>(R.id.imgProductImg)
        val imgStoreLogo = view.findViewById<ImageView>(R.id.imgStoreLogo)
        val txtStoreName = view.findViewById<TextView>(R.id.txtStoreName)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtProductPrice = view.findViewById<TextView>(R.id.txtProductPrice)
        val rootLayout = view.findViewById<LinearLayout>(R.id.rootLayout)

        fun bind(data: ProductData){
            Glide.with(mContext).load(data.image_url).into(imgProductImg)
            Glide.with(mContext).load(data.store.logoURL).into(imgStoreLogo)
            txtStoreName.text = data.store.name
            txtProductName.text = data.name

//            39800 -> 39,800 형태로 가공해주자.  상품데이터에 가격을 가공해주는 함수를 추가해보자.
            txtProductPrice.text = data.getFormattedPrice()

//            val priceFormat = NumberFormat.getInstance(Locale.KOREA).format(data.price)
//            txtProductPrice.text = "${priceFormat}원"

            rootLayout.setOnClickListener {
                Toast.makeText(mContext, "${data.name} 상품이 선택되었습니다!!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent, false)
        return ProductViewHolder(row)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(mList[position])

    }

    override fun getItemCount() = mList.size
}