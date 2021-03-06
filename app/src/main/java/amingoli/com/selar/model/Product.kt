package amingoli.com.selar.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
class Product {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
    var id_code :String? = null
    var qrcode :String? = null
    var name :String? = null
    var image_defult :String? = null
    var descrption :String? = null
    var branch :Int? = null
    var status :Int? = null
    var stock :Double? = null
    var price_buy :Double? = 0.0
    var price_sale_on_product :Double? =  0.0
    var price_sale :Double? =  0.0
    var price_discount :Double? =  0.0
    var price_profit :Double? =  0.0
    var min_selection :Double? = null
    var max_selection :Double? = null
    var increase :String? = null
    var created_at : Date? = null
    var updated_at :Date? = null
    var date_expired :Date? = null
    var tax_percent :Int? = null
    var user :Int? = null

    constructor()

    @Ignore
    constructor(
        id_code: String?,
        qrcode: String?,
        name: String?,
        image_defult: String?,
        branch: Int?,
        stock: Double?,
        price_buy: Double,
        price_sale_on_product: Double,
        price_sale: Double,
        increase: String?
    ) {
        this.id_code = id_code
        this.qrcode = qrcode
        this.name = name
        this.image_defult = image_defult
        this.branch = branch
        this.stock = stock
        this.price_buy = price_buy
        this.price_sale_on_product = price_sale_on_product
        this.price_sale = price_sale
        this.increase = increase

        this.price_discount =
            if (this.price_sale_on_product!! > this.price_sale!!)
                this.price_sale_on_product!! - this.price_sale!!
            else 0.0

        this.price_profit =
            if (this.price_sale!! > this.price_buy!!) this.price_sale!! - this.price_buy!! else 0.0

        this.created_at = Date()
        this.updated_at = Date()
        this.status = 1
    }


}