package cafe.domain

import cafe.domain.product.Product
import kotlin.reflect.KClass

class Cafe {
    /**
     * internal 모듈에서만 통용됨. jar파일로 모듈을 만들면 implement 한쪽에서는 쓸수 없음
     * 함수의 반환타입은 하위 반환타입을 반환할 수 있음 () -> Product 의 하위타입으로 () -> Americano 를 인식함. 반환값에 대한 공변이 성립
     */
    @PublishedApi
    internal val factories: HashMap<KClass<*>, () -> Product> = hashMapOf()

    fun addFactory(vararg products: Product) {
        products.forEach {
            factories[it::class] = it.factory
        }
    }

    /**
     * 독립된 스텝을 통제하는 방법은 타입을 통해 증명한다.
     * buy를 잘 수행했어? -> Charge를 가지고있어야함
     * payment를 잘 수행했어? -> Receipt를 가지고 있어야함
     */
    fun <P : Product> getProduct(product: KClass<P>): P = factories[product]!!() as P
    fun <P : Product> buy(cc: CreditCard, qty: Int, product: KClass<P>): Charge<P>? =
        if (qty == 0) null else Charge(cc, qty, product)

    fun <P : Product> payment(vararg charges: Charge<P>): Receipt<P>? {
        if (charges.isEmpty()) return null
        val totalQty = charges.sumOf { it.qty }
        // 결제 부수효과
        val isPaidOK = true
        return if (isPaidOK) Receipt(totalQty, charges.first().product) else null
    }

    inline fun <reified P : Product> receive(receipt: Receipt<P>): Array<P>? {
        factories[receipt.product] ?: return null
        return Array(receipt.qty) { getProduct(receipt.product) }
    }
}
