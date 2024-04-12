package cafe.domain

import cafe.domain.product.Product
import kotlin.reflect.KClass

class Charge<P : Product>(val cc: CreditCard, val qty: Int, val product: KClass<P>)
