package cafe.domain

import cafe.domain.product.Product
import kotlin.reflect.KClass

class Receipt<P : Product>(val qty: Int, val product: KClass<P>)
