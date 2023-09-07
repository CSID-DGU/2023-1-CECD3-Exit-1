package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDURSeniorCaution(
    val body: Body,
    val header: Header
) {
    @Serializable
    data class Body(
        val items: List<Item>,
        val numOfRows: Int,
        val pageNo: Int,
        val totalCount: Int
    ) {
        @Serializable
        data class Item(
            val CHANGE_DATE: String,
            val CHART: String,
            val CLASS_CODE: String,
            val CLASS_NAME: String,
            val ENTP_NAME: String,
            val ETC_OTC_NAME: String,
            val FORM_NAME: String,
            val INGR_CODE: String,
            val INGR_ENG_NAME: String,
            val INGR_ENG_NAME_FULL: String,
            val INGR_NAME: String,
            val ITEM_NAME: String,
            val ITEM_PERMIT_DATE: String,
            val ITEM_SEQ: String,
            val MAIN_INGR: String,
            val MIX_INGR: String,
            val MIX_TYPE: String,
            val NOTIFICATION_DATE: String,
            val PROHBT_CONTENT: String,
            val REMARK: String?,
            val TYPE_NAME: String
        )
    }

    @Serializable
    data class Header(
        val resultCode: String,
        val resultMsg: String
    )
}
