package com.start.retrofitlibrarytest_20220610.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.start.retrofitlibrarytest_20220610.fragments.MyProfileFragment
import com.start.retrofitlibrarytest_20220610.fragments.ProductListFragment
import com.start.retrofitlibrarytest_20220610.fragments.ReviewListFragment

class MainViewPagerAdapter(fm : FragmentManager): FragmentPagerAdapter(fm){
    override fun getCount() = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ReviewListFragment()
            1 -> ProductListFragment()
            else -> MyProfileFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "리뷰 목록"
            1 -> "상품 목록"
            else -> "프로필 보기"
        }
    }
}