package com.start.retrofitlibrarytest_20220610.datas

class DataResponse(
    var user: UserData,
    var token: String,

//    이 변수는 상품목록에서만 사용
    var products: List<ProductData>,

) {
}