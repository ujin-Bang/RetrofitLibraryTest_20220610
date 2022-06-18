package com.start.retrofitlibrarytest_20220610.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.start.retrofitlibrarytest_20220610.fragments.MyProfileFragment
import com.start.retrofitlibrarytest_20220610.fragments.ProductListFragment
import com.start.retrofitlibrarytest_20220610.fragments.RecyclerViewPracticeFragment
import com.start.retrofitlibrarytest_20220610.fragments.ReviewListFragment

class MainViewPagerAdapter(fm : FragmentManager): FragmentPagerAdapter(fm){
    override fun getCount() = 4

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> RecyclerViewPracticeFragment()
            1 -> ProductListFragment()
            2 -> ReviewListFragment()
            else -> MyProfileFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "리싸이클러뷰연습"
            1 -> "상품 목록"
            2 -> "리뷰 목록"
            else -> "프로필 보기"
        }
    }
}