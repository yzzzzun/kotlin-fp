package cafe.domain

import cafe.domain.product.Americano
import cafe.domain.product.CafeLatte
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CafeTest {
    @Test
    fun test() {
        val cafe: Cafe = Cafe().also {
            it.addFactory(Americano(), CafeLatte())
        }

        val myCard = object : CreditCard {}
        val americanos = cafe.buy(myCard, 2, Americano::class)?.let { charge ->
            cafe.payment(charge)?.let { receipt ->
                cafe.receive(receipt)
            }
        }

        assertEquals(americanos?.size, 2)
        assertEquals(americanos?.get(0)?.price, 1000.0)
    }
}
