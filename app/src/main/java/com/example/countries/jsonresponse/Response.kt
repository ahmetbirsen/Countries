package com.example.countries.jsonresponse

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)

data class ResponseItem(

	@field:SerializedName("RESERVE_QUANTITY")
	val rESERVEQUANTITY: String? = null,

	@field:SerializedName("IS_DARA")
	val iSDARA: String? = null,

	@field:SerializedName("TOPLAM_BEGENME")
	val tOPLAMBEGENME: String? = null,

	@field:SerializedName("URL_PATH")
	val uRLPATH: String? = null,

	@field:SerializedName("IS_WEIGHED_PRODUCT")
	val iSWEIGHEDPRODUCT: String? = null,

	@field:SerializedName("BEGENDIMMI")
	val bEGENDIMMI: String? = null,

	@field:SerializedName("YORUMADET")
	val yORUMADET: String? = null,

	@field:SerializedName("Durum")
	val durum: String? = null,

	@field:SerializedName("PRODUCT_CODE")
	val pRODUCTCODE: String? = null,

	@field:SerializedName("PRDUCT_GROUP_NAME")
	val pRDUCTGROUPNAME: String? = null,

	@field:SerializedName("PRODUCT_DESCRIPTION")
	val pRODUCTDESCRIPTION: String? = null,

	@field:SerializedName("IS_NEGATIVE_CONTROL")
	val iSNEGATIVECONTROL: String? = null,

	@field:SerializedName("REMAINDER_QUANTITY")
	val rEMAINDERQUANTITY: String? = null,

	@field:SerializedName("PRODUCT_TRADEMARK_ID")
	val pRODUCTTRADEMARKID: String? = null,

	@field:SerializedName("UNIT_NAME")
	val uNITNAME: String? = null,

	@field:SerializedName("PRODUCT_UNIT_ID")
	val pRODUCTUNITID: String? = null,

	@field:SerializedName("AVATAR")
	val aVATAR: String? = null,

	@field:SerializedName("CATEGORY_NAME")
	val cATEGORYNAME: String? = null,

	@field:SerializedName("IS_PRODUCING")
	val iSPRODUCING: String? = null,

	@field:SerializedName("PRODUCT_YPE")
	val pRODUCTYPE: String? = null,

	@field:SerializedName("DATABASE_ID")
	val dATABASEID: String? = null,

	@field:SerializedName("GOSTERADET")
	val gOSTERADET: String? = null,

	@field:SerializedName("MODEL_NAME")
	val mODELNAME: String? = null,

	@field:SerializedName("IMAGE_NAME")
	val iMAGENAME: String? = null,

	@field:SerializedName("UNIT_CODE")
	val uNITCODE: String? = null,

	@field:SerializedName("PRODUCT_PAYMENT_ID")
	val pRODUCTPAYMENTID: String? = null,

	@field:SerializedName("PRODUCT_ID")
	val pRODUCTID: String? = null,

	@field:SerializedName("PRODUCT_NAME")
	val pRODUCTNAME: String? = null,

	@field:SerializedName("PRODUCT_GROUP_ID")
	val pRODUCTGROUPID: String? = null,

	@field:SerializedName("PRODUCT_MODEL_ID")
	val pRODUCTMODELID: String? = null,

	@field:SerializedName("IS_SERIAL_TRACKING")
	val iSSERIALTRACKING: String? = null,

	@field:SerializedName("SALE_PRICE")
	val sALEPRICE: String? = null,

	@field:SerializedName("IS_STATUS")
	val iSSTATUS: String? = null,

	@field:SerializedName("MINIMUM_QUANTITY")
	val mINIMUMQUANTITY: String? = null,

	@field:SerializedName("FAVORI_VAR")
	val fAVORIVAR: String? = null,

	@field:SerializedName("PURCHASE_PRICE")
	val pURCHASEPRICE: String? = null,

	@field:SerializedName("PRODUCT_CATEGORY_ID")
	val pRODUCTCATEGORYID: String? = null,

	@field:SerializedName("TRADE_MARK_NAME")
	val tRADEMARKNAME: String? = null,

	@field:SerializedName("IS_LOT_TRACKING")
	val iSLOTTRACKING: String? = null
)
